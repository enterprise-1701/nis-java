package com.cubic.nisjava.apiobjects;

/**
 * CRMRespHeader
 * Implements the JSON object to hold Response Header (hdr) Object to Customer Service API Requests
 */
public class CRMRespHeader {

	private String result;
	private String uid;
	
	/**
	 * CRMRespHeader()
	 * Default constructor
	 */
	public CRMRespHeader() {
		// Default
	}
	
	/**
	 * CRMRespHeader()
	 * Constructor that takes all parameters to initialize member data
	 * @param result - Response result
	 * @param uid - Response Unique ID
	 */
	public CRMRespHeader(String result, String uid) {
		this.result = result;
		this.uid = uid;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the uid
	 */
	public String getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
}
