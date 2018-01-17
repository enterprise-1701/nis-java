package com.cubic.nisjava.api;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.cubic.accelerators.RESTActions;
import com.cubic.accelerators.RESTConstants;
import com.cubic.backoffice.constants.BackOfficeGlobals;
import com.cubic.backoffice.utils.BackOfficeUtils;
import com.cubic.logutils.Log4jUtil;
import com.cubic.nisjava.apiobjects.CSSubSystemAccountLink;

public class CSSubSystemAccountLinkPOST {

	public static final String CLASS_NAME = "CSSubSystemAccountLinkPOST";
	private static final Logger LOG = Logger.getLogger(CLASS_NAME);
	
	/**
	 * sendReq()
	 * Sends the Link account Request by calling REST POST. NOTE: This method does
	 * NOT return anything, hence the change in the expected HTTP Response Code
	 * @param customerType - Customer Type
	 * @param contact - WSCustomerContactInfo Object with contact details
	 * @return Response Object (JSON)
	 */
	public static void sendReq(String oneAccountId, String subsystemId, String accountRef, String subsystemAccountNickname, RESTActions actions) {
		String jsonStr = "";
		String resp = "";
		CSSubSystemAccountLink jsonObj = null;
		Hashtable<String, String> headerMap = null;
		
		// Create URL
		String url = "http://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/csapi/v1/oneaccount/" + oneAccountId + "/subsystem/" + subsystemId + "/subsystemaccount/" + accountRef + "/link";
		LOG.info("URL: " + url);
				
		try {
		    // Build JSON Object
	        jsonObj = new CSSubSystemAccountLink(oneAccountId, subsystemId, accountRef, subsystemAccountNickname);
            
            // Get JSON String representation of the Object
            jsonStr = BackOfficeUtils.getJSONFromObject(jsonObj);
            LOG.info("Converted JSON String: " + jsonStr);
            
            // Generate Header
            headerMap = BackOfficeUtils.createRESTHeader(RESTConstants.APPLICATION_JSON);

            // Send request
            resp = actions.postJSONResponseAsString(url, jsonStr, headerMap, null);
            LOG.info("Response: \n" + resp);
            // NOTHING to de-serialize
        }
        catch (Exception e) {
            LOG.error(Log4jUtil.getStackTrace(e));
        }
        catch (Throwable t) {
            LOG.error(Log4jUtil.getStackTrace(t));
        }
	}
}
