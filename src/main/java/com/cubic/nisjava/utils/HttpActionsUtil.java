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
	 * Notifications list using the Notification Id.
	 *  
	 * @param data The Test Data
	 * @return The CNG URL
	 */
	protected static String buildNISURLByNotificationId(Hashtable<String,String> data)
	{
		String customerId = data.get("CUSTOMER_ID");
		String nisFmt = data.get("NIS_URL_FMT");
		String notificationId = data.get("NOTIFICATION_ID");
		String sURL = NISUtils.getURL() + String.format(nisFmt, customerId, notificationId );
		return sURL;
	}	

}
