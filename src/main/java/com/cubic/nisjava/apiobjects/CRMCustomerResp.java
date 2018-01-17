package com.cubic.nisjava.apiobjects;

import com.cubic.backoffice.apiobjects.ApiRespHeader;

public class CRMCustomerResp {
	private String customerId;
	private String contactId;
	private String tempPassword;
	private String tempPasswordExpirationDate;
	
	// Optional Error block (if returned in Response)
	private ApiRespHeader hdr;
	
	/**
	 * CRMCustomerResp()
	 * Default constructor
	 */
	public CRMCustomerResp() {
		// Default
	}
	
	/**
	 * CRMCustomerResp()
	 * Constructor that takes all parameters to initialize member data
	 * @param customerId - Customer ID
	 * @param contactId - Contact ID
	 * @param tempPassword - tempPassword
	 * @param tempPasswordExpirationDate - Pwd Expiration date
	 * @param hdr - Optional error object
	 */
	public CRMCustomerResp(String customerId, String contactId, String tempPassword, String tempPasswordExpirationDate,
			ApiRespHeader hdr) {
		this.customerId = customerId;
		this.contactId = contactId;
		this.tempPassword = tempPassword;
		this.tempPasswordExpirationDate = tempPasswordExpirationDate;
		this.hdr = hdr;
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
	 * @return the tempPassword
	 */
	public String getTempPassword() {
		return tempPassword;
	}

	/**
	 * @param 
	 */
	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}

	/**
	 * @return the tempPasswordExpirationDate
	 */
	public String getTempPasswordExpirationDate() {
		return tempPasswordExpirationDate;
	}

	/**
	 * @param tempPasswordExpirationDate the tempPasswordExpirationDate to set
	 */
	public void setTempPasswordExpirationDate(String tempPasswordExpirationDate) {
		this.tempPasswordExpirationDate = tempPasswordExpirationDate;
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
