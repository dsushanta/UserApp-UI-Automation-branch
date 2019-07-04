package com.microfocus.idm.userApp.setup_creation.Service;

import com.microfocus.idm.userApp.setup_creation.GenericRESTHandler;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static com.microfocus.idm.userApp.setup_creation.CommonUtils.getBaseURI;

public class ServiceUsers{

    private Map<String, String> responseFromApi = new HashMap<>();
    private String URI = "";
    public GenericRESTHandler genRESTHandler = new GenericRESTHandler();

    public Map<String, String> createUser(String uname, String pwd, String payload) throws UnsupportedEncodingException {
        URI = getBaseURI()+ "/IDMProv/rest/access/user";
        responseFromApi = genRESTHandler.ExecuteAPI(GenericRESTHandler.HTTPMethod.POST, URI, uname, pwd, payload);
        return responseFromApi;
    }

    public Map<String, String> deleteUser(String uname, String pwd, String payload) throws UnsupportedEncodingException {
        URI = getBaseURI()+"/IDMProv/rest/access/user";
        responseFromApi = genRESTHandler.ExecuteAPI(GenericRESTHandler.HTTPMethod.DELETE, URI, uname, pwd, payload);
        return responseFromApi;
    }
}
