package com.microfocus.idm.userApp.utils.xml;

import com.microfocus.idm.userApp.utils.loadEnvironment;
import org.dom4j.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sushant on 3/23/2017.
 */
public class extractFromRRAXml extends loadEnvironment {

    public static Map<String, String> getCreateResourceWithoutEntDetails() {
        String ent_type;
        Map<String, String> create_resource_details = new HashMap<String, String>();
        List<Node> nodes = rra.selectNodes("rra/resourcesTab/create/resources/resource");
        for(Node n : nodes) {
            ent_type = n.selectSingleNode("type").getText();
            if(ent_type.equalsIgnoreCase("Without Entitlement")) {
                create_resource_details.put("id", n.selectSingleNode("id").getText());
                create_resource_details.put("name", n.selectSingleNode("name").getText());
                create_resource_details.put("description", n.selectSingleNode("description").getText());
            }
        }
        return create_resource_details;
    }

    public static Map<String, String> getApproverDetailsToAddToAResource() {
        String ent_type;
        Map<String, String> resource_approver_details = new HashMap<String, String>();
        List<Node> nodes = rra.selectNodes("rra/resourcesTab/create/resources/resource");
        for(Node n : nodes) {
            ent_type = n.selectSingleNode("type").getText();
            if(ent_type.equalsIgnoreCase("Without Entitlement")) {
                resource_approver_details.put("name", n.selectSingleNode("name").getText());
                resource_approver_details.put("approval_process", n.selectSingleNode("approval/process").getText());
                resource_approver_details.put("approver_type", n.selectSingleNode("approval/approvers/approver/type").getText());
                resource_approver_details.put("approver_value", n.selectSingleNode("approval/approvers/approver/value").getText());
            }
        }
        return resource_approver_details;
    }
}
