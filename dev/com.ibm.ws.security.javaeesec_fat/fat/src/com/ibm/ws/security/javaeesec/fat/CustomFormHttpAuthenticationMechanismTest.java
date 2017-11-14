/*
* IBM Confidential
*
* OCO Source Materials
*
* WLP Copyright IBM Corp. 2017
*
* The source code for this program is not published or otherwise divested
* of its trade secrets, irrespective of what has been deposited with the
* U.S. Copyright Office.
*/
package com.ibm.ws.security.javaeesec.fat;

import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;

import com.ibm.ws.security.javaeesec.fat_helper.Constants;
import com.ibm.ws.security.javaeesec.fat_helper.ServerHelper;
import com.ibm.ws.security.javaeesec.fat_helper.WCApplicationHelper;
import com.ibm.ws.security.javaeesec.fat_singleIS.CustomFormHttpAuthenticationMechanismSingleISTest;

/**
 *
 */
public class CustomFormHttpAuthenticationMechanismTest extends CustomFormHttpAuthenticationMechanismSingleISTest {
    @BeforeClass
    public static void setUp() throws Exception {

        ServerHelper.setupldapServer();

        WCApplicationHelper.addWarToServerApps(myServer, "JavaEESecCustomFormLoginServlet.war", true, JAR_NAME, false, "web.jar.base", "web.war.mechanism.customForm");
        myServer.copyFileToLibertyInstallRoot("lib/features", "internalFeatures/javaeesecinternals-1.0.mf");
        myServer.setServerConfigurationFile("customForm.xml");
        myServer.startServer(true);

        urlBase = "http://" + myServer.getHostname() + ":" + myServer.getHttpDefaultPort();

    }

    @AfterClass
    public static void tearDown() throws Exception {

        ServerHelper.commonStopServer(myServer, Constants.HAS_LDAP_SERVER);
    }

    @Before
    public void setupConnection() {
        httpclient = new DefaultHttpClient();
    }

    @After
    public void cleanupConnection() {
        httpclient.getConnectionManager().shutdown();
    }

    @Rule
    public TestName name = new TestName();

    @Override
    protected String getCurrentTestName() {
        return name.getMethodName();
    }

}
