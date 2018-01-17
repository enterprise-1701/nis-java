package com.cubic.nisjava.api;

import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.cubic.accelerators.RESTActions;
import com.cubic.accelerators.RESTConstants;
import com.cubic.backoffice.constants.BackOfficeGlobals;
import com.cubic.backoffice.utils.BackOfficeUtils;
import com.cubic.logutils.Log4jUtil;
import com.cubic.nisjava.apiobjects.CSSubSystemTokenTypeMappingsResp;
import com.google.gson.Gson;

/**
 * CSSubSystemTokenTypeMappingsGET
 * Implements Sub-System Travel Token Type Mappings Request via NIS Customer Service API
 */
public class CSSubSystemTokenTypeMappingsGET {

	public static final String CLASS_NAME = "CSSubSystemTokenTypeMappingsGET";
	private static final Logger LOG = Logger.getLogger(CLASS_NAME);
	
	/**
	 * sendReq()
	 * Sends the Sub-System Token Mappings Request by calling REST GET
	 * @return Response Object (JSON)
	 */
	public static CSSubSystemTokenTypeMappingsResp sendReq(RESTActions actions) {
		String resp = "";
		CSSubSystemTokenTypeMappingsResp respObj = null;
		Hashtable<String, String> headerMap = null;
		Class<CSSubSystemTokenTypeMappingsResp> jsonClass = CSSubSystemTokenTypeMappingsResp.class;
		
		// Create URL
		String url = "http://" + BackOfficeGlobals.ENV.NIS_HOST + ":" + BackOfficeGlobals.ENV.NIS_PORT + "/nis/csapi/v1/subsystem/tokentypemappings";
		LOG.info("URL: " + url);
		
		try {
            // Generate Header
            headerMap = BackOfficeUtils.createRESTHeader(RESTConstants.APPLICATION_TEXT);

            // Send request
            resp = actions.getTextResponseAsString(url, headerMap, null);
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
