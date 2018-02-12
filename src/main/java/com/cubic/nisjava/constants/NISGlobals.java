package com.cubic.nisjava.constants;

public class NISGlobals {

	public static final String NIS_DEVICE_ID = "NISJava";

	public static final String NIS_CONTENT_TYPE = "Content-type";
	public static final String NIS_CONTENT_TYPE_JSON = "application/json";

	// ##### x-cub-hdr RELATED
	public static final String NIS_XCUBHDR_RESULT_SUCCESSFUL = "Successful";
	public static final String NIS_XCUBHDR_FMT = "{ \"uid\": \"%s\", \"device\": \"%s\" }";
	public static final String NIS_XCUBHDR_ALT_FMT = "{ \"uid\" : \"0A1EBC2F-5CBE-4B0D-8FA0-24902E7E773D\",  \"device\" : \"%s\",  \"appId\" : \"%s\" })"; 
	public static final String NIS_XCUBHDR_DEV = "WEB01234";
	public static final String NIS_XCUBHDR_NAME = "x-cub-hdr";
	
	// ##### Property names
	public static final String NIS_AUTHORIZATION_HDR_NAME = "Authorization";
	public static final String NIS_AUTHORIZATION_HDR_VALUE = "Basic d2ViOnBhc3N3b3Jk";
	public static final String NIS_COOKIE_NAME = "cookie";
	public static final String NIS_COOKIE_VALUE = "JSESSIONID=A37F17266E5C6245E0B79D439BF253E9; ncsPageRef=1510607216399; ApplicationGatewayAffinity=1921a547eeb3ca96d7fc0b94cd54a65c6fe0ece336f8839dbe7d892b59c0482f;";
	public static final String NIS_DEVICE_ID_NAME = "DEVICE_ID";
	public static final String NIS_APPID_NAME = "APP_ID";
	public static final String NIS_CSC_UID_NAME = "CSC_UID";
	public static final String NIS_HOST_NAME = "HOST";
	public static final String NIS_CREATE_SESSION_URL_NAME = "CREATE_SESSION_URL";
	public static final String NIS_CARD_SUMMARY_URL_NAME = "CARD_SUMMARY_URL";
	public static final String NIS_DELETE_SESSION_URL_NAME = "DELETE_SESSION_URL";
	public static final String NIS_DELETE_NAME = "DELETE";
	public static final String NIS_TERMINAL_RESPONSES_FMT = "{ \"terminalResponses\":\"%s\" }";
	
	// ##### DB REQUEST LOG SEARCH
	public static final String NIS_SEARCH_REQUEST_LOG_SQL = "select * from nis_main.api_request_log where REQUEST_UID = '%s'";
	public static final String NIS_INSERTED_DTM_COLNAME = "INSERTED_DTM";

	// ##### DEFS FOR PARSING GET NOTIF PREFS RESPONSE
	public static final String NIS_CHANNEL_PUSHNOTIFICATION = "PUSHNOTIFICATION";
	public static final String NIS_CHANNEL_EMAIL = "EMAIL";
	public static final String NIS_REQTYPE_DEFAULT = "DEFAULT";
	public static final String NIS_REQTYPE_OPTIONAL = "OPTIONAL";
	public static final String NIS_TICKET_RECALLED_TYPE = "TicketRecalled";
	public static final String NIS_TICKET_RECALLED_DESC = "Metra ticket recalled notification";

	// ##### ERROR KEY RETURNED FOR INVALID CUSTOMER ID
	public static final String NIS_ERRORS_GENERAL_RECORD_NOT_FOUND = "errors.general.record.not.found";

	// ##### ERROR MESSAGES
	public static final String NIS_BAD_RESPONSE_CODE_FMT = "BAD RESPONSE CODE - EXPECTED %d, FOUND %d";
	public static final String NIS_BAD_XCUBHDR_RESULT_FMT = "BAD X_CUB_HDR RESULT - EXPECTED '%s', ACTUAL '%s'";
	public static final String NIS_BAD_RESPONSE_NULL = "BAD RESPONSE: null";
	public static final String NIS_BAD_RESPONSE_NOT_NULL = "BAD RESPONSE: RESPONSE IS NOT NULL BUT IT SHOULD BE";

	// ##### ERROR MESSAGE FOR TESTING CONTACT ID
	public static final String NIS_ERROR_MSG_CONTACT_ID = "EXPECTED CONTACT ID = '%s', ACTUAL CONTACT ID = '%s'";
}
