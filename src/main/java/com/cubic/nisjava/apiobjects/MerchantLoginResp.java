/**
 * 
 */
package com.cubic.nisjava.apiobjects;

/**
 * @author 205974
 *
 */

import java.util.List;

public class MerchantLoginResp {
	private String authCode;
	private String userId;
	private String contactId;
	private List<MerchantErrors> authErrors;
	
	// Optional Error block (if returned in Response)
	private WSHdr hdr;
	
	public MerchantLoginResp() {
		// Default
	}

	/**
	 * @param authCode
	 * @param customerId
	 * @param contactId
	 * @param authErrors
	 * @param fault
	 */
	public MerchantLoginResp(String authCode, String customerId, String contactId, List<MerchantErrors> authErrors,	WSHdr Hdr) {
		this.authCode = authCode;
		this.userId = customerId;
		this.contactId = contactId;
		this.authErrors = authErrors;
		this.hdr = Hdr;
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
	public String getuserId() {
		return userId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setuserId(String customerId) {
		this.userId = customerId;
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
	public List<MerchantErrors> getAuthErrors() {
		return authErrors;
	}

	/**
	 * @param authErrors the authErrors to set
	 */
	public void setAuthErrors(List<MerchantErrors> authErrors) {
		this.authErrors = authErrors;
	}

	/**
	 * @return the fault
	 */
	public WSHdr getHdr() {
		return hdr;
	}

	/**
	 * @param fault the fault to set
	 */
	public void setHdr(WSHdr Hdr) {
		this.hdr = Hdr;
	}
}
