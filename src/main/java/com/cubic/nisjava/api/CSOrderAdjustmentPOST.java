package com.cubic.nisjava.api;

import java.util.Hashtable;
import java.util.List;

import org.apache.log4j.Logger;

import com.cubic.accelerators.RESTActions;
import com.cubic.accelerators.RESTConstants;
import com.cubic.backoffice.apiobjects.WSAdjustProductLineItem;
import com.cubic.backoffice.constants.BackOfficeGlobals;
import com.cubic.backoffice.utils.BackOfficeUtils;
import com.cubic.logutils.Log4jUtil;
import com.cubic.nisjava.apiobjects.CSOrderAdjustment;
import com.cubic.nisjava.apiobjects.CSOrderAdjustmentResp;
import com.google.gson.Gson;

/**
 * CSOrderAdjustmentPOST
 * Implements Adjustment Order POST Request via NIS Customer Service API
 */
public class CSOrderAdjustmentPOST {

	public static final String CLASS_NAME = "CSOrderAdjustmentPOST";
	private static final Logger LOG = Logger.getLogger(CLASS_NAME);
	
	/**
	 * sendReq()
	 * Sends the Create Customer Request by calling REST POST
	 * @param customerType - Customer Type
	 * @param contact - WSCustomerContactInfo Object with contact details
	 * @return Response Object (JSON)
	 */
	public static CSOrderAdjustmentResp sendReq(String customerId, String unregisteredEmail, String clientRefId, List<WSAdjustProductLineItem> productLineItems,
			String reasonCode, String notes, int financiallyResponsibleOperatorId, RESTActions actions) {
		String jsonStr = "";
		String resp = "";
		CSOrderAdjustment jsonObj = null;
		CSOrderAdjustmentResp respObj = null;
		Hashtable<String, String> headerMap = null;
		Class<CSOrderAdjustmentResp> jsonClass = CSOrderAdjustmentResp.class;
		
		// Create URL
		String url = "http://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/csapi/v1/order/adjustment";
		LOG.info("URL: " + url);
				
		try {
            // Build JSON Object
		    jsonObj = new CSOrderAdjustment(customerId, unregisteredEmail, clientRefId, productLineItems, reasonCode, notes, financiallyResponsibleOperatorId);
            
            // Get JSON String representation of the Object
            jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
            LOG.info("Converted JSON String: " + jsonStr);
            
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
