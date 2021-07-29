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

package test.wssecfvt.sha2sig;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

/**
 * This class was generated by Apache CXF 2.6.2
 * 2013-03-08T14:54:04.906-06:00
 * Generated source version: 2.6.2
 *
 */
@WebServiceClient(name = "Sha2SigService4",
                  wsdlLocation = "Sha2SigAlg.wsdl",
                  targetNamespace = "http://sha2sig.wssecfvt.test")
public class Sha2SigService4 extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://sha2sig.wssecfvt.test", "Sha2SigService4");
    public final static QName UrnSha2Sig4 = new QName("http://sha2sig.wssecfvt.test", "UrnSha2Sig4");
    static {
        URL url = Sha2SigService4.class.getResource("Sha2SigAlg.wsdl");
        if (url == null) {
            java.util.logging.Logger.getLogger(Sha2SigService4.class.getName()).log(java.util.logging.Level.INFO,
                                                                                    "Can not initialize the default wsdl from {0}", "Sha2SigAlg.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public Sha2SigService4(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public Sha2SigService4(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Sha2SigService4() {
        super(WSDL_LOCATION, SERVICE);
    }

    /**
     *
     * @return
     *         returns Sha2SigAlg
     */
    @WebEndpoint(name = "UrnSha2Sig4")
    public Sha2SigAlg getUrnSha2Sig4() {
        return super.getPort(UrnSha2Sig4, Sha2SigAlg.class);
    }

}