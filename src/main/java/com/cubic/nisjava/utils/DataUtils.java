package com.cubic.nisjava.utils;

import java.rmi.server.UID;
import java.util.Hashtable;
import java.util.Random;

import com.cubic.backoffice.constants.BackOfficeGlobals;
import com.cubic.backoffice.utils.BackOfficeUtils;
import com.cubic.nisjava.apiobjects.OptionalData;
import com.cubic.nisjava.apiobjects.WSAddressExt;
import com.cubic.nisjava.apiobjects.WSCustomerRegisterRequest;
import com.cubic.nisjava.apiobjects.WSName;
import com.cubic.nisjava.apiobjects.WSPatronAuthenticateRequest;
import com.cubic.nisjava.apiobjects.WSPhone;


/**
 * A Utility class used to create test data for sending Requests to NIS.
 * 
 */

public class DataUtils 
{

	static 
	{
		BackOfficeGlobals.ENV.setEnvironmentVariables();
	}
	public static String url = "http://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/nwapi/v1.1/patron/";
	public static String uid = new UID().toString();
	public static Hashtable<String, String> headerMap = BackOfficeUtils.nisPatchHeaderWithUid(uid);
	/*
	 * To generate Random email
	 */
	public static String randomEmailString() {
		String randomEmail = BackOfficeUtils.generateRandomString(8) + BackOfficeGlobals.BACKOFFICE_DEFAULT_EMAIL_ADDRESS_PROVIDER;
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
public static WSCustomerRegisterRequest createPatronAccount(Hashtable<String,String> data){
		
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
 * To verify  Patron Authentication
 */
	public static WSPatronAuthenticateRequest patronAuthenticate(String Username,	String password) {
		WSPatronAuthenticateRequest jsonObj = new WSPatronAuthenticateRequest();
		jsonObj.setUsername(Username);
		jsonObj.setPassword(password);
		return jsonObj;	
	}
	
}
