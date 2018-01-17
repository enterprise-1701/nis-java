package com.cubic.nisjava.apiobjects;

import com.cubic.backoffice.apiobjects.ApiRespHeader;

/**
 * CSCustomerCompleteRegistrationResp
 * Implements the JSON object to hold the Customer Complete Registration POST Response from NIS Customer Service API
 */
public class CSCustomerCompleteRegistrationResp {

	private String oneAccountId;
	
	// Optional Error block (if returned in Response)
	private ApiRespHeader hdr;
	
	/**
	 * CRMCustomerCompleteRegistrationResp()
	 * Default constructor
	 */
	public CSCustomerCompleteRegistrationResp() {
		// Default
	}
	
	/**
	 * CRMCustomerCompleteRegistrationResp()
	 * Constructor that takes all parameters to initialize member data
	 * @param oneAccountId - OneAccount ID
	 * @param hdr - Response Header Object
	 */
	public CSCustomerCompleteRegistrationResp(String oneAccountId, ApiRespHeader hdr) {
		this.oneAccountId = oneAccountId;
		this.hdr = hdr;
	}
	
	/**
	 * @return the oneAccountId
	 */
	public String getOneAccountId() {
		return oneAccountId;
	}

	/**
	 * @param oneAccountId the oneAccountId to set
	 */
	public void setOneAccountId(String oneAccountId) {
		this.oneAccountId = oneAccountId;
	}
	
	/**
	 * @return the hdr
	 */
	public ApiRespHeader getHdr() {
		return hdr;
	}

	/**
	 * @param hdr the hdr to set
	 */
	public void setHdr(ApiRespHeader hdr) {
		this.hdr = hdr;
	}
}
