package com.microfocus.idm.userApp.utils.xml;

import com.microfocus.idm.userApp.utils.loadEnvironment;
import org.dom4j.Node;

/**
 * Created by Sushant on 3/9/2017.
 */
public class extractFromEnvXml extends loadEnvironment {

    public static String getServerIP() {
        Node node = env.selectSingleNode("Environment/Setup/Server/IP");
        String ip = node.getText();
        return ip;
    }

    public static String getServerHTTPPort() {
        Node node = env.selectSingleNode("Environment/Setup/Server/HTTP");
        String http = node.getText();
        return http;
    }

    public static String getServerHTTPSPort() {
        Node node = env.selectSingleNode("Environment/Setup/Server/HTTPS");
        String https = node.getText();
        return https;
    }

    public static String getNodeIP() {
        Node node = env.selectSingleNode("Environment/Setup/Nodes/Node/IP");
        String ip = node.getText();
        return ip;
    }

    public static String getNodePort() {
        Node node = env.selectSingleNode("Environment/Setup/Nodes/Node/HTTP");
        String http = node.getText();
        return http;
    }

    public static long getImplicitTimeout() {
        Node node = env.selectSingleNode("Environment/Selenium/Timeout/Implicit");
        long implicit_timeout = Integer.parseInt(node.getText());
        return implicit_timeout;
    }
}
