package com.microfocus.idm.userApp.utils.screenshotAndRecording;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

/**
 * Created by Sushant on 3/24/17...
 */
public class Video {

    public static Client client = Client.create();
    public static WebResource wResource;
    public static String URL = null;
    public static ClientResponse response;
    public static String response_string;

    public static void start_video() {

        URL = "http://164.99.178.12:9998/rec/start";
        wResource = client.resource(URL);
        response = wResource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        response_string = response.getEntity(String.class);
    }

    public static void stop_video() {

        URL = "http://164.99.178.12:9998/rec/save/vid";
        wResource = client.resource(URL);
        response = wResource.accept(MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        response_string = response.getEntity(String.class);
    }
}
