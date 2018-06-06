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
import com.cubic.nisjava.apiobjects.WSCustomerRegisterRequest;
import com.cubic.nisjava.apiobjects.WSCustomerRegisterResponse;
import com.cubic.nisjava.utils.DataUtils;
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
	//static Hashtable<String, String> headerMap = null;
	static String jsonStr = "";
	static String resp = "";
	
	/*
	 * This method is used to to test Create Merchant Subaccount with valid test data 
	 * @param data
	 * @param actions
	 */
	public static void CreateMerchantSubaccount(Hashtable<String, String> data, RESTActions actions) {

		try {
			
			// Create URL
			//String url = "http://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/CMS000001000/user";
			String url = "https://lab7319.ctsservice.com"+"/nis/retailapi/v1/customer/CMS000001000/user";
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

			if (HttpURLConnection.HTTP_OK == clientResponse.getStatus()) {

				actions.successReport("Sending Http request with valid data ...", ""+jsonStr);
				Assert.assertEquals( HttpURLConnection.HTTP_OK, clientResponse.getStatus(), httpMessage );
				actions.successReport("Expecting  Http Response code is 200", "Http Response code is ..."+clientResponse.getStatus());
				actions.successReport("Success", "Create Merchant Subaccount .." +respObj.getUserId());
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
