package com.cubic.nisjava.api;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.cubic.accelerators.RESTActions;
import com.cubic.accelerators.RESTConstants;
import com.cubic.backoffice.constants.BackOfficeGlobals;
import com.cubic.backoffice.utils.BackOfficeUtils;
import com.cubic.logutils.Log4jUtil;
import com.cubic.nisjava.apiobjects.NWCustomerCompleteRegistrationResp;
import com.google.gson.Gson;

/**
 * NWCustomerCompleteRegistrationPOST
 * Implements Customer Complete Registration POST Request via NIS Customer Service API
 */
public class NWCustomerCompleteRegistrationPOST {

	public static final String CLASS_NAME = "NWCustomerCompleteRegistrationPOST";
	private static final Logger LOG = Logger.getLogger(CLASS_NAME);
	
	/**
	 * sendReq()
	 * Sends the Complete Customer Registration Request by calling REST POST
	 * @param customerId - Customer ID
	 * @return Response Object (JSON)
	 */
	public static NWCustomerCompleteRegistrationResp sendReq(String customerId, RESTActions actions) {
		String jsonStr = "";
		String resp = "";
		NWCustomerCompleteRegistrationResp respObj = null;
		Hashtable<String, String> headerMap = null;
		Class<NWCustomerCompleteRegistrationResp> jsonClass = NWCustomerCompleteRegistrationResp.class;
		
		// Create URL
		String url = "http://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/nwapi/v2/customer/" + customerId + "/completeregistration";
		LOG.info("URL: " + url);
		
		try {
            // Generate Header
            headerMap = BackOfficeUtils.createRESTHeader(RESTConstants.APPLICATION_JSON);

            // Send request
            resp = actions.postJSONResponseAsString(url, jsonStr, headerMap, null);
            LOG.info("Response: \n" + resp);
            
            // De-serialize the Response into a JSON Object
            Gson g = new Gson();
            respObj = g.fromJson(resp, jsonClass);
        }
        catch (Exception e) {
            LOG.error(Log4jUtil.getStackTrace(e));
        }
        catch (Throwable t) {
            LOG.error(Log4jUtil.getStackTrace(t));
        }
		
		return respObj;
	}
}
