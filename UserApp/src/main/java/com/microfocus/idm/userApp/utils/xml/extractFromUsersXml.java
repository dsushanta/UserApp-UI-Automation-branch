package com.microfocus.idm.userApp.utils.xml;

import com.microfocus.idm.userApp.utils.loadEnvironment;
import org.dom4j.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sushant on 3/23/17.
 */
public class extractFromUsersXml extends loadEnvironment {

    public static Map<String, String> getDetailsOfUserToBeCreated() {

        // Update this method if more than one recipient is added in Users.xml file

        String dn, name, full_name, email, pwd, title, telephone;
        Map<String, String> create_user_details = new HashMap<String, String>();
        Node node = users.selectSingleNode("Objects/Object/Users/Recipients/User");
        dn = node.selectSingleNode("dn").getText();
        pwd = node.selectSingleNode("pwd").getText();
        name = node.selectSingleNode("name").getText();
        full_name = node.selectSingleNode("fullName").getText();
        email = node.selectSingleNode("email").getText();
        title = node.selectSingleNode("title").getText();
        telephone = node.selectSingleNode("telephone").getText();
        create_user_details.put("dn", dn);
        create_user_details.put("pwd", pwd);
        create_user_details.put("name", name);
        create_user_details.put("full_name", full_name);
        create_user_details.put("email", email);
        create_user_details.put("title", title);
        create_user_details.put("telephone", telephone);
        return create_user_details;
    }

    public static String[] getUserAppAdminCredentials() {

        String[] credentials = new String[2];
        List<Node> nodes = users.selectNodes("Objects/Object/Users/Admins/User");
        for(Node n : nodes) {
            String admin_type = n.selectSingleNode("userType").getText();
            if(admin_type.equalsIgnoreCase("UserApp Admin")) {
                credentials[0] = n.selectSingleNode("name").getText();
                credentials[1] = n.selectSingleNode("pwd").getText();
            }
        }
        return credentials;
    }

    public static String[] getApproverCredentials() {

        // Update this method if more than one approver is added in Users.xml file

        String[] credentials = new String[2];
        Node node = users.selectSingleNode("Objects/Object/Users/Approvers/User");
        credentials[0] = node.selectSingleNode("name").getText();
        credentials[1] = node.selectSingleNode("pwd").getText();
        return credentials;
    }

    public static String[] getRecipientCredentials() {

        // Update this method if more than one approver is added in Users.xml file

        String[] credentials = new String[2];
        Node node = users.selectSingleNode("Objects/Object/Users/Recipients/User");
        credentials[0] = node.selectSingleNode("name").getText();
        credentials[1] = node.selectSingleNode("pwd").getText();
        return credentials;
    }
}
