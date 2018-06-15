package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSPrevalidateResponse {

	@SerializedName("isUsernameValid")
	@Expose
	private Boolean isUsernameValid;
	@SerializedName("usernameErrors")
	@Expose
	private List<Object> usernameErrors = null;
	@SerializedName("isPasswordValid")
	@Expose
	private Boolean isPasswordValid;
	@SerializedName("passwordErrors")
	@Expose
	private List<Object> passwordErrors = null;

	public Boolean getIsUsernameValid() {
		return isUsernameValid;
	}

	public void setIsUsernameValid(Boolean isUsernameValid) {
		this.isUsernameValid = isUsernameValid;
	}

	public List<Object> getUsernameErrors() {
		return usernameErrors;
	}

	public void setUsernameErrors(List<Object> usernameErrors) {
		this.usernameErrors = usernameErrors;
	}

	public Boolean getIsPasswordValid() {
		return isPasswordValid;
	}

	public void setIsPasswordValid(Boolean isPasswordValid) {
		this.isPasswordValid = isPasswordValid;
	}

	public List<Object> getPasswordErrors() {
		return passwordErrors;
	}

	public void setPasswordErrors(List<Object> passwordErrors) {
		this.passwordErrors = passwordErrors;
	}

}
