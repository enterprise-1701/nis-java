package com.cubic.nisjava.utils;

import java.util.Hashtable;

import com.cubic.accelerators.RESTActions;
import com.cubic.accelerators.RESTConstants;
import com.cubic.backoffice.utils.BackOfficeUtils;
import com.sun.jersey.api.client.ClientResponse;

/**
 * A Utility class used to make HTTP Requests to NIS.
 */
public class HttpActionsUtil {

	/**
	 * Helper method to look up a Notification Detail.
	 *  
	 * @param data		The Test Data
	 * @param action	A RESTActions object
	 * @return			A ClientResponse object
	 */
	public static ClientResponse getNotificationDetailBasic(Hashtable<String, String> data, RESTActions action) {
		String sURL = buildNISURLBasic( data );
		return getNotificationDetail( data, action, sURL );
	}	
	
	/**
	 * Helper method to look up a Notification Detail using the Notification Id.
	 *  
	 * @param data		The Test Data
	 * @param action	A RESTActions object
	 * @return			A ClientResponse object
	 */
	public static ClientResponse getNotificationDetailByNotificationId(Hashtable<String, String> data, RESTActions action) {
		String sURL = buildNISURLByNotificationId( data );
		return getNotificationDetail( data, action, sURL );
	}

	/**
	 * Helper method to look up a Notification Detail using the Customer Id and ContactId Id.
	 *  
	 * @param data		The Test Data
	 * @param action	A RESTActions object
	 * @return			A ClientResponse object
	 */
	public static ClientResponse getNotificationDetailErrors( Hashtable<String, String> data, RESTActions action ) {
		String sURL = buildNISURLErrors( data );
		return getNotificationDetail( data, action, sURL );
	}
	
	/**
	 * Helper method to look up a Notification Detail.
	 *  
	 * @param data		The Test Data
	 * @param action	A RESTActions object
	 * @param sURL		The URL to use to look up
	 * @return			A ClientResponse object
	 */
	private static ClientResponse getNotificationDetail( Hashtable<String, String> data, RESTActions action, String sURL ) {
		Hashtable<String, String> headerTable = BackOfficeUtils.createRESTHeader(RESTConstants.APPLICATION_JSON);	
		
		Hashtable<String, String> urlQueryParameters = new Hashtable<String, String>();
		
		// Make HTTP GET request
		ClientResponse clientResponse = action.getClientResponse(sURL, headerTable, urlQueryParameters, RESTConstants.APPLICATION_JSON);
		
		return clientResponse;
	}	
	
	/**
	 * Helper method to build a NIS URL used to look up the
	 * Notifications list.
	 *  
	 * @param data The Test Data
	 * @return The CNG URL
	 */
	protected static String buildNISURLBasic(Hashtable<String,String> data) {
		String cngHost = data.get("NIS_HOST");
		String cngPort = data.get("NIS_PORT");
		String customerId = data.get("CUSTOMER_ID");
		String cngFmt = data.get("NIS_URL_FMT");
		String sURL = String.format(cngFmt, cngHost, cngPort, customerId );
		return sURL;
	}
	
	/**
	 * Helper method to build a NIS URL used to look up the
	 * Notifications list using the Notification Id.
	 *  
	 * @param data The Test Data
	 * @return The CNG URL
	 */
	protected static String buildNISURLByNotificationId(Hashtable<String,String> data)
	{
		String nisHost = data.get("NIS_HOST");
		String nisPort = data.get("NIS_PORT");
		String customerId = data.get("CUSTOMER_ID");
		String nisFmt = data.get("NIS_URL_FMT");
		String notificationId = data.get("NOTIFICATION_ID");
		String sURL = String.format(nisFmt, nisHost, nisPort, customerId, notificationId );
		return sURL;
	}
	
	/**
	 * Helper method to build the NIS URL
	 * 
	 * @param data The Test Data
	 */
	protected static String buildNISURLErrors(Hashtable<String, String> data) {
		String cngHost = data.get("NIS_HOST");
		String cngPort = data.get("NIS_PORT");
		String customerId = data.get("CUSTOMER_ID");
		String cngFmt = data.get("NIS_URL_FMT");
		String contactId = data.get("CONTACT_ID");
		String sURL = String.format( cngFmt, cngHost, cngPort, customerId, contactId );
		return sURL;
	}	
}
