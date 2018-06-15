package com.cubic.nisjava.api;

/**
 * @author 205974
 *
 */

import java.net.HttpURLConnection;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.cubic.accelerators.RESTActions;
import com.cubic.backoffice.constants.BackOfficeGlobals;
import com.cubic.backoffice.utils.BackOfficeUtils;
import com.cubic.logutils.Log4jUtil;
import com.cubic.nisjava.apiobjects.MerchantLogin;
import com.cubic.nisjava.apiobjects.MerchantLoginResp;
import com.cubic.nisjava.utils.DataUtils;
import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;

public class MerchantPatronAuthenticatePost {

	static 
    {
        BackOfficeGlobals.ENV.setEnvironmentVariables();
    }

	static String url =  "https://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/CMS000001000/authenticate";
	
	public static final String CLASS_NAME = "MerchantPatronAuthenticatePost";
	private static final Logger LOG = Logger.getLogger(CLASS_NAME);
	static ClientResponse clientResponse;
	static MerchantLogin jsonObj = null;	
	static MerchantLoginResp respObj = null;
	static Class<MerchantLoginResp> jsonClass = MerchantLoginResp.class;
	static String jsonStr = "";
	static String resp = "";
	
	/*
	 * This method is used to test patron authenticate with valid test data - C190398
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAuthenticate(Hashtable<String, String> data, RESTActions actions) {

		try {

			// Build JSON Object with User name, Password and Device Serial Number
			jsonObj = DataUtils.patronAuthenticate(data.get("Username_Valid"),data.get("Password_Valid"), data.get("deviceSerialNumber_Valid"));
					
			// Get JSON String representation of the Object
			logJsonString();

			// Make HTTP Post request to verify Patron Authenticate
			patronClientResponse(actions);
			
			//Logging url, status and Response
			loggingStatus();

			// De-serialize the Response into a JSON Object
			gsonDeserializeResp(); 
			
			//Expected API Response
			String ExpectedResp = "{\""+data.get("EXPECTED_FIELDNAME1")+"\":\""+data.get("EXPECTED_FIELDNAME1_VALUE")+"\",\""+data.get("EXPECTED_FIELDNAME2")+"\":\""+data.get("EXPECTED_FIELDNAME2_VALUE")+"\"}";
			
			if (HttpURLConnection.HTTP_OK == clientResponse.getStatus()) {
				
				if(ExpectedResp.equalsIgnoreCase(resp)){
				
					logURLStatus(actions);
					actions.successReport("Sending Http request body with valid username and password...", ""+jsonStr);
					logExpectedActualResp(actions, ExpectedResp);
					
				}
				else
					actions.failureReport("Expecting Response - " + ExpectedResp,"Actual Response - " +resp);
			}
			else 
				actions.failureReport("Expecting  Http Response code is -"+HttpURLConnection.HTTP_OK, "Actual Http Response code is - "+clientResponse.getStatus());		
	
		}
		catch (Exception e) {
			LOG.error(Log4jUtil.getStackTrace(e));
			throw new RuntimeException(e);
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
			throw new RuntimeException(t);
		}

	}

	/**
	 * @param actions
	 * @param ExpectedResp
	 */
	private static void logExpectedActualResp(RESTActions actions, String ExpectedResp) {
		actions.successReport("Expected Response - ","" + ExpectedResp);
		actions.successReport("Actual Response - ",""+resp);
		actions.successReport("API Reponse Validation - ","Expected Response and Actual Response are matching");
	}

	/**
	 * @param actions
	 */
	private static void logURLStatus(RESTActions actions) {
		actions.successReport("Sending Patron Authenticate POST request...", ""+url);
		actions.successReport("Expecting  Http Response code is -"+HttpURLConnection.HTTP_OK, "Actual Http Response code is - "+clientResponse.getStatus());
	}

	/**
	 * 
	 */
	private static void gsonDeserializeResp() {
		Gson g = new Gson();
		respObj = g.fromJson(resp, jsonClass);
	}

	/**
	 * 
	 */
	private static void loggingStatus() {
		LOG.info("URL: " + url);
		LOG.info("Http Status is ... "+ clientResponse.getStatus());
		LOG.info("Response: \n" + resp);
	}

	/**
	 * @param actions
	 * @throws Throwable
	 */
	private static void patronClientResponse(RESTActions actions) throws Throwable {
		clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.getHeaderForRetailApi(), null,null);
		resp = clientResponse.getEntity(String.class);
	}

	/**
	 * 
	 */
	private static void logJsonString() {
		jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
		LOG.info("Converted JSON String: " + jsonStr);
	}
	
	/*
	 * This method is used to test patron authenticate with Registered Device - C190399
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAuthenticateWithRegisteredDevice(Hashtable<String, String> data, RESTActions actions) {

		try {

			// Build JSON Object with User name, Password and Device Serial Number
			jsonObj = DataUtils.patronAuthenticate(data.get("Username_Valid"),data.get("Password_Valid"), data.get("deviceSerialNumber_Registered"));

			// Get JSON String representation of the Object
			logJsonString();

			// Make HTTP Post request to verify Patron Authenticate
			patronClientResponse(actions);
						
			//Logging url, status and Response
			loggingStatus();

			// De-serialize the Response into a JSON Object
			gsonDeserializeResp();  
			
			//Expected API Response
			String ExpectedResp = "{\""+data.get("EXPECTED_FIELDNAME1")+"\":\""+data.get("EXPECTED_FIELDNAME1_VALUE")+"\",\""+data.get("EXPECTED_FIELDNAME2")+"\":\""+data.get("EXPECTED_FIELDNAME2_VALUE")+"\"}";
			
			if (HttpURLConnection.HTTP_OK == clientResponse.getStatus()) {

				if(ExpectedResp.equalsIgnoreCase(resp)){
					
					logURLStatus(actions);
					actions.successReport("Sending Http request body with valid registered device details...", ""+jsonStr);
					logExpectedActualResp(actions, ExpectedResp);
					
				}
				
				else
					actions.failureReport("Expecting Response - " + ExpectedResp,"Actual Response - " +resp);
				}
				else 
					actions.failureReport("Expecting  Http Response code is -"+HttpURLConnection.HTTP_OK, "Actual Http Response code is - "+clientResponse.getStatus());		
			}
		
		catch (Exception e) {
			LOG.error(Log4jUtil.getStackTrace(e));
			throw new RuntimeException(e);
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
			throw new RuntimeException(t);
		}

	}
	
	/*
	 * This method is used to test patron authenticate with Unregistered Device 
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAuthenticateWithUnregisteredDevice(Hashtable<String, String> data, RESTActions actions) {

		try {

			// Build JSON Object with User name, Password and Device Serial Number
			jsonObj = DataUtils.patronAuthenticate(data.get("Username_Valid"),data.get("Password_Valid"), data.get("deviceSerialNumber_UnRegistered"));

			// Get JSON String representation of the Object
			logJsonString();

			// Make HTTP Post request to verify Patron Authenticate
			patronClientResponse(actions);
						
			//Logging url, status and Response
			loggingStatus();

			// De-serialize the Response into a JSON Object
			gsonDeserializeResp(); 
							
			//Expected API Response
			String ExpectedResp = "{\""+data.get("expected_hdr_field")+"\":{\""+data.get("expected_result")+"\":\""+data.get("expected_result_value")+"\",\""+data.get("expected_uid")+"\":\""+data.get("expected_uid_value")+"\",\""+data.get("expected_fieldName")+"\":\""+data.get("expected_fieldName_value")+"\",\""+data.get("expected_errorKey")+"\":\""+data.get("expected_errorKey_value")+"\"}}";
		
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {
								
				if(respObj.getHdr().getFieldName().equalsIgnoreCase(data.get("expected_fieldName_value")) && respObj.getHdr().getResult().equalsIgnoreCase(data.get("expected_result_value"))){
						
					logURLStatusBadRequest(actions);
					actions.successReport("Sending Http request body with unregistered device details...", ""+jsonStr);
					logExpectedActualResp(actions, ExpectedResp);

			}
				else
					actions.failureReport("Expecting Response - " + ExpectedResp,"Actual Response - " +resp);	
			}

			else {
				actions.failureReport("Expecting  Http Response code is -"+HttpURLConnection.HTTP_BAD_REQUEST, "Actual Http Response code is - "+clientResponse.getStatus());
								
			}

		}
		catch (Exception e) {
			LOG.error(Log4jUtil.getStackTrace(e));
			throw new RuntimeException(e);
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
			throw new RuntimeException(t);
		}

	}

	/**
	 * @param actions
	 */
	private static void logURLStatusBadRequest(RESTActions actions) {
		actions.successReport("Sending Patron Authenticate POST request...", ""+url);
		actions.successReport("Expecting  Http Response code is -"+HttpURLConnection.HTTP_BAD_REQUEST, "Actual Http Response code is - "+clientResponse.getStatus());
	}
	
	/*
	 * This method is used to test patron login with Authorization Failed
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronLoginWithAuthorizationFailed(Hashtable<String, String> data, RESTActions actions) {

		try {

			// Build JSON Object with User name and Password
			jsonObj = DataUtils.patronAuthenticateWithoutDeviceSerialNumber(data.get("Username_Invalid"),data.get("Password_Invalid"));
					
			// Get JSON String representation of the Object
			logJsonString();

			// Make HTTP Post request to verify Patron Authenticate
			patronClientResponse(actions);
						
			//Logging url, status and Response
			loggingStatus();

			// De-serialize the Response into a JSON Object
			gsonDeserializeResp(); 
			
			//Expected API Response
			String ExpectedResp = "{\""+data.get("expected_authCode")+"\":\""+data.get("expected_authCode_value")+"\",\""+data.get("expected_authErrors")+"\":[{\""+data.get("expected_errorKey")+"\":\""+data.get("expected_errorKey_value")+"\"}]}";
			
			if (HttpURLConnection.HTTP_UNAUTHORIZED == clientResponse.getStatus()) {
		
				if(ExpectedResp.equalsIgnoreCase(resp)){
		
					logURLStatusUnAuthorized(actions);
					actions.successReport("Sending Http request body with Invalid Username and Password...", ""+jsonStr);
					logExpectedActualResp(actions, ExpectedResp);
			}
			else
				actions.failureReport("Expecting Response - " + ExpectedResp,"Actual Response - " +resp);	
		}

		else {
			actions.failureReport("Expecting  Http Response code is -"+HttpURLConnection.HTTP_UNAUTHORIZED, "Actual Http Response code is - "+clientResponse.getStatus());
							
		}
		}
		catch (Exception e) {
			LOG.error(Log4jUtil.getStackTrace(e));
			throw new RuntimeException(e);
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
			throw new RuntimeException(t);
		}

}

	/**
	 * @param actions
	 */
	private static void logURLStatusUnAuthorized(RESTActions actions) {
		actions.successReport("Sending Patron Authenticate POST request...", ""+url);
		actions.successReport("Expecting  Http Response code is -"+HttpURLConnection.HTTP_UNAUTHORIZED , "Actual Http Response code is - "+clientResponse.getStatus());
	}
	
	/*
	 * This method is used to test patron login with Locked User
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronLoginWithLockedUser(Hashtable<String, String> data, RESTActions actions) {

		try {

			// Build JSON Object with User name and Password
			jsonObj = DataUtils.patronAuthenticateWithoutDeviceSerialNumber(data.get("username"),data.get("password"));
					
			// Get JSON String representation of the Object
			logJsonString();

			// Make HTTP Post request to verify Patron Authenticate
			patronClientResponse(actions);
						
			//Logging url, status and Response
			loggingStatus();

			// De-serialize the Response into a JSON Object
			gsonDeserializeResp(); 
			
			//Expected API Response
			String ExpectedResp = "{\""+data.get("expected_authCode")+"\":\""+data.get("expected_authCode_value")+"\",\""+data.get("expected_authErrors")+"\":[{\""+data.get("expected_errorKey")+"\":\""+data.get("expected_errorKey_value")+"\"}]}";
			
			if (HttpURLConnection.HTTP_UNAUTHORIZED == clientResponse.getStatus()) {

				if(ExpectedResp.equalsIgnoreCase(resp)){
				logURLStatusUnAuthorized(actions);
				actions.successReport("Sending Http request body with locked user...", ""+jsonStr);
				logExpectedActualResp(actions, ExpectedResp);
				
			}
				else
				actions.failureReport("Expecting Response - " + ExpectedResp,"Actual Response - " +resp);	
			}

		else {
			actions.failureReport("Expecting  Http Response code is -"+HttpURLConnection.HTTP_UNAUTHORIZED, "Actual Http Response code is - "+clientResponse.getStatus());
							
		}
			}
		catch (Exception e) {
			LOG.error(Log4jUtil.getStackTrace(e));
			throw new RuntimeException(e);
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
			throw new RuntimeException(t);
		}

	}
	
	/*
	 * This method is used to test patron login with Suspended User
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronLoginWithSuspendedUser(Hashtable<String, String> data, RESTActions actions) {

		try {

			// Build JSON Object with User name and Password
			jsonObj = DataUtils.patronAuthenticateWithoutDeviceSerialNumber(data.get("username"),data.get("password"));
				
			// Get JSON String representation of the Object
			logJsonString();

			// Make HTTP Post request to verify Patron Authenticate
			patronClientResponse(actions);
						
			//Logging url, status and Response
			loggingStatus();

			// De-serialize the Response into a JSON Object
			gsonDeserializeResp(); 
						
			//Expected Response String
			String ExpectedResp = "{\""+data.get("expected_authCode")+"\":\""+data.get("expected_authCode_value")+"\",\""+data.get("expected_authErrors")+"\":[{\""+data.get("expected_errorKey")+"\":\""+data.get("expected_errorKey_value")+"\"}]}";
			
			if (HttpURLConnection.HTTP_UNAUTHORIZED == clientResponse.getStatus()) {
				
				if(respObj.getAuthCode().equalsIgnoreCase(data.get("expected_authCode")) && respObj.getAuthErrors().get(0).geterrorKey().equalsIgnoreCase(data.get("expected_errorKey_value"))){
					
				logURLStatusUnAuthorized(actions);
				actions.successReport("Sending Http request body with valid username and password of suspended user...", ""+jsonStr);
				
				logExpectedActualResp(actions, ExpectedResp);
							
				}
				else
					actions.failureReport("Expecting Response - " + ExpectedResp,"Actual Response - " +resp);	
			}

			else {
				actions.failureReport("Expecting  Http Response code is -"+HttpURLConnection.HTTP_UNAUTHORIZED, "Actual Http Response code is - "+clientResponse.getStatus());
								
			}
			}
		catch (Exception e) {
			LOG.error(Log4jUtil.getStackTrace(e));
			throw new RuntimeException(e);
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
			throw new RuntimeException(t);
		}

	}
	
	/*
	 * This method is used to test patron login with No Auth
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronLoginWithNoAuth(Hashtable<String, String> data, RESTActions actions) {

		try {

			// Build JSON Object with User name and Password
			jsonObj = DataUtils.patronAuthenticateWithoutDeviceSerialNumber(data.get("username"),data.get("password"));
			
			// Get JSON String representation of the Object
			logJsonString();

			// Make HTTP Post request to verify Patron Authenticate
			clientResponse = actions.postClientResponse(url, jsonStr,null, null,null);
			resp = clientResponse.getEntity(String.class);
		
			//Logging url, status and Response
			loggingStatus();

			// De-serialize the Response into a JSON Object
			gsonDeserializeResp(); 
			
			String ExpectedResp = "{\""+data.get("expected_fieldName")+"\": {\""+data.get("expected_result")+"\": \""+data.get("expected_result_value")+"\",\""+data.get("expected_uid")+"\": \""+data.get("expected_uid_value")+"\",\""+data.get("expected_fieldName")+"\": \""+data.get("expected_fieldName_value")+"\",\""+data.get("expected_errorKey")+"\": \""+data.get("expected_errorKey_value")+"\",\""+data.get("expected_errorMessage")+"\": \""+data.get("expected_errorMessage_value")+"\"}}";
			
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				logURLStatusBadRequest(actions);
				actions.successReport("Sending Http request body with Blank Auth ...", ""+jsonStr);
				actions.successReport("Expecting Response is - " + ExpectedResp,"Actual Response is - " +resp);
				
			}
			
			else {
				actions.failureReport("Expecting  Http Response code is -"+HttpURLConnection.HTTP_BAD_REQUEST, "Actual Http Response code is - "+clientResponse.getStatus());
				actions.failureReport("Expecting Response - " + ExpectedResp,"Actual Response - " +resp);
				
			}
			}
		catch (Exception e) {
			LOG.error(Log4jUtil.getStackTrace(e));
			throw new RuntimeException(e);
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
			throw new RuntimeException(t);
		}

	}
	
	/*
	 * This method is used to test forgot pin
	 * @param data
	 * @param actions
	 */
	public static void verifyForgotPin(Hashtable<String, String> data, RESTActions actions) {

		try {
			
			//Get security Question
			getSecurityQuestion(data, actions);
				
			}
		catch (Exception e) {
			LOG.error(Log4jUtil.getStackTrace(e));
			throw new RuntimeException(e);
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
			throw new RuntimeException(t);
		}

	}

	/**
	 * @param data
	 * @param actions
	 * @throws Throwable
	 */
	private static void getSecurityQuestion(Hashtable<String, String> data, RESTActions actions) throws Throwable {
		
		//Get Security Question
		
		String url = "https://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/CMS000001000/securityquestion";
		
		// Build JSON Object with User name and Password
		 jsonStr = "{\"username\":\""+data.get("ForgotPin_username")+"\"}";

		// Make HTTP Post request to get Security Question
		clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.getHeaderForRetailApi(), null,null);
		resp = clientResponse.getEntity(String.class);
		
		//Logging url, status and Response
		loggingStatus();

		// De-serialize the Response into a JSON Object
		gsonDeserializeResp(); 
		
		//Expected API Response
		String ExpectedResp = "{\"securityQuestions\":[\""+data.get("SecurityQuestion")+"\"]}";
			
		if (HttpURLConnection.HTTP_OK == clientResponse.getStatus()) {

			if(ExpectedResp.equalsIgnoreCase(resp)){
			logURLStatus(actions);
			actions.successReport("Sending Http request body with valid username ...", ""+jsonStr);
			logExpectedActualResp(actions, ExpectedResp);
			
		}
		else
			actions.failureReport("Expecting Response - " + ExpectedResp,"Actual Response - " +resp);	
		}

		else {
		actions.failureReport("Expecting  Http Response code is -"+HttpURLConnection.HTTP_OK, "Actual Http Response code is - "+clientResponse.getStatus());
						
		}
	}

	public static void verifyLoginWithMultipleUsers(Hashtable<String, String> data, RESTActions actions) {
		
		try {

			// Build JSON Object with User name, Password and Device Serial Number
			jsonObj = DataUtils.patronAuthenticate(data.get("Username_Valid"),data.get("Password_Valid"), data.get("deviceSerialNumber_Valid"));
				
			//Logging url, status and Response
			logJsonString();

			//Make HTTP Post request to get Security Question
			patronClientResponse(actions);
			
			//Logging url, status and Response
			loggingStatus();

			// De-serialize the Response into a JSON Object
			gsonDeserializeResp();  
			
			//Expected API Response
			String ExpectedResp = "{\""+data.get("EXPECTED_FIELDNAME1")+"\":\""+data.get("EXPECTED_FIELDNAME1_VALUE")+"\",\""+data.get("EXPECTED_FIELDNAME2")+"\":\""+data.get("EXPECTED_FIELDNAME2_VALUE")+"\"}";
			
			if (HttpURLConnection.HTTP_OK == clientResponse.getStatus()) {
				
				if(ExpectedResp.equalsIgnoreCase(resp)){
				
					logURLStatus(actions);
					actions.successReport("Sending Http request body with valid username and password...", ""+jsonStr);
					logExpectedActualResp(actions, ExpectedResp);
					
				}
				else
					actions.failureReport("Expecting Response - " + ExpectedResp,"Actual Response - " +resp);
			}
			else 
				actions.failureReport("Expecting  Http Response code is -"+HttpURLConnection.HTTP_OK, "Actual Http Response code is - "+clientResponse.getStatus());		
	
		}
		catch (Exception e) {
			LOG.error(Log4jUtil.getStackTrace(e));
			throw new RuntimeException(e);
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
			throw new RuntimeException(t);
		}
		
	}
		
}
