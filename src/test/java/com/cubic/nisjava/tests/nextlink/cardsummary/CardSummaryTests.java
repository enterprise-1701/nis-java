package com.cubic.nisjava.tests.nextlink.cardsummary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Hashtable;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.cubic.nisjava.apiobjects.WSCreateSessionResponse;
import com.cubic.nisjava.constants.AppConstants;
import com.cubic.nisjava.dataproviders.NISDataProviderNEXTLINK_v1;
import com.cubic.nisjava.apiobjects.WSCardSummaryResponse;
import com.cubic.vpos.trm.TrmCommands;
import com.cubic.vpos.trm.TrmResponses;
import com.cubic.vpos.trm.mock.MockRISCardReader;
import com.cubic.vpos.trm.mock.MockedTerminalProxy;
import com.google.gson.Gson;
import com.sun.jersey.api.client.ClientResponse;
import com.cubic.accelerators.RESTActions;
import com.cubic.accelerators.RESTConstants;
import com.cubic.accelerators.RESTEngine;

public class CardSummaryTests extends RESTEngine {

	private final Logger LOG = Logger.getLogger(this.getClass().getName());

	@Test(dataProvider = AppConstants.DATA_PROVIDER, dataProviderClass = NISDataProviderNEXTLINK_v1.class)
	public void cardSummaryTest(ITestContext context, Hashtable<String, String> data) throws Throwable {
		String testCaseName = data.get("TestCase_Description");
		LOG.info("TestCase_Description=" + testCaseName);

		try {
			RESTActions restActions = setupAutomationTest(context, testCaseName);

			// get headers
			Hashtable<String, String> headers = buildHeaders(data);
			// build create session URL
			String createSessionURL = buildCreateSessionURL(data);

			Hashtable<String, String> urlQueryParameters = new Hashtable<String, String>();

			// get create session
			ClientResponse clientResponse = restActions.getClientResponse(createSessionURL, headers, urlQueryParameters,
					RESTConstants.APPLICATION_JSON);

			// Verify HTTP response code
			int status = clientResponse.getStatus();
			String msg = "WRONG HTTP RESPONSE CODE - EXPECTED 200, FOUND " + status;
			restActions.assertTrue(status == HttpURLConnection.HTTP_OK, msg);

			String response = clientResponse.getEntity(String.class);
			LOG.info(response);

			Gson gson = new Gson();

			// parse the JSON String response into a List
			WSCreateSessionResponse responseObj = gson.fromJson(response, WSCreateSessionResponse.class);

			// verify the Create Session operation was Successful
			restActions.assertTrue("Successful".equals(responseObj.getHdr().getResult()),
					"Hdr Result IS NOT 'Successful' BUT IT SHOULD BE");

			String sessionId = responseObj.getSessionId();
			LOG.info("sessionId=" + sessionId);

			String trmResponsesStr = responseObj.getResult().getTerminalCommands();
			LOG.info("terminalCommands=" + trmResponsesStr);

			// == do the card/summary operations
			headers.put("x-cub-sessionid", sessionId);

			// build the card/summary URL
			String sCardSummaryURL = buildCardSummaryURL(data);

			// prepare the terminal commands
			TrmCommands trmCommands = TrmCommands.fromString(trmResponsesStr);

			// use the RIS card for Miami
			MockedTerminalProxy mockedTerminalProxy = new MockedTerminalProxy(new MockRISCardReader());
			mockedTerminalProxy.selectCard();

			// execute terminal commands to get the terminal responses
			TrmResponses trmResponses = mockedTerminalProxy.executeCommandsOnReader(trmCommands);
			trmResponsesStr = trmResponses.toString();

			String cardSummaryRequestBody = buildCardSummaryRequestBody(trmResponsesStr);

			clientResponse = restActions.postClientResponse(sCardSummaryURL, cardSummaryRequestBody, headers,
					urlQueryParameters, RESTConstants.APPLICATION_JSON);

			status = clientResponse.getStatus();
			msg = "WRONG HTTP RESPONSE CODE - EXPECTED 200, FOUND " + status;
			restActions.assertTrue(status == HttpURLConnection.HTTP_OK, msg);

			response = clientResponse.getEntity(String.class);
			LOG.info(response);

			responseObj = gson.fromJson(response, WSCreateSessionResponse.class);

			restActions.assertTrue("CONTINUE".equals(responseObj.getResult().getResponseCode()),
					"Response Code IS NOT 'CONTINUE' BUT IT SHOULD BE");
			trmResponsesStr = responseObj.getResult().getTerminalCommands();
			LOG.info("terminalCommands=" + trmResponsesStr);

			do {

				trmCommands = TrmCommands.fromString(trmResponsesStr);

				// execute terminal commands to get the terminal responses
				trmResponses = mockedTerminalProxy.executeCommandsOnReader(trmCommands);
				trmResponsesStr = trmResponses.toString();

				cardSummaryRequestBody = buildCardSummaryRequestBody(trmResponsesStr);

				clientResponse = restActions.postClientResponse(sCardSummaryURL, cardSummaryRequestBody, headers,
						urlQueryParameters, RESTConstants.APPLICATION_JSON);

				status = clientResponse.getStatus();
				msg = "WRONG HTTP RESPONSE CODE - EXPECTED 200, FOUND " + status;
				restActions.assertTrue(status == HttpURLConnection.HTTP_OK, msg);

				response = clientResponse.getEntity(String.class);
				LOG.info(response);

				responseObj = gson.fromJson(response, WSCreateSessionResponse.class);

				if ("OK".equals(responseObj.getResult().getResponseCode())) {
					break;
				}

				trmResponsesStr = responseObj.getResult().getTerminalCommands();
				LOG.info("terminalCommands=" + trmResponsesStr);

				// } while( n++ < 5 );
			} while ("CONTINUE".equals(responseObj.getResult().getResponseCode()));

			// assert the fare card summary
			if ("OK".equals(responseObj.getResult().getResponseCode())) {
				WSCardSummaryResponse responseObj2 = gson.fromJson(response, WSCardSummaryResponse.class);

				LOG.info("Serial number=" + responseObj2.getSerialNumber());

				LOG.info("Result=" + responseObj2.getHdr().getResult());

				LOG.info("Card summary=" + responseObj2.getCardSummary());

				restActions.assertTrue(null != responseObj2.getCardSummary(),
						"Card Summary IS NULL BUT IT SHOULD NOT BE");

				if (null != responseObj2.getCardSummary()) {
					restActions.assertTrue(null != responseObj2.getCardSummary().getRiderClassId(),
							"Rider Class Id IS NULL BUT IT SHOULD NOT BE");
					restActions.assertTrue(null != responseObj2.getCardSummary().getExpirationDateTime(),
							"Expiration Date Time IS NULL BUT IT SHOULD NOT BE");
					restActions.assertTrue(null != responseObj2.getCardSummary().getSvRecurringAmount(),
							"Sv Recurring Amount IS NULL BUT IT SHOULD NOT BE");
					restActions.assertTrue(null != responseObj2.getCardSummary().getSvAutoloadSetup(),
							"Sv Autoload Setup IS NULL BUT IT SHOULD NOT BE");
					restActions.assertTrue(null != responseObj2.getCardSummary().getTotalPasses(),
							"Total Passes IS NULL BUT IT SHOULD NOT BE");
					restActions.assertTrue(null != responseObj2.getCardSummary().getCscRegisteredFlag(),
							"Csc Registered Flag IS NULL BUT IT SHOULD NOT BE");
					restActions.assertTrue(null != responseObj2.getCardSummary().getMaxAddValue(),
							"Max Add Value IS NULL BUT IT SHOULD NOT BE");
					restActions.assertTrue(null != responseObj2.getCardSummary().getMaxCancelValue(),
							"Max Cancel Value IS NULL BUT IT SHOULD NOT BE");
					restActions.assertTrue(null != responseObj2.getCardSummary().getTestFlag(),
							"Test Flag IS NULL BUT IT SHOULD NOT BE");
				}
			}

			// close session with vpos/NL
			String deleteSesssionURL = buildDeleteSessionURL(data);

			trmResponsesStr = responseObj.getResult().getTerminalCommands();
			LOG.info("terminalCommands=" + trmResponsesStr);

			trmCommands = TrmCommands.fromString(trmResponsesStr);

			// execute terminal commands to get the terminal responses
			trmResponses = mockedTerminalProxy.executeCommandsOnReader(trmCommands);
			trmResponsesStr = trmResponses.toString();

			String deleteSessionRequestBody = buildCardSummaryRequestBody("EA+ADQICAABqBwAAAQAAkQA=");

			LOG.info("Delete session request body=" + deleteSessionRequestBody);

			deleteClientResponse(deleteSesssionURL, deleteSessionRequestBody, headers);

			// assert that vpos/NL responds with OK from the close request
			responseObj = gson.fromJson(response, WSCreateSessionResponse.class);

			restActions.assertTrue("OK".equals(responseObj.getResult().getResponseCode()),
					"Response Code IN NOT 'OK' BUT IT SHOULD BE");

			restActions.assertTrue("Successful".equals(responseObj.getHdr().getResult()),
					"Result IS NOT 'Successful' BUT IT SHOULD BE");

		} finally {
			teardownAutomationTest(context, testCaseName);
		}
	}

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
		String deviceId = data.get("DEVICE_ID");
		String appId = data.get("APP_ID");
		String format = "{ \"uid\" : \"0A1EBC2F-5CBE-4B0D-8FA0-24902E7E773D\",  \"device\" : \"%s\",  \"appId\" : \"%s\" })";
		String xCubHdr = String.format(format, deviceId, appId);
		headers.put("x-cub-hdr", xCubHdr);
		headers.put("content-type", "application/json");
		String authorization = data.get("AUTHORIZATION");
		headers.put("authorization", authorization);
		headers.put("cookie",
				"JSESSIONID=A37F17266E5C6245E0B79D439BF253E9; ncsPageRef=1510607216399; ApplicationGatewayAffinity=1921a547eeb3ca96d7fc0b94cd54a65c6fe0ece336f8839dbe7d892b59c0482f; ApplicationGatewayAffinity=1921a547eeb3ca96d7fc0b94cd54a65c6fe0ece336f8839dbe7d892b59c0482f; ApplicationGatewayAffinity=1921a547eeb3ca96d7fc0b94cd54a65c6fe0ece336f8839dbe7d892b59c0482f; ApplicationGatewayAffinity=1921a547eeb3ca96d7fc0b94cd54a65c6fe0ece336f8839dbe7d892b59c0482f; ApplicationGatewayAffinity=1921a547eeb3ca96d7fc0b94cd54a65c6fe0ece336f8839dbe7d892b59c0482f; ApplicationGatewayAffinity=1921a547eeb3ca96d7fc0b94cd54a65c6fe0ece336f8839dbe7d892b59c0482f; ApplicationGatewayAffinity=1921a547eeb3ca96d7fc0b94cd54a65c6fe0ece336f8839dbe7d892b59c0482f; ApplicationGatewayAffinity=1921a547eeb3ca96d7fc0b94cd54a65c6fe0ece336f8839dbe7d892b59c0482f; ApplicationGatewayAffinity=1921a547eeb3ca96d7fc0b94cd54a65c6fe0ece336f8839dbe7d892b59c0482f; ApplicationGatewayAffinity=1921a547eeb3ca96d7fc0b94cd54a65c6fe0ece336f8839dbe7d892b59c0482f; ApplicationGatewayAffinity=1921a547eeb3ca96d7fc0b94cd54a65c6fe0ece336f8839dbe7d892b59c0482f; ApplicationGatewayAffinity=1921a547eeb3ca96d7fc0b94cd54a65c6fe0ece336f8839dbe7d892b59c0482f; ApplicationGatewayAffinity=1921a547eeb3ca96d7fc0b94cd54a65c6fe0ece336f8839dbe7d892b59c0482f; ApplicationGatewayAffinity=1921a547eeb3ca96d7fc0b94cd54a65c6fe0ece336f8839dbe7d892b59c0482f; ApplicationGatewayAffinity=1921a547eeb3ca96d7fc0b94cd54a65c6fe0ece336f8839dbe7d892b59c0482f; ApplicationGatewayAffinity=1921a547eeb3ca96d7fc0b94cd54a65c6fe0ece336f8839dbe7d892b59c0482f");
		return headers;
	}

	/**
	 * Build the URL of the Create Session endpoint.
	 * 
	 * @param data
	 *            The Test Data
	 * @return the URL of the Create Session endpoint
	 */
	public String buildCreateSessionURL(Hashtable<String, String> data) {
		String cscUID = data.get("CSC_UID");
		String deviceId = data.get("DEVICE_ID");
		String host = data.get("HOST");
		String sfmt = data.get("CREATE_SESSION_URL");
		String sURL = String.format(sfmt, host, deviceId, cscUID);
		return sURL;
		// return
		// "https://lab7319.ctsservice.com/nis/nextlink/v1/session?region=2&deviceId=POI65001&cscUID=045d54223e2280";
	}

	/**
	 * Build the URL of the Card Summary endpoint.
	 * 
	 * @param data
	 *            The Test Data
	 * @return the URL of the Card Summary endpoint
	 */
	public String buildCardSummaryURL(Hashtable<String, String> data) {
		String host = data.get("HOST");
		String sfmt = data.get("CARD_SUMMARY_URL");
		String sURL = String.format(sfmt, host);
		return sURL;
		// return "https://lab7319.ctsservice.com/nis/nextlink/v1/card/summary";
	}

	/**
	 * Build the URL of the Delete Session endpoint.
	 * 
	 * @param data
	 *            The Test Data
	 * @return the URL of the Delete Session endpoint
	 */
	public String buildDeleteSessionURL(Hashtable<String, String> data) {
		String host = data.get("HOST");
		String sfmt = data.get("DELETE_SESSION_URL");
		String sURL = String.format(sfmt, host);
		return sURL;
		// return "https://lab7319.ctsservice.com/nis/nextlink/v1/session";
	}

	/**
	 * Build the request body of the card/summary operation.
	 * 
	 * @param data
	 *            The Test Data
	 * @return the request body of the card/summary operation
	 */
	public String buildCardSummaryRequestBody(String data) {
		return String.format("{ \"terminalResponses\":\"%s\" }", data);
	}

	/**
	 * Had to implement this method because the RestActions alternative doesn't
	 * take a request body, which is required.
	 * 
	 * @param sURL
	 *            The URL of the Delete Session endpoint, in String form
	 * @param requestBody
	 *            The Request Body of the operation
	 * @param headers
	 *            The Header Set of the operation
	 * @return The response from the endpoint
	 */
	public String deleteClientResponse(String sURL, String requestBody, Hashtable<String, String> headers) {

		StringBuilder sb = new StringBuilder();
		try {
			URL url = new URL(sURL);
			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
			httpCon.setDoOutput(true);

			Set<Entry<String, String>> set = headers.entrySet();
			for (Entry<String, String> entry : set) {
				String skey = "" + entry.getKey();
				String sval = "" + entry.getValue();

				httpCon.setRequestProperty(skey, sval);
			}

			httpCon.setRequestMethod("DELETE");
			OutputStreamWriter out = new OutputStreamWriter(httpCon.getOutputStream());

			BufferedWriter bw = new BufferedWriter(out);
			PrintWriter pw = new PrintWriter(bw);
			pw.println(requestBody);
			pw.flush();
			out.close();
			httpCon.connect();

			InputStreamReader isr = new InputStreamReader(httpCon.getInputStream());
			BufferedReader br = new BufferedReader(isr);
			for (String line = null; null != (line = br.readLine());) {
				sb.append(line);
			}

			LOG.info("response code=" + httpCon.getResponseCode());
			LOG.info("response msg=" + httpCon.getResponseMessage());
			LOG.info(sb.toString());
		} catch (IOException e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			pw.println(e.getMessage());
			e.printStackTrace(pw);
			LOG.info(sw.toString());
		}
		return sb.toString();
	}
}
