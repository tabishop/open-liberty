/*******************************************************************************
 * Copyright (c) 2011, 2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.classloading.internal;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.osgi.framework.Bundle;

import com.ibm.websphere.ras.Tr;
import com.ibm.websphere.ras.TraceComponent;
import com.ibm.websphere.ras.annotation.Trivial;
import com.ibm.ws.ffdc.annotation.FFDCIgnore;
import com.ibm.wsspi.classloading.ApiType;
import com.ibm.wsspi.kernel.service.utils.CompositeEnumeration;

/**
 * This class loader needs to be public in order for Spring's ReflectiveLoadTimeWeaver
 * to discover the special methods:
 */
public class UnifiedClassLoader extends LibertyLoader implements SpringLoader {
    static {
        ClassLoader.registerAsParallelCapable();
    }
    private final static TraceComponent tc = Tr.register(UnifiedClassLoader.class);

    /**
     * Spring to register the given ClassFileTransformer on this ClassLoader
     */
    @Override
    public boolean addTransformer(final ClassFileTransformer cft) {
        boolean added = false;
        for (ClassLoader loader : followOnClassLoaders) {
            if (loader instanceof SpringLoader) {
                added |= ((SpringLoader) loader).addTransformer(cft);
            }
        }
        return added;
    }

    /**
     * Special method used by Spring to obtain a throwaway class loader for this ClassLoader
     */
    @Override
    public ClassLoader getThrowawayClassLoader() {
        ClassLoader newParent = getThrowawayVersion(parent);
        ClassLoader[] newFollowOns = new ClassLoader[followOnClassLoaders.size()];
        for (int i = 0; i < newFollowOns.length; i++) {
            newFollowOns[i] = getThrowawayVersion(followOnClassLoaders.get(i));
        }
        return new UnifiedClassLoader(newParent, newFollowOns);
    }

    /**
     * @param loader
     * @return
     */
    private ClassLoader getThrowawayVersion(ClassLoader loader) {
        return loader instanceof SpringLoader ? ((SpringLoader) loader).getThrowawayClassLoader() : loader;
    }

    static class Delegation {
        // This is only used to place a non-class loader class on the call stack which is loaded from a bundle.
        // This is needed as a workaround for defect 89337.
        @Trivial
        static Class<?> loadClass(String className, boolean resolve, boolean onlySearchSelf, boolean returnNull, UnifiedClassLoader loader) throws ClassNotFoundException {
            return loader.loadClass0(className, resolve, onlySearchSelf, returnNull);
        }
    }

    private final List<ClassLoader> followOnClassLoaders;

    /**
     * @param parent
     * @param followOnCls
     */
    public UnifiedClassLoader(ClassLoader parent, ClassLoader... followOns) {
        super(parent);
        followOnClassLoaders = new ArrayList<ClassLoader>();
        Collections.addAll(followOnClassLoaders, followOns);
    }

    @Override
    @Trivial
    protected final Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        return Delegation.loadClass(name, resolve, false, false, this);
    }

    @Override
    @Trivial
    protected Class<?> loadClass(String name, boolean resolve, boolean onlySearchSelf, boolean returnNull) throws ClassNotFoundException {
        return Delegation.loadClass(name, resolve, onlySearchSelf, returnNull, this);
    }

    @Trivial
    @FFDCIgnore(ClassNotFoundException.class)
    Class<?> loadClass0(String name, boolean resolve, boolean onlySearchSelf, boolean returnNull) throws ClassNotFoundException {
        if (parent == null) {
            return super.loadClass(name, resolve);
        }
        Object lock = getClassLoadingLock(name);
        synchronized (lock) {
            Class<?> result = findLoadedClass(name);
            if (result != null) {
                return result;
            }
            if (!onlySearchSelf) {
                if (parent instanceof NoClassNotFoundLoader) {
                    result = ((NoClassNotFoundLoader) parent).loadClassNoException(name);
                } else {
                    try {
                        result = parent.loadClass(name);
                    } catch (ClassNotFoundException e) {
                        // move on to local findClass
                    }
                }
                if (result != null) {
                    return result;
                }
            }
            return findClass(name, returnNull);
        }
    }

    @Override
    @FFDCIgnore(ClassNotFoundException.class)
    protected Class<?> findClass(String name, boolean returnNull) throws ClassNotFoundException {
        for (ClassLoader cl : followOnClassLoaders) {
            try {
                if (cl instanceof NoClassNotFoundLoader) {
                    Class<?> result = ((NoClassNotFoundLoader) cl).loadClassNoException(name);
                    if (result != null) {
                        return result;
                    }
                } else {
                    return cl.loadClass(name);
                }
            } catch (ClassNotFoundException swallowed) {
                if (TraceComponent.isAnyTracingEnabled() && tc.isDebugEnabled()) {
                    Tr.debug(tc, "CNFE from followOnClassLoader " + cl, swallowed);
                }
            }
        }
        if (returnNull) {
            return null;
        }
        throw new ClassNotFoundException(name);
    }

    @Override
    protected URL findResource(String arg0) {
        URL result = null;
        for (ClassLoader cl : followOnClassLoaders) {
            result = cl.getResource(arg0);
            if (result != null)
                break;
        }
        return result;
    }

    @Override
    protected Enumeration<URL> findResources(String arg0) throws IOException {
        List<Enumeration<URL>> rawResult = new ArrayList<Enumeration<URL>>();

        for (ClassLoader cl : followOnClassLoaders) {
            Enumeration<URL> result = cl.getResources(arg0);
            if (result != null) {
                rawResult.add(result);
            }
        }

        final Iterator<Enumeration<URL>> allURLs = rawResult.iterator();

        return new Enumeration<URL>() {
            private Enumeration<URL> current;

            @Override
            public boolean hasMoreElements() {
                // I hate using an infinite loop here, but I really can't think of a better way.
                // I'll hopefully come up with one before I deliver.
                for (;;) {
                    if (current != null && current.hasMoreElements()) {
                        return true;
                    }

                    // if we are here we need to move to a new current until we find a URL.

                    if (!!!allURLs.hasNext()) {
                        return false;
                    }

                    current = allURLs.next();
                }
            }

            @Override
            public URL nextElement() {
                if (current == null)
                    throw new NoSuchElementException();
                return current.nextElement();
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @FFDCIgnore(PrivilegedActionException.class)
    public Enumeration<URL> getResources(final String name) throws IOException {
        /*
         * The default implementation of getResources never calls getResources on it's parent, instead it just calls findResources on all of the loaders parents. We know that our
         * parent will be a gateway class loader that changes the order that resources are loaded but it does this in getResources (as that is where the order *should* be changed
         * according to the JavaDoc). Therefore call getResources on our parent and then findResources on ourself.
         */

        if (parent == null) {
            // If there's no parent there is nothing to worry about so use the super.getResources
            return super.getResources(name);
        }

        try {
            Enumeration<URL> eURL = AccessController.doPrivileged(new java.security.PrivilegedExceptionAction<Enumeration<URL>>() {
                @Override
                public Enumeration<URL> run() throws Exception {
                    return parent.getResources(name);
                }
            });

            return new CompositeEnumeration<URL>(eURL).add(this.findResources(name));

        } catch (PrivilegedActionException pae) {
            return null;
        }

        // Note we don't need to worry about getSystemResources as our parent will do that for us
        //return new CompositeEnumeration<URL>(parent.getResources(name)).add(this.findResources(name));
    }

    synchronized void addFollowOnClassLoader(ClassLoader cl) {
        if (!this.followOnClassLoaders.contains(cl)) {
            this.followOnClassLoaders.add(cl);
        }
    }

    /**
     * @return the followOnClassLoaders
     */
    List<ClassLoader> getFollowOnClassLoaders() {
        return followOnClassLoaders;
    }

    @Override
    public EnumSet<ApiType> getApiTypeVisibility() {
        return null;
    }

    @Override
    public Bundle getBundle() {
        return null;
    }
}
