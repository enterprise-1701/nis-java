package com.cubic.nisjava.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.cubic.accelerators.RESTActions;
import com.cubic.accelerators.RESTConstants;
import com.cubic.backoffice.apiobjects.WSAddress;
import com.cubic.backoffice.apiobjects.WSAdjustOneAccountValue;
import com.cubic.backoffice.apiobjects.WSAdjustProduct;
import com.cubic.backoffice.apiobjects.WSAdjustProductLineItem;
import com.cubic.backoffice.apiobjects.WSCustomerContact;
import com.cubic.backoffice.apiobjects.WSName;
import com.cubic.backoffice.apiobjects.WSPersonalIdentifier;
import com.cubic.backoffice.apiobjects.WSPhone;
import com.cubic.backoffice.apiobjects.WSSecurityQA;
import com.cubic.backoffice.constants.BackOfficeGlobals;
import com.cubic.backoffice.utils.BackOfficeUtils;
import com.cubic.database.DataBaseUtil;
import com.cubic.nisjava.api.NWCustomerPOST;
import com.cubic.nisjava.api.CSCustomerCompleteRegistrationPOST;
import com.cubic.nisjava.api.CSOrderAdjustmentPOST;
import com.cubic.nisjava.api.CSSubSystemAccountLinkPOST;
import com.cubic.nisjava.apiobjects.CSCustomerCompleteRegistrationResp;
import com.cubic.nisjava.apiobjects.NWCustomerResp;
import com.cubic.nisjava.apiobjects.WSAddressContainer;
import com.cubic.nisjava.apiobjects.WSAddress_;
import com.cubic.nisjava.apiobjects.WSCompleteRegistrationResponse;
import com.cubic.nisjava.apiobjects.WSCreateCustomerResponse;
import com.cubic.nisjava.apiobjects.WSCustomerInfo;
import com.cubic.nisjava.apiobjects.WSCustomerInfoContainer;
import com.cubic.nisjava.apiobjects.WSCustomerPostAddressResponse;
import com.cubic.nisjava.apiobjects.WSDependencyLists;
import com.cubic.nisjava.apiobjects.WSHdr;
import com.cubic.nisjava.apiobjects.WSPrevalidateResponse;
import com.cubic.nisjava.apiobjects.WSSecurityQuestion;
import com.cubic.nisjava.apiobjects.WSSecurityQuestionList;
import com.cubic.nisjava.constants.NISGlobals;
import com.cubic.nisjava.apiobjects.CSOrderAdjustmentResp;
import com.cubic.oamjava.objects.OAMAccount;
import com.cubic.oamjava.utils.OAMUtils;
import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;

/**
 * NISUtils
 * Implements NIS-specific utilities and helper functions 
 * @author romeroo
 *
 */
public class NISUtils {

	public static final String CLASS_NAME = "NISUtils";
	private static final Logger LOG = Logger.getLogger(CLASS_NAME);
	
	/**
	 * oamAdjustBalance()
	 * Utility function to Adjust the Balance of a OneAccount account
	 * @param oneAccountId - OneAccount ID
	 * @param adjustAmount - Adjust amount (in decimal notation)
	 * @return Response Code string
	 */
	public static String oamAdjustBalance(DataBaseUtil dataBaseUtil, String oneAccountId, int adjustAmount, RESTActions actions) {
		String uid = UUID.randomUUID().toString();
		String unregisteredEmail = "";
		String deviceId = NISGlobals.NIS_DEVICE_ID;
		String clientRefId = deviceId + ":" + BackOfficeUtils.generateRandomString(BackOfficeGlobals.BACKOFFICE_CLIENT_REF_ID_LENGTH - deviceId.length() - 1);
		List<WSAdjustProductLineItem> productLineItems = null;
		WSAdjustProductLineItem productLineItem = null;
		WSAdjustProduct product = null;
		String notes = BackOfficeGlobals.BACKOFFICE_OAM_ADJUST_NOTES_TEXT;
		String purseId = "";
		int financiallyResponsibleOperatorId = BackOfficeGlobals.BACKOFFICE_DEFAULT_OAM_OPERATOR_ID;
		OAMAccount accountObj = null;
		CSOrderAdjustmentResp respObj = null;
		
		LOG.info("##### Adjusting Balance to: " + adjustAmount + ", for OneAccount ID: " + oneAccountId + "\n");
		
		// Get all values needed for the request
		purseId = OAMUtils.oamGetPurseId(dataBaseUtil, oneAccountId);
		accountObj = OAMUtils.oamGetAccount(dataBaseUtil, oneAccountId);
		product = new WSAdjustOneAccountValue(adjustAmount, BackOfficeGlobals.BACKOFFICE_ADJUST_PRODUCT_TYPE_OAM_VALUE, oneAccountId, purseId);
		
		productLineItem = new WSAdjustProductLineItem(product);
		productLineItems = Arrays.asList(productLineItem);
		
		respObj = CSOrderAdjustmentPOST.sendReq(accountObj.getCustomerId(), unregisteredEmail, clientRefId, productLineItems, uid, notes, financiallyResponsibleOperatorId, actions);
		LOG.info("Returning Response Code: " + respObj.getResponseCode() + "\n");
		
		return respObj.getResponseCode();
	}
	
	/**
	 * oamLinkABPAccount()
	 * Utility function that links an ABP Transit Account with a OneAccount account
	 * @param oneAccountId - Unique identifier for the customer's One Account
	 * @param abpTransitAccountId - ABP Transit Account ID
	 */
	public static void oamLinkABPAccount(String oneAccountId, String abpTransitAccountId, RESTActions actions) {
		// Create unique Nickname for ABP Transit Account
		String subsystemAccountNickname = BackOfficeGlobals.BACKOFFICE_OAM_ACCOUNT_NICKNAME_PREFIX + abpTransitAccountId;
		
		LOG.info("##### Linking ABP Transit Account: " + abpTransitAccountId + " with OneAccount ID: " + oneAccountId + ", using Nickname: " + subsystemAccountNickname);
		
		// Link accounts
		CSSubSystemAccountLinkPOST.sendReq(oneAccountId, BackOfficeGlobals.BACKOFFICE_SUBSYSTEM_ABP, abpTransitAccountId, subsystemAccountNickname, actions);
	}
	
	/**
	 * oamRegisterCustomer()
	 * Utility function that Completes a OneAccount Customer Registration
	 * @param customerId - Unique identifier for the customer
	 * @return Complete Customer Registration Response Object (JSON)
	 */
	public static CSCustomerCompleteRegistrationResp oamRegisterCustomer(String customerId, RESTActions actions) {
		LOG.info("##### Completing OneAccount Customer Registration for Customer ID: " + customerId);
		
		return CSCustomerCompleteRegistrationPOST.sendReq(customerId, actions);
	}
	
	/**
	 * oamCreateCustomer()
	 * Utility function that creates a new OneAccount customer of the given
	 * Customer Type
	 * @param customerType - Type of OneAccount customer to create
	 * @return Object repesenting JSON structure of the Create Customer API Response
	 */
	public static NWCustomerResp oamCreateCustomer(String customerType, RESTActions actions) {
		NWCustomerResp respObj = null;
		WSCustomerContact contact = null;
		
		// Create "Generic" WSCustomerContactInfo
		contact = createWSCustomerContact();
		
		// Send Request
		respObj = NWCustomerPOST.sendReq(customerType, contact, actions);
		
		return respObj;
	}
	
	/**
	 * createWSCustomerContactInfo()
	 * Utility function that creates a generic WSCustomerContactInfo object that can
	 * be used in multiple requests
	 * @return Generic WSCustomerContactInfo object
	 */
	public static WSCustomerContact createWSCustomerContact() {
		// Contact info
		WSCustomerContact contact = null;
		String contactType = BackOfficeGlobals.BACKOFFICE_CONTACT_TYPE_PRIMARY;
		String emailName = BackOfficeUtils.generateRandomString(BackOfficeGlobals.BACKOFFICE_DEFAULT_EMAIL_NAME_LENGTH);
		String email = emailName + BackOfficeGlobals.BACKOFFICE_DEFAULT_EMAIL_ADDRESS_PROVIDER;
		String dateOfBirth = BackOfficeGlobals.BACKOFFICE_CUSTOMER_BIRTHDATE_ZULU;
		String username = email;
		String password = BackOfficeGlobals.BACKOFFICE_CUSTOMER_PASSWORD;
		WSAddress address = null;
		List<WSPhone> phone = null;
		WSName name = null;
		String pin = BackOfficeGlobals.BACKOFFICE_CUSTOMER_PIN;
		List<WSSecurityQA> securityQAs = null;
		WSPersonalIdentifier personalIdentifierInfo = null;
		String addressId = null;								// If address Object present this must be null and vice-versa
		
		// WSAddress fields
		String address1 = BackOfficeGlobals.BACKOFFICE_CUSTOMER_ADDRESS1;
		String address2 = BackOfficeGlobals.BACKOFFICE_CUSTOMER_ADDRESS2;
		String address3 = BackOfficeGlobals.BACKOFFICE_CUSTOMER_ADDRESS3;
		String city = BackOfficeGlobals.BACKOFFICE_CUSTOMER_CITY;
		String state = BackOfficeGlobals.BACKOFFICE_CUSTOMER_STATE;
		String postalCode = BackOfficeGlobals.BACKOFFICE_CUSTOMER_POSTAL_CODE;
		String country = BackOfficeGlobals.BACKOFFICE_CUSTOMER_COUNTRY;

		// Personal identifier
		personalIdentifierInfo = new WSPersonalIdentifier(emailName, BackOfficeGlobals.BACKOFFICE_PERSONAL_ID_TYPE_DRIVERS_LICENSE);
				
		LOG.info("##### Creating Generic WSCustomerContactInfo");
		
		// WSPhone fields
		WSPhone phone1 = new WSPhone(BackOfficeGlobals.BACKOFFICE_CUSTOMER_PHONE_NUMBER, BackOfficeGlobals.BACKOFFICE_PHONE_TYPE_MOBILE, country);
		phone = Arrays.asList(phone1);
		
		// WSSecurityQA fields are Optional

		// Create WSAddress
		address = new WSAddress(address1, address2, address3, city, state, postalCode, country);
		
		// Create WSName (use the randomly generated email "name" as First and Last Name. Suffix and Title optional)
		name = new WSName(emailName, emailName, "", "");
		
		// Create WSCustomerContact
		contact = new WSCustomerContact(contactType, name, address, addressId, phone, email, dateOfBirth, personalIdentifierInfo, username, password, pin, securityQAs);

		return contact;
	}
	
	/**
	 * Utility to build the NIS URL with HTTP or HTTPS using default NIS Environment variables
	 * @return
	 */
	public static String getURL() {
	    return getURL(BackOfficeGlobals.ENV.NIS_USE_HTTPS, BackOfficeGlobals.ENV.NIS_HOST, BackOfficeGlobals.ENV.NIS_PORT);
	}
	
	/**
	 * Utility to build the url with the given host and port. useHTTPS is the string version of a boolean value (true or
	 * false) to indicate if HTTPS will be used or not.
	 * @param useHTTPS
	 * @param host
	 * @param port
	 * @return
	 */
	public static String getURL(String useHTTPS, String host, String port) {
	    String url = "";
	    String urlPrefix = "";
	    
        if (useHTTPS != null && useHTTPS.equalsIgnoreCase("true")) {
            LOG.info("##### NIS USING HTTPS");
            urlPrefix = "https://";
        }
        else {     // Anything else
            urlPrefix = "http://";
        }
        
        url = urlPrefix + host + ":" + port;
        LOG.info("##### url: " + url);
        
        return url;
	}
	
	/**
	 * Helper method to Create a Customer using NWAPIV2 calls.
	 * 
	 * @param restActions  The RESTActions object created by the @Test method.
	 * @return A WSCreateCustomerResponse object.
	 * @throws Throwable  Thrown if something went wrong.
	 */
	public static WSCreateCustomerResponse getCustomer(RESTActions restActions) throws Throwable {
        LOG.info("##### Creating GET request headers");
		Hashtable<String,String> headerTable = BackOfficeUtils.createRESTHeader(RESTConstants.APPLICATION_JSON);			
		
		String uniqueID = UUID.randomUUID().toString();
		String username = uniqueID + "@test.com";
		String password = "Pas5word!";
		
		LOG.info("##### Call the Prevalidate API");
		prevalidate(restActions, headerTable, username, password);
		
		LOG.info("##### Get a Security Question");
		String securityQuestion = securityQuestion(restActions, headerTable);
		
		LOG.info("##### Call the Create Customer API");
		WSCreateCustomerResponse customerResponse = createCustomer(restActions, headerTable, username, password, securityQuestion);
		String expectedCustomerId = customerResponse.getCustomerId();		
		
		LOG.info("##### Call the Complete Registration API");
		completeRegistration(restActions, headerTable, expectedCustomerId);		
		
		return customerResponse;
	}	
	
	/**
	 * Call the prevalidate API
	 * 
	 * @param restActions  The RESTActions object used by the test
	 * @param headerTable  The HTTP Headers
	 * @param username	The username
	 * @param password	The password
	 * @throws Throwable  Thrown if something goes wrong
	 */
	public static void prevalidate(RESTActions restActions, Hashtable<String,String> headerTable, String username, String password) throws Throwable {
		String sURL = buildPrevalidateURL();
        LOG.info("##### Built URL: " + sURL);
        
		String sPrevalidateRequestBody = buildPrevalidateRequestBody(username,password);            
        
		LOG.info("##### Making HTTP request to prevalidate the credentials...");
		ClientResponse clientResponse = restActions.postClientResponse(
				sURL, sPrevalidateRequestBody, headerTable, null,
				RESTConstants.APPLICATION_JSON);

		LOG.info("##### Verifying HTTP response code...");
		int status = clientResponse.getStatus();
		String msg = "WRONG HTTP RESPONSE CODE - EXPECTED 200, FOUND " + status;
		restActions.assertTrue(status == HttpURLConnection.HTTP_OK, msg);
		
		String response = clientResponse.getEntity(String.class);
		LOG.info("##### Got the response body: " + response);
		restActions.assertTrue(response != null, "RESPONSE IS NULL");	
		
		LOG.info("##### Parse the actual response...");
		Gson gson = new Gson();
		WSPrevalidateResponse prevalidateResponse = gson.fromJson(response, WSPrevalidateResponse.class);
		restActions.assertTrue( prevalidateResponse.getIsUsernameValid(), 
				"IsUsernameValid SHOULD BE TRUE BUT IT IS NOT" );
		restActions.assertTrue( prevalidateResponse.getIsPasswordValid(),
				"IsPasswordValid SHOULD BE TRUE BUT IT IS NOT" );
	}
	
	/**
	 * Helper method to build the Prevalidate URL.
	 * 
	 * @return the Prevalidate URL in String form
	 */
	public static String buildPrevalidateURL() {
		return NISUtils.getURL() + "/nis/nwapi/v2/customer/credentials/prevalidate";
	}
	
	/**
	 * Helper method to build the Prevalidate request body.
	 * 
	 * @param username  The username to use
	 * @param password  The password to use
	 * @return  the Prevalidate request body
	 */
	public static String buildPrevalidateRequestBody( String username, String password ) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter( sw );
		pw.println("{");
		pw.println("\"username\":\"" + username + "\",");
		pw.println("\"password\":\"" + password + "\"");
		pw.println("}");
		return sw.toString();
	}
	
	/**
	 * Get Security Question
	 * 
	 * @param restActions  The RESTActions object used by the test
	 * @param headerTable  The HTTP Headers
	 * @return  The security question
	 */
	public static String securityQuestion(RESTActions restActions, Hashtable<String,String> headerTable) {
		String securityQuestionsURL = buildSecurityQuestionsURL();
		ClientResponse clientResponse = restActions.getClientResponse(securityQuestionsURL, headerTable, null,
				RESTConstants.APPLICATION_JSON);

		LOG.info("##### Verifying HTTP response code...");
		int status = clientResponse.getStatus();
		String msg = "WRONG HTTP RESPONSE CODE - EXPECTED 200, FOUND " + status;
		restActions.assertTrue(status == HttpURLConnection.HTTP_OK, msg);
		
		String response = clientResponse.getEntity(String.class);
		LOG.info("##### Got the response body");
		restActions.assertTrue(response != null, "RESPONSE IS NULL");
		LOG.info( response );
		
		Gson gson = new Gson();
		WSSecurityQuestionList actualQuestionList = gson.fromJson(response, WSSecurityQuestionList.class);
		List<WSSecurityQuestion> securityQuestionList = actualQuestionList.getSecurityQuestions();
		WSSecurityQuestion securityQuestion = securityQuestionList.get(0);
		
		return securityQuestion.getName();
	}
	
	/**
	 * Build the URL used to get the security questions list.
	 * 
	 * @return The URL in string format.
	 */
	public static String buildSecurityQuestionsURL() {
        return NISUtils.getURL() + "/nis/nwapi/v2/customerservice/securityquestions";
	}
	
	/**
	 * Call the Create Customer API
	 * 
	 * @param restActions  The RESTActions object used by the test
	 * @param headerTable  The HTTP Headers
	 * @param username  The username
	 * @param password	The password
	 * @param securityQuestion  The security question
	 * @return  The customer Id
	 * @throws Throwable  Thrown if something goes wrong
	 */
	public static WSCreateCustomerResponse createCustomer(RESTActions restActions, Hashtable<String,String> headerTable, String username, String password, String securityQuestion ) throws Throwable {
		String createCustomerURL = buildCreateCustomerURL();
		LOG.info("##### Built URL: " + createCustomerURL);
		String createCustomerRequestBody = buildCreateCustomerRequestBody( username, password, securityQuestion );
		
		ClientResponse clientResponse = restActions.postClientResponse(
				createCustomerURL, createCustomerRequestBody, headerTable, null,
				RESTConstants.APPLICATION_JSON);
		
		LOG.info("##### Verifying HTTP response code...");
		int status = clientResponse.getStatus();
		String msg = "WRONG HTTP RESPONSE CODE - EXPECTED 200, FOUND " + status;
		restActions.assertTrue(status == HttpURLConnection.HTTP_OK, msg);
		
		String response = clientResponse.getEntity(String.class);
		LOG.info("##### Got the response body");
		restActions.assertTrue(response != null, "RESPONSE IS NULL");
		LOG.info( response );
		
		Gson gson = new Gson();
		WSCreateCustomerResponse createCustomerResponse = gson.fromJson(response, WSCreateCustomerResponse.class);
		restActions.assertTrue(createCustomerResponse != null, "createCustomerResponse IS NULL BUT SHOULD NOT BE NULL");
		String customerId = createCustomerResponse.getCustomerId();
		restActions.assertTrue(customerId != null, "customerId IS NULL BUT SHOULD NOT BE NULL");
		
		String contactId = createCustomerResponse.getContactId();
		restActions.assertTrue(contactId != null, "contactId IS NULL BUT SHOULD NOT BE NULL");
		
		Integer oneAccountId = createCustomerResponse.getOneAccountId();
		restActions.assertTrue(oneAccountId != null, "oneAccountId IS NULL BUT SHOULD NOT BE NULL");
		
		return createCustomerResponse;
	}
	
	/**
	 * Helper method to build the Create Customer URL.
	 * 
	 * @return the Create Customer URL in String form
	 */
	public static String buildCreateCustomerURL() {
        return NISUtils.getURL() + "/nis/nwapi/v2/customer";
	}
	
	/**
	 * Helper method to build the Create Customer request body.
	 * 
	 * @param username  The username value
	 * @param password  The password value
	 * @param securityQuestion  The security question
	 * @return the Create Customer request body in String form
	 */
	public static String buildCreateCustomerRequestBody(String username, String password, String securityQuestion) {
		String email = username;
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter( sw );
		pw.println("{");
		pw.println("    \"customerType\":\"Traveler\",");
		pw.println("    \"contact\": {");
		pw.println("        \"contactType\":\"Primary\",");
		pw.println("        \"name\": {");
		pw.println("            \"firstName\":\"Monika\",");
		pw.println("            \"middleInitial\":null,");
		pw.println("            \"lastName\":\"Mest\",");
		pw.println("            \"nameSuffixId\":null,");
		pw.println("            \"title\":null");
		pw.println("        },");
		pw.println("        \"address\": {");
		pw.println("            \"address1\":\"123 broadway\",");
		pw.println("            \"address2\":null,");
		pw.println("            \"city\":\"san diego\",");
		pw.println("            \"postalCode\":\"92122\",");
		pw.println("            \"state\":\"CA\",");
		pw.println("            \"country\":\"US\"");
		pw.println("        },");
		pw.println("        \"addressId\":null,");
		pw.println("        \"phone\": [");
		pw.println("           {");
		pw.println("              \"number\":\"8596140263\",");
		pw.println("              \"type\":\"M\",");
		pw.println("              \"country\":\"US\",");
		pw.println("              \"displayNumber\":null");		
		pw.println("           }");
		pw.println("        ],");
		pw.println("        \"email\":\"" + email + "\",");
		pw.println("        \"dateOfBirth\":\"1980-01-02\",");
		pw.println("        \"personalIdentifierInfo\": {");
		pw.println("            \"personalIdentifier\":\"1\",");
		pw.println("            \"personalIdentifierType\":\"DriversLicense\"");
		pw.println("        },");
		pw.println("        \"username\":\"" + username + "\",");
		pw.println("        \"password\":\"" + password + "\",");
		pw.println("        \"pin\":\"1234\",");
		pw.println("        \"securityQAs\": [ {");
		pw.println("            \"securityQuestion\":\"" + securityQuestion + "\",");
		pw.println("            \"securityAnswer\":\"blue\"");
		pw.println("        } ]");
		pw.println("    }");
		pw.println("}");
		return sw.toString();
	}
	
	/**
	 * Call the completeregistration API
	 * 
	 * @param restActions  The RESTActions object used by the test
	 * @param headerTable  The HTTP Headers
	 * @param customerIdn  The customer Id
	 * @throws Throwable  Thrown if something goes wrong
	 */
	public static void completeRegistration(RESTActions restActions, Hashtable<String,String> headerTable, String customerId) throws Throwable {

		String completeRegistrationURL = buildCompleteRegistrationURL( customerId );
		
		ClientResponse clientResponse = restActions.postClientResponse(
				completeRegistrationURL, "", headerTable, null,
				RESTConstants.APPLICATION_JSON);

		LOG.info("##### Verifying HTTP response code...");
		int status = clientResponse.getStatus();
		String msg = "WRONG HTTP RESPONSE CODE - EXPECTED 200, FOUND " + status;
		restActions.assertTrue(status == HttpURLConnection.HTTP_OK, msg);
		
		String response = clientResponse.getEntity(String.class);
		LOG.info("##### Got the response body");
		restActions.assertTrue(response != null, "RESPONSE IS NULL");
		LOG.info( response );
		
		Gson gson = new Gson();
		WSCompleteRegistrationResponse completeRegistrationResponse =
				gson.fromJson(response, WSCompleteRegistrationResponse.class);
		
		restActions.assertTrue(completeRegistrationResponse != null, 
				"completeRegistrationResponse IS NULL BUT IT SHOULD NOT BE");
		if ( null == completeRegistrationResponse ) {
			return;
		}
		
		WSHdr hdr = completeRegistrationResponse.getHdr();
		restActions.assertTrue(completeRegistrationResponse != null, 
				"hdr IS NULL BUT IT SHOULD NOT BE");			
		if ( null == hdr ) {
			return;
		}
		
		restActions.assertTrue( hdr.getUid() != null, 
				"UID IS NULL BUT SHOULD NOT BE");
		
		restActions.assertTrue( "Successful".equals( hdr.getResult()), 
				String.format("BAD RESULT: EXPCTED 'Successful' FOUND '%s'", hdr.getResult() ) );
		
		Integer oneAccountId = completeRegistrationResponse.getOneAccountId();
		restActions.assertTrue( oneAccountId != null, 
				"oneAccountId IS NULL BUT SHOULD NOT BE");
	}	
	
	/**
	 * Helper method to build the /completeregistration URL.
	 * 
	 * @param customerId  The customer-id to add to the URL
	 * @return  The URL in String form
	 */
	public static String buildCompleteRegistrationURL( String customerId ) {
        return NISUtils.getURL() + "/nis/nwapi/v2/customer/" + customerId + "/completeregistration";
	}
	
	/**
	 * Helper method to build the URL used to call the 'Get Customer' API.
	 * 
	 * @param data  The Test Data from the JSON input file.
	 * @return The URL in string form.
	 */
	public static String buildGetCustomerURL( String customerId ) {
        String sURL = NISUtils.getURL()
                + "/nis/nwapi/v2/customer/" + customerId + "?returnCustomerInfo=true";
		return sURL;
	}
	
	/**
	 * Call the GET Customer API.
	 * 
	 * @param restActions  The RESTActions object used by the test.
	 * @param headerTable  HTTP Headers 
	 * @param expectedCustomerId  The expected Customer Id.
	 */
	public static WSCustomerInfoContainer getCustomer( RESTActions restActions, Hashtable<String,String> headerTable, String expectedCustomerId ) {
		String sURL = buildGetCustomerURL(expectedCustomerId);	
		LOG.info("##### Built URL: " + sURL);
		
		LOG.info("##### Making HTTP request to get the EULA...");
		ClientResponse clientResponse = restActions.getClientResponse(sURL, headerTable, null,
				RESTConstants.APPLICATION_JSON);

		LOG.info("##### Verifying HTTP response code...");
		int actualHTTPResponseCode = clientResponse.getStatus();
		int expectedResponseCode = 200;
		String msg = String.format("WRONG HTTP RESPONSE CODE - EXPECTED %s, FOUND %s",
				expectedResponseCode, actualHTTPResponseCode);
		restActions.assertTrue(expectedResponseCode == actualHTTPResponseCode, msg);
		
		String response = clientResponse.getEntity(String.class);
		LOG.info("##### Got the response body:");
		LOG.info( response );
		restActions.assertTrue(response != null, "RESPONSE IS NULL BUT SHOULD NOT BE NULL");
		
		Gson gson = new Gson();
		WSCustomerInfoContainer customerInfoContainer =
				gson.fromJson(response, WSCustomerInfoContainer.class);
		
		restActions.assertTrue(customerInfoContainer != null, 
				"customerInfoContainer IS NULL BUT IT SHOULD NOT BE");
		
		if ( null == customerInfoContainer ) {
			return customerInfoContainer;
		}
		
		WSCustomerInfo customerInfo = customerInfoContainer.getCustomerInfo();
		
		restActions.assertTrue(customerInfo != null, 
				"customerInfo IS NULL BUT IT SHOULD NOT BE");			
		
		if ( null == customerInfo ) {
			return customerInfoContainer;
		}
		
		String actualCustomerId = customerInfo.getCustomerId();
		restActions.assertTrue(expectedCustomerId.equals(actualCustomerId),
				String.format(
						"BAD CUSTOMER ID - EXPECTED %s, FOUND %s",
						expectedCustomerId, actualCustomerId));
		
		return customerInfoContainer;
	}
	
	/**
	 * Build the URL used to get the security question.
	 * 
	 * @return The URL in string format.
	 */
	public static String buildSecurityQuestionURL() {
        return NISUtils.getURL() + "/nis/nwapi/v2/customer/securityquestion";
	}
	
	/**
	 * Helper method to build the Prevalidate request body.
	 * 
	 * @param username  The username to use
	 * @param password  The password to use
	 * @return  the Prevalidate request body
	 */
	public static String buildSecurityQuestionRequestBody( String username ) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter( sw );
		pw.println("{");
		pw.println("\"username\":\"" + username + "\"");
		pw.println("}");
		return sw.toString();
	}
	
	/**
	 * Helper method to lookup the Security Questions that belong to a username.
	 * 
	 * @param restActions  The RESTActions object used by the @Test method.
	 * @param data  The Test Data from the JSON input file
	 * @param headerTable  The HTTP Headers used to call the API.
	 * @param username  The username used to get the Security Questions.
	 * @return The response in String form.
	 * @throws Throwable  Thrown if something goes wrong.
	 */
	public static String securityQuestion(RESTActions restActions, Hashtable<String,String> data, Hashtable<String,String> headerTable, String username ) throws Throwable {

		String securityQuestionURL = buildSecurityQuestionURL();
		LOG.info("##### Built URL: " + securityQuestionURL);
		
		String requestBody = buildSecurityQuestionRequestBody( username );
		LOG.info("##### Built request body: " + requestBody);
		
		LOG.info("##### Making HTTP request to obtain the Security Question...");
		ClientResponse clientResponse = restActions.postClientResponse(
				securityQuestionURL, requestBody, headerTable, null,
				RESTConstants.APPLICATION_JSON);

		LOG.info("##### Verifying HTTP response code...");
		String sExpectedResponseCode = data.get("EXPECTED_RESPONSE_CODE");
		int iExpectedResponseCode = Integer.parseInt(sExpectedResponseCode);
		int iActualResponseCode = clientResponse.getStatus();
		String fmt = "WRONG HTTP RESPONSE CODE - EXPECTED %s, FOUND %s";
		String msg = String.format(fmt, iExpectedResponseCode, iActualResponseCode);
		restActions.assertTrue(iExpectedResponseCode == iActualResponseCode, msg);
		
		String response = clientResponse.getEntity(String.class);
		LOG.info("##### Got the response body");
		restActions.assertTrue(response != null, 
				"RESPONSE IS NULL BUT SHOULD NOT BE NULL");
		LOG.info( response );
		
		return response;
	}
	
	/**
	 * Build the URL used to call the username forgot API.
	 * 
	 * @return The URL in string format.
	 */
	public static String buildUsernameForgotURL() {
        return NISUtils.getURL() + "/nis/nwapi/v2/customer/username/forgot";
	}
	
	/**
	 * Helper method to build the 'Username Forgot' request body.
	 * 
	 * @param email  The email address to use
	 * @return  the 'Username Forgot' request body
	 */
	public static String buildUsernameForgotRequestBody( String email ) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter( sw );
		pw.println("{");
		pw.println("    \"email\":\"" + email + "\"");
		pw.println("}");
		return sw.toString();
	}
	
	/**
	 * Helper method to build the 'Username Forgot' request body.
	 * 
	 * @param email  The email address to use
	 * @return  the 'Username Forgot' request body
	 */

	public static String buildUsernameForgotRequestBody() {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter( sw );
		pw.println("{");
		pw.println("    \"postalCode\":\"92131\"");
		pw.println("}");
		return sw.toString();
	}	
	
	/**
	 * This is a Helper method to call the 'Username Forgot' API.
	 * 
	 * @param restActions  The RESTActions object used by the @Test method.
	 * @param data  The Test Data from the JSON input file.
	 * @param headerTable  The HTTP Headers using by the @Test method.
	 * @param email  The email address of the user we're searching for.
	 * @throws Throwable  Thrown if something goes wrong.
	 */
	public static ClientResponse usernameForgot(RESTActions restActions, Hashtable<String,String> data, Hashtable<String,String> headerTable, String email ) throws Throwable {

		String usernameForgotURL = buildUsernameForgotURL();
		LOG.info("##### Built URL: " + usernameForgotURL);
		
		String requestBody = buildUsernameForgotRequestBody( email );
		LOG.info("##### Built request body: " + requestBody);
		
		LOG.info("##### Making HTTP request to obtain the Username Forgot...");
		ClientResponse clientResponse = restActions.postClientResponse(
				usernameForgotURL, requestBody, headerTable, null,
				RESTConstants.APPLICATION_JSON);

		LOG.info("##### Verifying HTTP response code...");
		String sExpectedResponseCode = data.get("EXPECTED_RESPONSE_CODE");
		int iExpectedResponseCode = Integer.parseInt(sExpectedResponseCode);
		int iActualResponseCode = clientResponse.getStatus();
		String fmt = "WRONG HTTP RESPONSE CODE - EXPECTED %s, FOUND %s";
		String msg = String.format(fmt, iExpectedResponseCode, iActualResponseCode);
		restActions.assertTrue(iExpectedResponseCode == iActualResponseCode, msg);
		
		return clientResponse;
	}
	
	/**
	 * This is a Helper method to call the 'Username Forgot' API.
	 * 
	 * @param restActions  The RESTActions object used by the @Test method.
	 * @param data  The Test Data from the JSON input file.
	 * @param headerTable  The HTTP Headers using by the @Test method.
	 * @param email  The email address of the user we're searching for.
	 * @throws Throwable  Thrown if something goes wrong.
	 */
	public static ClientResponse usernameForgotAlt(RESTActions restActions, Hashtable<String,String> data, Hashtable<String,String> headerTable, String email ) throws Throwable {

		String usernameForgotURL = buildUsernameForgotURL();
		LOG.info("##### Built URL: " + usernameForgotURL);
		
		String requestBody = buildUsernameForgotRequestBody();
		LOG.info("##### Built request body: " + requestBody);
		
		LOG.info("##### Making HTTP request to obtain the Username Forgot...");
		ClientResponse clientResponse = restActions.postClientResponse(
				usernameForgotURL, requestBody, headerTable, null,
				RESTConstants.APPLICATION_JSON);

		LOG.info("##### Verifying HTTP response code...");
		String sExpectedResponseCode = data.get("EXPECTED_RESPONSE_CODE");
		int iExpectedResponseCode = Integer.parseInt(sExpectedResponseCode);
		int iActualResponseCode = clientResponse.getStatus();
		String fmt = "WRONG HTTP RESPONSE CODE - EXPECTED %s, FOUND %s";
		String msg = String.format(fmt, iExpectedResponseCode, iActualResponseCode);
		restActions.assertTrue(iExpectedResponseCode == iActualResponseCode, msg);
		
		return clientResponse;
	}
	
	/**
	 * Helper method to build the URL used to call the 'Get Address' API.
	 * 
	 * @see GET http://10.252.1.21:8201/nis/nwapi/v2/customer/<customer-id>/address/<address-id>
	 * 
	 * @param data  The Test Data from the JSON input file.
	 * @return The URL in string form.
	 */
	public static String buildGetAddressURL( String customerId, String addressId ) {
        String sURL = NISUtils.getURL()
                + "/nis/nwapi/v2/customer/" + customerId + "/address/" + addressId;
		return sURL;
	}
	
	/**
	 * Call the GET Customer Address API.
	 * 
	 * @see GET http://10.252.1.21:8201/nis/nwapi/v2/customer/<customer-id>/address/<address-id>
	 * 
	 * @param restActions  The RESTActions object used by the test.
	 * @param headerTable  HTTP Headers 
	 * @param expectedCustomerId  The expected Customer Id.
	 */
	public static void getCustomerAddress( RESTActions restActions, Hashtable<String,String> data, Hashtable<String,String> headerTable, String expectedCustomerId, WSAddress_ expectedAddress ) {
		String sURL = buildGetAddressURL(expectedCustomerId, expectedAddress.getAddressId());	
		LOG.info("##### Built URL: " + sURL);
		
		LOG.info("##### Making HTTP request to get the Customer Address...");
		ClientResponse clientResponse = restActions.getClientResponse(sURL, headerTable, null,
				RESTConstants.APPLICATION_JSON);

		LOG.info("##### Verifying HTTP response code...");
		String sExpectedStatus = data.get("EXPECTED_HTTP_RESPONSE_CODE");
		int expectedStatus = Integer.parseInt(sExpectedStatus);
		int actualStatus = clientResponse.getStatus();
		String msg =  String.format("WRONG HTTP RESPONSE CODE - EXPECTED %s, FOUND %s", 
				expectedStatus, actualStatus);
		restActions.assertTrue(expectedStatus == actualStatus, msg);
		
		String response = clientResponse.getEntity(String.class);
		LOG.info("##### Got the response body:");
		LOG.info( response );
		restActions.assertTrue(response != null, "RESPONSE IS NULL BUT SHOULD NOT BE NULL");
		
		LOG.info("##### Parsing the response body...");
		Gson gson = new Gson();
		WSAddressContainer addressContainer =
				gson.fromJson(response, WSAddressContainer.class);
		
		restActions.assertTrue(addressContainer != null, 
				"addressContainer IS NULL BUT IT SHOULD NOT BE NULL");
		
		if ( null == addressContainer ) {
			return;
		}
		
		restActions.assertTrue(addressContainer.getAddress() != null, 
				"addressContainer.getAddress() IS NULL BUT IT SHOULD NOT BE NULL");
		
		if ( null == addressContainer.getAddress() ) {
			return;
		}
		
		LOG.info("##### Comparing the expected Address with the actual Address...");
		String actualAddressId = addressContainer.getAddress().getAddressId();
		String expectedAddressId = expectedAddress.getAddressId();
		restActions.assertTrue(expectedAddressId.equals(actualAddressId), 
				String.format("BAD ADDRESS ID - EXPECTED %s FOUND %s", 
						expectedAddressId, actualAddressId));
		
		String actualAddress1 = addressContainer.getAddress().getAddress1();
		String expectedAddress1 = expectedAddress.getAddress1();
		restActions.assertTrue(expectedAddress1.equals(actualAddress1), 
				String.format("BAD ADDRESS 1 - EXPECTED %s FOUND %s", 
						expectedAddress1, actualAddress1));
		
		String actualCity = addressContainer.getAddress().getCity();
		String expectedCity = expectedAddress.getCity();
		restActions.assertTrue(expectedCity.equals(actualCity), 
				String.format("BAD CITY - EXPECTED %s FOUND %s", 
						expectedCity, actualCity));
		
		String actualState = addressContainer.getAddress().getState();
		String expectedState = expectedAddress.getState();
		restActions.assertTrue(expectedState.equals(actualState), 
				String.format("BAD STATE - EXPECTED %s FOUND %s", 
						expectedState, actualState));
		
		String actualPostalCode = addressContainer.getAddress().getPostalCode();
		String expectedPostalCode = expectedAddress.getPostalCode();
		restActions.assertTrue(expectedPostalCode.equals(actualPostalCode), 
				String.format("BAD PostalCode - EXPECTED %s FOUND %s", 
						expectedPostalCode, actualPostalCode));
		
		String actualCountry = addressContainer.getAddress().getCountry();
		String expectedCountry = expectedAddress.getCountry();
		restActions.assertTrue(expectedCountry.equals(actualCountry), 
				String.format("BAD Country - EXPECTED %s FOUND %s", 
						expectedCountry, actualCountry));
	}
	
	/**
	 * Helper method to build the URL used to call the 'Post Address' API.
	 * 
	 * @see POST http://10.252.1.21:8201/nis/nwapi/v2/customer/<customer-id>/address
	 * 
	 * @param data  The Test Data from the JSON input file.
	 * @return The URL in string form.
	 */
	public static String buildPostAddressURL( String customerId ) {
        String sURL = NISUtils.getURL()
                + "/nis/nwapi/v2/customer/" + customerId + "/address";
		return sURL;
	}
	
	/**
	 * Helper method to build the Request Body for the 'Post Address' API.
	 * 
	 * @return  The 'Post Address' request Body in String form.
	 */
	public static String buildPostAddressRequestBody() {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter( sw );
		pw.println("{");
		pw.println("    \"address\": {");
		pw.println("        \"address1\":\"12345 Ramsfell Court\",");
		pw.println("        \"address2\":null,");
		pw.println("        \"city\":\"San Diego\",");
		pw.println("        \"postalCode\":\"92131\",");
		pw.println("        \"state\":\"CA\",");
		pw.println("        \"country\":\"US\"");			
		pw.println("    }");
		pw.println("}");
		return sw.toString();
	}
	
	/**
	 * Helper method to call the /customer/<customer-id>/address API.
	 * 
	 * @see POST http://10.252.1.21:8201/nis/nwapi/v2/customer/<customer-id>/address 
	 * 
	 * @param restActions  The RESTActions object created by the @Test method.
	 * @param data  The Test Data from the JSON input file.
	 * @param headerTable  The HTTP Headers created by the @Test method.
	 * @param expectedCustomerId  The customer Id used to build the URL.
	 * @return a WSCustomerPostAddressResponse object.
	 * @throws Throwable  Thrown if something goes wrong.
	 */
	public static WSCustomerPostAddressResponse addCustomerAddress(
			RESTActions restActions, Hashtable<String,String> data, 
			Hashtable<String,String> headerTable, String expectedCustomerId ) throws Throwable {
		String sURL = buildPostAddressURL(expectedCustomerId);	
		LOG.info("##### Built URL: " + sURL);
		
		String requestBody = buildPostAddressRequestBody();
		
		LOG.info("##### Making HTTP request to Add the address...");
		ClientResponse clientResponse = restActions.postClientResponse(sURL, requestBody, headerTable, null,
				RESTConstants.APPLICATION_JSON);

		LOG.info("##### Verifying HTTP response code...");
		String sExpectedStatus = data.get("EXPECTED_HTTP_RESPONSE_CODE");
		int expectedStatus = Integer.parseInt(sExpectedStatus);
		int actualStatus = clientResponse.getStatus();
		String msg =  String.format("WRONG HTTP RESPONSE CODE - EXPECTED %s, FOUND %s",
				expectedStatus, actualStatus);
		restActions.assertTrue(expectedStatus == actualStatus, msg);
		
		String response = clientResponse.getEntity(String.class);
		LOG.info("##### Got the response body:");
		LOG.info( response );
		restActions.assertTrue(response != null, "RESPONSE IS NULL BUT SHOULD NOT BE NULL");
		
		Gson gson = new Gson();
		WSCustomerPostAddressResponse postAddressResponse =
				gson.fromJson(response, WSCustomerPostAddressResponse.class);
		
		return postAddressResponse;
	}
	
	/**
	 * Build the URL of the customer/<customer-id>/address/<address-id> API.
	 * 
	 * @see PUT http://10.252.1.21:8201/nis/nwapi/v2/customer/4E5885AA-9179-E811-80CC-000D3A36F32A/address/7B5885AA-9179-E811-80CC-000D3A36F32A
	 * 
	 * @param customerId  The customer Id to use to build the URL.
	 * @param addressId  The address Id to use the build the URL.
	 * @return The URL in String form.
	 */
	public static String buildCustomerAddressURL(String customerId, String addressId) {
        String sURL = "http://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT
                + "/nis/nwapi/v2/customer/" + customerId + "/address/" + addressId;
		return sURL;
	}	
	
	/**
	 * This method will build the URL of the PUT customer/<customerId>/address/<addressId> API,
	 * then build a Request Body containing a Address elements, then PUT the request body to
	 * the URL.  Finally, it will verify that the HTTP Response Code is 204 No Content.
	 * 
	 * @see PUT http://10.252.1.21:8201/nis/nwapi/v2/customer/4E5885AA-9179-E811-80CC-000D3A36F32A/address/7B5885AA-9179-E811-80CC-000D3A36F32A
	 *
	 * @param restActions  The RESTActions object created in the @Test method.
	 * @param data  the Test Data read from the JSON input file.
	 * @param headerTable  Set of HTTP Headers to use.
	 * @param customerId  The customer Id to build the URL. 
	 * @param addressId  The address Id to build the URL.
	 * @throws Throwable  Thrown if something goes wrong.
	 */
	public static void updateCustomerAddress( RESTActions restActions, Hashtable<String,String> data, 
			Hashtable<String,String> headerTable, String customerId, String addressId ) throws Throwable {
		int iExpectedResponseCode = 204;
		
		String updateAddressURL = buildCustomerAddressURL( customerId, addressId );
		LOG.info("##### Built URL: " + updateAddressURL);
		
		String updateAddressRequestBody = buildPostAddressRequestBody();
		LOG.info("##### Built request body: " + updateAddressRequestBody);
		
		LOG.info("##### Making HTTP request to the PUT Address API...");
		ClientResponse clientResponse = restActions.putClientResponse(
				updateAddressURL, updateAddressRequestBody, headerTable, null,
				RESTConstants.APPLICATION_JSON);

		LOG.info("##### Verifying HTTP response code...");
		int iActualResponseCode = clientResponse.getStatus();
		String msg = String.format("WRONG HTTP RESPONSE CODE - EXPECTED %s, FOUND %s",
				iExpectedResponseCode, iActualResponseCode);
		restActions.assertTrue(iExpectedResponseCode == iActualResponseCode, msg);
	}
	
	/**
	 * Helper method to build the customer/<customer-id>/address/<address-id>/dependencies URL.
	 * 
	 * @param customerId  The customer Id used to build the URL.
	 * @param addressId  The address Id used to build the URL.
	 * @return  The URL, in String form.
	 */
	public static String buildDependenciesURL(String customerId, String addressId) {
        String sURL = NISUtils.getURL()
                + "/nis/nwapi/v2/customer/" + customerId 
                + "/address/" + addressId + "/dependencies";
		return sURL;
	}
	
	/**
	 * Helper method to call the customer/<customer-id>/address/<address-id>/dependencies URL.
	 * 
	 * @param restActions  The RESTActions object.
	 * @param data  Test Data from the JSON input file.
	 * @param headerTable The HTTP Headers.
	 * @param customerId  The customer Id used to build the URL.
	 * @param addressId  The address Id used to build the URL.
	 * @return A WSDependencyLists object, for testing.
	 */
	public static WSDependencyLists getContactDependencies( 
			RESTActions restActions, Hashtable<String,String> data, 
			Hashtable<String,String> headerTable, String customerId, String addressId ) {
		
		String contactDependenciesURL = buildDependenciesURL( customerId, addressId );
		LOG.info("##### Built URL: " + contactDependenciesURL);
		
		LOG.info("##### Making HTTP request to get the Customer Address...");
		ClientResponse clientResponse = restActions.getClientResponse(
				contactDependenciesURL, headerTable, null,
				RESTConstants.APPLICATION_JSON);

		LOG.info("##### Verifying HTTP response code...");
		String sExpectedStatus = data.get("EXPECTED_HTTP_RESPONSE_CODE");
		int expectedStatus = Integer.parseInt(sExpectedStatus);
		int actualStatus = clientResponse.getStatus();
		String msg =  String.format("WRONG HTTP RESPONSE CODE - EXPECTED %s, FOUND %s", expectedStatus, actualStatus);
		restActions.assertTrue(expectedStatus == actualStatus, msg);
		
		String response = clientResponse.getEntity(String.class);
		LOG.info("##### Got the response body:");
		LOG.info( response );
		restActions.assertTrue(response != null, "RESPONSE IS NULL BUT SHOULD NOT BE NULL");
		
		LOG.info("##### Parsing the response body...");
		Gson gson = new Gson();
		WSDependencyLists dependencyLists =
				gson.fromJson(response, WSDependencyLists.class);
		
		return dependencyLists;
	}
	
	/**
	 * Helper method to build the URL used to call the 'Delete Address' API.
	 * 
	 * @see DELETE http://10.252.1.21:8201/nis/nwapi/v2/customer/<customer-id>/address/<address-id>
	 * 
	 * @param data  The Test Data from the JSON input file.
	 * @return The URL in string form.
	 */
	public static String buildDeleteAddressURL( String customerId, String addressId ) {
        String sURL = NISUtils.getURL()
                + "/nis/nwapi/v2/customer/" + customerId + "/address/" + addressId;
		return sURL;
	}
	
	/**
	 * 
	 * Helper method to call the DELETE http://10.252.1.21:8201/nis/nwapi/v2/customer/<customer-id>/address/<address-id> URL.
	 * 
	 * Please note that this method fails due to CCBO-13129.
	 * 
	 * @param restActions  The RESTActions object created by the @Test method.
	 * @param data  The Test Data from the JSON input file.
	 * @param headerTable  A set of HTTP Headers suitable for calling NIS.
	 * @param expectedCustomerId  The expected value of the customer Id used for building the API's URL.
	 * @param expectedAddressId  The expected value of the address Id used for building the API's URL.
	 * @throws Throwable  Thrown if something goes wrong.
	 */
	public static void deleteCustomerAddress(
			RESTActions restActions, Hashtable<String,String> data, Hashtable<String,String>headerTable,
			String expectedCustomerId, String expectedAddressId ) throws Throwable {
		String sURL = buildDeleteAddressURL(expectedCustomerId, expectedAddressId);	
		LOG.info("##### Built URL: " + sURL);
		
		LOG.info("##### Making HTTP request to get the EULA...");
		ClientResponse clientResponse = restActions.deleteClientResponse(sURL, headerTable, null,
				RESTConstants.APPLICATION_JSON);

		LOG.info("##### Verifying HTTP response code...");
		String sExpectedStatus = data.get("EXPECTED_HTTP_RESPONSE_CODE");
		int expectedStatus = Integer.parseInt(sExpectedStatus);
		int actualStatus = clientResponse.getStatus();
		String msg =  String.format("WRONG HTTP RESPONSE CODE - EXPECTED %s, FOUND %s",
				expectedStatus, actualStatus);
		restActions.assertTrue(expectedStatus == actualStatus, msg);
		
		String response = clientResponse.getEntity(String.class);
		LOG.info("##### Got the response body:");
		LOG.info( response );
		restActions.assertTrue(response != null, "RESPONSE IS NULL BUT SHOULD NOT BE NULL");
	}

	/**
	 * Helper method to build the Get Contact URL
	 * 
	 * @param customerId The customerId to use to build the URL
	 * @param contactId  The contactId to use to build the URL
	 * @return  The Get Contact URL in String form
	 */
	public static String buildGetContactURL( String customerId, String contactId ) {
        String sURL = NISUtils.getURL()
                + "/nis/nwapi/v2/customer/" + customerId
                + "/contact/" + contactId;
		return sURL;
	}
	
	/**
	 * Helper method to call the API
	 * 
	 * GET http://10.252.1.21:8201/nis/nwapi/v2/customer/2B911170-4690-E811-80CC-000D3A36F32A/contact/33911170-4690-E811-80CC-000D3A36F32A
	 * 
	 * @param restActions  The RESTActions object created by the calling @Test method
	 * @param headerTable  The set of Headers created by the calling @Test method
	 * @param customerId The CustomerId created by the calling @Test method
	 * @param contactId  The ContactId created by the calling @Test method
	 * @return A WSContactContainer object, initialized by parsing the HTTP Response.
	 * @throws Throwable  Thrown if something goes wrong
	 */
	public static String getContact(RESTActions restActions, Hashtable<String,String> headerTable,
			Hashtable<String,String> data ) throws Throwable {
		
		String customerId = data.get("CUSTOMER_ID");
		String contactId = data.get("CONTACT_ID");
		
		String getContactURL = buildGetContactURL( customerId, contactId );
		
		ClientResponse clientResponse = restActions.getClientResponse(
				getContactURL, headerTable, null, RESTConstants.APPLICATION_JSON);

		LOG.info("##### Verifying HTTP response code...");
		String sExpectedStatus = data.get("EXPECTED_HTTP_RESPONSE_CODE");
		int expectedStatus = Integer.parseInt(sExpectedStatus);
		int actualStatus = clientResponse.getStatus();
		String msg =  String.format("WRONG HTTP RESPONSE CODE - EXPECTED %s, FOUND %s",
				expectedStatus, actualStatus);
		restActions.assertTrue(expectedStatus == actualStatus, msg);
		
		String response = clientResponse.getEntity(String.class);
		LOG.info("##### Got the response body");
		restActions.assertTrue(response != null, "RESPONSE IS NULL BUT SHOULD NOT BE NULL");
		LOG.info( response );
		
		return response;
	}
	
	/**
	 * Helper method to build the Request Body for the
	 * PATCH customer/<customerid>/contact/<contact-id>
	 * API.
	 * 
	 * @return  The Request Body in String form.
	 */
	public static String buildPatchContactRequestBody() {
		String uniqueID = UUID.randomUUID().toString();
		String username = uniqueID + "@cubic.com";	
		
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter( sw );
		pw.println("{");
		pw.println("    \"contactType\": \"Primary\",");
		pw.println("    \"username\":\"" + username + "\"");
		pw.println("}");
		return sw.toString();
	}	
	
	/**
	 * Helper method call by the @Test method to build the URL of the
	 * PATCH customer/<customerid>/contact/<contact-id> API, and build
	 * the Request Body to send, then call the API.
	 * 
	 * @param restActions  The RESTActions object created by the @Test method.
	 * @param headerTable  The HTTP Headers used to send the message.
	 * @param data  The test Data from the JSON input file.
	 * @throws Throwable  Thrown if something went wrong.
	 */
	public static void patchContact(RESTActions restActions,Hashtable<String,String> headerTable,Hashtable<String, String> data) throws Throwable {
		String customerId = data.get("CUSTOMER_ID");
		String contactId = data.get("CONTACT_ID");
		
		String patchContactURL = buildGetContactURL( customerId, contactId );
        LOG.info("##### Built URL: " + patchContactURL);
        
		String patchContactRequestBody = buildPatchContactRequestBody();            
        
		LOG.info("##### Making HTTP request to prevalidate the credentials...");
		ClientResponse clientResponse = restActions.patchClientResponse(
				patchContactURL, patchContactRequestBody, headerTable, null,
				RESTConstants.APPLICATION_JSON);

		LOG.info("##### Verifying HTTP response code...");
		String sExpectedStatus = data.get("EXPECTED_HTTP_RESPONSE_CODE");
		int expectedStatus = Integer.parseInt(sExpectedStatus);
		int actualStatus = clientResponse.getStatus();
		String msg =  String.format("WRONG HTTP RESPONSE CODE - EXPECTED %s, FOUND %s",
				expectedStatus, actualStatus);
		restActions.assertTrue(expectedStatus == actualStatus, msg);
	}
}
