package com.cubic.nisjava.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.cubic.accelerators.RESTEngine;
import com.cubic.nisjava.constants.NISGlobals;

/**
 * Base class for the NextLink series of test classes.
 * 
 * @author 203402
 *
 */
public class NextLinkBase extends RESTEngine {

	private final Logger LOG = Logger.getLogger(this.getClass().getName());	
	
	/**
	 * Build the set of headers used to contact NextLink.
	 * 
	 * <PRE>
	 * x-cub-hdr - built using the DEVICE_ID and APP_ID data items
	 * content-type
	 * authorization
	 * cookie
	 * </PRE>
	 * 
	 * @param data
	 *            The Test Data
	 * @return A Hashtable<String,String> instance containing the headers
	 */
	public Hashtable<String, String> buildHeaders(Hashtable<String, String> data) {
		Hashtable<String, String> headers = new Hashtable<String, String>();
		String deviceId = data.get(NISGlobals.NIS_DEVICE_ID_NAME);
		String appId = data.get(NISGlobals.NIS_APPID_NAME);
		String xCubHdr = String.format(NISGlobals.NIS_XCUBHDR_ALT_FMT, deviceId, appId);
		headers.put(NISGlobals.NIS_XCUBHDR_NAME, xCubHdr);
		headers.put(NISGlobals.NIS_CONTENT_TYPE, NISGlobals.NIS_CONTENT_TYPE_JSON);
		headers.put(NISGlobals.NIS_AUTHORIZATION_HDR_NAME, NISGlobals.NIS_AUTHORIZATION_HDR_VALUE);
		headers.put(NISGlobals.NIS_COOKIE_NAME, NISGlobals.NIS_COOKIE_VALUE); 
		return headers;
	}
	
	/**
	 * Build the URL of the Create Session endpoint.
	 * 
	 * @param data
	 *            The Test Data
	 * @return the URL of the Create Session endpoint
	 */
	protected String buildCreateSessionURL(Hashtable<String, String> data) {
		String cscUID = data.get(NISGlobals.NIS_CSC_UID_NAME);
		String deviceId = data.get(NISGlobals.NIS_DEVICE_ID_NAME);
		String host = data.get(NISGlobals.NIS_HOST_NAME);
		String sfmt = data.get(NISGlobals.NIS_CREATE_SESSION_URL_NAME);
		String sURL = String.format(sfmt, host, deviceId, cscUID);
		return sURL;
	}	

	/**
	 * Build the URL of the Delete Session endpoint.
	 * 
	 * @param data
	 *            The Test Data
	 * @return the URL of the Delete Session endpoint
	 */
	protected String buildDeleteSessionURL(Hashtable<String, String> data) {
		String host = data.get(NISGlobals.NIS_HOST_NAME);
		String sfmt = data.get(NISGlobals.NIS_DELETE_SESSION_URL_NAME);
		String sURL = String.format(sfmt, host);
		return sURL;
	}	
	
	/**
	 * Build the Card Summary request body.
	 * 
	 * { "terminalResponses":"XYZ" }
	 * 
	 * @param data  The test data
	 * @return the Card Summary request body
	 */
	protected String buildCardSummaryRequestBody(String data) {
		return String.format( "{ \"terminalResponses\":\"%s\" }", data );
	}
	
	/**
	 * Send a DELETE HTTP request, with the specified request body.
	 * 
	 * Had to implement this method because the RestActions alternative doesn't
	 * take a request body, which is required.
	 * 
	 * @param sURL  The URL to call
	 * @param requestBody  The request body to send
	 * @param headers  The headers
	 * @return The response body
	 */
	protected String deleteClientResponse(String sURL, String requestBody, Hashtable<String,String> headers) {
		URL url = null;
		StringBuilder sb = new StringBuilder();
		InputStreamReader isr = null;
		OutputStreamWriter out = null;
		BufferedWriter bw = null;
		BufferedReader br = null;
		try {
			url = new URL( sURL );
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);

			Set<Entry<String,String>> set = headers.entrySet();
			for( Entry<String,String> entry : set ) {
				String skey = entry.getKey();
				String sval = entry.getValue();
				httpCon.setRequestProperty( skey, sval );
			}
			
			httpCon.setRequestMethod("DELETE");
			out = new OutputStreamWriter(
			                httpCon.getOutputStream());
			
			bw = new BufferedWriter( out );
			PrintWriter pw = new PrintWriter( bw );
			pw.println( requestBody );
			pw.flush();
			
			httpCon.connect();
			
			isr = new InputStreamReader( httpCon.getInputStream() );
			br = new BufferedReader( isr );
			for ( String line = null; null != ( line = br.readLine() ); ) {
				sb.append( line );
			}
			
			LOG.info("response code=" + httpCon.getResponseCode());
			LOG.info( "response msg  =" + httpCon.getResponseMessage() );
			LOG.info( sb.toString() );
		} catch (MalformedURLException e) {
			LOG.error( e.toString(), e );
		} catch (IOException e) {
			LOG.error( e.toString(), e );
		} finally {
			if ( bw != null ) {
				try {
					bw.close();
				} catch (IOException e) {
					LOG.error( e.toString(), e );
				}
			}
			if ( br != null ) {
				try {
					br.close();
				} catch (IOException e) {
					LOG.error( e.toString(), e );
				}
			}
			if ( null != isr ) {
				try {
					isr.close();
				} catch (IOException e) {
					LOG.error( e.toString(), e );
				}
			}
			if ( null != out ) {
				try {
					out.close();
				} catch (IOException e) {
					LOG.error( e.toString(), e );
				}
			}
		}
		return sb.toString();
	}
}
