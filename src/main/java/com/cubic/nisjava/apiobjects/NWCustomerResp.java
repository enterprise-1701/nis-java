package com.cubic.nisjava.apiobjects;

import com.cubic.backoffice.apiobjects.ApiRespHeader;

/**
 * NWCustomerResp
 * Implements the JSON object to hold the Customer POST Response from NIS NextWave API
 */
public class NWCustomerResp {

	private String customerId;
	private String contactId;
	private String oneAccountId;
	
	// Optional Error block (if returned in Response)
	private ApiRespHeader fault;
	
	/**
	 * NWCustomerResp()
	 * Default constructor
	 */
	public NWCustomerResp() {
		// Default
	}
	
	/**
	 * NWCustomerResp()
	 * Constructor that takes all parameters to initialize member data
	 * @param customerId - Customer ID
	 * @param contactId - Contact ID
	 * @param oneAccountId - OneAccount ID
	 * @param fault - Optional error object
	 */
	public NWCustomerResp(String customerId, String contactId, String oneAccountId, ApiRespHeader fault) {
		this.customerId = customerId;
		this.contactId = contactId;
		this.oneAccountId = oneAccountId;
		this.fault = fault;
	}
	
	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the contactId
	 */
	public String getContactId() {
		return contactId;
	}

	/**
	 * @param contactId the contactId to set
	 */
	public void setContactId(String contactId) {
		this.contactId = contactId;
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
	 * @return the fault
	 */
	public ApiRespHeader getFault() {
		return fault;
	}

	/**
	 * @param fault the fault to set
	 */
	public void setFault(ApiRespHeader fault) {
		this.fault = fault;
	}
}
