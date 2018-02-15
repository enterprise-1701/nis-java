package com.cubic.nisjava.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.cubic.accelerators.RESTEngine;
import com.cubic.nisjava.constants.NISGlobals;

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
	
	protected String buildCardSummaryRequestBody(String data) {
		return String.format( "{ \"terminalResponses\":\"%s\" }", data );
	}
	
	protected String deleteClientResponse(String sURL, String requestBody, Hashtable<String,String> headers) {
		URL url;
		StringBuilder sb = new StringBuilder();
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
			OutputStreamWriter out = new OutputStreamWriter(
			                httpCon.getOutputStream());
			
			BufferedWriter bw = new BufferedWriter( out );
			PrintWriter pw = new PrintWriter( bw );
			pw.println( requestBody );
			pw.flush();
			out.close();
			httpCon.connect();
			
			
			InputStreamReader isr = new InputStreamReader( httpCon.getInputStream() );
			BufferedReader br = new BufferedReader( isr );
			for ( String line = null; null != ( line = br.readLine() ); ) {
				sb.append( line );
			}
			
			LOG.info("response code=" + httpCon.getResponseCode());
			LOG.info( "response msg  =" + httpCon.getResponseMessage() );
			LOG.info( sb.toString() );
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}
}
