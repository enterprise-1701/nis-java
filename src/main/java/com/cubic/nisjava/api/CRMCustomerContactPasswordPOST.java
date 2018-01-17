package com.cubic.nisjava.api;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.cubic.accelerators.RESTActions;
import com.cubic.accelerators.RESTConstants;
import com.cubic.backoffice.constants.BackOfficeGlobals;
import com.cubic.backoffice.utils.BackOfficeUtils;
import com.cubic.logutils.Log4jUtil;
import com.cubic.nisjava.apiobjects.CRMCustomerContactPassword;

public class CRMCustomerContactPasswordPOST {	
	
	public static final String CLASS_NAME = "CRMCustomerContactPasswordPOST";
	private static final Logger LOG = Logger.getLogger(CLASS_NAME);
	
	/**
	 * sendReq()
	 * Sends the Create Customer Request by calling REST POST
	 * @param customerId - Unique identifier for the customer.
	 * @param contactId - 
	 * @param oldPassword - 
	 * @param newPassword - 
	 * No data is returned by this method.Common headers and http response codes are returned.
	 */
	public static void sendReq(String customerId, String contactId, String oldPassword, String newPassword, RESTActions actions) {
		String jsonStr = "";
		String resp = "";
		CRMCustomerContactPassword jsonObj = null;
		Hashtable<String, String> headerMap = null;
		
		// Create URL
		String url = "http://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/nwapi/v2/customer/" + customerId + "/contact/" + contactId + "/password";
		LOG.info("URL: " + url);
				
		try {
            // Build JSON Object
            jsonObj = new CRMCustomerContactPassword(customerId, contactId, oldPassword, newPassword);
            
            // Get JSON String representation of the Object
            jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
            LOG.info("Converted JSON String: " + jsonStr);
            
            // Generate Header
            headerMap = BackOfficeUtils.createRESTHeader(RESTConstants.APPLICATION_JSON);

            // Send request
            resp = actions.postJSONResponseAsString(url, jsonStr, headerMap, null);
            LOG.info("Response: \n" + resp);
            // NOTHING to deserialize
        }
        catch (Exception e) {
            LOG.error(Log4jUtil.getStackTrace(e));
        }
        catch (Throwable t) {
            LOG.error(Log4jUtil.getStackTrace(t));
        }
	}
}
