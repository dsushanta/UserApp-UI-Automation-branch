package com.microfocus.idm.userApp.utils;

import com.microfocus.idm.userApp.utils.xml.extractFromEnvXml;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import java.io.File;

/**
 * Created by Sushant on 3/9/2017.
 */
public class loadEnvironment {

    public static Document env = null;
    public static Document rra = null;
    public static Document users = null;
    public static Document request = null;
    public static String SERVER_IP;
    public static String SERVER_PORT;
    public static String SERVER_HTTPS_PORT;
    public static String NODE_IP;
    public static String NODE_PORT;
    public static long IMPLICIT_TIMEOUT;
    public static Logger LOGGER = null;

    public static void loadProperties() throws DocumentException {

        File envFile = new File(System.getProperty("user.dir")+"/src/main/resources/Properties/"+"Environment.xml");
        File rraFile = new File(System.getProperty("user.dir")+"/src/main/resources/Properties/"+"RRA.xml");
        File usersFile = new File(System.getProperty("user.dir")+"/src/main/resources/Properties/"+"Users.xml");
        File requestFile = new File(System.getProperty("user.dir")+"/src/main/resources/Properties/"+"Request.xml");
        SAXReader reader = new SAXReader();
        env = reader.read(envFile);
        rra = reader.read(rraFile);
        users = reader.read(usersFile);
        request = reader.read(requestFile);
        SERVER_IP = extractFromEnvXml.getServerIP();
        SERVER_PORT = extractFromEnvXml.getServerHTTPPort();
        SERVER_HTTPS_PORT = extractFromEnvXml.getServerHTTPSPort();
        NODE_IP = extractFromEnvXml.getNodeIP();
        NODE_PORT = extractFromEnvXml.getNodePort();
        IMPLICIT_TIMEOUT = extractFromEnvXml.getImplicitTimeout();
        LOGGER = Logger.getLogger("testBase");
        PropertyConfigurator.configure("log4j.properties");
    }
}
