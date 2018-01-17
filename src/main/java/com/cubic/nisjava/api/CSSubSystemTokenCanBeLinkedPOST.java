package com.cubic.nisjava.api;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.cubic.accelerators.RESTActions;
import com.cubic.accelerators.RESTConstants;
import com.cubic.backoffice.apiobjects.WSTravelToken;
import com.cubic.backoffice.constants.BackOfficeGlobals;
import com.cubic.backoffice.utils.BackOfficeUtils;
import com.cubic.logutils.Log4jUtil;
import com.cubic.nisjava.apiobjects.CSSubSystemTokenCanBeLinked;
import com.cubic.nisjava.apiobjects.CSSubSystemTokenCanBeLinkedResp;
import com.google.gson.Gson;

/**
 * CSSubSystemTokenCanBeLinkedPOST
 * Implements Sub-System Token Can Be Linked POST Request via NIS Customer Service API
 */
public class CSSubSystemTokenCanBeLinkedPOST {

	public static final String CLASS_NAME = "CSSubSystemTokenCanBeLinkedPOST";
	private static final Logger LOG = Logger.getLogger(CLASS_NAME);
	
	/**
	 * sendReq()
	 * Sends the Create Customer Request by calling REST POST
	 * @param customerType - Customer Type
	 * @param contact - WSCustomerContactInfo Object with contact details
	 * @return Response Object (JSON)
	 */
	public static CSSubSystemTokenCanBeLinkedResp sendReq(String subsystemId, WSTravelToken travelToken, String foreignCustomerId,
			String foreignTokenSubsystem, boolean autoCreateFlag, RESTActions actions) {
		String jsonStr = "";
		String resp = "";
		CSSubSystemTokenCanBeLinked jsonObj = null;
		CSSubSystemTokenCanBeLinkedResp respObj = null;
		Hashtable<String, String> headerMap = null;
		Class<CSSubSystemTokenCanBeLinkedResp> jsonClass = CSSubSystemTokenCanBeLinkedResp.class;
		
		// Create URL
		String url = "http://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/csapi/v1/subsystem/" + subsystemId + "/traveltoken/canbelinked";
		LOG.info("URL: " + url);
				
		try {
		 // Build JSON Object
	        jsonObj = new CSSubSystemTokenCanBeLinked(subsystemId, travelToken, foreignCustomerId, foreignTokenSubsystem, autoCreateFlag);
            
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
