package com.cubic.nisjava.api;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.cubic.accelerators.RESTActions;
import com.cubic.accelerators.RESTConstants;
import com.cubic.backoffice.constants.BackOfficeGlobals;
import com.cubic.backoffice.utils.BackOfficeUtils;
import com.cubic.logutils.Log4jUtil;
import com.cubic.nisjava.apiobjects.*;

public class CRMCustomerCredentialPrevalidatePOST {

	public static final String CLASS_NAME = "CRMCustomerCredentialPrevalidatePOST";
	private static final Logger LOG = Logger.getLogger(CLASS_NAME);
	
	/**
	 * sendReq()
	 * Sends the Complete Customer Authentication Request by calling REST POST
	 * @param username - 
	 * @param password - 
	 * @return Response Object (JSON)
	 */
	public static CRMCustomerCredentialPrevalidateResp sendReq(String username, String password, RESTActions actions) {
		String jsonStr = "";
		String resp = "";
		CRMCustomerAuthenticate jsonObj = null;    //CustomerAuthenticate obj is used to send request.
		CRMCustomerCredentialPrevalidateResp respObj = null;
		Hashtable<String, String> headerMap = null;
		Class<CRMCustomerCredentialPrevalidateResp> jsonClass = CRMCustomerCredentialPrevalidateResp.class;
		
		// Create URL
		String url = "http://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/nwapi/v2/customer/credentials/prevalidate";
		LOG.info("URL: " + url);
             
		try {
            // Build JSON Object
            jsonObj = new CRMCustomerAuthenticate(username, password);
            
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
