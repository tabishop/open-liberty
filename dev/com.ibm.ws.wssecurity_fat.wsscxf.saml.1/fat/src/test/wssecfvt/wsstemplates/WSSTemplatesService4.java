/*******************************************************************************
 * Copyright (c) 2021 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/

package test.wssecfvt.wsstemplates;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.6.2
 * 2015-10-01T15:37:33.820-05:00
 * Generated source version: 2.6.2
 * 
 */
@WebServiceClient(name = "WSSTemplatesService4", 
                  wsdlLocation = "WEB-INF/WSSSAMLTemplatesTest.wsdl",
                  targetNamespace = "http://wsstemplates.wssecfvt.test") 
public class WSSTemplatesService4 extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://wsstemplates.wssecfvt.test", "WSSTemplatesService4");
    public final static QName WSSTemplate4 = new QName("http://wsstemplates.wssecfvt.test", "WSSTemplate4");
    static {
        URL url = WSSTemplatesService4.class.getResource("WEB-INF/WSSSAMLTemplatesTest.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(WSSTemplatesService4.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "WEB-INF/WSSSAMLTemplatesTest.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public WSSTemplatesService4(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public WSSTemplatesService4(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public WSSTemplatesService4() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    /**
     *
     * @return
     *     returns WSSTemplates
     */
    @WebEndpoint(name = "WSSTemplate4")
    public WSSTemplates getWSSTemplate4() {
        return super.getPort(WSSTemplate4, WSSTemplates.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WSSTemplates
     */
    @WebEndpoint(name = "WSSTemplate4")
    public WSSTemplates getWSSTemplate4(WebServiceFeature... features) {
        return super.getPort(WSSTemplate4, WSSTemplates.class, features);
    }

}