package com.cubic.nisjava.api;

import java.net.HttpURLConnection;

import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.cubic.accelerators.RESTActions;
import com.cubic.backoffice.constants.BackOfficeGlobals;
import com.cubic.backoffice.utils.BackOfficeUtils;
import com.cubic.logutils.Log4jUtil;
import com.cubic.nisjava.apiobjects.WSPatronAuthenticateRequest;
import com.cubic.nisjava.apiobjects.WSPatronAuthenticateResponse;
import com.cubic.nisjava.utils.DataUtils;
import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;

public class PatronAuthenticatePost {
	
	static String url = DataUtils.url+"authenticate";
	public static final String CLASS_NAME = "PatronAuthenticatePost";
	private static final Logger LOG = Logger.getLogger(CLASS_NAME);
	static ClientResponse clientResponse;
	static WSPatronAuthenticateRequest  jsonObj = null;	
	static WSPatronAuthenticateResponse	 respObj = null;
	static Class<WSPatronAuthenticateResponse> jsonClass = 	WSPatronAuthenticateResponse.class;
	static String jsonStr = "";
	static String resp = "";
	
	/*
	 * This method is used to to test patron authenticate with valid test data 
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAhenticate(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object
			jsonObj = DataUtils.patronAuthenticate(data.get("Username_valid"),data.get("password_valid"));

			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);

			// Make HTTP Post request to verify Patron Authenticate
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			String httpMessage = "WRONG HTTP RESPONSE CODE - EXPECTED 200, FOUND " + clientResponse.getStatus();
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 

			if (HttpURLConnection.HTTP_OK == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with valid data ...", ""+jsonStr);
				Assert.assertEquals( HttpURLConnection.HTTP_OK, clientResponse.getStatus(), httpMessage );
				actions.successReport("Expecting  Http Response code is 200", "Http Response code is ..."+clientResponse.getStatus());
				actions.successReport("Success", "Patron Account is Authenticated,authCode is .." +respObj.getAuthCode());
				actions.successReport("Success", "Patron Account is Created .." +resp);
			}

			else {
				actions.failureReport("Expecting Http Response code is 200","Http Response code is ...." +clientResponse.getStatus());
				actions.failureReport("Error field : "+respObj.getHdr().getFieldName(),"Error key : "+respObj.getHdr().getErrorKey());

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
	 * This method is used to to test patron authenticate with Username is blank 
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAhenticateWithUsernameBlank(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object
			jsonObj = DataUtils.patronAuthenticate(data.get("Username_blank"),data.get("password_valid"));

			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);

			// Make HTTP Post request to verify Patron Authenticate
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 

			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with User Name blank ", ""+jsonStr);
				actions.successReport("Expecting  Http Response code is 400", "Http Response code is ..."+clientResponse.getStatus());
				actions.successReport("Error field : "+respObj.getHdr().getFieldName(),"Error key : "+respObj.getHdr().getErrorKey());
				actions.successReport("DataValidationError", "Http Response is .." +resp);
								
			}

			else {
				actions.failureReport("Expecting Http Response code is 400","Http Response code is ...." +clientResponse.getStatus());
				actions.failureReport("Error field : "+respObj.getHdr().getFieldName(),"Error key : "+respObj.getHdr().getErrorKey());
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
	 * This method is used to to test patron authenticate with password is blank 
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAhenticateWithpasswordBlank(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object
			jsonObj = DataUtils.patronAuthenticate(data.get("Username_valid"),data.get("password_blank"));

			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);

			// Make HTTP Post request to verify Patron Authenticate
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 

			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with password blank ", ""+jsonStr);
				actions.successReport("Expecting  Http Response code is 400", "Http Response code is ..."+clientResponse.getStatus());
				actions.successReport("Error field : "+respObj.getHdr().getFieldName(),"Error key : "+respObj.getHdr().getErrorKey());
				actions.successReport("DataValidationError", "Http Response is .." +resp);
								
			}

			else {
				actions.failureReport("Expecting Http Response code is 400","Http Response code is ...." +clientResponse.getStatus());
				actions.failureReport("Error field : "+respObj.getHdr().getFieldName(),"Error key : "+respObj.getHdr().getErrorKey());
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
	 * This method is used to to test patron authenticate with invalid username / password combination 
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAhenticateWithInvalidUsernameandpassword(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object
			jsonObj = DataUtils.patronAuthenticate(data.get("Username_Invalid"),data.get("password_Invalid"));

			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);

			// Make HTTP Post request to verify Patron Authenticate
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 

			if (HttpURLConnection.HTTP_UNAUTHORIZED == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Invalid Username / password combination ", ""+jsonStr);
				actions.successReport("Expecting  Http Response code is 401", "Http Response code is ..."+clientResponse.getStatus());
				actions.successReport("Error field : "+respObj.getHdr().getFieldName(),"Error key : "+respObj.getHdr().getErrorKey());
				actions.successReport("DataValidationError", "Http Response is .." +resp);
								
			}

			else {
				actions.failureReport("Expecting Http Response code is 401","Http Response code is ...." +clientResponse.getStatus());
				actions.failureReport("Error field : "+respObj.getHdr().getFieldName(),"Error key : "+respObj.getHdr().getErrorKey());
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
	 * This method is used to to test patron authenticate with Account Disabled 
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAhenticateWithAccountDisabled(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object
			jsonObj = DataUtils.patronAuthenticate(data.get("Username_Disabled"),data.get("password_Disabled"));

			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);

			// Make HTTP Post request to verify Patron Authenticate
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 

			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Account Disabled  ", ""+jsonStr);
				actions.successReport("Expecting  Http Response code is 400", "Http Response code is ..."+clientResponse.getStatus());
				actions.successReport("Error field : "+respObj.getHdr().getFieldName(),"Error key : "+respObj.getHdr().getErrorKey());
				actions.successReport("DataValidationError", "Http Response is .." +resp);
								
			}

			else {
				actions.failureReport("Expecting Http Response code is 400","Http Response code is ...." +clientResponse.getStatus());
				actions.failureReport("Error field : "+respObj.getHdr().getFieldName(),"Error key : "+respObj.getHdr().getErrorKey());
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


	

}
