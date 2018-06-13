/**
 * @author 203610
 * Jun 4, 2018
 */
package com.cubic.nisjava.api;

import java.util.Hashtable;
import org.apache.log4j.Logger;
import com.cubic.accelerators.RESTActions;
import com.cubic.accelerators.RESTConstants;
import com.cubic.backoffice.constants.BackOfficeGlobals;
import com.cubic.backoffice.utils.BackOfficeUtils;
import com.cubic.nisjava.apiobjects.WSResetMerchantSubAccountPasswordRequest;
import com.cubic.nisjava.apiobjects.WSResetMerchantSubAccountPasswordResponse;
import com.cubic.nisjava.utils.DataUtils;
import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;

public class ResetMerchantSubaccountPasswordPATCH 
{
	static 
    {
        BackOfficeGlobals.ENV.setEnvironmentVariables();
    }
	public static final String CLASS_NAME = "ResetMerchantSubaccountPasswordPATCH";
	private static final Logger LOG = Logger.getLogger(CLASS_NAME);
	static String url = "https://" + BackOfficeGlobals.ENV.NIS_HOST  + "/nis/retailapi/v1/customer/CMS000001000/user/";
	static ClientResponse clientResponse;
	static WSResetMerchantSubAccountPasswordRequest  jsonObj = null;	
	static WSResetMerchantSubAccountPasswordResponse respObj = null;
	static Class<WSResetMerchantSubAccountPasswordResponse> jsonClass = 	WSResetMerchantSubAccountPasswordResponse.class;
	static String jsonStr = "";
	static String resp = "";	
	
	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable 
	 */
	public static void verifyResetMerchantSubaccountPasswordBlank(Hashtable<String, String> data, RESTActions restActions) throws Throwable
	{
		verifyErrorKeyWithWrongPwdUpdate(restActions,data,data.get("PasswordBlank"));
	}
	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable 
	 */
	public static void verifyResetMerchantSubaccountPasswordConsecutive(Hashtable<String, String> data, RESTActions restActions) throws Throwable
	{
		verifyErrorKeyWithWrongPwdUpdate(restActions,data,data.get("PasswordConsecutive"));		      		
	}
	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable 
	 */
	public static void verifyResetMerchantSubaccountPasswordContiguous(Hashtable<String, String> data, RESTActions restActions) throws Throwable 
	{
		verifyErrorKeyWithWrongPwdUpdate(restActions,data,data.get("PasswordContiguous"));	
	}	
	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable 
	 */
	public static void verifyResetMerchantSubaccountPasswordTooSmall(Hashtable<String, String> data, RESTActions restActions) throws Throwable 
	{
		verifyErrorKeyWithWrongPwdUpdate(restActions,data,data.get("PasswordTooSmall"));	
	}	
	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable 
	 */
	public static void verifyResetMerchantSubaccountPasswordTooLong(Hashtable<String, String> data, RESTActions restActions) throws Throwable 
	{
		verifyErrorKeyWithWrongPwdUpdate(restActions,data,data.get("PasswordTooLong"));	
	}	
	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable 
	 */
	public static void verifyResetMerchantSubaccountPasswordNoDigits(Hashtable<String, String> data, RESTActions restActions) throws Throwable 
	{
		verifyErrorKeyWithWrongPwdUpdate(restActions,data,data.get("PasswordNoDigits"));	
	}	
	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable 
	 */
	public static void verifyResetMerchantSubaccountPasswordUserName(Hashtable<String, String> data, RESTActions restActions) throws Throwable
	{
		verifyErrorKeyWithWrongPwdUpdate(restActions,data,data.get("PasswordUserName"));
	}
	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable 
	 */
	public static void verifyResetMerchantSubaccountPassword(Hashtable<String, String> data, RESTActions restActions) throws Throwable 
	{
		url = url+data.get("UserId");
		//Send request and get Client response
		sendUrlAndGetClientResponse(restActions,url,data.get("ResetPassword"));
						
		//Validate Status Code
		DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCode"), clientResponse);
		
		restActions.successReport("Resetting Password", "Resetting Password to initials");
		sendUrlAndGetClientResponse(restActions,url,data.get("Password"));
		DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCode"), clientResponse);		
	}
	/**
	 * @param restActions
	 * @param data
	 * @param wrongPwd
	 * @throws Throwable 
	 */
	private static void verifyErrorKeyWithWrongPwdUpdate(RESTActions restActions, Hashtable<String, String> data, String wrongPwd) throws Throwable
	{
		url = url+data.get("UserId");
		//Send request and get Client response
		sendUrlAndGetClientResponse(restActions,url,wrongPwd);
				
		//Validate Status Code
		DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCode"), clientResponse);
				
		// De-serialize the Response into a JSON Object
		Gson g = new Gson();
		respObj = g.fromJson(resp, jsonClass);
				
		//Validate Response error message and Error Key
		verifyResponseFromServer(restActions,data); 		
	}
	
	/**
	 * @param restActions
	 * @param url
	 * @param string
	 * @throws Throwable 
	 */
	private static void sendUrlAndGetClientResponse(RESTActions restActions,String url,String data) throws Throwable
	{
		LOG.info(url);
		restActions.successReport("Sending URL..", url);		
		// Build JSON Object
		jsonStr = buildJsonRequestBody(data,restActions);
		restActions.successReport("Sending Request body", jsonStr);
		//Send request and get Client response
		clientResponse = restActions.patchClientResponse(url, jsonStr, DataUtils.getHeaderForRetailApi(),null,RESTConstants.APPLICATION_JSON);
		restActions.successReport("Response Code from Server", ""+clientResponse.getStatus());
		resp = clientResponse.getEntity(String.class);
		LOG.info(resp);
		restActions.successReport("Response from Server is ", resp);		
	}
	/**
	 * @param String
	 * @param restActions
	 * @return
	 */
	private static String buildJsonRequestBody(String data,RESTActions restActions) 
	{
		// Get JSON String representation of the Object
		jsonStr = BackOfficeUtils.getJSONFromObject(setPassword(data));		
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
		jsonObj = new WSResetMerchantSubAccountPasswordRequest();        
 		jsonObj.setPassword(pwd);
		return jsonObj;	
	}	
	/**
	 * @param restActions
	 * @param data
	 */
	private static void verifyResponseFromServer(RESTActions restActions,Hashtable<String, String> data) 
	{
		//Verifying error key from Response with the expected error key
		if(respObj.getHdr().getErrorKey().equalsIgnoreCase(data.get("ExpectedErrorKey")))
		{
			restActions.successReport("Verifying Error key", "Expected Error Key : "+data.get("ExpectedErrorKey")+" is same as error key from the ClientResponse i.e., "+respObj.getHdr().getErrorKey() );
		}
		else
		{
			restActions.failureReport("Verifying Error key", "Expected Error Key : "+data.get("ExpectedErrorKey")+" but error key from the ClientResponse i.e., "+respObj.getHdr().getErrorKey() );
		}
	}
	
	
}
