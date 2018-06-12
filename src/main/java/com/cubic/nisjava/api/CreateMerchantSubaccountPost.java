package com.cubic.nisjava.api;

import java.net.HttpURLConnection;
import java.util.Hashtable;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.cubic.accelerators.RESTActions;
import com.cubic.accelerators.RESTConstants;
import com.cubic.nisjava.apiobjects.WSName;
import com.cubic.backoffice.constants.BackOfficeGlobals;
import com.cubic.backoffice.utils.BackOfficeUtils;
import com.cubic.logutils.Log4jUtil;
import com.cubic.nisjava.apiobjects.CreateMerchantSubaccountRequest;
import com.cubic.nisjava.apiobjects.CreateMerchantSubaccountResponse;
import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;

/**
 * Create Merchant Subaccount
  *  Author:Vijaya Bhaskar Palem
 */
public class CreateMerchantSubaccountPost {
	
	public static final String CLASS_NAME = "CreateMerchantSubaccountPost";
	private static final Logger LOG = Logger.getLogger(CLASS_NAME);
	static ClientResponse clientResponse;
	static CreateMerchantSubaccountRequest  jsonObj = null;	
	static CreateMerchantSubaccountResponse respObj = null;
	static Class<CreateMerchantSubaccountResponse> jsonClass = 	CreateMerchantSubaccountResponse.class;
	static String jsonStr = "";
	static String resp = "";
	static String userId = "";
	static 
	{
		BackOfficeGlobals.ENV.setEnvironmentVariables();
	}
	
	
	/*
	 * This method is used to to test Create Merchant Subaccount with valid test data 
	 * @param data
	 * @param actions
	 */
	public static void CreateMerchantSubaccount(Hashtable<String, String> data, RESTActions actions) {

		try {
			
			// Create URL
			String url = "http://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/CMS000001000/user";
			
			//String url = "https://lab7319.ctsservice.com"+"/nis/retailapi/v1/customer/CMS000001000/user";
			LOG.info("URL: " + url);
	
			// Build JSON Object
			jsonObj = merchantSubaccountRequest(data);

			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to Create Merchant Subaccount
			clientResponse = actions.postClientResponse(url, jsonStr,BackOfficeUtils.createRESTHeader(RESTConstants.APPLICATION_JSON), null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			String httpMessage = "WRONG HTTP RESPONSE CODE - EXPECTED 200, FOUND " + clientResponse.getStatus();
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			actions.successReport("CreateMerchantSubaccount", "Verify CreateMerchantSubaccount functionality");
			actions.successReport("Sending URL ..+POST method+..  ", url);
			userId = respObj.getUserId();
			if (HttpURLConnection.HTTP_OK == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with valid data ...", ""+jsonStr);
				Assert.assertEquals( HttpURLConnection.HTTP_OK, clientResponse.getStatus(), httpMessage );
				actions.successReport("Expecting  Http Response code is 200", "Http Response code is ..."+clientResponse.getStatus());
				actions.successReport("Response from Server", resp);
				actions.assertTrue(!respObj.getUserId().isEmpty(), "userid should not be NULL.");
				actions.successReport("User ID is from Response...", respObj.getUserId());
				actions.successReport("Success", "Create Merchant Subaccount .." +resp);
			}
			else {
				actions.failureReport("Expecting Http Response code is 200","Http Response code is ...." +clientResponse.getStatus());
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
	 * This method is used to to test Create Merchant Subaccount - Name Blank w 
	 * @param data
	 * @param actions
	 */
	public static void CreateMerchantSubaccountNameBlank(Hashtable<String, String> data, RESTActions actions) {

		try {
			
			// Create URL
			//String url = "http://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/CMS000001000/user";
			String url = "https://lab7319.ctsservice.com"+"/nis/retailapi/v1/customer/CMS000001000/user";
			LOG.info("URL: " + url);
			

			// Build JSON Object
			jsonObj = merchantSubaccountRequest(data);
			jsonObj.setName(null);

			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to Create Merchant Subaccount with Name Blank
			clientResponse = actions.postClientResponse(url, jsonStr,BackOfficeUtils.createRESTHeader(RESTConstants.APPLICATION_JSON), null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			String httpMessage = "WRONG HTTP RESPONSE CODE - EXPECTED 400, FOUND " + clientResponse.getStatus();
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			actions.successReport("CreateMerchantSubaccount", "Verify CreateMerchantSubaccount functionality with name Blank");
			actions.successReport("Sending URL ..+POST method+..  ", url);
			
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Blank Name ...", ""+jsonStr);
				actions.successReport("Expecting  Http Response code is 400", "Http Response code is ..."+clientResponse.getStatus());
				Assert.assertEquals( HttpURLConnection.HTTP_BAD_REQUEST, clientResponse.getStatus(), httpMessage );
				actions.successReport("Error field : "+respObj.getHdr().getFieldName(),"Error key : "+respObj.getHdr().getErrorKey());
				actions.assertTrue(data.get("ErrorKey").contains(respObj.getHdr().getErrorKey()), "Error validation:");
				actions.successReport("DataValidationError", "Http Response is .." +resp);
				Assert.assertEquals( HttpURLConnection.HTTP_BAD_REQUEST, clientResponse.getStatus(), httpMessage );
				actions.successReport("Response from Server - Success", resp);
				
			}

			else {
				actions.failureReport("Expecting Http Response code is 400","Http Response code is ...." +clientResponse.getStatus());
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
	 * This method is used to to test Create Merchant Subaccount - Userid Blank 
	 * @param data
	 * @param actions
	 */
	public static void CreateMerchantSubaccountUserIdBlank(Hashtable<String, String> data, RESTActions actions) {

		try {
			
			// Create URL
			//String url = "http://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/CMS000001000/user";
			String url = "https://lab7319.ctsservice.com"+"/nis/retailapi/v1/customer/CMS000001000/user";
			LOG.info("URL: " + url);

			// Build JSON Object
			jsonObj = merchantSubaccountRequest(data);
			jsonObj.setUsername(data.get("UserIdBlank"));

			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to Create Merchant Subaccount with Userid Blank
			clientResponse = actions.postClientResponse(url, jsonStr,BackOfficeUtils.createRESTHeader(RESTConstants.APPLICATION_JSON), null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			String httpMessage = "WRONG HTTP RESPONSE CODE - EXPECTED 400, FOUND " + clientResponse.getStatus();
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			actions.successReport("CreateMerchantSubaccount", "Verify CreateMerchantSubaccount functionality with UserID Blank");
			actions.successReport("Sending URL ..+POST method+..  ", url);
			
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Blank UserId ...", ""+jsonStr);
				actions.successReport("Expecting  Http Response code is 400", "Http Response code is ..."+clientResponse.getStatus());
				Assert.assertEquals( HttpURLConnection.HTTP_BAD_REQUEST, clientResponse.getStatus(), httpMessage );
				actions.successReport("Error field : "+respObj.getHdr().getFieldName(),"Error key : "+respObj.getHdr().getErrorKey());
				actions.successReport("DataValidationError", "Http Response is .." +resp);
				actions.assertTrue(data.get("ErrorKey").contains(respObj.getHdr().getErrorKey()), "Error validation:");
				actions.successReport("Response from Server - Success", resp);
				
			}

			else {
				actions.failureReport("Expecting Http Response code is 400","Http Response code is ...." +clientResponse.getStatus());
	
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
	 * This method is used to to test Create Merchant Subaccount - Userid Duplicate 
	 * @param data
	 * @param actions
	 */
	public static void CreateMerchantSubaccountUserIdDuplicate(Hashtable<String, String> data, RESTActions actions) {

		try {
			
			// Create URL
			//String url = "http://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/CMS000001000/user";
			String url = "https://lab7319.ctsservice.com"+"/nis/retailapi/v1/customer/CMS000001000/user";
			LOG.info("URL: " + url);

			// Build JSON Object
			jsonObj = merchantSubaccountRequest(data);
			jsonObj.setUsername(data.get("UserIdDuplicate"));

			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to Create Merchant Subaccount with Userid Duplicate
			clientResponse = actions.postClientResponse(url, jsonStr,BackOfficeUtils.createRESTHeader(RESTConstants.APPLICATION_JSON), null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			String httpMessage = "WRONG HTTP RESPONSE CODE - EXPECTED 400, FOUND " + clientResponse.getStatus();
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			actions.successReport("CreateMerchantSubaccount", "Verify CreateMerchantSubaccount functionality with UserID Duplicate");
			actions.successReport("Sending URL ..+POST method+..  ", url);
			
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with UserId Duplicate ...", ""+jsonStr);
				actions.successReport("Expecting  Http Response code is 400", "Http Response code is ..."+clientResponse.getStatus());
				Assert.assertEquals( HttpURLConnection.HTTP_BAD_REQUEST, clientResponse.getStatus(), httpMessage );
				actions.successReport("Error field : "+respObj.getHdr().getFieldName(),"Error key : "+respObj.getHdr().getErrorKey());
				actions.successReport("DataValidationError", "Http Response is .." +resp);
				actions.assertTrue(data.get("ErrorKey").contains(respObj.getHdr().getErrorKey()), "Error validation:");
				actions.successReport("Response from Server - Success", resp);
				
			}

			else {
				actions.failureReport("Expecting Http Response code is 400","Http Response code is ...." +clientResponse.getStatus());
	
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
	 * This method is used to to test Create Merchant Subaccount - Password Blank 
	 * @param data
	 * @param actions
	 */
	public static void CreateMerchantSubaccountPasswordBlank(Hashtable<String, String> data, RESTActions actions) {

		try {
			
			// Create URL
			//String url = "http://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/CMS000001000/user";
			String url = "https://lab7319.ctsservice.com"+"/nis/retailapi/v1/customer/CMS000001000/user";
			LOG.info("URL: " + url);

			// Build JSON Object
			jsonObj = merchantSubaccountRequest(data);
			jsonObj.setPassword(data.get("PasswordBlank"));

			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to Create Merchant Subaccount with Password Blank
			clientResponse = actions.postClientResponse(url, jsonStr,BackOfficeUtils.createRESTHeader(RESTConstants.APPLICATION_JSON), null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			String httpMessage = "WRONG HTTP RESPONSE CODE - EXPECTED 400, FOUND " + clientResponse.getStatus();
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			actions.successReport("CreateMerchantSubaccount", "Verify CreateMerchantSubaccount functionality with Password Blank");
			actions.successReport("Sending URL ..+POST method+..  ", url);
			
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Password Blank  ...", ""+jsonStr);
				actions.successReport("Expecting  Http Response code is 400", "Http Response code is ..."+clientResponse.getStatus());
				Assert.assertEquals( HttpURLConnection.HTTP_BAD_REQUEST, clientResponse.getStatus(), httpMessage );
				actions.successReport("Error field : "+respObj.getHdr().getFieldName(),"Error key : "+respObj.getHdr().getErrorKey());
				actions.successReport("DataValidationError", "Http Response is .." +resp);
				actions.assertTrue(data.get("ErrorKey").contains(respObj.getHdr().getErrorKey()), "Error validation:");
				actions.successReport("Response from Server - Success", resp);
				
			}

			else {
				actions.failureReport("Expecting Http Response code is 400","Http Response code is ...." +clientResponse.getStatus());
	
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
	 * This method is used to to test  Merchant Subaccount - Delete Merchant Subaccount
	 * @param data
	 * @param actions
	 */
	public static void DeleteMerchantSubaccount(Hashtable<String, String> data, RESTActions actions) {

		try {
			
			// Create URL
			//String url = "http://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/CMS000001000/user";
			String url = "https://lab7319.ctsservice.com/nis/retailapi/v1/customer/CMS000001000/user/"+ userId;
			LOG.info("URL: " + url);
			
		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Get request to get existing customer details
			clientResponse = actions.getClientResponse(url, BackOfficeUtils.createRESTHeader(RESTConstants.APPLICATION_JSON), null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			String httpMessage = "WRONG HTTP RESPONSE CODE - EXPECTED 200, FOUND " + clientResponse.getStatus();
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			actions.successReport("DeleteMerchantSubaccount", "Verify DeleteMerchantSubaccount functionality");
			actions.successReport("Sending URL ..+Get method+..  ", url);
			
			if (HttpURLConnection.HTTP_OK == clientResponse.getStatus()) {

				actions.successReport("Expecting  Http Response code is 200", "Http Response code is ..."+clientResponse.getStatus());
				Assert.assertEquals( HttpURLConnection.HTTP_OK, clientResponse.getStatus(), httpMessage );
				actions.successReport("Response from Server - Success", resp);
				
			}

			else {
				actions.failureReport("Expecting Http Response code is 200","Http Response code is ...." +clientResponse.getStatus());
	
			}
			
			/***  Delete Merchant Subaccount **/
			String urlForTerminate = "https://lab7319.ctsservice.com/nis/retailapi/v1/customer/CMS000001000/user/"+ userId+ "/status/terminate";
			LOG.info("URL: " + urlForTerminate);
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
								
			// Make HTTP Get request to get existing customer details
			clientResponse = actions.putClientResponse(urlForTerminate, jsonStr,BackOfficeUtils.createRESTHeader(RESTConstants.APPLICATION_JSON), null,RESTConstants.APPLICATION_JSON);
			//resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
		
			// De-serialize the Response into a JSON Object
			Gson gs = new Gson();
			respObj = gs.fromJson(resp, jsonClass); 
			actions.successReport("DeleteMerchantSubaccount", "Verify DeleteMerchantSubaccount functionality - with Delete Merchant Subaccount");
			actions.successReport("Sending URL ..+Put method+..  ", urlForTerminate);
			int status = clientResponse.getStatus();
			if (HttpURLConnection.HTTP_NO_CONTENT == status) {

				actions.successReport("Expecting  Http Response code is 204", "Http Response code is ..."+clientResponse.getStatus());
				actions.successReport("Response from Server - Success", resp);
				
			}

			else {
				actions.failureReport("Expecting Http Response code is 204","Http Response code is ...." +clientResponse.getStatus());
	
			}
			
			/** Verify firstName and lastName are returned per PATCH changes ***/
			// Create URL
			//String url = "http://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/CMS000001000/user";
			String urlAfterPutchanges = "https://lab7319.ctsservice.com/nis/retailapi/v1/customer/CMS000001000/user/" + userId;
			LOG.info("URL: " + urlAfterPutchanges);
			
		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Get request to get existing customer details
			clientResponse = actions.getClientResponse(urlAfterPutchanges,BackOfficeUtils.createRESTHeader(RESTConstants.APPLICATION_JSON), null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			String httpMessageAfterPutChanges = "WRONG HTTP RESPONSE CODE - EXPECTED 200, FOUND " + clientResponse.getStatus();
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson gn = new Gson();
			respObj = gn.fromJson(resp, jsonClass); 
						
			actions.successReport("DeleteMerchantSubaccount", "Verify DeleteMerchantSubaccount functionality after Put changes  :"+clientResponse.getStatus());
			actions.successReport("Sending URL ..+Get method+..  ", urlAfterPutchanges);
			if (HttpURLConnection.HTTP_OK == clientResponse.getStatus()) {

				actions.successReport("Expecting  Http Response code is 200", "Http Response code is ..."+clientResponse.getStatus());
				Assert.assertEquals( HttpURLConnection.HTTP_OK, clientResponse.getStatus(), httpMessageAfterPutChanges );
				actions.successReport("Verify firstName and lastName are returned per PUT changes",resp);
				actions.successReport("Response from Server - Success", resp);
				
			}

			else {
				actions.failureReport("Expecting Http Response code is 200","Http Response code is ...." +clientResponse.getStatus());
	
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
	 * This method is used to to test  Merchant Subaccount - Delete Merchant Subaccount -  Invalid status action
	 * @param data
	 * @param actions
	 */
	public static void DeleteMerchantSubaccountWithInvalidStatus(Hashtable<String, String> data, RESTActions actions) {

		try {
			
			// Create new User id
			jsonObj = merchantSubaccountRequest(data);
			String urlForUserId = "https://lab7319.ctsservice.com"+"/nis/retailapi/v1/customer/CMS000001000/user";
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to Create Merchant Subaccount
			clientResponse = actions.postClientResponse(urlForUserId, jsonStr,BackOfficeUtils.createRESTHeader(RESTConstants.APPLICATION_JSON), null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			userId = respObj.getUserId();
			
			
			
			// Create URL
			//String url = "http://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/CMS000001000/user";
			String url = "https://lab7319.ctsservice.com/nis/retailapi/v1/customer/CMS000001000/user/"+userId;
			LOG.info("URL: " + url);
									
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Get request to get existing customer details
			clientResponse = actions.getClientResponse(url,BackOfficeUtils.createRESTHeader(RESTConstants.APPLICATION_JSON), null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			String httpMessage = "WRONG HTTP RESPONSE CODE - EXPECTED 200, FOUND " + clientResponse.getStatus();
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson gs = new Gson();
			respObj = gs.fromJson(resp, jsonClass); 
			actions.successReport("DeleteMerchantSubaccount", "Verify DeleteMerchantSubaccount functionality with Invalid status action");
			actions.successReport("Sending URL ..+Get method+..  ", url);
			
			if (HttpURLConnection.HTTP_OK == clientResponse.getStatus()) {

				actions.successReport("Expecting  Http Response code is 200", "Http Response code is ..."+clientResponse.getStatus());
				Assert.assertEquals( HttpURLConnection.HTTP_OK, clientResponse.getStatus(), httpMessage );
				actions.successReport("Response from Server - Success", resp);
				
			}

			else {
				actions.failureReport("Expecting Http Response code is 200","Http Response code is ...." +clientResponse.getStatus());
	
			}
			
			/***  Delete Merchant Subaccount **/
			String urlForDelete = "https://lab7319.ctsservice.com/nis/retailapi/v1/customer/CMS000001000/user/"+ userId + "/status/delete";
			LOG.info("URL: " + urlForDelete);
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Get request to get existing customer details
			clientResponse = actions.putClientResponse(urlForDelete, jsonStr,BackOfficeUtils.createRESTHeader(RESTConstants.APPLICATION_JSON), null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			String httpMessageForDelete = "WRONG HTTP RESPONSE CODE - EXPECTED 400, FOUND " + clientResponse.getStatus();
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson gso = new Gson();
			respObj = gso.fromJson(resp, jsonClass); 
			actions.successReport("DeleteMerchantSubaccount", "Verify DeleteMerchantSubaccount functionality - with Delete Merchant Subaccount with Invalid Status");
			actions.successReport("Sending URL ..+Put method+..  ", urlForDelete);
			
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Expecting  Http Response code is 400", "Http Response code is ..."+clientResponse.getStatus());
				Assert.assertEquals( HttpURLConnection.HTTP_BAD_REQUEST, clientResponse.getStatus(), httpMessageForDelete );
				actions.successReport("Error field : "+respObj.getHdr().getFieldName(),"Error key : "+respObj.getHdr().getErrorKey());
				actions.assertTrue(data.get("ErrorKey").equals(respObj.getHdr().getErrorKey()), "Error validation:");
				actions.successReport("Response from Server - Success", resp);
				
			}

			else {
				actions.failureReport("Expecting Http Response code is 400","Http Response code is ...." +clientResponse.getStatus());
	
			}
			
			/** Verify firstName and lastName are unchanged per PUT changes ***/
			// Create URL
			//String url = "http://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/CMS000001000/user";
			String urlAfterPutchanges = "https://lab7319.ctsservice.com/nis/retailapi/v1/customer/CMS000001000/user/" + userId;
			LOG.info("URL: " + urlAfterPutchanges);
			
		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Get request to get existing customer details
			clientResponse = actions.getClientResponse(urlAfterPutchanges, BackOfficeUtils.createRESTHeader(RESTConstants.APPLICATION_JSON), null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			String httpMessageAfterPutChanges = "WRONG HTTP RESPONSE CODE - EXPECTED 200, FOUND " + clientResponse.getStatus();
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson gn = new Gson();
			respObj = gn.fromJson(resp, jsonClass); 
			actions.successReport("DeleteMerchantSubaccount", "Verify DeleteMerchantSubaccount functionality after Put changes");
			actions.successReport("Sending URL ..+Get method+..  ", urlAfterPutchanges);
			
			if (HttpURLConnection.HTTP_OK == clientResponse.getStatus()) {

				actions.successReport("Expecting  Http Response code is 200", "Http Response code is ..."+clientResponse.getStatus());
				Assert.assertEquals( HttpURLConnection.HTTP_OK, clientResponse.getStatus(), httpMessageAfterPutChanges );
				actions.successReport("Response from Server - Success", resp);
				
			}

			else {
				actions.failureReport("Expecting Http Response code is 200","Http Response code is ...." +clientResponse.getStatus());
	
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
	
	
		
	/*This method is used to to test Create Merchant Subaccount with valid test data */
	private static CreateMerchantSubaccountRequest merchantSubaccountRequest(Hashtable<String,String> data) {
		
		CreateMerchantSubaccountRequest jsonObj = new CreateMerchantSubaccountRequest();
		jsonObj.setUserType(data.get("userType"));
		jsonObj.setUsername(BackOfficeUtils.generateRandomString(4));
		jsonObj.setPassword(data.get("password"));
		WSName name = new WSName();
		name.setFirstName(data.get("firstName"));
		name.setLastName(data.get("lastName"));
		jsonObj.setName(name);
		return jsonObj;
	}	
	

}
