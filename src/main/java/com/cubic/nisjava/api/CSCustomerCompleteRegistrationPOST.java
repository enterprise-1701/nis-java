package com.cubic.nisjava.api;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.cubic.accelerators.RESTActions;
import com.cubic.accelerators.RESTConstants;
import com.cubic.backoffice.constants.BackOfficeGlobals;
import com.cubic.backoffice.utils.BackOfficeUtils;
import com.cubic.logutils.Log4jUtil;
import com.cubic.nisjava.apiobjects.CSCustomerCompleteRegistrationResp;
import com.google.gson.Gson;

public class CSCustomerCompleteRegistrationPOST {
	
	public static final String CLASS_NAME = "CSCustomerCompleteRegistrationPOST";
	private static final Logger LOG = Logger.getLogger(CLASS_NAME);
	
	/**
	 * sendReq()
	 * Sends the Complete Customer Registration Request by calling REST POST
	 * @param customerId - Customer ID
	 * @return Response Object (JSON)
	 */
	public static CSCustomerCompleteRegistrationResp sendReq(String customerId, RESTActions actions) {
		String jsonStr = "";
		String resp = "";
		CSCustomerCompleteRegistrationResp respObj = null;
		Hashtable<String, String> headerMap = null;
		Class<CSCustomerCompleteRegistrationResp> jsonClass = CSCustomerCompleteRegistrationResp.class;
		
		// Create URL
		String url = "http://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/csapi/v1/customer/" + customerId + "/completeregistration";
		LOG.info("URL: " + url);
		
		try {
            // Generate Header
            headerMap = BackOfficeUtils.createRESTHeader(RESTConstants.APPLICATION_TEXT);

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
