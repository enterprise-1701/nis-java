package com.cubic.nisjava.api;

import java.net.HttpURLConnection;
import java.util.Hashtable;
import java.util.List;

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
import com.cubic.nisjava.apiobjects.Device;
import com.cubic.nisjava.apiobjects.MerchantDeviceConfigResponse;
import com.cubic.nisjava.utils.DataUtils;
import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;

/**
 * Merchant Device Configuration
  *  Author:Vijaya Bhaskar Palem
 */
public class MerchantDeviceConfigurationGET {
	
	public static final String CLASS_NAME = "MerchantDeviceConfigurationGET";
	private static final Logger LOG = Logger.getLogger(CLASS_NAME);
	static ClientResponse clientResponse;
	static MerchantDeviceConfigResponse respObj = null;
	static Class<MerchantDeviceConfigResponse> jsonClass = 	MerchantDeviceConfigResponse.class;
	static String jsonStr = "";
	static String resp = "";
	static String deviceId = "";
	static 
	{
		BackOfficeGlobals.ENV.setEnvironmentVariables();
	}
	//static String url = "https://lab7319.ctsservice.com/nis/retailapi/v1/customer/";
				
	/*
	 * This method is used to verify Retrieve Device Configuration 
	 * @param data
	 * @param actions
	 */
	
	public static void retrieveDeviceConfiguration(Hashtable<String, String> data, RESTActions actions) {

		try {
			
			// Create URL
			String CustomerId = data.get("CustomerId"); 
			//url= "https://lab7319.ctsservice.com/nis/retailapi/v1/customer/";
			String url = "https://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/";
			url = url +CustomerId+ "/device";
			LOG.info("URL: " + url);
			
			// Make HTTP Get request to Retrieve Device Configuration
			clientResponse(actions,url);
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			gsonDeserializeResp(); 
			
			// verify http response and verify Retrieve Device Configuration
			String httpMessage = "WRONG HTTP RESPONSE CODE - EXPECTED 200, FOUND " + clientResponse.getStatus();
			actions.successReport("Merchant Device Configuration", "Verify Device Configuration is retrived corretly");
			actions.successReport("Sending URL ..+Get method+..  ", url);
			
			 List<Device> devices = respObj.getDevices();
			if (HttpURLConnection.HTTP_OK == clientResponse.getStatus()) {

				Assert.assertEquals( HttpURLConnection.HTTP_OK, clientResponse.getStatus(), httpMessage );
				actions.successReport("Expecting  Http Response code is 200", "Http Response code is ..."+clientResponse.getStatus());
				for (int i = 0; i<devices.size();i++){
					DataUtils.validateResponseStringFieldForNullValues(actions, devices.get(i).getDeviceId(), data.get("DeviceID"));
					DataUtils.validateResponseIntegerField(actions,devices.get(i).getLocation().getLocationId(), data.get("LocationID"));
					DataUtils.validateResponseStringFieldForNullValues(actions,devices.get(i).getDeviceSerialNumber(), data.get("deviceSerialNbr"));
					DataUtils.validateResponseStringFieldForNullValues(actions,devices.get(i).getNickName(), data.get("Nickname"));
					DataUtils.validateResponseStringFieldForNullValues(actions,devices.get(i).getStatus(), data.get("Status"));
				}
			     actions.successReport("Response from Server","Retrieving Device Configuration : All list of devices is returned with the following attributes,deviceId,location,deviceSerialNbr,nickname (optional),status: "+ resp);
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

		// verify  device object is returned with the attributes
		deviceId = respObj.getDevices().get(0).getDeviceId();
		//url = "https://lab7319.ctsservice.com/nis/retailapi/v1/customer/";
		String url = "https://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/";
		String CustomerId = data.get("CustomerId");
		url = url +  CustomerId + "/device/"+ deviceId;
		LOG.info("URL: " + url);
		verifyDeviceIdAttributes(data,actions,url);
	}
	
	/*
	 * This method is used to verify  device object is returned with the attributes 
	 * @param data
	 * @param actions
	 */
	public static void verifyDeviceIdAttributes(Hashtable<String, String> data, RESTActions actions,String url) {

		try {
			
											
			// Make HTTP Get request to Retrieve Device Configuration
			clientResponse(actions,url);
			LOG.info("Response: \n" + resp);
			
			// De-serialize the Response into a JSON Object
			gsonDeserializeResp(); 
			
			//verify http response and verify  device object is returned with the attributes 
			String httpMessage = "WRONG HTTP RESPONSE CODE - EXPECTED 200, FOUND " + clientResponse.getStatus();
			actions.successReport("Merchant Device ID Attributes", "Verify device object is returned with the following attributes ");
			actions.successReport("Sending URL ..+Get method+..  ", url);
			if (HttpURLConnection.HTTP_OK == clientResponse.getStatus()) {

				Assert.assertEquals( HttpURLConnection.HTTP_OK, clientResponse.getStatus(), httpMessage );
				actions.successReport("Expecting  Http Response code is 200", "Http Response code is ..."+clientResponse.getStatus());
				actions.successReport("Response from Server","Device object is returned with the following attributes:  "+ resp);
			
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
	 * This method is used to verify  Retrieve Device Configuration - customerId not found 
	 * @param data
	 * @param actions
	 */
	public static void verifyDeviceConfigWithcustomerNotFound(Hashtable<String, String> data, RESTActions actions) {

		try {
			
			// Create URL
			//String url = "https://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/CMS000001000/user";
			String Invalid_CustomerId = data.get("Invalid_CustomerId");
			//url = "https://lab7319.ctsservice.com/nis/retailapi/v1/customer/"+Invalid_CustomerId+"/device/";
			String url = "https://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/"+Invalid_CustomerId+"/device/";
			
			// Make HTTP Get request to Retrieve Device Configuration
			clientResponse(actions,url);
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			gsonDeserializeResp(); 
			LOG.info("Response: \n" + resp);
						
			String httpMessage = "WRONG HTTP RESPONSE CODE - EXPECTED 200, FOUND " + clientResponse.getStatus();
			actions.successReport("Retrieve Device Configuration - customerId not found", "verify  Retrieve Device Configuration - customerId not found");
			actions.successReport("Sending URL ..+Get method+..  ", url);
			if (HttpURLConnection.HTTP_NOT_FOUND == clientResponse.getStatus()) {

				Assert.assertEquals( HttpURLConnection.HTTP_NOT_FOUND, clientResponse.getStatus(), httpMessage );
				actions.successReport("Expecting  Http Response code is 404", "Http Response code is ..."+clientResponse.getStatus());
				actions.successReport("Error field : "+respObj.getHdr().getFieldName(),"Error key : "+respObj.getHdr().getErrorKey());
				actions.assertTrue(data.get("ErrorKey").equals(respObj.getHdr().getErrorKey()), "Error validation:");
				actions.successReport("Response from Server","Retrieve Device Configuration - customerId not found :"+ resp);
							
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
	 * This method is used to verify  Retrieve Device Configuration - No devices returned 
	 * @param data
	 * @param actions
	 */
	public static void verifyDeviceConfigWithNoDevicesReturned(Hashtable<String, String> data, RESTActions actions) {

		try {
			
			// Create URL
			//String url = "https://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/CMS000001000/user";
			String CustomerId = data.get("CustomerId"); 
			//url = "https://lab7319.ctsservice.com/nis/retailapi/v1/customer/"+CustomerId+"/device/";
			String url = "https://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/"+CustomerId+"/device/";
			
			// Make HTTP Get request to Retrieve Device Configuration
			clientResponse(actions,url);
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			gsonDeserializeResp(); 
			LOG.info("Response: \n" + resp);
			
			List<Device> devices = respObj.getDevices();
			String httpMessage = "WRONG HTTP RESPONSE CODE - EXPECTED 200, FOUND " + clientResponse.getStatus();
			actions.successReport("Retrieve Device Configuration - No devices returned", "verify  Retrieve Device Configuration - No devices returned");
			actions.successReport("Sending URL ..+Get method+..  ", url);
			if (HttpURLConnection.HTTP_OK == clientResponse.getStatus()) {

				Assert.assertEquals( HttpURLConnection.HTTP_OK, clientResponse.getStatus(), httpMessage );
				actions.successReport("Expecting  Http Response code is 200", "Http Response code is ..."+clientResponse.getStatus());
				
				actions.successReport("Response from Server","Retrieve Device Configuration - No devices returned :" +resp);
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
	 * This method is used to verify Retrieve Device Configuration - deviceId not found
	 * @param data
	 * @param actions
	 */
	public static void verifyDeviceConfigurationDeviceNotFound(Hashtable<String, String> data, RESTActions actions) {

		try {
			
			// Create URL
			//String url = "https://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/CMS000001000/user";
			String CustomerId = data.get("CustomerId"); 
			//url = "https://lab7319.ctsservice.com/nis/retailapi/v1/customer/"+CustomerId+"/device";
			String url = "https://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/"	+CustomerId+"/device";		
			// Make HTTP Get request to Retrieve Device Configuration
			clientResponse(actions,url);
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			gsonDeserializeResp(); 
			LOG.info("Response: \n" + resp);
			
			String httpMessage = "WRONG HTTP RESPONSE CODE - EXPECTED 200, FOUND " + clientResponse.getStatus();
			LOG.info("Response: \n" + resp);
			actions.successReport("Merchant Device Configuration", "Verify Device Configuration - deviceId not found ");
			actions.successReport("Sending URL ..+Get method+..  ", url);
			 List<Device> devices = respObj.getDevices();
			 if (HttpURLConnection.HTTP_OK == clientResponse.getStatus()) {

				Assert.assertEquals( HttpURLConnection.HTTP_OK, clientResponse.getStatus(), httpMessage );
				actions.successReport("Expecting  Http Response code is 200", "Http Response code is ..."+clientResponse.getStatus());
				for (int i = 0; i<devices.size();i++){
					DataUtils.validateResponseStringFieldForNullValues(actions, devices.get(i).getDeviceId(), data.get("DeviceID"));
					DataUtils.validateResponseIntegerField(actions,devices.get(i).getLocation().getLocationId(), data.get("LocationID"));
					DataUtils.validateResponseStringFieldForNullValues(actions,devices.get(i).getDeviceSerialNumber(), data.get("deviceSerialNbr"));
					DataUtils.validateResponseStringFieldForNullValues(actions,devices.get(i).getNickName(), data.get("Nickname"));
					DataUtils.validateResponseStringFieldForNullValues(actions,devices.get(i).getStatus(), data.get("Status"));
				}
				actions.successReport("Response from Server","Retrieve Device Configuration - deviceId not found:"+ resp);
		
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

		// verify  device object is returned with the attributes 
		verifyDeviceIdErrorkey(data,actions);
	}
	
	/*
	 * This method is used to verify  device object is returned with the attributes 
	 * @param data
	 * @param actions
	 */
	public static void verifyDeviceIdErrorkey(Hashtable<String, String> data, RESTActions actions) {

		try {
			
					
			// get Device id from previous method of device configuration 
			String invalidDeviceId = data.get("InvalidDeviceID");
			String CustomerId = data.get("CustomerId"); 
			
			// Create URL
			String url = "https://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/"+CustomerId+"/device/"+invalidDeviceId;
			//url = "https://lab7319.ctsservice.com/nis/retailapi/v1/customer/"+CustomerId+"/device/"+invalidDeviceId;
			LOG.info("URL: " + url);
			
			// Make HTTP Get request to Retrieve Device Configuration
			clientResponse(actions,url);
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			gsonDeserializeResp(); 
			LOG.info("Response: \n" + resp);
									
			String httpMessage = "WRONG HTTP RESPONSE CODE - EXPECTED 200, FOUND " + clientResponse.getStatus();
			actions.successReport("Merchant Device ID Error info", "Verify device-id error key");
			actions.successReport("Sending URL ..+Get method+..  ", url);
			if (HttpURLConnection.HTTP_NOT_FOUND == clientResponse.getStatus()) {

				Assert.assertEquals( HttpURLConnection.HTTP_NOT_FOUND, clientResponse.getStatus(), httpMessage );
				actions.successReport("Expecting  Http Response code is 404", "Http Response code is ..."+clientResponse.getStatus());
				actions.successReport("Error field : "+respObj.getHdr().getFieldName(),"Error key : "+respObj.getHdr().getErrorKey());
				actions.assertTrue(data.get("ErrorKey").equals(respObj.getHdr().getErrorKey()), "Error validation:");
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
	 * This method is used to verify  Retrieve Device Configuration Data 
	 * @param data
	 * @param actions
	 */
	public static void verifyDeviceConfigData(Hashtable<String, String> data, RESTActions actions) {

		try {
			
			// Create URL
			String CustomerId = data.get("CustomerId"); 
			String url = "https://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/"+CustomerId+"/settings";
			//url = "https://lab7319.ctsservice.com/nis/retailapi/v1/customer/"+CustomerId+"/settings";
			LOG.info("URL: " + url);
			
			
			// Make HTTP Get request to Retrieve Device Configuration
			clientResponse(actions,url);
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			gsonDeserializeResp(); 
			LOG.info("Response: \n" + resp);
									
			String httpMessage = "WRONG HTTP RESPONSE CODE - EXPECTED 200, FOUND " + clientResponse.getStatus();
			actions.successReport("Retrieve Device Configuration Data", "Verify Device Configuration Data");
			actions.successReport("Sending URL ..+Get method+..  ", url);
			if (HttpURLConnection.HTTP_OK == clientResponse.getStatus()) {

				Assert.assertEquals( HttpURLConnection.HTTP_OK, clientResponse.getStatus(), httpMessage );
				actions.successReport("Expecting  Http Response code is 200", "Http Response code is ..."+clientResponse.getStatus());
				actions.successReport("Response from Server - Success","Retrieve Device Configuration Data :"+ resp);
						
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
	 * This method is used to verify  Retrieve Device Configuration Data - customerId not found
	 * @param data
	 * @param actions
	 */
	public static void verifyDeviceConfigDataCustomerNotFound(Hashtable<String, String> data, RESTActions actions) {

		try {
			
			// Create URL
			
			String Invalid_CustomerId = data.get("Invalid_CustomerId"); 
			String url = "https://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/"+Invalid_CustomerId+"/settings";
			//url = "https://lab7319.ctsservice.com/nis/retailapi/v1/customer/"+Invalid_CustomerId+"/settings";
			LOG.info("URL: " + url);
			
			// Make HTTP Get request to Retrieve Device Configuration
			clientResponse(actions,url);
			LOG.info("Response: \n" + resp);

			// De-serialize the Response into a JSON Object
			gsonDeserializeResp(); 
			LOG.info("Response: \n" + resp);
			
			String httpMessage = "WRONG HTTP RESPONSE CODE - EXPECTED 200, FOUND " + clientResponse.getStatus();
			actions.successReport("Retrieve Device Configuration Data - customerId not found", "Verify Device Configuration Data , customerId not found");
			actions.successReport("Sending URL ..+Get method+..  ", url);
			if (HttpURLConnection.HTTP_NOT_FOUND == clientResponse.getStatus()) {

				Assert.assertEquals( HttpURLConnection.HTTP_NOT_FOUND, clientResponse.getStatus(), httpMessage );
				actions.successReport("Expecting  Http Response code is 404", "Http Response code is ..."+clientResponse.getStatus());
				actions.successReport("Error field : "+respObj.getHdr().getFieldName(),"Error key : "+respObj.getHdr().getErrorKey());
				actions.assertTrue(data.get("ErrorKey").equals(respObj.getHdr().getErrorKey()), "Error validation:");
				actions.successReport("Response from Server - Success","Retrieve Device Configuration Data - customerId not found:"+ resp);
							
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
	
	/**
	 * @param actions
	 * @throws Throwable
	 */
	private static void clientResponse(RESTActions actions,String url) throws Throwable {
		clientResponse = actions.getClientResponse(url,BackOfficeUtils.createRESTHeader(RESTConstants.APPLICATION_JSON), null,null);
		resp = clientResponse.getEntity(String.class);
	}
	
	/**
	 * 
	 */
	private static void gsonDeserializeResp() {
		Gson g = new Gson();
		respObj = g.fromJson(resp, jsonClass);
	}
	
}
