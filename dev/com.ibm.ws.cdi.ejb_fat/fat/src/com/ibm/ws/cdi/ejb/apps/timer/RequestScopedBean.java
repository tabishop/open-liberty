/*******************************************************************************
 * Copyright (c) 2022 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.ws.cdi.ejb.apps.timer;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class RequestScopedBean {

    private static int globalIndex = 0;
    private static synchronized int generateIndex() {
        globalIndex++;
        return globalIndex;
    }

    private int index;

    public RequestScopedBean() {
        index = generateIndex();
    }

    public int getIndex() {
        return index;
    }

}
