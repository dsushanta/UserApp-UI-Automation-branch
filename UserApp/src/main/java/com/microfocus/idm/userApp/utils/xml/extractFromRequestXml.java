package com.microfocus.idm.userApp.utils.xml;

import com.microfocus.idm.userApp.utils.loadEnvironment;
import org.dom4j.Node;

/**
 * Created by Sushant on 3/23/17.
 */
public class extractFromRequestXml extends loadEnvironment {

    public static String getResourceToRequest() {

        Node node = request.selectSingleNode("Environment/Permissions/Resource/Permission");
        String res_name = node.selectSingleNode("name").getText();
        return res_name;
    }
}
