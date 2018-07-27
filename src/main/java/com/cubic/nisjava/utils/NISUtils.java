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
import com.cubic.nisjava.apiobjects.WSCompleteRegistrationResponse;
import com.cubic.nisjava.apiobjects.WSCreateCustomerResponse;
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
	private static void prevalidate(RESTActions restActions, Hashtable<String,String> headerTable, String username, String password) throws Throwable {
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
	private static String buildPrevalidateURL() {
		return NISUtils.getURL() + "/nis/nwapi/v2/customer/credentials/prevalidate";
	}
	
	/**
	 * Helper method to build the Prevalidate request body.
	 * 
	 * @param username  The username to use
	 * @param password  The password to use
	 * @return  the Prevalidate request body
	 */
	private static String buildPrevalidateRequestBody( String username, String password ) {
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
	private static String securityQuestion(RESTActions restActions, Hashtable<String,String> headerTable) {
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
	private static String buildSecurityQuestionsURL() {
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
	private static WSCreateCustomerResponse createCustomer(RESTActions restActions, Hashtable<String,String> headerTable, String username, String password, String securityQuestion ) throws Throwable {
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
	private static String buildCreateCustomerURL() {
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
	private static String buildCreateCustomerRequestBody(String username, String password, String securityQuestion) {
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
	private static void completeRegistration(RESTActions restActions, Hashtable<String,String> headerTable, String customerId) throws Throwable {

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
	private static String buildCompleteRegistrationURL( String customerId ) {
        return NISUtils.getURL() + "/nis/nwapi/v2/customer/" + customerId + "/completeregistration";
	}	
}
