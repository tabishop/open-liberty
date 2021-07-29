/*******************************************************************************
 * Copyright (c) 2020 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package test.wssecfvt.x509enc;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.2
 * 2013-01-03T17:08:32.076-06:00
 * Generated source version: 2.6.2
 * 
 */
@WebServiceClient(name = "X509XmlEncService8", 
                  wsdlLocation = "X509XmlEnc2.wsdl",
                  targetNamespace = "http://x509enc.wssecfvt.test") 
public class X509XmlEncService8 extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://x509enc.wssecfvt.test", "X509XmlEncService8");
    public final static QName UrnX509Enc8 = new QName("http://x509enc.wssecfvt.test", "UrnX509Enc8");
    static {
        URL url = X509XmlEncService8.class.getResource("X509XmlEnc2.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(X509XmlEncService8.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "X509XmlEnc2.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public X509XmlEncService8(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public X509XmlEncService8(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public X509XmlEncService8() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    /**
     *
     * @return
     *     returns XmlEnc2
     */
    @WebEndpoint(name = "UrnX509Enc8")
    public XmlEnc2 getUrnX509Enc8() {
        return super.getPort(UrnX509Enc8, XmlEnc2.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns XmlEnc2
     */
    @WebEndpoint(name = "UrnX509Enc8")
    public XmlEnc2 getUrnX509Enc8(WebServiceFeature... features) {
        return super.getPort(UrnX509Enc8, XmlEnc2.class, features);
    }

}