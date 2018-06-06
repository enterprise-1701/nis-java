/**
 * 
 */
package com.cubic.nisjava.api;

import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.cubic.accelerators.RESTActions;
import com.cubic.accelerators.RESTConstants;
import com.cubic.backoffice.constants.BackOfficeGlobals;
import com.cubic.backoffice.utils.BackOfficeUtils;
import com.cubic.nisjava.apiobjects.WSHdr;
import com.cubic.nisjava.apiobjects.WSResetMerchantSubAccountPasswordRequest;
import com.cubic.nisjava.apiobjects.WSResetMerchantSubAccountPasswordResponse;
import com.cubic.nisjava.utils.DataUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;

/**
 * @author 203610
 * Jun 4, 2018
 */
public class ResetMerchantSubaccountPasswordPATCH 
{

	public static final String CLASS_NAME = "ResetMerchantSubaccountPassword";
	private static final Logger LOG = Logger.getLogger(CLASS_NAME);
	static String url;
	static ClientResponse clientResponse;
	static WSResetMerchantSubAccountPasswordRequest  jsonObj = null;	
	static WSResetMerchantSubAccountPasswordResponse respObj = null;
	static Class<WSResetMerchantSubAccountPasswordResponse> jsonClass = 	WSResetMerchantSubAccountPasswordResponse.class;
	static String jsonStr = "";
	static String resp = "";
	static ObjectMapper mapper = new ObjectMapper();
	
	public static void verifyResetMerchantSubaccountPasswordBlank(Hashtable<String, String> data, RESTActions restActions) throws Throwable
	{
		//sendRequestAndGetClientResponse(restActions, url, data.get("PasswordBlank"));
		url = "https://lab7319.ctsservice.com/nis/retailapi/v1/customer/CMS000001000/user/1121";
		LOG.info(url);	
		
		// Build JSON Object
		jsonStr = buildJsonRequestBody(data.get("PasswordBlank"),restActions);
		
		//Send request and get Client response
		clientResponse = getClientResponse(restActions,url, jsonStr, createRESTHeader(RESTConstants.APPLICATION_JSON));
		resp = clientResponse.getEntity(String.class);
		
		//Validate Status Code
		DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCode"), clientResponse);				
		
		// De-serialize the Response into a JSON Object
		Gson g = new Gson();
		respObj = g.fromJson(resp, jsonClass);
        LOG.info(respObj.getHdr().getErrorKey());       

	}

	/**
	 * @param data
	 * @param restActions
	 */
	public static void verifyResetMerchantSubaccountPasswordConsecutive(Hashtable<String, String> data, RESTActions restActions) 
	{
		url = "https://lab7319.ctsservice.com/nis/retailapi/v1/customer/CMS000001000/user/1121";
		LOG.info(url);	
		
		// Build JSON Object
		jsonStr = buildJsonRequestBody(data.get("PasswordConsecutive"),restActions);

		//Send request and get Client response
		clientResponse = getClientResponse(restActions,url, jsonStr, createRESTHeader(RESTConstants.APPLICATION_JSON));
		resp = clientResponse.getEntity(String.class);
		
		//Validate Status Code
		DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCode"), clientResponse);
		
		// De-serialize the Response into a JSON Object
		Gson g = new Gson();
		respObj = g.fromJson(resp, jsonClass);
        LOG.info(respObj.getHdr().getErrorKey());       		
	}
	
	/**
	 * @param restActions
	 * @param url
	 * @param jsonStr
	 * @param createRESTHeader
	 * @return
	 */
	private static ClientResponse getClientResponse(RESTActions restActions,String url, String jsonStr,Hashtable<String, String> createRESTHeader)
	{
		try
		{
		clientResponse = restActions.patchClientResponse(url, jsonStr, createRESTHeader(RESTConstants.APPLICATION_JSON), null, null);
		}
		catch(Throwable e)
		{
			e.printStackTrace();			
		}		

		LOG.info("Http Status is ... "+ clientResponse.getStatus());
		
		return clientResponse;
	}

	/**
	 * @param data
	 * @param restActions
	 */
	public static void verifyResetMerchantSubaccountPasswordContiguous(Hashtable<String, String> data, RESTActions restActions) 
	{
				
	}
	
	/**
	 * @param data
	 * @param restActions
	 */
	public static void verifyResetMerchantSubaccountPasswordTooSmall(Hashtable<String, String> data, RESTActions restActions) 
	{
				
	}
	
	/**
	 * @param data
	 * @param restActions
	 */
	public static void verifyResetMerchantSubaccountPasswordTooLong(Hashtable<String, String> data, RESTActions restActions) 
	{
				
	}
	
	/**
	 * @param data
	 * @param restActions
	 */
	public static void verifyResetMerchantSubaccountPasswordNoDigits(Hashtable<String, String> data, RESTActions restActions) 
	{
				
	}
	
	/**
	 * @param data
	 * @param restActions
	 */
	public static void verifyResetMerchantSubaccountPasswordUserName(Hashtable<String, String> data,
			RESTActions restActions) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * @param data
	 * @param restActions
	 */
	public static void verifyResetMerchantSubaccountPassword(Hashtable<String, String> data, RESTActions restActions) 
	{
				
	}
	
	/**
	 * @param string
	 * @return
	 */
	private static String buildJsonRequestBody(String data,RESTActions restActions) 
	{
		jsonObj = setPassword(data);
		// Get JSON String representation of the Object
		jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);		
		LOG.info("Converted JSON String: " + jsonStr);
		restActions.successReport("Request body is ", jsonStr);
		return jsonStr;
	}
	
	/**
	 * @param string
	 * @Method to set the password field in request body and return as an object
	 */
	public static WSResetMerchantSubAccountPasswordRequest setPassword(String pwd)
	{
		WSResetMerchantSubAccountPasswordRequest jsonObj = null;
		jsonObj = new WSResetMerchantSubAccountPasswordRequest();
        
 		jsonObj.setPassword(pwd);
		return jsonObj;	
	}
	
	
	//Method to create Header for API calls
	public static Hashtable<String, String> createRESTHeader(String contentType) {
		Hashtable<String, String> header = null;
		String uid = "";
		
		// Populate the Header Values
		uid = UUID.randomUUID().toString();
		
		// Create header Map and add each header individually
		header = new Hashtable<String, String>();
		
		header.put("Content-Type", contentType);
		header.put("Authorization", BackOfficeGlobals.BACKOFFICE_REST_REQ_AUTH_STR);
		header.put("x-cub-uid", uid);
		header.put("x-cub-device", BackOfficeGlobals.BACKOFFICE_REST_REQ_X_CUB_DEVICE_STR);
		header.put("x-cub-audit", BackOfficeGlobals.BACKOFFICE_REST_REQ_X_CUB_AUDIT_STR);
		
		return header;
	}

	
}
