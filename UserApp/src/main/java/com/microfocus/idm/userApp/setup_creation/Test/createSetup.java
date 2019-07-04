package com.microfocus.idm.userApp.setup_creation.Test;

import com.microfocus.idm.userApp.setup_creation.CommonUtils;
import com.microfocus.idm.userApp.setup_creation.Service.ServiceUsers;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class createSetup {

    private static Map<String, String> responseFromApi = new HashMap<>();
    private static ServiceUsers sUsers = new ServiceUsers();

    public static void generateCreateUserEvent() throws IOException {

        String uname = "uaadmin";
        String pwd = "novell";
        String jsonFileName = "CreateUser.json";
        String payload = CommonUtils.getJSONPayload(jsonFileName);
        responseFromApi = sUsers.createUser(uname, pwd, payload);
        if(responseFromApi.get("status").equalsIgnoreCase("200")) {
            System.out.println("User Created Successfully by REST APIs !!");
        }
        else {
            System.out.println("User was not Created Successfully by REST APIs !!");
        }
    }
}
