/**
 * @author 203610
 * Jul 6, 2018
 */
package com.cubic.nisjava.api;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.cubic.accelerators.RESTActions;
import com.cubic.accelerators.RESTConstants;
import com.cubic.backoffice.constants.BackOfficeGlobals;
import com.cubic.backoffice.utils.BackOfficeUtils;
import com.cubic.genericutils.StringUtil;
import com.cubic.nisjava.apiobjects.ViewEditMerchantSubaccountRequest;
import com.cubic.nisjava.apiobjects.ViewEditMerchantSubaccountResponse;
import com.cubic.nisjava.apiobjects.WSMerchantAuthenticationErrorResponse;
import com.cubic.nisjava.apiobjects.WSName;
import com.cubic.nisjava.apiobjects.WSPatronAuthenticateRequest;
import com.cubic.nisjava.apiobjects.WSPatronAuthenticateResponse;
import com.cubic.nisjava.utils.DataUtils;
import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;

public class ViewEditMerchantSubaccountGETAndPATCH 
{
	static 
	{
		BackOfficeGlobals.ENV.setEnvironmentVariables();
	}
	public static final String CLASS_NAME = "ViewEditMerchantSubaccountGETAndPATCH";
	private static final Logger LOG = Logger.getLogger(CLASS_NAME);
	static String baseUrl =  "https://" + BackOfficeGlobals.ENV.NIS_HOST+"/nis/retailapi/v1/customer/CMS000001000/user/";
	static String merchantAuthUrl =  "https://" + BackOfficeGlobals.ENV.NIS_HOST+"/nis/retailapi/v1/customer/CMS000001000/authenticate";
	static ClientResponse clientResponse;
	static ViewEditMerchantSubaccountRequest  jsonObj = null;	
	static ViewEditMerchantSubaccountResponse respObj = null;
	static Class<ViewEditMerchantSubaccountResponse> jsonClass = 	ViewEditMerchantSubaccountResponse.class;
	static WSPatronAuthenticateRequest authReq=null;
	static WSPatronAuthenticateResponse authRespObj = null;
	static Class<WSPatronAuthenticateResponse> authJsonClass = 	WSPatronAuthenticateResponse.class;	
	static WSMerchantAuthenticationErrorResponse errRespAuthMerchant=null;
	static Class<WSMerchantAuthenticationErrorResponse> errAuthJsonClass = 	WSMerchantAuthenticationErrorResponse.class;
	static String jsonStr = "";
	static String resp = "";
	static String password = "";
	static String fname="";
	static String updatedFname="";
	static String lname = "";
	static String updatedLname="";
	static String userID = "";
	static String userType = "";
	static String userName = "";
	static String userStatus = "";
	static String credentialStatus = "";
	static String url;
	static Gson g = new Gson();

	/**
	 * @param restActions
	 * @param data
	 * @throws Throwable 
	 */
	public static void viewEditMerchantSubaccountFNameLName(RESTActions restActions, Hashtable<String, String> data) throws Throwable
	{
		//Case 1
		restActions.successReport("Case 1 --GET--", "Get User :"+data.get("User")+" details");
		getUserDetailsAndValidateResponseCode(restActions,data);
		validateViewEditMerchantSubaccountUserDetails(restActions,data);
		//Getting FistName and LastName value from Response
		fname = respObj.getUser().getName().getFirstName();
		lname = respObj.getUser().getName().getLastName();
		
		//Case 2
		restActions.successReport("Case 2 --PATCH--", "Update User :"+data.get("User")+" for FirstName & LastName");		
		//Building json request body for PATCH API call
		updatedFname = StringUtil.getRandomString(6);
		updatedLname = StringUtil.getRandomString(6);
		jsonStr = BackOfficeUtils.getJSONFromObject(buildJsonRequestBodyWithFnameLname(updatedFname,updatedLname));
		//Send request and get Client response
		sendUrlAndGetPATCHClientResponse(restActions,url);
		//Validate Status Code
		DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCodePATCH"), clientResponse);
		
		//Case 3
		restActions.successReport("Case 3 --GET--", "Verify User :"+data.get("User")+"  FirstName & LastName got updated in 2nd case");		
		//Send request and get GET Client response
		sendUrlAndGetGETClientResponse(restActions,url);
		if(DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCodeGET"), clientResponse))
		{
			validateFnameAndLnameChanges(restActions,data);
		}
		restActions.successReport("Case 4 --PATCH--", "Setting User :"+data.get("User")+"  FirstName & LastName to earlier value i.e FirstName to : -"+fname+" and LastName to : -"+lname);
		
		//updating FirstName and LastName again back to Earlier values
		jsonStr = BackOfficeUtils.getJSONFromObject(buildJsonRequestBodyWithFnameLname(fname,lname));
		//Send request and get Client response
		sendUrlAndGetPATCHClientResponse(restActions,url);
		//Validate Status Code
		DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCodePATCH"), clientResponse);
				
	}
	
	/**
	 * @param restActions
	 * @param data
	 * @throws Throwable 
	 */
	public static void viewEditMerchantSubaccountFNameBlank(RESTActions restActions, Hashtable<String, String> data) throws Throwable 
	{
		//Case 1
		restActions.successReport("Case 1 --GET--", "Get User :"+data.get("User")+" details");
		getUserDetailsAndValidateResponseCode(restActions,data);
		validateViewEditMerchantSubaccountUserDetails(restActions,data);
		//Getting FistName and LastName value from Response
		lname = respObj.getUser().getName().getLastName();

		//Case 2
		restActions.successReport("Case 2 --PATCH--", "Update User :"+data.get("User")+" for FirstName & LastName where FirstName is Blank and to find the Error Response");		
		//Building json request body for PATCH API call
		updatedLname = StringUtil.getRandomString(6);
		jsonStr = BackOfficeUtils.getJSONFromObject(buildJsonRequestBodyWithLname(updatedLname));
		//Send request and get Client response
		sendUrlAndGetPATCHClientResponse(restActions,url);
		//Validate Status Code
		DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCodePATCH"), clientResponse);
		//Validate FieldName, Error Key and Error Message
		validateFieldNameErrorKeyANdErrorMessage(restActions,data,respObj.getErrorHeader().getFieldName(), data.get("FieldName"),respObj.getErrorHeader().getErrorKey(), data.get("ErrorKey"),respObj.getErrorHeader().getErrorMessage(), data.get("ErrorMessage"));

		//Case 3
		restActions.successReport("Case 3 --GET--", "Verify User :"+data.get("User")+"   LastName is not updated in 2nd case");		
		//Send request and get GET Client response
		sendUrlAndGetGETClientResponse(restActions,url);
		if(DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCodeGET"), clientResponse))
		{
			validateLNameDoNotChanges(restActions);			
		}		
	}	
	
	/**
	 * @param restActions
	 * @param data
	 * @throws Throwable 
	 */
	public static void viewEditMerchantSubaccountFNameTooLong(RESTActions restActions, Hashtable<String, String> data) throws Throwable 
	{
		//Case 1
		restActions.successReport("Case 1 --GET--", "Get User :"+data.get("User")+" details");
		getUserDetailsAndValidateResponseCode(restActions,data);
		validateViewEditMerchantSubaccountUserDetails(restActions,data);
		//Getting FistName and LastName value from Response
		fname = respObj.getUser().getName().getFirstName();
		lname = respObj.getUser().getName().getLastName();

		//Case 2
		restActions.successReport("Case 2 --PATCH--", "Update User :"+data.get("User")+" for FirstName & LastName where FirstName is TooLong and to Get the Error Response");		
		//Building json request body for PATCH API call
		updatedFname = data.get("UserFirstName");
		updatedLname = data.get("UserLastName");
		jsonStr = BackOfficeUtils.getJSONFromObject(buildJsonRequestBodyWithFnameLname(updatedFname,updatedLname));
		//Send request and get Client response
		sendUrlAndGetPATCHClientResponse(restActions,url);
		//Validate Status Code
		DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCodePATCH"), clientResponse);
		//Validate FieldName, Error Key and Error Message
		validateFieldNameErrorKeyANdErrorMessage(restActions,data,respObj.getErrorHeader().getFieldName(), data.get("FieldName"),respObj.getErrorHeader().getErrorKey(), data.get("ErrorKey"),respObj.getErrorHeader().getErrorMessage(), data.get("ErrorMessage"));

		//Case 3
		restActions.successReport("Case 3 --GET--", "Verify User :"+data.get("User")+"  FirstName & LastName doesn't updated in 2nd case");		
		//Send request and get GET Client response
		sendUrlAndGetGETClientResponse(restActions,url);
		if(DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCodeGET"), clientResponse))
		{
			validateFNameDoNotChanges(restActions);	
			validateLNameDoNotChanges(restActions);
		}
	}	
	/**
	 * @param restActions
	 * @param data
	 * @throws Throwable 
	 */
	public static void viewEditMerchantSubaccountLNameBlank(RESTActions restActions, Hashtable<String, String> data) throws Throwable 
	{
		//Case 1
		restActions.successReport("Case 1 --GET--", "Get User :"+data.get("User")+" details");
		getUserDetailsAndValidateResponseCode(restActions,data);
		validateViewEditMerchantSubaccountUserDetails(restActions,data);
		//Getting FistName and LastName value from Response
		fname = respObj.getUser().getName().getFirstName();
		//Case 2
		restActions.successReport("Case 2 --PATCH--", "Update User :"+data.get("User")+" for FirstName & LastName where Last is Blank and to find the Error Response");		
		//Building json request body for PATCH API call
		updatedFname = StringUtil.getRandomString(6);
		jsonStr = BackOfficeUtils.getJSONFromObject(buildJsonRequestBodyWithFname(updatedFname));
		//Send request and get Client response
		sendUrlAndGetPATCHClientResponse(restActions,url);
		//Validate Status Code
		DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCodePATCH"), clientResponse);
		//Validate FieldName, Error Key and Error Message
		validateFieldNameErrorKeyANdErrorMessage(restActions,data,respObj.getErrorHeader().getFieldName(), data.get("FieldName"),respObj.getErrorHeader().getErrorKey(), data.get("ErrorKey"),respObj.getErrorHeader().getErrorMessage(), data.get("ErrorMessage"));

		//Case 3
		restActions.successReport("Case 3 --GET--", "Verify User :"+data.get("User")+"   FirstName is not updated in 2nd case");		
		//Send request and get GET Client response
		sendUrlAndGetGETClientResponse(restActions,url);
		if(DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCodeGET"), clientResponse))
		{
			validateFNameDoNotChanges(restActions);	
		}		
	}	
	/**
	 * @param restActions
	 * @param data
	 * @throws Throwable 
	 */
	public static void viewEditMerchantSubaccountLNameTooLong(RESTActions restActions, Hashtable<String, String> data) throws Throwable
	{
		//Case 1
		restActions.successReport("Case 1 --GET--", "Get User :"+data.get("User")+" details");
		getUserDetailsAndValidateResponseCode(restActions,data);
		validateViewEditMerchantSubaccountUserDetails(restActions,data);
		//Getting FistName and LastName value from Response
		fname = respObj.getUser().getName().getFirstName();
		lname = respObj.getUser().getName().getLastName();

		//Case 2
		restActions.successReport("Case 2 --PATCH--", "Update User :"+data.get("User")+" for FirstName & LastName where FirstName is TooLong and to get the Error Response");		
		//Building json request body for PATCH API call
		updatedFname = data.get("UserFirstName");
		updatedLname = data.get("UserLastName");
		jsonStr = BackOfficeUtils.getJSONFromObject(buildJsonRequestBodyWithFnameLname(updatedFname,updatedLname));
		//Send request and get Client response
		sendUrlAndGetPATCHClientResponse(restActions,url);
		//Validate Status Code
		DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCodePATCH"), clientResponse);
		//Validate FieldName, Error Key and Error Message
		validateFieldNameErrorKeyANdErrorMessage(restActions,data,respObj.getErrorHeader().getFieldName(), data.get("FieldName"),respObj.getErrorHeader().getErrorKey(), data.get("ErrorKey"),respObj.getErrorHeader().getErrorMessage(), data.get("ErrorMessage"));

		//Case 3
		restActions.successReport("Case 3 --GET--", "Verify User :"+data.get("User")+"  FirstName & LastName doesn't updated in 2nd case");		
		//Send request and get GET Client response
		sendUrlAndGetGETClientResponse(restActions,url);
		if(DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCodeGET"), clientResponse))
		{
			validateFNameDoNotChanges(restActions);	
			validateLNameDoNotChanges(restActions);	
		}		
	}
	/**
	 * @param restActions
	 * @param data
	 * @throws Throwable 
	 */
	public static void viewEditMerchantSubaccountPassword(RESTActions restActions, Hashtable<String, String> data) throws Throwable 
	{
		//Case 1
		restActions.successReport("Case 1 --GET--", "Get User :"+data.get("User")+" details");
		getUserDetailsAndValidateResponseCode(restActions,data);
		validateViewEditMerchantSubaccountUserDetails(restActions,data);
		//Storing userName value from response object to use it as LogIn in 3rd case
		userName = respObj.getUser().getUsername();

		//Case 2
		restActions.successReport("Case 2 --PATCH--", "Update User :"+data.get("User")+" Password");		
		//Building json request body for PATCH API call
		password = data.get("UpdatedPassword");
		jsonStr = BackOfficeUtils.getJSONFromObject(buildJsonRequestBodyWithPassword(password));
		//Send request and get Client response
		sendUrlAndGetPATCHClientResponse(restActions,url);
		//Validate Status Code
		DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCodePATCH"), clientResponse);
		restActions.successReport("Update Password", "User :"+data.get("User")+" successfully updated Password to "+password);
		
		//Case 3
		restActions.successReport("Case 3 --POST--", "User :"+data.get("User")+" is trying to LogIn with old password to get error message as password is updated in 2nd case");
		jsonStr = BackOfficeUtils.getJSONFromObject(DataUtils.patronAuthenticate(userName, data.get("Password")));
		//Send request and get POST Client response
		sendUrlAndGetPOSTClientResponse(restActions);
		// De-serialize the Response into a JSON Object
		errRespAuthMerchant = g.fromJson(resp, errAuthJsonClass);
		DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCodePOSTWIthWrongPassword"), clientResponse);
		validateUnSuccessfulLogInWithOldPassword(restActions,data);
		
		//Case 4
		restActions.successReport("Case 4 --POST--", "User :"+data.get("User")+" is trying to LogIn with new password in order to verify the password is updated in 2nd case ");
		jsonStr = BackOfficeUtils.getJSONFromObject(DataUtils.patronAuthenticate(userName, password));
		//Send request and get POST Client response
		sendUrlAndGetPOSTClientResponse(restActions);
		// De-serialize the Response into a JSON Object
		authRespObj = g.fromJson(resp, authJsonClass);
		validateSuccessfulLogInWithOldPassword(restActions,data);
		
		//Case5
		restActions.successReport("Case 5 --PATCH--", "Update User :"+data.get("User")+" Password again to the old password");		
		//Building json request body for PATCH API call
		password = data.get("Password");
		jsonStr = BackOfficeUtils.getJSONFromObject(buildJsonRequestBodyWithPassword(password));
		//Send request and get Client response
		sendUrlAndGetPATCHClientResponse(restActions,url);
		//Validate Status Code
		DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCodePATCH"), clientResponse);
		restActions.successReport("Update Password", "User :"+data.get("User")+" successfully updated Password again to "+password);
	}
	
	/**
	 * @param restActions
	 * @param data
	 * @throws Throwable 
	 */
	public static void viewEditMerchantSubaccountPasswordBlank(RESTActions restActions,Hashtable<String, String> data) throws Throwable
	{
		//Case 1
		restActions.successReport("Case 1 --GET--", "Get User :"+data.get("User")+" details");
		getUserDetailsAndValidateResponseCode(restActions,data);
		validateViewEditMerchantSubaccountUserDetails(restActions,data);
		//Storing userName value from response object to use it as LogIn in 3rd case
		userName = respObj.getUser().getUsername();

		//Case 2
		restActions.successReport("Case 2 --PATCH--", "Update User :"+data.get("User")+" Password, where Password is Blank");		
		//Building json request body for PATCH API call
		password = "";
		jsonStr = BackOfficeUtils.getJSONFromObject(buildJsonRequestBodyWithPassword(password));
		//Send request and get Client response
		sendUrlAndGetPATCHClientResponse(restActions,url);
		//Validate Status Code
		DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCodePATCH"), clientResponse);
		//Validate FieldName, Error Key and Error Message
		validateFieldNameErrorKeyANdErrorMessage(restActions,data,respObj.getErrorHeader().getFieldName(), data.get("FieldName"),respObj.getErrorHeader().getErrorKey(), data.get("ErrorKey"),respObj.getErrorHeader().getErrorMessage(), data.get("ErrorMessage"));

		//Case 3
		restActions.successReport("Case 3 --POST--", "User :"+data.get("User")+" is trying to LogIn with old password in order to verify the password is not updated in 2nd case");
		jsonStr = BackOfficeUtils.getJSONFromObject(DataUtils.patronAuthenticate(userName, data.get("Password")));
		//Send request and get POST Client response
		sendUrlAndGetPOSTClientResponse(restActions);
		// De-serialize the Response into a JSON Object
		authRespObj = g.fromJson(resp, authJsonClass);
		DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCodePOST"), clientResponse);
		validateSuccessfulLogInWithOldPassword(restActions,data);	
	}
	
	/**
	 * @param restActions
	 * @param data
	 * @throws Throwable 
	 */
	public static void viewEditMerchantSubaccountPasswordContainsUserName(RESTActions restActions,Hashtable<String, String> data) throws Throwable
	{
		//Case 1
		restActions.successReport("Case 1 --GET--", "Get User :"+data.get("User")+" details");
		getUserDetailsAndValidateResponseCode(restActions,data);
		validateViewEditMerchantSubaccountUserDetails(restActions,data);
		//Storing userName value from response object to use it as LogIn in 3rd case
		userName = respObj.getUser().getUsername();

		//Case 2
		restActions.successReport("Case 2 --PATCH--", "Update User :"+data.get("User")+" Password, where Password is same as UserName");		
		//Building json request body for PATCH API call
		password = userName;
		jsonStr = BackOfficeUtils.getJSONFromObject(buildJsonRequestBodyWithPassword(password));
		//Send request and get Client response
		sendUrlAndGetPATCHClientResponse(restActions,url);
		//Validate Status Code
		DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCodePATCH"), clientResponse);	
		//Verifying FieldName & Error Key in Response Object
		DataUtils.validateResponseFieldValue(restActions, respObj.getErrorHeader().getFieldName(), data.get("FieldName"), data.get("FieldName"));
		DataUtils.validateResponseFieldValue(restActions, respObj.getErrorHeader().getErrorKey(), data.get("ErrorKey"), data.get("ErrorKey"));	

		//Case 3
		restActions.successReport("Case 3 --POST--", "User :"+data.get("User")+" is trying to LogIn with old password in order to verify the password is not updated in 2nd case");
		jsonStr = BackOfficeUtils.getJSONFromObject(DataUtils.patronAuthenticate(userName, data.get("Password")));
		//Send request and get POST Client response
		sendUrlAndGetPOSTClientResponse(restActions);
		// De-serialize the Response into a JSON Object
		authRespObj = g.fromJson(resp, authJsonClass);
		DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCodePOST"), clientResponse);
		validateSuccessfulLogInWithOldPassword(restActions,data);
	}	
	/**
	 * @param restActions
	 * @param data
	 * @throws Throwable 
	 */
	public static void viewEditMerchantSubaccountPasswordMustContainsDigits(RESTActions restActions,Hashtable<String, String> data) throws Throwable
	{
		//Case 1
		restActions.successReport("Case 1 --GET--", "Get User :"+data.get("User")+" details");
		getUserDetailsAndValidateResponseCode(restActions,data);
		validateViewEditMerchantSubaccountUserDetails(restActions,data);
		//Storing userName value from response object to use it as LogIn in 3rd case
		userName = respObj.getUser().getUsername();

		//Case 2
		restActions.successReport("Case 2 --PATCH--", "Update User :"+data.get("User")+" Password, where Password doesn't contains digits");		
		//Building json request body for PATCH API call
		password = data.get("PasswordwithoutDigit");
		jsonStr = BackOfficeUtils.getJSONFromObject(buildJsonRequestBodyWithPassword(password));
		//Send request and get Client response
		sendUrlAndGetPATCHClientResponse(restActions,url);
		//Validate Status Code
		DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCodePATCH"), clientResponse);
		validateFieldNameErrorKeyANdErrorMessage(restActions,data,respObj.getErrorHeader().getFieldName(), data.get("FieldName"),respObj.getErrorHeader().getErrorKey(), data.get("ErrorKey"),respObj.getErrorHeader().getErrorMessage(), data.get("ErrorMessage"));

		//Case 3
		restActions.successReport("Case 3 --POST--", "User :"+data.get("User")+" is trying to LogIn with old password in order to verify the password is not updated in 2nd case");
		jsonStr = BackOfficeUtils.getJSONFromObject(DataUtils.patronAuthenticate(userName, data.get("Password")));
		//Send request and get POST Client response
		sendUrlAndGetPOSTClientResponse(restActions);
		// De-serialize the Response into a JSON Object
		authRespObj = g.fromJson(resp, authJsonClass);
		DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCodePOST"), clientResponse);
		validateSuccessfulLogInWithOldPassword(restActions,data);			
	}
	/**
	 * @param restActions
	 * @param data
	 * @throws Throwable 
	 */
	public static void validateSuccessfulLogInWithOldPassword(RESTActions restActions,	Hashtable<String, String> data) throws Throwable 
	{
		if((authRespObj.getAuthCode().equals(data.get("AuthCode")))&&(authRespObj.getUserId().equals(data.get("User"))))
		{
			restActions.successReport("User :"+data.get("User")+"Authentication", "User :"+data.get("User")+" Authenticated successfully as AuthCode is "+authRespObj.getAuthCode());
		}
		else
		{
			restActions.failureReport("User :"+data.get("User")+"Authentication", "User :"+data.get("User")+" Authentication failed");
		}		
	}	
	/**
	 * @param restActions
	 * @param data
	 * @throws Throwable 
	 */
	public static void validateUnSuccessfulLogInWithOldPassword(RESTActions restActions,	Hashtable<String, String> data) throws Throwable 
	{
		if((errRespAuthMerchant.getAuthCode().equals(data.get("ErrorAuthCode")))&&(errRespAuthMerchant.getAuthErrors().get(0).getErrorKey().equals(data.get("ResponseErrorKey"))))
		{
			restActions.successReport("User :"+data.get("User")+"Authentication", "User :"+data.get("User")+" Authenticated is failed as error is "+errRespAuthMerchant.getAuthErrors().get(0).getErrorKey());
		}
		else
		{
			restActions.failureReport("User :"+data.get("User")+"Authentication", "User :"+data.get("User")+" Authenticated is successful with wrong username :"+userName+" and Password :"+data.get("Password"));
		}		
	}
	/**
	 * @param restActions
	 * @param data
	 * @throws Throwable 
	 */
	private static void getUserDetailsAndValidateResponseCode(RESTActions restActions, Hashtable<String, String> data) throws Throwable
	{
		url = baseUrl+data.get("User");			
		//Send request and get GET Client response
		sendUrlAndGetGETClientResponse(restActions,url);
		DataUtils.validateResponseCode(restActions, data.get("ExpectedResponseCodeGET"), clientResponse);		
	}
	/**
	 * @param restActions
	 * @param data
	 */
	private static void validateViewEditMerchantSubaccountUserDetails(RESTActions restActions,Hashtable<String, String> data) 
	{
		restActions.successReport("Verifying Response for User : "+data.get("User"), "Verifying Response : whether Important fields returned without Null values");
		DataUtils.validateResponseStringFieldForNullValues(restActions, respObj.getUser().getUserId(), data.get("UserId"));
		DataUtils.validateResponseStringFieldForNullValues(restActions, respObj.getUser().getUserType(), data.get("UserType"));
		DataUtils.validateResponseStringFieldForNullValues(restActions, respObj.getUser().getName().getFirstName(), data.get("FirstName"));
		DataUtils.validateResponseStringFieldForNullValues(restActions, respObj.getUser().getName().getLastName(), data.get("LastName"));
		DataUtils.validateResponseStringFieldForNullValues(restActions, respObj.getUser().getUsername(), data.get("UserName"));
		DataUtils.validateResponseStringFieldForNullValues(restActions, respObj.getUser().getUserStatus(), data.get("UserStatus"));
		DataUtils.validateResponseStringFieldForNullValues(restActions, respObj.getUser().getCredentialStatus(), data.get("CredentialStatus"));	
		restActions.successReport("Verified Response for User : "+data.get("User"), "Verified Response : Important fields returned without Null values");
	}

	/**
	 * @param fieldName
	 * @param field_Name
	 * @param errorKey
	 * @param error_Key
	 * @param errorMessage
	 * @param error_Message
	 */
	private static void validateFieldNameErrorKeyANdErrorMessage(RESTActions restActions,Hashtable<String, String> data,String fieldName, String field_Name, String errorKey,String error_Key, String errorMessage, String error_Message) 
	{
		restActions.successReport("Validating FieldName, Error Key and Error Message in Response", "Verifying FieldName, Error Key and Error message from Response body");
		DataUtils.validateResponseFieldValue(restActions, field_Name, fieldName, data.get("FieldName"));
		DataUtils.validateResponseFieldValue(restActions, error_Key, errorKey, data.get("ErrorKey"));
		DataUtils.validateResponseFieldValue(restActions, error_Message, errorMessage, data.get("ErrorMessage"));
		restActions.successReport("Verified FieldName, Error Key and Error Message in Response", "Verified FieldName, Error Key and Error message from Response body");		
	}
	/**
	 * @param restActions
	 * @param data
	 */
	private static void validateFnameAndLnameChanges(RESTActions restActions, Hashtable<String, String> data) 
	{
		if(fname.equals(respObj.getUser().getName().getFirstName()))
		{
			restActions.failureReport("Verifying updated First Name in Response", "FirstName in Response is "+respObj.getUser().getName().getFirstName()+"which means First name is not updated in 2nd case as PATCH method");
		}
		else
		{
			restActions.successReport("Verifying updated First Name in Response", "FirstName in Response is "+respObj.getUser().getName().getFirstName()+"which means First name is  updated in 2nd case as PATCH method");
		}
		
		if(lname.equals(respObj.getUser().getName().getLastName()))
		{
			restActions.failureReport("Verifying updated Last Name in Response", "LastName in Response is "+respObj.getUser().getName().getLastName()+"which means Last name is not updated in 2nd case as PATCH method");
		}
		else
		{
			restActions.successReport("Verifying updated Last Name in Response", "LastName in Response is "+respObj.getUser().getName().getLastName()+"which means Last name is updated in 2nd case as PATCH method");
		}
	}
	/**
	 * @param restActions
	 */
	private static void validateFNameDoNotChanges(RESTActions restActions) 
	{
		if(fname.equals(respObj.getUser().getName().getFirstName()))
		{
			restActions.successReport("Verifying First Name in Response", "FirstName in Response is "+respObj.getUser().getName().getFirstName()+"which means First name is not updated in 2nd case");
		}
		else
		{
			restActions.failureReport("Verifying First Name in Response", "FirstName in Response is "+respObj.getUser().getName().getFirstName()+"which means First name is updated in 2nd case");	
		}			
	}
	/**
	 * @param restActions
	 */
	private static void validateLNameDoNotChanges(RESTActions restActions) 
	{
		if(lname.equals(respObj.getUser().getName().getLastName()))
		{
			restActions.successReport("Verifying Last Name in Response", "LastName in Response is "+respObj.getUser().getName().getLastName()+" which means failed PATCH method in 2nd case doesn't changed the Last Name in user details");
		}
		else
		{
			restActions.failureReport("Verifying Last Name in Response", "LastName in Response is "+respObj.getUser().getName().getLastName()+" which means failed PATCH method in 2nd case has changed the Last Name in user details");	
		}		
	}
	
	/**
	 * @param restActions
	 * @param url
	 * @param string
	 * @throws Throwable 
	 */
	private static void sendUrlAndGetGETClientResponse(RESTActions restActions,String url) throws Throwable
	{
		//Send request and get Client response
		LOG.info(url);
		restActions.successReport("Sending URL..", url);		
		clientResponse = restActions.getClientResponse(url, DataUtils.getHeaderForRetailApi(),null,RESTConstants.APPLICATION_JSON);
		restActions.successReport("Response Code from Server", ""+clientResponse.getStatus());
		resp = clientResponse.getEntity(String.class);
		DataUtils.printResponseWithPrettyPrinter(resp);
		restActions.successReport("Response from Server is ", resp);
		// De-serialize the Response into a JSON Object
		Gson g = new Gson();
		respObj = g.fromJson(resp, jsonClass);
	}
	/**
	 * @param restActions
	 * @param url
	 * @throws Throwable 
	 */
	private static void sendUrlAndGetPATCHClientResponse(RESTActions restActions,String url) throws Throwable
	{
		//Send request and get Client response
		LOG.info(url);
		restActions.successReport("Sending URL..", url);		
		// Build JSON Object
		restActions.successReport("Sending Request body", jsonStr);
		clientResponse = restActions.patchClientResponse(url, jsonStr, DataUtils.getHeaderForRetailApi(),null,RESTConstants.APPLICATION_JSON);
		restActions.successReport("Response Code from Server", ""+clientResponse.getStatus());
		resp = clientResponse.getEntity(String.class);
		LOG.info(resp);
		restActions.successReport("Response from Server is ", resp);
		// De-serialize the Response into a JSON Object
		Gson g = new Gson();
		respObj = g.fromJson(resp, jsonClass);
	}
	/**
	 * @param restActions
	 * @throws Throwable 
	 */
	public static void sendUrlAndGetPOSTClientResponse(RESTActions restActions) throws Throwable 
	{
		//Send request and get Client response
		LOG.info(merchantAuthUrl);
		restActions.successReport("Sending URL..", merchantAuthUrl);		
		// Build JSON Object
		restActions.successReport("Sending Request body", jsonStr);
		clientResponse = restActions.postClientResponse(merchantAuthUrl, jsonStr, DataUtils.getHeaderForRetailApi(),null,RESTConstants.APPLICATION_JSON);
		restActions.successReport("Response Code from Server", ""+clientResponse.getStatus());
		resp = clientResponse.getEntity(String.class);
		DataUtils.printResponseWithPrettyPrinter(resp);
		restActions.successReport("Response from Server is ", resp);
	}
	/**
	 * @param fname
	 * @param lname
	 * @return
	 */
	public static ViewEditMerchantSubaccountRequest buildJsonRequestBodyWithFnameLname(String fname, String lname) 
	{
		jsonObj = new ViewEditMerchantSubaccountRequest();
		//Setting FirstName and Lastname
		WSName name = new WSName();
		name.setFirstName(fname);
		name.setLastName(lname);
		//Setting Name
		jsonObj.setName(name);
		return jsonObj;
	}
	/**
	 * @param name
	 * @return
	 */
	public static ViewEditMerchantSubaccountRequest buildJsonRequestBodyWithFname(String name)
	{
		jsonObj = new ViewEditMerchantSubaccountRequest();
		//Setting FirstName
		WSName fname = new WSName();
		fname.setFirstName(name);
		//Setting Name
		jsonObj.setName(fname);
		return jsonObj;
	}		
	/**
	 * @param name
	 * @return
	 */
	public static ViewEditMerchantSubaccountRequest buildJsonRequestBodyWithLname(String name) 
	{
		jsonObj = new ViewEditMerchantSubaccountRequest();
		//Setting Lastname
		WSName lname = new WSName();
		lname.setLastName(name);
		//Setting Name
		jsonObj.setName(lname);
		return jsonObj;
	}
	/**
	 * @param password
	 * @return
	 */
	public static ViewEditMerchantSubaccountRequest buildJsonRequestBodyWithPassword(String password) 
	{
		jsonObj = new ViewEditMerchantSubaccountRequest();
		//Setting Password
		jsonObj.setPassword(password);
		return jsonObj;
	}	
}
