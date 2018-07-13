/**
 * @author 203610
 * Jul 11, 2018
 */
package com.cubic.nisjava.apiobjects;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSMerchantAuthenticationErrorResponse 
{
	@SerializedName("authCode")
	@Expose
	private String authCode;
	@SerializedName("authErrors")
	@Expose
	private List<MerchantAuthError> authErrors = null;

	public String getAuthCode() {
	return authCode;
	}

	public void setAuthCode(String authCode) {
	this.authCode = authCode;
	}

	public List<MerchantAuthError> getAuthErrors() {
	return authErrors;
	}

	public void setAuthErrors(List<MerchantAuthError> authErrors) {
	this.authErrors = authErrors;
	}
}
