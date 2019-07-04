package com.microfocus.idm.userApp.utils;

/**
 * Created by Sushant on 3/10/2017.
 */
public class commonUtils {

    public static String baseURL = null;
    public static String nodeURL = null;

    public static String makeBaseURL(String connection_type) {
        if(connection_type.equalsIgnoreCase("http")) {
            baseURL = "http://" + loadEnvironment.SERVER_IP + ":" + loadEnvironment.SERVER_PORT + pageURIs.idmdash;
        }
        if(connection_type.equalsIgnoreCase("https")) {
            baseURL = "https://" + loadEnvironment.SERVER_IP + ":" + loadEnvironment.SERVER_HTTPS_PORT + pageURIs.idmdash;
        }
        return baseURL;
    }

    public static String makeNodeURL() {
        nodeURL = "http://" + loadEnvironment.NODE_IP + ":" + loadEnvironment.NODE_PORT + "/wd/hub";
        return nodeURL;
    }
}
