package com.cubic.nisjava.apiobjects;

import java.util.List;

import com.cubic.backoffice.apiobjects.ApiRespHeader;
import com.cubic.backoffice.apiobjects.WSErrors;

public class CRMCustomerAuthenticateResp {
	private String authCode;
	private String customerId;
	private String contactId;
	private List<WSErrors> authErrors;
	
	// Optional Error block (if returned in Response)
	private ApiRespHeader fault;
		
	public CRMCustomerAuthenticateResp() {
		// Default
	}

	/**
	 * @param authCode
	 * @param customerId
	 * @param contactId
	 * @param authErrors
	 * @param fault
	 */
	public CRMCustomerAuthenticateResp(String authCode, String customerId, String contactId, List<WSErrors> authErrors,
			ApiRespHeader fault) {
		this.authCode = authCode;
		this.customerId = customerId;
		this.contactId = contactId;
		this.authErrors = authErrors;
		this.fault = fault;
	}

	/**
	 * @return the authCode
	 */
	public String getAuthCode() {
		return authCode;
	}

	/**
	 * @param authCode the authCode to set
	 */
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
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
	 * @return the authErrors
	 */
	public List<WSErrors> getAuthErrors() {
		return authErrors;
	}

	/**
	 * @param authErrors the authErrors to set
	 */
	public void setAuthErrors(List<WSErrors> authErrors) {
		this.authErrors = authErrors;
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
