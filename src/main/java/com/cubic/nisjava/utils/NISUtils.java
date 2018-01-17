package com.cubic.nisjava.utils;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.cubic.accelerators.RESTActions;
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
import com.cubic.nisjava.constants.NISGlobals;
import com.cubic.nisjava.apiobjects.CSOrderAdjustmentResp;
import com.cubic.oamjava.objects.OAMAccount;
import com.cubic.oamjava.utils.OAMUtils;

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
}
