package com.cubic.nisjava.apiobjects;

import com.cubic.backoffice.apiobjects.ApiRespHeader;

/**
 * NWCustomerCompleteRegistrationResp
 * Implements the JSON object to hold the Customer Complete Registration POST Response from NextWave API
 */
public class NWCustomerCompleteRegistrationResp {

	private String oneAccountId;
	
	// Optional Error block (if returned in Response)
	private ApiRespHeader hdr;
	
	/**
	 * CRMCustomerCompleteRegistrationResp()
	 * Default constructor
	 */
	public NWCustomerCompleteRegistrationResp() {
		// Default
	}
	
	/**
	 * CRMCustomerCompleteRegistrationResp()
	 * Constructor that takes all parameters to initialize member data
	 * @param oneAccountId - OneAccount ID
	 * @param hdr - Response Header Object
	 */
	public NWCustomerCompleteRegistrationResp(String oneAccountId, ApiRespHeader hdr) {
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
