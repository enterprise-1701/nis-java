package com.cubic.nisjava.apiobjects;

import java.util.List;

import com.cubic.backoffice.apiobjects.ApiRespHeader;
import com.cubic.backoffice.apiobjects.WSErrors;

public class CRMCustomerCredentialPrevalidateResp {
       
	private boolean isUsernameValid;
	private boolean isPasswordValid;
	public List<WSErrors> usernameErrors;
	public List<WSErrors> passwordErrors;
       
    // Optional Error block (if returned in Response)
   	private ApiRespHeader fault;

	public CRMCustomerCredentialPrevalidateResp() {
		//Default
	}

	/**
	 * @param isUsernameValid
	 * @param isPasswordValid
	 * @param usernameErrors
	 * @param passwordErrors
	 * @param fault
	 */
	public CRMCustomerCredentialPrevalidateResp(boolean isUsernameValid, boolean isPasswordValid,
		List<WSErrors> usernameErrors, List<WSErrors> passwordErrors, ApiRespHeader fault) {
		this.isUsernameValid = isUsernameValid;
		this.isPasswordValid = isPasswordValid;
		this.usernameErrors = usernameErrors;
		this.passwordErrors = passwordErrors;
		this.fault = fault;
	}

	/**
	 * @return the isUsernameValid
	 */
	public boolean isUsernameValid() {
		return isUsernameValid;
	}

	/**
	 * @param isUsernameValid the isUsernameValid to set
	 */
	public void setUsernameValid(boolean isUsernameValid) {
		this.isUsernameValid = isUsernameValid;
	}

	/**
	 * @return the isPasswordValid
	 */
	public boolean isPasswordValid() {
		return isPasswordValid;
	}

	/**
	 * @param isPasswordValid the isPasswordValid to set
	 */
	public void setPasswordValid(boolean isPasswordValid) {
		this.isPasswordValid = isPasswordValid;
	}

	/**
	 * @return the usernameErrors
	 */
	public List<WSErrors> getUsernameErrors() {
		return usernameErrors;
	}

	/**
	 * @param usernameErrors the usernameErrors to set
	 */
	public void setUsernameErrors(List<WSErrors> usernameErrors) {
		this.usernameErrors = usernameErrors;
	}

	/**
	 * @return the passwordErrors
	 */
	public List<WSErrors> getPasswordErrors() {
		return passwordErrors;
	}

	/**
	 * @param passwordErrors the passwordErrors to set
	 */
	public void setPasswordErrors(List<WSErrors> passwordErrors) {
		this.passwordErrors = passwordErrors;
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
