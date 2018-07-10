package com.cubic.nisjava.utils;

import java.io.IOException;
import java.rmi.server.UID;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.UUID;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.JsonMappingException;

import com.cubic.accelerators.RESTActions;
import com.cubic.accelerators.RESTConstants;
import com.cubic.backoffice.constants.BackOfficeGlobals;
import com.cubic.backoffice.utils.BackOfficeUtils;
import com.cubic.nisjava.apiobjects.MerchantLogin;
import com.cubic.nisjava.apiobjects.OptionalData;
import com.cubic.nisjava.apiobjects.WSAddressExt;
import com.cubic.nisjava.apiobjects.WSCustomerRegisterRequest;
import com.cubic.nisjava.apiobjects.WSName;
import com.cubic.nisjava.apiobjects.WSPatronAuthenticateRequest;
import com.cubic.nisjava.apiobjects.WSPhone;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.ClientResponse;

/**
 * A Utility class used to create test data for sending Requests to NIS.

 * 
 */

public class DataUtils {

	static {
		BackOfficeGlobals.ENV.setEnvironmentVariables();
	}
	public static final String CLASS_NAME = "DataUtils";
	private static final Logger LOG = Logger.getLogger(CLASS_NAME);
	public static String url = "http://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/nwapi/v1.1/patron/";
	public static String uid = new UID().toString();
	public static Hashtable<String, String> headerMap = BackOfficeUtils.nisPatchHeaderWithUid(uid);
	static ObjectMapper mapper =  new ObjectMapper();

	/*
	 * To generate Random email
	 */
	public static String randomEmailString() {
		String randomEmail = BackOfficeUtils.generateRandomString(8)
				+ BackOfficeGlobals.BACKOFFICE_DEFAULT_EMAIL_ADDRESS_PROVIDER;
		return randomEmail;
	}

	/*
	 * To generate Random UserName
	 */
	public static String randomUsernameString() {
		String randomUsername = BackOfficeUtils.generateRandomString(4);
		return randomUsername;
	}

	/*
	 * To create Patron Account
	 */
	public static WSCustomerRegisterRequest createPatronAccount(Hashtable<String, String> data) {

		WSCustomerRegisterRequest jsonObj = null;
		jsonObj = new WSCustomerRegisterRequest();
		WSAddressExt address = new WSAddressExt();
		address.setAddress1(data.get("Address1"));
		address.setAddress2(data.get("Address2"));
		address.setCity(data.get("City"));
		address.setCountry(data.get("Country"));
		address.setPostalCode(data.get("PostalCode"));
		address.setState(data.get("State"));
		jsonObj.setAddress(address);
		jsonObj.setDateOfBirth(data.get("DateOfBirth"));
		jsonObj.setEmail(DataUtils.randomEmailString());
		jsonObj.setUsername(DataUtils.randomUsernameString());
		jsonObj.setPassword(data.get("Password"));
		OptionalData optionalData = new OptionalData();
		jsonObj.setSecurityAnswer(data.get("SecurityAnswer"));
		jsonObj.setSecurityQuestion(data.get("SecurityQuestion"));
		jsonObj.setOptionalData(optionalData);
		WSPhone phone = new WSPhone();
		phone.setCountry(data.get("Country"));
		phone.setNumber(data.get("Phone"));
		phone.setType("M");
		jsonObj.getPhone().add(phone);
		WSName name = new WSName();
		name.setFirstName(data.get("FirstName"));
		name.setLastName(data.get("LastName"));
		jsonObj.setName(name);
		return jsonObj;

	}

	/*
	 * To verify Patron Authentication
	 */
	public static WSPatronAuthenticateRequest patronAuthenticate(String Username, String password) {
		WSPatronAuthenticateRequest jsonObj = new WSPatronAuthenticateRequest();
		jsonObj.setUsername(Username);
		jsonObj.setPassword(password);
		return jsonObj;
	}

	/*
	 * To verify Patron Authentication
	 */
	public static MerchantLogin patronAuthenticate(String Username, String password, String deviceSerialNumber) {
		MerchantLogin jsonObj = new MerchantLogin();
		jsonObj.setUsername(Username);
		jsonObj.setPassword(password);
		jsonObj.setDeviceSerialNumber(deviceSerialNumber);
		return jsonObj;
	}

	/*
	 * To verify Patron Authentication Without DeviceSerialNumber
	 */
	public static MerchantLogin patronAuthenticateWithoutDeviceSerialNumber(String Username, String password) {
		MerchantLogin jsonObj = new MerchantLogin();
		jsonObj.setUsername(Username);
		jsonObj.setPassword(password);
		return jsonObj;
	}
	
	/*
	 * Method to validate the response code against the expected response code from Client response
	 */
	public static boolean validateResponseCode(RESTActions restActions, String expectedResponseCode, ClientResponse clientResponse)
	{
		int status = clientResponse.getStatus();					
		LOG.info("Http Status is ... "+ status);
		restActions.successReport("Http Response Status Code: ", ""+status);
		int statusExpected = Integer.parseInt(expectedResponseCode);
		String msg = "HTTP RESPONSE CODE - EXPECTED "+expectedResponseCode+", FOUND " + status;
		restActions.assertTrue(status == statusExpected, msg);
		if(status == statusExpected)
		{
			return true;
		}
		else
		{
			return false;
		}
	}	
	
	/*
	 * Method to create Request Header for RetailAPI
	 */
	public static Hashtable<String,String> getHeaderForRetailApi()
	{
		String uid = "";				
		// Populate the Header Values
		uid = UUID.randomUUID().toString();
		Hashtable<String,String> header = new Hashtable<>();
		String sXCubHdr = String.format("{ \"uid\": \"%s\", \"device\": \"%s\" }", uid, BackOfficeGlobals.BACKOFFICE_XCUBHDR_DEV);
		header.put(BackOfficeGlobals.BACKOFFICE_XCUBHDR_NAME, sXCubHdr);
		header.put(BackOfficeGlobals.BACKOFFICE_AUTHORIZATION_HDR_NAME, BackOfficeGlobals.BACKOFFICE_AUTHORIZATION_HDR_VALUE);
		header.put(BackOfficeGlobals.BACKOFFICE_CONTENT_TYPE, RESTConstants.APPLICATION_JSON);
		header.put(BackOfficeGlobals.BACKOFFICE_AUTHORIZATION_HDR_NAME, BackOfficeGlobals.BACKOFFICE_AUTHORIZATION_HDR_VALUE);				
		return header;  			                
	}
	/*
	 * Method to get the current date in ISO8601 in String format
	 */
	public static String getCurrentDateInISO8601Format()
	{
		Date javaUtilDate= new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss");
		return formatter.format(javaUtilDate);
	}	
	/**
	 * @param resp
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public static void printResponseWithPrettyPrinter(String resp) throws JsonParseException, JsonMappingException, IOException 
	{
		  Object jsonObject = mapper.readValue(resp, Object.class);
	      LOG.info("API Response: \n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonObject));		
	}	
	/**
	 * Method to verify Response object attribute as Integer value and for Null values
	 * @param restActions
	 * @param string
	 * @param string
	 */
	public static void validateResponseIntegerField(RESTActions restActions ,Integer responseFieldValue, String fieldName) 
	{
		validateResponseStringFieldForNullValues(restActions, String.valueOf(responseFieldValue),fieldName);
	}	
	/**
	 * Method to verify Response object attribute as String value and for Null values
	 * @param restActions
	 * @param responseField
	 * @param attribute
	 */
	public static void validateResponseStringFieldForNullValues(RESTActions restActions, String responseField, String attribute) 
	{
		try
		{
			LOG.info(attribute +" : " +responseField);
			if(responseField.isEmpty())
			{
				restActions.failureReport("Validating "+attribute, attribute+" is having Null value i.e., "+responseField);
			}
		}
		catch(Exception e)
		{
			LOG.info(responseField+" : Issue getting info about "+attribute);
		}
	}

	/**
	 * @param restActions
	 * @param expectedFieldValue
	 * @param responseFieldValue
	 * * @param fieldName
	 */
	public static void validateResponseFieldValue(RESTActions restActions, String expectedFieldValue, String responseFieldValue, String fieldName) 
	{
		String message = "Expected "+fieldName+" ::-"+expectedFieldValue+"-:: Found "+fieldName+" from Response is ::-"+responseFieldValue+"-::";
		if(expectedFieldValue.equals(responseFieldValue))
		{
			LOG.info(message);
			restActions.successReport("Validating "+fieldName, message);
		}
		else
		{
			LOG.info(message);
			restActions.failureReport("Validating "+fieldName, message);
		}		
	}
}

	
