/**
 * 
 */
package com.cubic.nisjava.api;

/**
 * @author 205974
 *
 */

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.cubic.accelerators.RESTActions;
import com.cubic.accelerators.RESTConstants;
import com.cubic.backoffice.utils.BackOfficeUtils;
import com.cubic.logutils.Log4jUtil;
import com.cubic.nisjava.apiobjects.*;

public class MerchantLoginPOST {

	public static final String CLASS_NAME = "MerchantLoginPOST";
	private static final Logger LOG = Logger.getLogger(CLASS_NAME);
       
	/**
	 * sendReq()
	 * Sends the Complete Customer Authentication Request by calling REST POST
	 * @param username
	 * @param password
	 * @return Response Object (JSON)
	 */
	
	public static MerchantLoginResp sendReq(String username, String password, RESTActions actions) {
		String jsonStr = "";
		String resp = "";
		MerchantLogin jsonObj = null;
		MerchantLoginResp respObj = null;
		Hashtable<String, String> headerMap = null;
		
		// Create URL
		String url = "https://lab7319.ctsservice.com/nis/retailapi/v1/customer/CMS000001000/authenticate";
		LOG.info("URL: " + url);
             
		try {
            // Build JSON Object
            jsonObj = new MerchantLogin(username, password);    
            
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
            respObj = g.fromJson(resp, MerchantLoginResp.class);
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
