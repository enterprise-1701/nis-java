package com.cubic.nisjava.api;

import java.net.HttpURLConnection;
import java.util.Hashtable;
import org.apache.log4j.Logger;
import org.testng.Assert;
import com.cubic.accelerators.RESTActions;
import com.cubic.backoffice.utils.BackOfficeUtils;
import com.cubic.logutils.Log4jUtil;
import com.cubic.nisjava.apiobjects.WSAddressExt;
import com.cubic.nisjava.apiobjects.WSCustomerRegisterRequest;
import com.cubic.nisjava.apiobjects.WSCustomerRegisterResponse;
import com.cubic.nisjava.apiobjects.WSName;
import com.cubic.nisjava.apiobjects.WSPhone;
import com.cubic.nisjava.utils.DataUtils;
import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;

/**
 * Patron Register
 * Implements Create Patron account POST Request via NextWave API
 *  Author:Vijaya Bhaskar Palem
 */

public class PatronRegisterPost {


	
	static String url = DataUtils.url+"register";
	public static final String CLASS_NAME = "PatronRegisterPost";
	private static final Logger LOG = Logger.getLogger(CLASS_NAME);
	static ClientResponse clientResponse;
	static WSCustomerRegisterRequest  jsonObj = null;	
	static WSCustomerRegisterResponse respObj = null;
	static Class<WSCustomerRegisterResponse> jsonClass = 	WSCustomerRegisterResponse.class;
	//static Hashtable<String, String> headerMap = null;
	static String jsonStr = "";
	static String resp = "";
	
	/*
	 * This method is used to to test patron Account with valid test data 
	 * @param data
	 * @param actions
	 */
	public static void createPatronAccount(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object
			jsonObj = DataUtils.createPatronAccount(data);

			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
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
				actions.successReport("Success", "Patron Account is Created .." +respObj.getpatronAccountId());
				actions.successReport("Success", "Patron Account is Created .." +resp);
			}

			else {
				actions.failureReport("Expecting Http Response code is 200","Http Response code is ...." +clientResponse.getStatus());
				actions.failureReport("Error field : "+respObj.getHdr().getFieldName(),"Error key : "+respObj.getHdr().getErrorKey());

			}


		}
		catch (Exception e) {
			LOG.error(Log4jUtil.getStackTrace(e));
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}

	}	

	/*
	 * This method is used to to test patron Account with Invalid Username 
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithInvalidUsername(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with invalid Username
			jsonObj = DataUtils.createPatronAccount(data);
			//jsonObj.setUsername("");
			jsonObj.setUsername(data.get("Invalid_Username"));
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);

			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 

			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with invalid User Name ", ""+jsonStr);
				actions.successReport("Expecting  Http Response code is 400", "Http Response code is ..."+clientResponse.getStatus());
				actions.successReport("Error field : "+respObj.getHdr().getFieldName(),"Error key : "+respObj.getHdr().getErrorKey());
				actions.successReport("DataValidationError", "Http Response is .." +resp);
				actions.successReport("Eexpcted error message :"+data.get("InvalidUsername_errorMessage"), "Actual Error Message :"+respObj.getHdr().getErrorKey());
				Assert.assertEquals(data.get("InvalidUsername_errorMessage"), respObj.getHdr().getErrorKey() );
			}

			else {
				actions.failureReport("Expecting Http Response code is 400","Http Response code is ...." +clientResponse.getStatus());
				actions.failureReport("Error field : "+respObj.getHdr().getFieldName(),"Error key : "+respObj.getHdr().getErrorKey());
			}

		}
		catch (Exception e) {
			LOG.error(Log4jUtil.getStackTrace(e));
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}

	}	
	
	/*
	 * This method is used to to test patron Account with password - Maximum length exceeded 
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithInvalidPassword(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with password - Maximum length exceeded 
			jsonObj = DataUtils.createPatronAccount(data);
			jsonObj.setPassword(data.get("Password_Max_length"));

			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);

			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 

			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Password Max length exceeded ", ""+jsonStr);
				actions.successReport("Expecting  Http Response code is 400", "Http Response code is ..."+clientResponse.getStatus());
				actions.successReport("Error field : "+respObj.getHdr().getFieldName(),"Error key : "+respObj.getHdr().getErrorKey());
				actions.successReport("DataValidationError", "Http Response is .." +resp);
				actions.successReport("Eexpcted error message :"+data.get("Password_Max_length_errorMessage"), "Actual Error Message :"+respObj.getHdr().getErrorKey());
				Assert.assertEquals(data.get("Password_Max_length_errorMessage"), respObj.getHdr().getErrorKey() );
			}

			else {
				actions.failureReport("Expecting Http Response code is 400","Http Response code is ...." +clientResponse.getStatus());
				actions.failureReport("Error field : "+respObj.getHdr().getFieldName(),"Error key : "+respObj.getHdr().getErrorKey());
			}
		}
		catch (Exception e) {
			LOG.error(Log4jUtil.getStackTrace(e));
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}

	}
	
	
	/*
	 * This method is used to to test patron Account with Name - blank 
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithNameBlank(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with Name Blank
			jsonObj = DataUtils.createPatronAccount(data);
			jsonObj.setName(null);

			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);

			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 

			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Blank Name", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}

	}
		
	/*
	 * This method is used to to test patron Account with First name - blank
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithFirstNameBlank(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with FirstName Blank
			jsonObj = DataUtils.createPatronAccount(data);
			WSName name = new WSName();
	 		name.setFirstName(data.get("Firstname_blank"));
	 		jsonObj.setName(name);

			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);

			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 

			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with First Name Blank", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}

	}
		
	/*
	 * This method is used to to test patron Account with First name - too long
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithFirstNametoolong(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with FirstName Blank
			jsonObj = DataUtils.createPatronAccount(data);
			WSName name = new WSName();
	 		name.setFirstName(data.get("Firstname_toolong"));
	 		jsonObj.setName(name);

			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);

			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 

			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with first Name too lang ", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}

	}
		
	/*
	 * This method is used to to test patron Account with Last name - blank
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithLastNameBlank(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with LastName Blank
			jsonObj = DataUtils.createPatronAccount(data);
			WSName name = new WSName();
			name.setFirstName(data.get("FirstName"));
	 		name.setLastName("");
	 		jsonObj.setName(name);
	 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Blank Last Name", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
			

	}

	/*
	 * This method is used to to test patron Account with Last name - too long
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithLastNametoolong(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with LastName Blank
			jsonObj = DataUtils.createPatronAccount(data);
			WSName name = new WSName();
			name.setFirstName(data.get("FirstName"));
	 		name.setLastName(data.get("Lastname_toolong"));
	 		jsonObj.setName(name);
	 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Last Name too lang", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}
		
	/*
	 * This method is used to to test patron Account with Address is Blank
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithAddressBlank(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with Address is Blank
			jsonObj = DataUtils.createPatronAccount(data);
			WSAddressExt address = new WSAddressExt();
	 		address.setCity(data.get("City"));
	 		address.setCountry(data.get("Country"));
	 		address.setPostalCode(data.get("PostalCode"));
	 		address.setState(data.get("State"));
	 		jsonObj.setAddress(address);
	 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Address field is blank", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}
	
	/*
	 * This method is used to to test patron Account with Address1 field is Blank
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithAddress1Blank(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with Address1 fields is Blank
			jsonObj = DataUtils.createPatronAccount(data);
			WSAddressExt address = new WSAddressExt();
			address.setAddress1(data.get("Address1_Field_Blank"));
	 		address.setCity(data.get("City"));
	 		address.setCountry(data.get("Country"));
	 		address.setPostalCode(data.get("PostalCode"));
	 		address.setState(data.get("State"));
	 		jsonObj.setAddress(address);
	 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Address1 field is blank", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}
	
	/*
	 * This method is used to to test patron Account with Address1 is too long
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithAddress1toolong(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with Address1 too long
			jsonObj = DataUtils.createPatronAccount(data);
			WSAddressExt address = new WSAddressExt();
			address.setAddress1(data.get("Address1_Field_toolong"));
	 		address.setCity(data.get("City"));
	 		address.setCountry(data.get("Country"));
	 		address.setPostalCode(data.get("PostalCode"));
	 		address.setState(data.get("State"));
	 		jsonObj.setAddress(address);
	 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Address1 too long ", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}
		
	/*
	 * This method is used to to test patron Account with Address2 is too long
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithAddress2toolong(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with Address2 too long
			jsonObj = DataUtils.createPatronAccount(data);
			WSAddressExt address = new WSAddressExt();
			address.setAddress1(data.get("Address1"));
			address.setAddress2(data.get("Address2_Field_toolong"));
	 		address.setCity(data.get("City"));
	 		address.setCountry(data.get("Country"));
	 		address.setPostalCode(data.get("PostalCode"));
	 		address.setState(data.get("State"));
	 		jsonObj.setAddress(address);
	 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Address2 too long", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}
		
	/*
	 * This method is used to to test patron Account with City is blank 
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithCityBlank(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with City is blank
			jsonObj = DataUtils.createPatronAccount(data);
			WSAddressExt address = new WSAddressExt();
			address.setAddress1(data.get("Address1"));
			address.setAddress2(data.get("Address2"));
	 		address.setCity("");
	 		address.setCountry(data.get("Country"));
	 		address.setPostalCode(data.get("PostalCode"));
	 		address.setState(data.get("State"));
	 		jsonObj.setAddress(address);
	 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with City field is blank", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}
	
	/*
	 * This method is used to to test patron Account with City is too long 
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithCitytoolong(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with city too long
			jsonObj = DataUtils.createPatronAccount(data);
			WSAddressExt address = new WSAddressExt();
			address.setAddress1(data.get("Address1"));
			address.setAddress2(data.get("Address2"));
	 		address.setCity(data.get("City_toolong"));
	 		address.setCountry(data.get("Country"));
	 		address.setPostalCode(data.get("PostalCode"));
	 		address.setState(data.get("State"));
	 		jsonObj.setAddress(address);
	 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with City field is too long", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}
		
	/*
	 * This method is used to to test patron Account with State is Blank 
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithStateBlank(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with State is Blank
			jsonObj = DataUtils.createPatronAccount(data);
			WSAddressExt address = new WSAddressExt();
			address.setAddress1(data.get("Address1"));
			address.setAddress2(data.get("Address2"));
			address.setCity(data.get("City"));
	 		address.setCountry(data.get("Country"));
	 		address.setPostalCode(data.get("PostalCode"));
	 		address.setState("");
	 		jsonObj.setAddress(address);
	 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with State field is Blank", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}
			
	/*
	 * This method is used to to test patron Account with Invalid State 
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithInvalidState(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with Invalid State
			jsonObj = DataUtils.createPatronAccount(data);
			WSAddressExt address = new WSAddressExt();
			address.setAddress1(data.get("Address1"));
			address.setAddress2(data.get("Address2"));
			address.setCity(data.get("City"));
	 		address.setCountry(data.get("Country"));
	 		address.setPostalCode(data.get("PostalCode"));
	 		address.setState(data.get("Invalid_State"));
	 		jsonObj.setAddress(address);
	 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Invalid State ", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}
	
	/*
	 * This method is used to to test patron Account with Country blank
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithCountryBlank(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with Country Blank
			jsonObj = DataUtils.createPatronAccount(data);
			WSAddressExt address = new WSAddressExt();
			address.setAddress1(data.get("Address1"));
			address.setAddress2(data.get("Address2"));
			address.setCity(data.get("City"));
	 		address.setCountry("");
	 		address.setPostalCode(data.get("PostalCode"));
	 		address.setState(data.get("State"));
	 		jsonObj.setAddress(address);
	 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
	 				
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Country Blank ", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}

	/*
	 * This method is used to to test patron Account with Invalid CountryID
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithInvalidCountryId(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with Invalid Country 
			jsonObj = DataUtils.createPatronAccount(data);
			WSAddressExt address = new WSAddressExt();
			address.setAddress1(data.get("Address1"));
			address.setAddress2(data.get("Address2"));
			address.setCity(data.get("City"));
	 		address.setCountry(data.get("Invalid_Country"));
	 		address.setPostalCode(data.get("PostalCode"));
	 		address.setState(data.get("State"));
	 		jsonObj.setAddress(address);
	 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Invalid Country  ", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}

	/*
	 * This method is used to to test patron Account with Postal Code is blank
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithPostalCodeBlank(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with Postal Code is blank
			jsonObj = DataUtils.createPatronAccount(data);
			WSAddressExt address = new WSAddressExt();
			address.setAddress1(data.get("Address1"));
			address.setAddress2(data.get("Address2"));
			address.setCity(data.get("City"));
	 		address.setCountry(data.get("Country"));
	 		address.setPostalCode("");
	 		address.setState(data.get("State"));
	 		jsonObj.setAddress(address);
	 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Postal Code is blank  ", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}
	
	/*
	 * This method is used to to test patron Account with Invalid Postal Code
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithInvalidPostalCode(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with Invalid Postal Code
			jsonObj = DataUtils.createPatronAccount(data);
			WSAddressExt address = new WSAddressExt();
			address.setAddress1(data.get("Address1"));
			address.setAddress2(data.get("Address2"));
			address.setCity(data.get("City"));
	 		address.setCountry(data.get("Country"));
	 		address.setPostalCode(data.get("Invalid_PostalCode"));
	 		address.setState(data.get("State"));
	 		jsonObj.setAddress(address);
	 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Invalid Postal Code  ", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}
	
	/*
	 * This method is used to to test patron Account with Phone is Blank
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithPhoneBlank(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with Phone is Blank
			jsonObj = DataUtils.createPatronAccount(data);
			WSPhone phone = new WSPhone();
	 		jsonObj.getPhone().add(phone);
				 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Phone is blank ", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}
	
	/*
	 * This method is used to to test patron Account with Phone is too long
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithPhonetoolong(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with Phone is too long
			jsonObj = DataUtils.createPatronAccount(data);
			WSPhone phone = new WSPhone();
			phone.setNumber("818-707-1234");
	 		phone.setType("M");
	 		phone.setCountryCode("US");
	 		phone.setNumber("818-707-1234");
	 		phone.setType("M");
	 		phone.setCountryCode("US");
	 		jsonObj.getPhone().add(phone);
	 		WSPhone phone1 = new WSPhone();
	 		phone.setNumber("818-707-5678");
	 		phone.setType("M");
	 		phone.setCountryCode("US");
	 		phone.setNumber("818-707-5678");
	 		phone.setType("M");
	 		phone.setCountryCode("US");
	 		jsonObj.getPhone().add(phone);
	 		jsonObj.getPhone().add(phone1);
				 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Phone is too long ", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}
	
	/*
	 * This method is used to to test patron Account with Phone number is blank 
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithPhoneNumberBlank(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with Phone Number is blank
			jsonObj = DataUtils.createPatronAccount(data);
			WSPhone phone = new WSPhone();
			phone.setNumber("");
	 		phone.setType("M");
	 		phone.setCountryCode("US");
	 		jsonObj.getPhone().add(phone);
	 		
				 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Phone Number is blank ", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}

	/*
	 * This method is used to to test patron Account with Invalid Phone number
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithInvalidPhoneNumber(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with Phone Number is blank
			jsonObj = DataUtils.createPatronAccount(data);
			WSPhone phone = new WSPhone();
			phone.setNumber(data.get("Invalid_PhoneNumber"));
	 		phone.setType("M");
	 		phone.setCountryCode("US");
	 		jsonObj.getPhone().add(phone);
	 		
				 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Invalid Phone Number ", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}

	/*
	 * This method is used to to test patron Account with Phone Type Blank
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithPhoneTypeBlank(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with Phone Type is blank
			jsonObj = DataUtils.createPatronAccount(data);
			WSPhone phone = new WSPhone();
			phone.setNumber(data.get("Phone"));
	 		phone.setType("");
	 		phone.setCountryCode("US");
	 		jsonObj.getPhone().add(phone);
	 		
				 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with  Phone Type is Blank ", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}

	/*
	 * This method is used to to test patron Account with Invalid Phone Type
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithInvalidPhoneType(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with Invalid Phone Type
			jsonObj = DataUtils.createPatronAccount(data);
			WSPhone phone = new WSPhone();
			phone.setNumber(data.get("Phone"));
	 		phone.setType("Z");
	 		phone.setCountryCode("US");
	 		jsonObj.getPhone().add(phone);
	 		
				 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_NOT_FOUND == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Invalid Phone Type ", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}

	/*
	 * This method is used to to test patron Account with Email - too long
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithEmailtoolong(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with Email - too long
			jsonObj = DataUtils.createPatronAccount(data);
			jsonObj.setEmail(data.get("Email_toolong"));
	 		
				 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			
			
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Email - too long", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}

	/*
	 * This method is used to to test patron Account with Email Special characters
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithEmailSpecialChars(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with Email - With Special characters 
			jsonObj = DataUtils.createPatronAccount(data);
			jsonObj.setEmail(data.get("Email_SpecialChars"));
	 		
				 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Email - with Special Characters", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}

	/*
	 * This method is used to to test patron Account with Email Invalid Format
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithEmailInvalidFormat(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with Email - invalidFormat 
			jsonObj = DataUtils.createPatronAccount(data);
			jsonObj.setEmail(data.get("Email_InvalidFormat"));
	 		
				 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Email - Invalid Format", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}

	/*
	 * This method is used to to test patron Account with  Security Answer too long
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithSecurityAnswertoolong(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with Security Answer too long
			jsonObj = DataUtils.createPatronAccount(data);
			jsonObj.setSecurityAnswer(data.get("securityAnswer_toolong"));
	 		
				 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with Security Answer too long", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}

	/*
	 * This method is used to to test patron Account with IVR pin too long
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithIVRPINtoolong(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with IVR pin too long
			jsonObj = DataUtils.createPatronAccount(data);
			jsonObj.setIvrPin(data.get("IVRPin_toolong"));
	 		
				 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with IVR Pin too long", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}
	
	
	/*
	 * This method is used to to test patron Account with IVR pin too small
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithIVRPINtoosmall(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with IVR pin too small
			jsonObj = DataUtils.createPatronAccount(data);
			jsonObj.setIvrPin(data.get("IVRPin_toosmall"));
	 		
				 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with IVR Pin too small", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}
	
	/*
	 * This method is used to to test patron Account with IVR pin Invalid
	 * @param data
	 * @param actions
	 */
	public static void verifyPatronAccountWithIVRPINInvalid(Hashtable<String, String> data, RESTActions actions) {

		try {

			LOG.info("URL: " + url);

			// Build JSON Object with IVR pin Invalid
			jsonObj = DataUtils.createPatronAccount(data);
			jsonObj.setIvrPin(data.get("IVRPin_Invalid"));
	 		
				 		
			// Get JSON String representation of the Object
			jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
			LOG.info("Converted JSON String: " + jsonStr);
			
			// Make HTTP Post request to get Patron AccountID
			clientResponse = actions.postClientResponse(url, jsonStr,DataUtils.headerMap, null,null);
			resp = clientResponse.getEntity(String.class);
			LOG.info("Http Status is ... "+ clientResponse.getStatus());
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			Gson g = new Gson();
			respObj = g.fromJson(resp, jsonClass); 
			
						
			if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with IVR Pin Invalid ", ""+jsonStr);
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
		}
		catch (Throwable t) {
			LOG.error(Log4jUtil.getStackTrace(t));
		}
	
	}
}
