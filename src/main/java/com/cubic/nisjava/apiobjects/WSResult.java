package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSResult {

	@SerializedName("responseCode")
	@Expose
	private String responseCode;
	@SerializedName("terminalCommands")
	@Expose
	private String terminalCommands;

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getTerminalCommands() {
		return terminalCommands;
	}

	public void setTerminalCommands(String terminalCommands) {
		this.terminalCommands = terminalCommands;
	}

}
