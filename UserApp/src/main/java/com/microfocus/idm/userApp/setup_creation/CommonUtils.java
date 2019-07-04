package com.microfocus.idm.userApp.setup_creation;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommonUtils {

	public static String getBaseURI() {
		return "http://"+"164.99.179.47"+":8180";
	}

	public static void ll(List<ArrayList<String>> aa) {
		for (int i=0; i<aa.size(); i++) {
			for(int j=0;j<aa.get(i).size(); j++) {
				System.out.println(aa.get(i).get(j));
			}
		}
	}
	
	public static void l(List<String> a) {
		for (String anA : a) {
			System.out.println(anA);
		}
	}
	
	public static String getJSONPayload(String file) throws IOException {
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/src/main/java/com/microfocus/idm/userApp/setup_creation/Resources/"+file);
        return IOUtils.toString(fis);
	}
	
	public static Boolean getBooleanElementValuefromJSON(Map<String, String> responseFromApi, String elementName) {
		
		JSONObject response = new JSONObject(responseFromApi.get("response"));
		return response.getBoolean(elementName);
	}
	
	public static ArrayList<String> getList(String s1, String s2) {
		ArrayList<String> l = new ArrayList<>();
		l.add(s1);
		l.add(s2);
		return l;
	}

	public static String removeAttributeFromJSONpayload(String payload, String attribute_to_be_removed) {
		JSONObject temp_json = new JSONObject(payload);
		temp_json.remove(attribute_to_be_removed);
		return temp_json.toString();
	}

	public static String makeUserDN(String cn) {
		return "cn="+cn+",ou=users,o=data";
	}

	public static String makeQueryParamList(Map<String, String> m) {
		String paramlist = "?";
		int f  = 0;
		Set<String> keySet = m.keySet();
		for(String k : keySet) {
			if(f == 1)
				paramlist = paramlist+"&";
			else
				f = 1;
			paramlist = paramlist+k+"="+m.get(k);
		}
		return paramlist;
	}

}
