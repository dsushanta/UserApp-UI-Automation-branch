package com.microfocus.idm.userApp.setup_creation;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class GenericRESTHandler {

	public enum HTTPMethod {
		GET,
		POST,
		PUT,
		DELETE
	}
	private Client client = Client.create();
	private WebResource wResource;
	private String user_cred;
	private String base64;
	private String auth_header;
	private String response_string;
	private int status;
	private ClientResponse response;
	private Map<String,String> apiResponseString;
	public String MEDIA_TYPE = "application/json";
	
	public Map<String,String> ExecuteAPI(HTTPMethod methodType, String URI, String uname, String pwd, String payload) throws UnsupportedEncodingException {
		
		switch(methodType) {
			case GET:
			{
				auth_header = getAuthHeader(uname,pwd);
				wResource = client.resource(URI);
				response = wResource.accept(MediaType.APPLICATION_JSON).header("Authorization", auth_header).type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
				response_string = response.getEntity(String.class);
				status = response.getStatus();
				apiResponseString = new HashMap<>();
				apiResponseString.put("response", response_string);
				apiResponseString.put("status", ""+status);
				break;
			}
			case POST: 
			{
				auth_header = getAuthHeader(uname,pwd);
				wResource = client.resource(URI);
				response = wResource.accept(MediaType.APPLICATION_JSON).header("Authorization", auth_header).type(MediaType.APPLICATION_JSON).post(ClientResponse.class, payload);
				response_string = response.getEntity(String.class);
				status = response.getStatus();
				apiResponseString = new HashMap<>();
				apiResponseString.put("response", response_string);
				apiResponseString.put("status", ""+status);
				break;
			}
			case PUT:
			{
				auth_header = getAuthHeader(uname,pwd);
				wResource = client.resource(URI);
				response = wResource.accept(MediaType.APPLICATION_JSON).header("Authorization", auth_header).type(MediaType.APPLICATION_JSON).put(ClientResponse.class, payload);
				response_string = response.getEntity(String.class);
				status = response.getStatus();
				apiResponseString = new HashMap<>();
				apiResponseString.put("response", response_string);
				apiResponseString.put("status", ""+status);
				break;
			}
			case DELETE:
			{
				auth_header = getAuthHeader(uname,pwd);
				wResource = client.resource(URI);
				response = wResource.accept(MediaType.APPLICATION_JSON).header("Authorization", auth_header).type(MediaType.APPLICATION_JSON).delete(ClientResponse.class, payload);
				response_string = response.getEntity(String.class);
				status = response.getStatus();
				apiResponseString = new HashMap<>();
				apiResponseString.put("response", response_string);
				apiResponseString.put("status", ""+status);
				break;
			}			
		}
		return apiResponseString;
	}
	
	private String getAuthHeader(String uname, String pwd) throws UnsupportedEncodingException {
		user_cred = uname+":"+pwd;
		base64 = new String (Base64.getEncoder().encodeToString(user_cred.getBytes("utf-8")));
		return("Basic "+base64);
	}
	
	public String getJSONObject(String cn) throws JSONException {
		JSONObject jo = new JSONObject();
		JSONObject jobj = new JSONObject();
		JSONArray jArray = new JSONArray();
		jo.put("dn",cn);
		jArray.put(jo);
		jobj.put("teams", jo);
        return jobj.toString();
	}

}
