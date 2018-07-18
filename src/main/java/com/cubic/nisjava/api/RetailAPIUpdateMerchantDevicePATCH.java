package com.cubic.nisjava.api;

/**
 * @author 205974

 *
 */

import java.io.IOException;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import org.apache.log4j.Logger;
import com.cubic.accelerators.RESTActions;
import com.cubic.accelerators.RESTConstants;
import com.cubic.backoffice.constants.BackOfficeGlobals;
import com.cubic.backoffice.utils.BackOfficeUtils;
import com.cubic.logutils.Log4jUtil;
import com.cubic.nisjava.apiobjects.CreateMerchantSubaccountRequest;
import com.cubic.nisjava.apiobjects.UpdateMerchantProfileResponse;
import com.cubic.nisjava.apiobjects.User;
import com.cubic.nisjava.apiobjects.ViewMerchantProfileResponse;
import com.cubic.nisjava.apiobjects.WSHdr;
import com.cubic.nisjava.apiobjects.WSName;
import com.cubic.nisjava.apiobjects.WSPhone;
import com.cubic.nisjava.apiobjects.WSResetMerchantSubAccountPasswordRequest;
import com.cubic.nisjava.apiobjects.WSSecurityQA;
import com.cubic.nisjava.utils.DataUtils;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;

public class RetailAPIUpdateMerchantDevicePATCH {

	static 
    {
        BackOfficeGlobals.ENV.setEnvironmentVariables();
    }

	static String baseurl =  "https://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/retailapi/v1/customer/CMS000001000/user/";
		
	public static final String CLASS_NAME = "RetailAPIUpdateMerchantDevicePATCH";
	private static final Logger LOG = Logger.getLogger(CLASS_NAME);
	static ClientResponse clientResponse;
	static WSHdr jsonObj = null;	
	static ViewMerchantProfileResponse respObj = null;
	static Class<ViewMerchantProfileResponse> jsonClass = ViewMerchantProfileResponse.class;
	
	static User jsonObj1 = null;		
	static UpdateMerchantProfileResponse respObj1 = null;
	static Class<UpdateMerchantProfileResponse> jsonClass1 = UpdateMerchantProfileResponse.class;
		
	static CreateMerchantSubaccountRequest jsonObj2 = null;	
	static WSResetMerchantSubAccountPasswordRequest jsonObj3 = null;
	static String jsonStr = "";
	static String resp = "";
	
	/*
	 * This method is used to View Merchant Profile - user-id not found - C612516
	 * @param data
	 * @param actions
	 */
	public static void verifyviewMerchantProfileWithUserIdNotFound(Hashtable<String, String> data, RESTActions restActions) {

		try {
			
			tcviewMerchantProfileWithUserIdNotFound(data, restActions);		 
			
		}
		catch (Exception e) {
			catchExceptionE(e);
		}
		catch (Throwable t) {
			catchExceptionT(t);
		}

	}

	/**
	 * @param data
	 * @param restActions
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 * @throws JsonSyntaxException
	 */
	private static void tcviewMerchantProfileWithUserIdNotFound(Hashtable<String, String> data, RESTActions restActions) throws ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException, JsonSyntaxException {
		String url = baseurl+data.get("UserId");
					
		getClientResp(restActions, url);
					
		logUrlStatusResp(url);

		//De-Serilaize
		deserializeGson();
		
		//Expected API Response
		String ExpectedResp = "{\""+data.get("EXPECTED_HDR_FIELD")+"\": {\""+data.get("EXPECTED_RESULT")+"\": \""+data.get("EXPECTED_RESULT_VALUE")+"\",\""+data.get("EXPECTED_FIELDNAME1")+"\": \""+data.get("EXPECTED_FIELDNAME1_VALUE")+"\",\""+data.get("EXPECTED_FIELDNAME2")+"\": \""+data.get("EXPECTED_FIELDNAME2_VALUE")+"\",\""+data.get("EXPECTED_FIELDNAME3")+"\": \""+data.get("EXPECTED_FIELDNAME3_VALUE")+"\"}}";
		
		//API Response Validations
		if (HttpURLConnection.HTTP_NOT_FOUND == clientResponse.getStatus()) {
				
			if(respObj.getHdr().getErrorKey().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME3_VALUE")) && respObj.getHdr().getResult().equalsIgnoreCase(data.get("EXPECTED_RESULT_VALUE"))&&  respObj.getHdr().getUid().length()==36 && respObj.getHdr().getFieldName().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME2_VALUE"))){			
				
				printUrlStatusBodyResp(restActions, url);
				
				//Print Expected and Actual Responses to report
				logExpectedActualRespToReport(restActions, ExpectedResp);
									
				//Verify fieldName: user-id
				verifyFieldName(restActions, data);
							
				//Verify errorKey: errors.general.record.not.found
				verifyErrorKey(restActions, data);
									
			} else
				failureReportRespMsg(restActions);
			}
		else 
			failureReportNotFoundHttpStatus(restActions);
	}

	/*
	 * This method is used to Edit Merchant Profile - email - C612543
	 * @param data
	 * @param actions
	 */
	public static void verifyEditMerchantProfileWithEmail(Hashtable<String, String> data, RESTActions restActions) {

		try {
			
			tcEditMerchantProfileWithEmail(data, restActions);		 
		}
		catch (Exception e) {
			catchExceptionE(e);
		}
		catch (Throwable t) {
			catchExceptionT(t);
		}

	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void tcEditMerchantProfileWithEmail(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		
		String url = urlAndUpdateEmail(data, restActions);
			
		//Send request and get Client response
		patchClientResp(restActions, url);
		
		//Logging URL, Status and Response
		logUrlStatusResp(url);				
		
		if (HttpURLConnection.HTTP_NO_CONTENT == clientResponse.getStatus()) {
			
			//Print to report
			printUrlStatusBodyResp(restActions, url);
			
			//Step 2 : Get and verify the User details after updating the email - customer/<customer-id>/user/<user-id> GET
			verifyUserProfile(data, restActions);	
			
			//Asserts
			assertsEmailUpdate(data, restActions, url);
			
		} else
			failureReportNoContentHttpStatus(restActions);
	}

	/*
	 * This method is used to Edit Merchant Profile - email - value too long : C612530
	 * @param data
	 * @param actions
	 */
	public static void verifyEditMerchantProfileWithEmailValueTooLong(Hashtable<String, String> data, RESTActions restActions) {

		try {
					
			tcEditMerchantProfileWithEmailValueTooLong(data, restActions);	
		}
		catch (Exception e) {
			catchExceptionE(e);
		}
		catch (Throwable t) {
			catchExceptionT(t);
		}

	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void tcEditMerchantProfileWithEmailValueTooLong(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		String url = urlAndUpdateEmail(data, restActions);							

		String ExpectedResp = expectedApiRespEmailTooLong(data);
		
		if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) 
			
			if(respObj.getHdr().getFieldName().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME2_VALUE")) && respObj.getHdr().getErrorKey().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME3_VALUE")) && respObj.getHdr().getErrorMessage().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME4_VALUE")) ){
					
				printUrlStatusBodyResp(restActions, url);
				
				//Print Expected and Actual Responses to report
				logExpectedActualRespToReport(restActions, ExpectedResp);
				
				//Asserts
				verifyFieldName(restActions, data);
				verifyErrorKey(restActions, data);
				verifyErrorMessage(restActions, data);
				
			} else
				failureReportRespMsg(restActions);
		else
			failureReportBadHttpStatus(restActions);
	}

	/*
	 * This method is used to Edit Merchant Profile - email - email cannot start with special characters - C612533
	 * @param data
	 * @param actions
	 */
	public static void verifyEditMerchantProfileWithEmailStartingWithSpecialCharacters(Hashtable<String, String> data, RESTActions restActions) {

		try {
					
			tcEditMerchantProfileWithEmailStartingWithSpecialCharacters(data, restActions);	
	
		}
		catch (Exception e) {
			catchExceptionE(e);
		}
		catch (Throwable t) {
			catchExceptionT(t);
		}

	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void tcEditMerchantProfileWithEmailStartingWithSpecialCharacters(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		
		String url = urlAndUpdateEmail(data, restActions);							

		String ExpectedResp = expectedApiRespEmailTooLong(data);
		
		if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) 
			
			if(respObj.getHdr().getFieldName().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME2_VALUE")) && respObj.getHdr().getErrorKey().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME3_VALUE")) && respObj.getHdr().getErrorMessage().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME4_VALUE")) ){
					
				printUrlStatusBodyResp(restActions, url);
				
				//Print Expected and Actual Responses to report
				logExpectedActualRespToReport(restActions, ExpectedResp);
				
				//Asserts
				verifyFieldName(restActions, data);
				verifyErrorKey(restActions, data);
				verifyErrorMessage(restActions, data);
								
			} else
				failureReportRespMsg(restActions);
		else
			failureReportBadHttpStatus(restActions);
	}
	
	/* 
	 * This method is used to Edit Merchant Profile - email - invalid format : C612528
	 * @param data
	 * @param actions
	 */
	public static void verifyEditMerchantProfileWithInvalidEmailFormat(Hashtable<String, String> data, RESTActions restActions) {

		try {
					
			tcEditMerchantProfileWithInvalidEmailFormat(data, restActions);	
	
		}
		catch (Exception e) {
			catchExceptionE(e);
		}
		catch (Throwable t) {
			catchExceptionT(t);
		}

	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void tcEditMerchantProfileWithInvalidEmailFormat(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		
		String url = urlAndUpdateEmail(data, restActions);							

		//Expected API Response
		String ExpectedResp = expectedApiRespInvalidEmailFormat(data);
		
		if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) 
			
			if(respObj.getHdr().getFieldName().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME2_VALUE")) && respObj.getHdr().getErrorKey().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME3_VALUE")) && respObj.getHdr().getErrorMessage().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME4_VALUE")) ){
					
				restActions.successReport("Sending View Merchant Profile GET request...", ""+url);
				failureReportBadHttpStatus(restActions);
				
				//Print Expected and Actual Responses to report
				logExpectedActualRespToReport(restActions, ExpectedResp);
				
				//Asserts
				verifyFieldName(restActions, data);
				verifyErrorKey(restActions, data);
				verifyErrorMessage(restActions, data);
								
			} else
				failureReportRespMsg(restActions);
		else
			failureReportBadHttpStatus(restActions);
	}
	
	/* 
	 * This method is used to Edit Merchant Profile - firstName - name.firstName value required : C612519
	 * @param data
	 * @param actions
	 */
	public static void verifyEditMerchantProfileWithEmptyFirstName(Hashtable<String, String> data,	RESTActions restActions) throws Throwable {
		
		try {
			
		tcEditMerchantProfileWithEmptyFirstName(data, restActions);			
		}
		catch (Exception e) {
			catchExceptionE(e);
		}
		catch (Throwable t) {
			catchExceptionT(t);
		}
		
	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 */
	private static void tcEditMerchantProfileWithEmptyFirstName(Hashtable<String, String> data, RESTActions restActions) throws Throwable {
		
		tcEditMerchantProfileName(data, restActions);///TBD
	}
	
	/*
	 *  This method is used to Edit Merchant Profile - firstName - name.firstName value toolong : C612521 
	 * @param data
	 * @param actions
	 */
	public static void verifyEditMerchantProfileWithFirstNameTooLong(Hashtable<String, String> data, RESTActions restActions) throws Throwable {
		
		try {
			
			tcEditMerchantProfileName(data, restActions);
		
		}
		catch (Exception e) {
			catchExceptionE(e);
		}
		catch (Throwable t) {
			catchExceptionT(t);
		}
		
	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 */
	private static void tcEditMerchantProfileName(Hashtable<String, String> data, RESTActions restActions) throws Throwable {
		
		//Step 1 : 	customer/<customer-id>/user/<user-id> PATCH
		String url = baseurl+data.get("UserId");
		updateUserFirstNameLastNameMethod(data, restActions);
				
		if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {
			
			if(respObj.getHdr().getErrorKey().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME3_VALUE")) && respObj.getHdr().getResult().equalsIgnoreCase(data.get("EXPECTED_RESULT_VALUE"))&&  respObj.getHdr().getUid().length()==36 && respObj.getHdr().getFieldName().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME2_VALUE"))){			
					
			//Print to Report
			printUrlStatusBodyResp(restActions, url);	
						
			//Asserts
			verifyFieldName(restActions, data);
			verifyErrorKey(restActions, data);
			verifyErrorMessage(restActions, data);
										
			} else
				failureReportRespMsg(restActions);
			}
		else 
			failureReportBadHttpStatus(restActions);
	}

	/*
	 * This method is used to Edit Merchant Profile - firstName + lastName : C612518
	 * @param data
	 * @param actions
	 */
	public static void verifyEditMerchantProfileWithFirstNameLastName(Hashtable<String, String> data, RESTActions restActions) {

		try {
					
			tcEditMerchantProfileWithFirstNameLastName(data, restActions);
			}
		catch (Exception e) {
			catchExceptionE(e);
		}
		catch (Throwable t) {
			catchExceptionT(t);
		}

	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 */
	private static void tcEditMerchantProfileWithFirstNameLastName(Hashtable<String, String> data, RESTActions restActions) throws Throwable {
		
		//Step 1 : 	customer/<customer-id>/user/<user-id> PATCH
		String url = baseurl+data.get("UserId");
		updateUserFirstNameLastNameMethod(data, restActions);
		
		if (HttpURLConnection.HTTP_NO_CONTENT == clientResponse.getStatus()) {
		
			//Step 2 : Verify firstName and lastName are returned per PATCH changes
			verifyUserProfile(data, restActions);	
		
			//Asserts
			assertsFirstNameLastName(data, restActions, url);

		}
		else
			failureReportNoContentHttpStatus(restActions);
	}

	/*
	 * This method is used to Edit Merchant Profile - lastName - name.lastName value required : C612524
	 * @param data
	 * @param actions
	 */
	public static void verifyEditMerchantProfileWithEmptyLastName(Hashtable<String, String> data, RESTActions restActions) throws Throwable {
		
		try {
		tcEditMerchantProfileName(data, restActions);
		}
		catch (Exception e) {
			catchExceptionE(e);
		}
		catch (Throwable t) {
			catchExceptionT(t);
		}
		
	}

	/*
	 * This method is used to Edit Merchant Profile - lastName - name.lastName value too long : C612527
	 * @param data
	 * @param actions
	 */
	public static void verifyEditMerchantProfileWithLastNameTooLong(Hashtable<String, String> data, RESTActions restActions) throws Throwable {
		
		try{
		
		tcEditMerchantProfileWithLastNameTooLong(data, restActions);
				}
		catch (Exception e) {
			catchExceptionE(e);
		}
		catch (Throwable t) {
			catchExceptionT(t);
		}
		
	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 */
	private static void tcEditMerchantProfileWithLastNameTooLong(Hashtable<String, String> data, RESTActions restActions) throws Throwable {
		//Step 1 : 	customer/<customer-id>/user/<user-id> PATCH
		String url = baseurl+data.get("UserId");
		updateUserFirstNameLastNameMethod(data, restActions);
						
		if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus() && respObj.getHdr().getErrorKey().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME3_VALUE")) && respObj.getHdr().getResult().equalsIgnoreCase(data.get("EXPECTED_RESULT_VALUE"))&&  respObj.getHdr().getUid().length()==36 && respObj.getHdr().getFieldName().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME2_VALUE"))) {
		
				//Print to Report
				printUrlStatusBodyResp(restActions, url);	
								
				//Asserts
				verifyFieldName(restActions, data);
				verifyErrorKey(restActions, data);
				verifyErrorMessage(restActions, data);
				
			}
			else 
				failureReportNotFoundHttpStatus(restActions);
	}

	/*
	 * This method is used to Edit Merchant Profile  - phone : C612542
	 * @param data
	 * @param actions
	 */	
	public static void verifyEditMerchantProfileWithPhone(Hashtable<String, String> data, RESTActions restActions) {
		
		try {
			
			tcEditMerchantProfileWithPhone(data, restActions);
								
		}
		catch (Exception e) {
			catchExceptionE(e);
		}
		catch (Throwable t) {
			catchExceptionT(t);
		}
		
	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void tcEditMerchantProfileWithPhone(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		//Step 1 : 	update user profile with valid phone
		String url = urlAndUpdatePhone(data, restActions);
			
		//Send request and get Client response
		patchClientResp(restActions, url);
		
		//Logging URL, Status and Response
		logUrlStatusResp(url);
		
		//Print to report
		printUrlStatusBodyResp(restActions, url);	
				
		if(HttpURLConnection.HTTP_NO_CONTENT == clientResponse.getStatus()) {
		
			//Step 2 : Get and verify the User details after updating the phone - customer/<customer-id>/user/<user-id> GET
			verifyUserProfile(data, restActions);	
		
			//Asserts
			assertsPhoneUpdate(data, restActions, url);
		}
		else 
			failureReportNoContentHttpStatus(restActions);
	}

	/*
	 * This method is used to Edit Merchant Profile  - phone  - phone[0].number value required : C967815
	 * @param data
	 * @param actions
	 */	
	public static void verifyEditMerchantProfileWithPhoneNoValueRequired(Hashtable<String, String> data, RESTActions restActions) throws JsonParseException, JsonMappingException, ClientHandlerException, UniformInterfaceException, JsonProcessingException, IOException, Throwable {

		try {
		
			tcEditMerchantProfileWithPhoneNoValueRequired(data, restActions);
				
		}
		catch (Exception e) {
			catchExceptionE(e);
		}
		catch (Throwable t) {
			catchExceptionT(t);
		}
		
	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void tcEditMerchantProfileWithPhoneNoValueRequired(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		String url = urlAndUpdatePhone(data, restActions);

		if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {
		
			if(respObj.getHdr().getErrorKey().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME3_VALUE")) && respObj.getHdr().getResult().equalsIgnoreCase(data.get("EXPECTED_RESULT_VALUE"))&&  respObj.getHdr().getUid().length()==36 && respObj.getHdr().getFieldName().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME2_VALUE"))){			
			
				//Print to Report
				printUrlStatusBodyResp(restActions, url);	
								
				//Asserts
				verifyFieldName(restActions, data);
				verifyErrorKey(restActions, data);
				verifyErrorMessage(restActions, data);
									
			} else
			failureReportRespMsg(restActions);
		}
		else 
			failureReportBadHttpStatus(restActions);
	}

	/*
	 * This method is used to Edit Merchant Profile - phone - phone[0].number value too short <TBD> : C967816
	 * @param data
	 * @param actions
	 */	
	public static void verifyEditMerchantProfileWithPhoneNoValueTooShort(Hashtable<String, String> data,RESTActions restActions) {
		
		try {
		
		tcEditMerchantProfileWithPhoneNoValueTooShort(data, restActions);
		
		}
	
		catch (Exception e) {
			catchExceptionE(e);
		}
		catch (Throwable t) {
			catchExceptionT(t);
		}

	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void tcEditMerchantProfileWithPhoneNoValueTooShort(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		String url = urlAndUpdatePhone(data, restActions);
				
		if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {
			
			if(respObj.getHdr().getErrorKey().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME3_VALUE")) && respObj.getHdr().getResult().equalsIgnoreCase(data.get("EXPECTED_RESULT_VALUE"))&&  respObj.getHdr().getUid().length()==36 && respObj.getHdr().getFieldName().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME2_VALUE"))){			
					
			//Print to Report
			printUrlStatusBodyResp(restActions, url);	
											
			//Asserts
			verifyFieldName(restActions, data);
			verifyErrorKey(restActions, data);
			verifyErrorMessage(restActions, data);
			
		} else
				failureReportRespMsg(restActions);
		}
		else 
			failureReportBadHttpStatus(restActions);
	}
	
	/*
	 * This method is used to Edit Merchant Profile - phone - phone[0].number value too long : C967817
	 * @param data
	 * @param actions
	 */	
	public static void verifyEditMerchantProfileWithPhoneNoValueTooLong(Hashtable<String, String> data,RESTActions restActions) {
		
		try {
		
		tcEditMerchantProfileWithPhoneNoValueTooLong(data, restActions);			
			
		}
	
		catch (Exception e) {
			catchExceptionE(e);
		}
		catch (Throwable t) {
			catchExceptionT(t);
		}

	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void tcEditMerchantProfileWithPhoneNoValueTooLong(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		String url = urlAndUpdatePhone(data, restActions);
				
		if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {
			
			if(respObj.getHdr().getErrorKey().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME3_VALUE")) && respObj.getHdr().getResult().equalsIgnoreCase(data.get("EXPECTED_RESULT_VALUE"))&&  respObj.getHdr().getUid().length()==36 && respObj.getHdr().getFieldName().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME2_VALUE"))){			
				
			//Print to Report
			printUrlStatusBodyResp(restActions, url);	
											
			//Asserts
			verifyFieldName(restActions, data);
			verifyErrorKey(restActions, data);
			verifyErrorMessage(restActions, data);
					
			} else
				failureReportRespMsg(restActions);
		}
		else 
			failureReportBadHttpStatus(restActions);
		}
		
	/*
	 * This method is used to Edit Merchant Profile - phone - phone[0].type value required : C967812
	 * @param data
	 * @param actions
	 */	
	public static void verifyEditMerchantProfileWithPhoneNoTypeRequired(Hashtable<String, String> data,RESTActions restActions) {
		
		try {
		
		tcEditMerchantProfileWithPhoneNoTypeRequired(data, restActions);			
			
		}
	
		catch (Exception e) {
			catchExceptionE(e);
		}
		catch (Throwable t) {
			catchExceptionT(t);
		}

	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void tcEditMerchantProfileWithPhoneNoTypeRequired(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		
		String url = urlAndUpdatePhone(data, restActions);
		
		// Get JSON String representation of the Object
		jsonPhone();
					
		if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {
		
			if(respObj.getHdr().getErrorKey().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME3_VALUE")) && respObj.getHdr().getResult().equalsIgnoreCase(data.get("EXPECTED_RESULT_VALUE"))&&  respObj.getHdr().getUid().length()==36 && respObj.getHdr().getFieldName().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME2_VALUE"))){			
				
			//Print to Report
			printUrlStatusBodyResp(restActions, url);	
											
			//Asserts
			verifyFieldName(restActions, data);
			verifyErrorKey(restActions, data);
			verifyErrorMessage(restActions, data);
															
		} else
				failureReportRespMsg(restActions);
		}
		else 
			failureReportBadHttpStatus(restActions);
		}
	
	/*
	 * This method is used to Edit Merchant Profile - phone - phone[0].type value invalid : C967814
	 * @param data
	 * @param actions
	 */	
	public static void verifyEditMerchantProfileWithPhoneNoTypeValueInvalid(Hashtable<String, String> data,RESTActions restActions) {
		
		try {
		
		tcEditMerchantProfileWithPhoneNoTypeValueInvalid(data, restActions);			
			
		}
	
		catch (Exception e) {
			catchExceptionE(e);
		}
		catch (Throwable t) {
			catchExceptionT(t);
		}

	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void tcEditMerchantProfileWithPhoneNoTypeValueInvalid(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		
		String url = urlAndUpdatePhone(data, restActions);
		
		jsonPhone();
				
		if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {
				
			if(respObj.getHdr().getErrorKey().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME3_VALUE")) && respObj.getHdr().getResult().equalsIgnoreCase(data.get("EXPECTED_RESULT_VALUE"))&&  respObj.getHdr().getUid().length()==36 && respObj.getHdr().getFieldName().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME2_VALUE"))){			
				
			//Print to Report
			printUrlStatusBodyResp(restActions, url);	
											
			//Asserts
			verifyFieldName(restActions, data);
			verifyErrorKey(restActions, data);
			verifyErrorMessage(restActions, data);												
			
			} else
				failureReportRespMsg(restActions);
			}
		else 
			failureReportBadHttpStatus(restActions);
		}
	
	
	/*
	 * This method is used to Edit Merchant Profile - password - C612556
	 * @param data
	 * @param actions
	 */
	public static void verifyEditMerchantProfileWithPassword(Hashtable<String, String> data, RESTActions restActions) {

		try {
			
			tcEditMerchantProfileWithPassword(data, restActions);
			
		}
		catch (Exception e) {
			catchExceptionE(e);
		}
		catch (Throwable t) {
			catchExceptionT(t);
		}

	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void tcEditMerchantProfileWithPassword(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		
		//Step 1 : 	update user profile with valid password
		// Build JSON Object
		String url = urlAndUpdatePassword(data, restActions);
			
		//Send request and get Client response
		patchClientResp(restActions, url);
		
		//API Response Validations
		if (HttpURLConnection.HTTP_NO_CONTENT == clientResponse.getStatus()) {
			
			//Logging URL, Status and Response
			logUrlStatusResp(url);
		
			//Print to report
			printUrlStatusBodyResp(restActions, url);	
			
		} else
				failureReportRespMsg(restActions);
	}

	
	/*
	 * This method is used to Edit Merchant Profile - password - value required - C612535
	 * @param data
	 * @param actions
	 */
	public static void verifyEditMerchantProfileWithPasswordValueRequired(Hashtable<String, String> data, RESTActions restActions) {

		try {
			
			tcEditMerchantProfileWithPasswordValueRequired(data, restActions);			
			
		}
		
			catch (Exception e) {
				catchExceptionE(e);
			}
			catch (Throwable t) {
				catchExceptionT(t);
			}
	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void tcEditMerchantProfileWithPasswordValueRequired(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		
		String url = urlAndUpdatePassword(data, restActions);
			
		//Send request and get Client response
		patchClientResp(restActions, url);
		
		//Logging URL, Status and Response
		logUrlStatusResp(url);
						
		if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {
			
			if(respObj.getHdr().getErrorKey().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME3_VALUE")) && respObj.getHdr().getResult().equalsIgnoreCase(data.get("EXPECTED_RESULT_VALUE"))&&  respObj.getHdr().getUid().length()==36 && respObj.getHdr().getFieldName().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME2_VALUE"))){			
				
			//Print to Report
			printUrlStatusBodyResp(restActions, url);	

			//Asserts
			verifyFieldName(restActions, data);
			verifyErrorKey(restActions, data);
			verifyErrorMessage(restActions, data);
													
		} 	else
				failureReportRespMsg(restActions);
		}
		else 
			failureReportBadHttpStatus(restActions);
		}
	
	
	/*
	 * This method is used to Edit Merchant Profile - Password - cannot contain username - C612536
	 * @param data
	 * @param actions
	 */
	public static void verifyEditMerchantProfileWithPasswordCannotContainUsername(Hashtable<String, String> data, RESTActions restActions) {

		try {
			
			tcEditMerchantProfileWithPasswordCannotContainUsername(data, restActions);			
				
		}
		
			catch (Exception e) {
				catchExceptionE(e);
			}
			catch (Throwable t) {
				catchExceptionT(t);
			}
	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void tcEditMerchantProfileWithPasswordCannotContainUsername(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		
		String url = urlAndUpdatePassword(data, restActions);
			
		//Send request and get Client response
		patchClientResp(restActions, url);
		
		//Logging URL, Status and Response
		logUrlStatusResp(url);
																
		if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {
				
			if(respObj.getHdr().getErrorKey().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME3_VALUE")) && respObj.getHdr().getResult().equalsIgnoreCase(data.get("EXPECTED_RESULT_VALUE"))&&  respObj.getHdr().getUid().length()==36 && respObj.getHdr().getFieldName().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME2_VALUE"))){			
				
			//Print to Report
			printUrlStatusBodyResp(restActions, url);	
						
			//Asserts
			verifyFieldName(restActions, data);
			verifyErrorMessage(restActions, data);
												
			} else
				failureReportRespMsg(restActions);
			}
		else 
			failureReportBadHttpStatus(restActions);
		}
	
	
	/*
	 * This method is used to Edit Merchant Profile - Password - must contain digits - C612537
	 * @param data
	 * @param actions
	 */
	public static void verifyEditMerchantProfileWithPasswordMustContainDigits(Hashtable<String, String> data, RESTActions restActions) {

		try {
			
			tcEditMerchantProfileWithPasswordMustContainDigits(data, restActions);			
				
		}
		
			catch (Exception e) {
				catchExceptionE(e);
			}
			catch (Throwable t) {
				catchExceptionT(t);
			}
	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void tcEditMerchantProfileWithPasswordMustContainDigits(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		
		String url = urlAndUpdatePassword(data, restActions);
			
		//Send request and get Client response
		patchClientResp(restActions, url);
		
		//Logging URL, Status and Response
		logUrlStatusResp(url);
																
		if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {
					
			if(respObj.getHdr().getErrorKey().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME3_VALUE")) && respObj.getHdr().getResult().equalsIgnoreCase(data.get("EXPECTED_RESULT_VALUE"))&&  respObj.getHdr().getUid().length()==36 && respObj.getHdr().getFieldName().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME2_VALUE"))){			
				
				//Print to Report
				printUrlStatusBodyResp(restActions, url);						
		
				//Validate Status Code as 400
				DataUtils.validateResponseCode(restActions, data.get("EXPECTED_HTTP_RESPONSE_CODE"), clientResponse);
				
				//Asserts
				verifyErrorKey(restActions, data);
				verifyErrorMessage(restActions, data);
									
			} else
				failureReportRespMsg(restActions);
							
		}
		else 
			failureReportBadHttpStatus(restActions);
			}
	
	
	/*
	 * This method is used to Edit Merchant Profile - securityQAs : C612544
	 * @param data
	 * @param actions
	 */
	public static void verifyEditMerchantProfileWithSecurityQA(Hashtable<String, String> data, RESTActions restActions) {

		try {
			
			tcEditMerchantProfileWithSecurityQA(data, restActions);		 
		
		}
		catch (Exception e) {
			catchExceptionE(e);
		}
		catch (Throwable t) {
			catchExceptionT(t);
		}

	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void tcEditMerchantProfileWithSecurityQA(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		
		//Step 1 : 	update user profile with valid security QA's
		String url = urlAndupdateSecurityQA(data, restActions);
			
		//Send request and get Client response
		patchClientResp(restActions, url);
		
		//Logging URL, Status and Response
		logUrlStatusResp(url);
		
		//Print to report
		printUrlStatusBodyResp(restActions, url);	
		
		//Verify 204 response is returned
		if(HttpURLConnection.HTTP_OK == clientResponse.getStatus()) {
		
			//Step 2 : Get and verify the User details after updating the security QA's - customer/<customer-id>/user/<user-id> GET
			verifyUserProfile(data, restActions);	
		
			//Asserts
			assertsSecurityQAs(data, restActions, url);
		}
		else 
			failureReportHttpOk(restActions);
			}
	

	/*
	 * This method is used to Edit Merchant Profile - securityQAs - securityQAs[0].securityQuestion value required : C612546
	 * @param data
	 * @param actions
	 */	
	public static void verifyEditMerchantProfileWithSecurityQuestionValueRequired(Hashtable<String, String> data, RESTActions restActions) throws JsonParseException, JsonMappingException, ClientHandlerException, UniformInterfaceException, JsonProcessingException, IOException, Throwable {

		try {
			
		tcEditMerchantProfileWithSecurityQuestionValueRequired(data, restActions);	
			
		}
		catch (Exception e) {
			catchExceptionE(e);
		}
		catch (Throwable t) {
			catchExceptionT(t);
		}
		
	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void tcEditMerchantProfileWithSecurityQuestionValueRequired(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		
		String url = urlAndupdateSecurityQA(data, restActions);
		
		if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {
			
			if(respObj.getHdr().getErrorKey().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME3_VALUE")) && respObj.getHdr().getResult().equalsIgnoreCase(data.get("EXPECTED_RESULT_VALUE"))&&  respObj.getHdr().getUid().length()==36 && respObj.getHdr().getFieldName().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME2_VALUE"))){			
				
			//Print to Report
			printUrlStatusBodyResp(restActions, url);	
									
			//Asserts
			verifyFieldName(restActions, data);
			verifyErrorKey(restActions, data);
			verifyErrorMessage(restActions, data);
										
			} else
				failureReportRespMsg(restActions);
			}
			else 
				failureReportBadHttpStatus(restActions);	
	}
	

	/*
	 * This method is used to Edit Merchant Profile - securityQAs - securityQAs[0].securityQuestion value unexpected : C612549
	 * @param data
	 * @param actions
	 */	
	public static void verifyEditMerchantProfileWithSecurityQuestionValueUnexpected(Hashtable<String, String> data, RESTActions restActions) throws JsonParseException, JsonMappingException, ClientHandlerException, UniformInterfaceException, JsonProcessingException, IOException, Throwable {

		try {
		
			tcEditMerchantProfileWithSecurityQuestionValueUnexpected(data, restActions);	
			
		}
		catch (Exception e) {
			catchExceptionE(e);
		}
		catch (Throwable t) {
			catchExceptionT(t);
		}
		
	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void tcEditMerchantProfileWithSecurityQuestionValueUnexpected(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		
		String url = urlAndupdateSecurityQA(data, restActions);
		
		if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {
			
			if(respObj.getHdr().getErrorKey().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME3_VALUE")) && respObj.getHdr().getResult().equalsIgnoreCase(data.get("EXPECTED_RESULT_VALUE"))&&  respObj.getHdr().getUid().length()==36 && respObj.getHdr().getFieldName().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME2_VALUE"))){			
				
			//Print to Report
			printUrlStatusBodyResp(restActions, url);	
									
			//Asserts
			verifyFieldName(restActions, data);
			verifyErrorKey(restActions, data);
			verifyErrorMessage(restActions, data);
										
			} else
				failureReportRespMsg(restActions);
			}
			else 
				failureReportBadHttpStatus(restActions);
		}
	

	/*
	 * This method is used to Edit Merchant Profile - securityQAs - securityQAs[0].securityAnswer value required : C612554
	 * @param data
	 * @param actions
	 */	
	public static void verifyEditMerchantProfileWithSecurityAnswerValueRequired(Hashtable<String, String> data, RESTActions restActions) throws JsonParseException, JsonMappingException, ClientHandlerException, UniformInterfaceException, JsonProcessingException, IOException, Throwable {

		try {
			
		tcEditMerchantProfileWithSecurityAnswerValueRequired(data, restActions);	
			
		}
		catch (Exception e) {
			catchExceptionE(e);
		}
		catch (Throwable t) {
			catchExceptionT(t);
		}
		
	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void tcEditMerchantProfileWithSecurityAnswerValueRequired(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		
		String url = urlAndupdateSecurityQA(data, restActions);
		
		if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {
			
			if(respObj.getHdr().getErrorKey().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME3_VALUE")) && respObj.getHdr().getResult().equalsIgnoreCase(data.get("EXPECTED_RESULT_VALUE"))&&  respObj.getHdr().getUid().length()==36 && respObj.getHdr().getFieldName().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME2_VALUE"))){			
				
			//Print to Report
			printUrlStatusBodyResp(restActions, url);	
									
			//Asserts
			verifyFieldName(restActions, data);
			verifyErrorKey(restActions, data);
			verifyErrorMessage(restActions, data);
													
			} else
				failureReportRespMsg(restActions);
			}
			else 
				failureReportBadHttpStatus(restActions);
		}
	

	/*
	 * This method is used to Edit Merchant Profile - securityQAs - securityQAs[0].securityAnswer value too long : C612555
	 * @param data
	 * @param actions
	 */	
	public static void verifyEditMerchantProfileWithSecurityAnswerValueTooLong(Hashtable<String, String> data,RESTActions restActions) {
		
		try {
		
		tcEditMerchantProfileWithSecurityAnswerValueTooLong(data, restActions);			
			
		}
	
		catch (Exception e) {
			catchExceptionE(e);
		}
		catch (Throwable t) {
			catchExceptionT(t);
		}

	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void tcEditMerchantProfileWithSecurityAnswerValueTooLong(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		
		String url = urlAndupdateSecurityQA(data, restActions);
				
		if (HttpURLConnection.HTTP_BAD_REQUEST == clientResponse.getStatus()) {
			
			if(respObj.getHdr().getErrorKey().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME3_VALUE")) && respObj.getHdr().getResult().equalsIgnoreCase(data.get("EXPECTED_RESULT_VALUE"))&&  respObj.getHdr().getUid().length()==36 && respObj.getHdr().getFieldName().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME2_VALUE"))){			
				
			//Print to Report
			printUrlStatusBodyResp(restActions, url);	
											
			//Asserts
			verifyFieldName(restActions, data);
			verifyErrorKey(restActions, data);
			verifyErrorMessage(restActions, data);
						
			} else
				failureReportRespMsg(restActions);
		}
		else 
			failureReportBadHttpStatus(restActions);	
	}
	
	
	/**
	 * @param data
	 * @param restActions
	 * @param url
	 */
	private static void assertsEmailUpdate(Hashtable<String, String> data, RESTActions restActions, String url) {
		//API Response Validations
		if (HttpURLConnection.HTTP_OK == clientResponse.getStatus()) {
		
			if(respObj1.getUser().getEmail().equalsIgnoreCase(data.get("Valid_Email"))){
				
				restActions.successReport("Sending View Merchant Profile GET request...", ""+url);
				restActions.successReport("Expecting  Http Response code is -"+HttpURLConnection.HTTP_OK, "Actual Http Response code is - "+clientResponse.getStatus());
				restActions.successReport("API Response - ",""+resp);				
				restActions.assertTrue(respObj1.getUser().getEmail().equalsIgnoreCase(data.get("Valid_Email")), "Verify email is updated as per the above PATCH changes");
					
			}
			else
				restActions.failureReport("Expecting Response - " + respObj1.getUser().getEmail().equalsIgnoreCase(data.get("Valid_Email")),"Actual Response - " +respObj1.getUser().getEmail());
		}
		else 
			restActions.failureReport("Expecting  Http Response code is -"+HttpURLConnection.HTTP_OK, "Actual Http Response code is - "+clientResponse.getStatus());
	}


	/**
	 * @param data
	 * @param restActions
	 * @param url
	 */
	private static void assertsPhoneUpdate(Hashtable<String, String> data, RESTActions restActions, String url) {
		//API Response Validations
		if (HttpURLConnection.HTTP_OK == clientResponse.getStatus()) {
		
			if(respObj1.getUser().getPhone().get(0).getCountry().equalsIgnoreCase(data.get("country")) && respObj1.getUser().getPhone().get(0).getNumber().equalsIgnoreCase(data.get("number")) && respObj1.getUser().getPhone().get(0).getType().equalsIgnoreCase(data.get("type"))){
					
				restActions.successReport("Sending View Merchant Profile GET request...", ""+url);
				restActions.successReport("Expecting  Http Response code is -"+HttpURLConnection.HTTP_OK, "Actual Http Response code is - "+clientResponse.getStatus());
				restActions.successReport("API Response - ",""+resp);				
				restActions.assertTrue(respObj1.getUser().getPhone().get(0).getCountry().equalsIgnoreCase(data.get("country")), "Verify country is updated as per the above PATCH changes");
				restActions.assertTrue(respObj1.getUser().getPhone().get(0).getNumber().equalsIgnoreCase(data.get("number")), "Verify number is updated as per the above PATCH changes");
				restActions.assertTrue(respObj1.getUser().getPhone().get(0).getType().equalsIgnoreCase(data.get("type")), "Verify type is updated as per the above PATCH changes");
			}
			else
				restActions.failureReport("Expecting Response - " + respObj1.getUser().getPhone().get(0).getCountry().equalsIgnoreCase(data.get("country")),"Actual Response - " +respObj1.getUser().getPhone());
		}
		else 
			restActions.failureReport("Expecting  Http Response code is -"+HttpURLConnection.HTTP_OK, "Actual Http Response code is - "+clientResponse.getStatus());
	}
	

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void updateUserEmail(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
				
		// Build JSON Body Request
		jsonStr = buildJsonRequestEmailBody(data.get("Valid_Email"), restActions);
		
	}


	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void updateUserPassword(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
				
		// Build JSON Body Request
		jsonStr = buildJsonRequestPasswordBody(data.get("password"), restActions);
		
	}
	

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void updateUserPhone(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
				
		// Build JSON Body Request
		jsonStr = buildJsonRequestPhoneBody(data, restActions);
		
	}
	
	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void updateUserSecurityQAs(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
				
		// Build JSON Body Request
		jsonStr = buildJsonRequestSecurityQABody(data, restActions);
		
	}
	
	/**
	 * @param restActions
	 * @param url
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 */
	private static void patchClientResp(RESTActions restActions, String url) throws Throwable, ClientHandlerException, UniformInterfaceException {
		clientResponse = restActions.patchClientResponse(url, jsonStr, DataUtils.getHeaderForRetailApi(),null,RESTConstants.APPLICATION_JSON);
		resp = clientResponse.getEntity(String.class);
		restActions.successReport("Response Code from Server", ""+clientResponse.getStatus());
	}

	/**
	 * @param restActions
	 * @param url
	 */
	private static void printUrlStatusBodyResp(RESTActions restActions, String url) {
		restActions.successReport("Sending Update User Profile PATCH URL", url);
		restActions.successReport("Response Code from Server", ""+clientResponse.getStatus());
		restActions.successReport("Sending Request body", jsonStr);
		restActions.successReport("Response from Server is ", resp);
	}

	/**
	 * @param url
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static void logUrlStatusResp(String url) throws IOException, JsonParseException, JsonMappingException, JsonProcessingException {
		LOG.info("URL: " + url);
		LOG.info("Http Status is ... "+ clientResponse.getStatus());
		LOG.info("Response :" +DataUtils.jsonPrinter(resp));
	}

	/**
	 * @param data
	 * @return
	 */
	private static String expectedApiRespEmailTooLong(Hashtable<String, String> data) {
		
		String ExpectedResp = expectedApiRespInvalidEmailFormat(data);
		return ExpectedResp;
	}

	/**
	 * @param data
	 * @return
	 */
	private static String expectedApiRespInvalidEmailFormat(Hashtable<String, String> data) {
		
		String ExpectedResp = "{\""+data.get("EXPECTED_HDR_FIELD")+"\": {\""+data.get("EXPECTED_RESULT")+"\": \""+data.get("EXPECTED_RESULT_VALUE")+"\",\""+data.get("EXPECTED_FIELDNAME1")+"\": "
				+ "\""+data.get("EXPECTED_FIELDNAME1_VALUE")+"\",\""+data.get("EXPECTED_FIELDNAME2")+"\": \""+data.get("EXPECTED_FIELDNAME2_VALUE")+"\",\""+data.get("EXPECTED_FIELDNAME3")+"\":"
				+ " \""+data.get("EXPECTED_FIELDNAME3_VALUE")+"\",\""+data.get("EXPECTED_FIELDNAME4")+"\": \""+data.get("EXPECTED_FIELDNAME4_VALUE")+"\"}}";
		return ExpectedResp;
	}
	
	/**
	 * @param data
	 * @param restActions
	 * @param url
	 */
	private static void assertsFirstNameLastName(Hashtable<String, String> data, RESTActions restActions, String url) {
		
		if (HttpURLConnection.HTTP_OK == clientResponse.getStatus()) {
			
			if(respObj1.getUser().getName().getFirstName().equalsIgnoreCase(data.get("firstName")) && respObj1.getUser().getName().getLastName().equalsIgnoreCase(data.get("lastName"))){
					
				//Print to Report
				printUrlStatusBodyResp(restActions, url);	
				
				//Verify firstName and lastName are returned per PATCH changes
				restActions.assertTrue(respObj1.getUser().getName().getFirstName().equalsIgnoreCase(data.get("firstName")), "Verify firstName updated as per PATCH changes");
				restActions.assertTrue(respObj1.getUser().getName().getLastName().equalsIgnoreCase(data.get("lastName")), "Verify lastName updated as per PATCH changes");
					
			}
			else
				restActions.failureReport("Expecting Response - " + respObj1.getUser().getEmail().equalsIgnoreCase(data.get("Valid_Email")),"Actual Response - " +respObj1.getUser().getEmail());
		} else
			failureReportHttpOk(restActions);
	}

	/**
	 * @param restActions
	 */
	private static void failureReportHttpOk(RESTActions restActions) {
		restActions.failureReport("Expecting Http Response code is -"+HttpURLConnection.HTTP_OK, "Actual Http Response code is - "+clientResponse.getStatus());
	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable
	 */
	private static void updateUserFirstNameLastNameMethod(Hashtable<String, String> data, RESTActions restActions) throws Throwable {
		
		String url = baseurl+data.get("UserId");
		
		// Build JSON Object
		jsonObj2 = merchantSubaccountRequest(data);

		// Get JSON String representation of the Object
		jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj2);
		LOG.info("Converted JSON String: " + jsonStr);
					
		//Send request and get Client response
		patchClientResp(restActions, url);		
		
		if (HttpURLConnection.HTTP_NO_CONTENT == clientResponse.getStatus()) 
			printUrlStatusBodyResp(restActions, url);
		else
			failureReportNoContentHttpStatus(restActions);
	}
			
	/**
	 * @param data
	 * @param restActions
	 * @param url
	 */
	private static void assertsSecurityQAs(Hashtable<String, String> data, RESTActions restActions, String url) {
		
		if (HttpURLConnection.HTTP_OK == clientResponse.getStatus()) {
			
			if(respObj1.getUser().getSecurityQAs().get(0).getSecurityQuestion().equalsIgnoreCase(data.get("securityQuestion")) && respObj1.getUser().getSecurityQAs().get(0).getSecurityAnswer().equalsIgnoreCase(data.get("securityAnswer"))){
					
				//Print to Report
				printUrlStatusBodyResp(restActions, url);	
				
				//Verify firstName and lastName are returned per PATCH changes
				restActions.assertTrue(respObj1.getUser().getSecurityQAs().get(0).getSecurityQuestion().equalsIgnoreCase(data.get("securityQuestion")), "Verify Security Question is updated as per PATCH changes");
				restActions.assertTrue(respObj1.getUser().getSecurityQAs().get(0).getSecurityAnswer().equalsIgnoreCase(data.get("securityAnswer")), "Verify Security Answer is updated as per PATCH changes");
					
			}
			else
				restActions.failureReport("Expecting Response - " + respObj1.getUser().getSecurityQAs().get(0).getSecurityQuestion().equalsIgnoreCase(data.get("securityQuestion")),"Actual Response - " +respObj1.getUser().getSecurityQAs());
		}
		else 
			restActions.failureReport("Expecting  Http Response code is -"+HttpURLConnection.HTTP_OK, "Actual Http Response code is - "+clientResponse.getStatus());
	}

	/**
	 * @param data
	 * @param restActions
	 * @throws Throwable 
	 */
	private static void verifyUserProfile(Hashtable<String, String> data, RESTActions restActions) throws Throwable {
		
		String url1 = baseurl+data.get("UserId");
				
		// Make HTTP Get request to verify Get User details
		getClientResp(restActions, url1);
		
		//Logging URL, Status and Response
		logUrlStatusResp(url1);
			
		// De-serialize the Response into a JSON Object
		Gson g = new Gson();
		respObj1 = g.fromJson(resp, jsonClass1);
		
	}

	/**
	 * @param restActions
	 * @param url1
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 */
	private static void getClientResp(RESTActions restActions, String url1)	throws ClientHandlerException, UniformInterfaceException {
	clientResponse = restActions.getClientResponse(url1, DataUtils.getHeaderForRetailApi(), null, RESTConstants.APPLICATION_JSON);
	resp = clientResponse.getEntity(String.class);
	}
	
	/**
	 * @param restActions
	 * @param ExpectedResp
	 */
	private static void logExpectedActualRespToReport(RESTActions restActions, String ExpectedResp) {
		restActions.successReport("Expected Response - ",""+ExpectedResp);
		restActions.successReport("Actual Response - ",""+resp);					
	}

	/**
	 * @param String - Data
	 * @param restActions
	 * @return
	 */
	private static String buildJsonRequestEmailBody(String data, RESTActions restActions) 	{
		
		// Get JSON String representation of the Object
		jsonStr = BackOfficeUtils.getJSONFromObject(setEmail(data));		
		LOG.info("Converted Email JSON String: " + jsonStr);
		restActions.successReport("Request body is ", jsonStr);
		return jsonStr;
	}	
	
	/**
	 * @param String - Data
	 * @param restActions
	 * @return
	 */
	private static String buildJsonRequestPasswordBody(String data, RESTActions restActions) 	{
		
		// Get JSON String representation of the Object
		jsonStr = BackOfficeUtils.getJSONFromObject(setPassword(data));	
		LOG.info("Converted Password JSON String: " + jsonStr);
		restActions.successReport("Request body is ", jsonStr);
		return jsonStr;
	}	
	
	/**
	 * @param String - Data
	 * @param restActions
	 * @return
	 */
	private static String buildJsonRequestPhoneBody(Hashtable<String, String> data, RESTActions restActions) 	{
		
		// Get JSON String representation of the Object
		jsonStr = BackOfficeUtils.getJSONFromObject(setPhone(data));
		LOG.info("Converted Phone JSON String: " + jsonStr);
		restActions.successReport("Request body is ", jsonStr);
		return jsonStr;
	}	
	
	/**
	 * @param String - Data
	 * @param restActions
	 * @return
	 */
	private static String buildJsonRequestSecurityQABody(Hashtable<String, String> data, RESTActions restActions) 	{
		
		// Get JSON String representation of the Object
		Gson gson = new GsonBuilder().disableHtmlEscaping().create();
	    jsonStr = gson.toJson(setSecurityQAs(data));    
		LOG.info("Converted Security QA JSON String: " + jsonStr);
		restActions.successReport("Request body is ", jsonStr);
		return jsonStr;
	}
	
	/**
	 * @param string
	 * @Method to set the email field in request body and return as an object
	 */
	public static User setEmail(String data){
		jsonObj1 = new User();        
 		jsonObj1.setEmail(data);
		return jsonObj1;				
	}	
	
	/**
	 * @param string
	 * @Method to set the password field in request body and return as an object
	 */
	public static WSResetMerchantSubAccountPasswordRequest setPassword(String data){
		jsonObj3 = new WSResetMerchantSubAccountPasswordRequest();        
 		jsonObj3.setPassword(data);
		return jsonObj3;				
	}

	/**
	 * @param string
	 * @Method to set the phone fields in request body and return as an object
	 */
	public static User setPhone(Hashtable<String,String> data){
		
		User userJsonObj = new User();
		List<WSPhone> phoneList = new ArrayList<WSPhone>();
		WSPhone phoneJsonObj = new WSPhone();
		phoneJsonObj.setCountry(data.get("country")); 
		phoneJsonObj.setNumber(data.get("number"));
		phoneJsonObj.setType(data.get("type"));
		phoneList.add(phoneJsonObj);
		userJsonObj.setPhone(phoneList);
		return userJsonObj;	
		 		
	}	
	
	/**
	 * @param string
	 * @Method to set the security fields in request body and return as an object
	 */
	public static User setSecurityQAs(Hashtable<String,String> data){
		
		User userJsonObj = new User();
		List<WSSecurityQA> security = new ArrayList<WSSecurityQA>();
		WSSecurityQA jsonObj = new WSSecurityQA();
		jsonObj.setSecurityQuestion(data.get("securityQuestion"));
		jsonObj.setSecurityAnswer(data.get("securityAnswer"));
		security.add(jsonObj);
		userJsonObj.setSecurityQAs(security);
		return userJsonObj;	
			 		
	}
	
	/*This method is used to test Edit Merchant Profile */
	private static CreateMerchantSubaccountRequest merchantSubaccountRequest(Hashtable<String,String> data) {
		
		CreateMerchantSubaccountRequest jsonObj = new CreateMerchantSubaccountRequest();
		WSName name = new WSName();
		name.setFirstName(data.get("firstName"));
		name.setLastName(data.get("lastName"));
		jsonObj.setName(name);
		return jsonObj;
	}
			
	/**
	 * @param t
	 * @throws RuntimeException
	 */
	private static void catchExceptionT(Throwable t) throws RuntimeException {
		LOG.error(Log4jUtil.getStackTrace(t));
		throw new RuntimeException(t);
	}

	/**
	 * @param e
	 * @throws RuntimeException
	 */
	private static void catchExceptionE(Exception e) throws RuntimeException {
		LOG.error(Log4jUtil.getStackTrace(e));
		throw new RuntimeException(e);
	}

	/**
	 * @throws JsonSyntaxException
	 */
	private static void deserializeGson() throws JsonSyntaxException {
		// De-serialize the Response into a JSON Object
		Gson g = new Gson();
		respObj = g.fromJson(resp, jsonClass);
	}

	/**
	 * @param restActions
	 * @param data
	 */
	private static void verifyFieldName(RESTActions restActions,Hashtable<String, String> data) 
	{
		//Verifying Field name from the response with the expected field name
		if(respObj.getHdr().getFieldName().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME2_VALUE")))
		{
			restActions.successReport("Verifying Field Name", "Expected Field Name : "+data.get("EXPECTED_FIELDNAME2_VALUE")+" is same as field name from the ClientResponse i.e., "+respObj.getHdr().getFieldName());
		}
		else
		{
			restActions.failureReport("Verifying Field Name", "Expected Field Name : "+data.get("EXPECTED_FIELDNAME2_VALUE")+" but field name from the ClientResponse i.e., "+respObj.getHdr().getFieldName());
		}
	}
	
	/**
	 * @param restActions
	 * @param data
	 */
	private static void verifyErrorKey(RESTActions restActions,Hashtable<String, String> data) 
	{
		//Verify errorKey: errors.general.record.not.found
		if(respObj.getHdr().getErrorKey().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME3_VALUE")))
		{		
			restActions.successReport("Verifying Error Key", "Expected Error Key : "+data.get("EXPECTED_FIELDNAME3_VALUE")+" is same as error key from the ClientResponse i.e., "+respObj.getHdr().getErrorKey());
		}
		else
		{
			restActions.failureReport("Verifying Error Key", "Expected Error Key : "+data.get("EXPECTED_FIELDNAME3_VALUE")+" but error key name from the ClientResponse i.e., "+respObj.getHdr().getErrorKey());
		}
	}
	
	/**
	 * @param restActions
	 * @param data
	 */
	private static void verifyErrorMessage(RESTActions restActions,Hashtable<String, String> data) 
	{
		//Verify errorKey: errors.general.value.toolong		
		if(respObj.getHdr().getErrorKey().equalsIgnoreCase(data.get("EXPECTED_FIELDNAME3_VALUE")))
		{		
			restActions.successReport("Verifying Error Message", "Expected Error Message : "+data.get("EXPECTED_FIELDNAME4_VALUE")+" is same as error message from the ClientResponse i.e., "+respObj.getHdr().getErrorMessage());
		}
		else
		{
			restActions.failureReport("Verifying Error Message", "Expected Error Message : "+data.get("EXPECTED_FIELDNAME4_VALUE")+" but error message from the ClientResponse i.e., "+respObj.getHdr().getErrorMessage());
		}
	}

	/**
	 * @param restActions
	 */
	private static void failureReportBadHttpStatus(RESTActions restActions) {
		restActions.failureReport("Expecting Http Response code is -"+HttpURLConnection.HTTP_BAD_REQUEST, "Actual Http Response code is - "+clientResponse.getStatus());
	}

	/**
	 * @param restActions
	 */
	private static void failureReportRespMsg(RESTActions restActions) {
		restActions.failureReport("Expecting Response - " + "","Actual Response - " +resp);
	}

	/**
	 * @param restActions
	 */
	private static void failureReportNoContentHttpStatus(RESTActions restActions) {
		restActions.failureReport("Expecting Http Response code is -"+HttpURLConnection.HTTP_NO_CONTENT, "Actual Http Response code is - "+clientResponse.getStatus());
	}

	/**
	 * @param restActions
	 */
	private static void failureReportNotFoundHttpStatus(RESTActions restActions) {
		restActions.failureReport("Expecting Http Response code is -"+HttpURLConnection.HTTP_NOT_FOUND, "Actual Http Response code is - "+clientResponse.getStatus());
	}

	/**
	 * 
	 */
	private static void jsonPhone() {
		jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj1);
		LOG.info("Converted JSON String: " + jsonStr);
	}

	/**
	 * @param data
	 * @param restActions
	 * @return
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static String urlAndUpdatePassword(Hashtable<String, String> data, RESTActions restActions)
			throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		String url = baseurl+data.get("UserId");
		updateUserPassword(data, restActions); //- PATCH METHOD
		return url;
	}
	
	/**
	 * @param data
	 * @param restActions
	 * @return
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static String urlAndUpdateEmail(Hashtable<String, String> data, RESTActions restActions) throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		
		//Step 1 : 	update user profile with valid email
		String url = baseurl+data.get("UserId");
		updateUserEmail(data, restActions); //- PATCH METHOD
		return url;
	}

	/**
	 * @param data
	 * @param restActions
	 * @return
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static String urlAndUpdatePhone(Hashtable<String, String> data, RESTActions restActions)
			throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		// Build JSON Object
		String url = baseurl+data.get("UserId");
		updateUserPhone(data, restActions); //- PATCH METHOD
		return url;
	}

	/**
	 * @param data
	 * @param restActions
	 * @return
	 * @throws Throwable
	 * @throws ClientHandlerException
	 * @throws UniformInterfaceException
	 * @throws IOException
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	private static String urlAndupdateSecurityQA(Hashtable<String, String> data, RESTActions restActions)
			throws Throwable, ClientHandlerException, UniformInterfaceException, IOException, JsonParseException,
			JsonMappingException, JsonProcessingException {
		// Build JSON Object
		String url = baseurl+data.get("UserId");
		updateUserSecurityQAs(data, restActions); //- PATCH METHOD
		return url;
	}
	
}
