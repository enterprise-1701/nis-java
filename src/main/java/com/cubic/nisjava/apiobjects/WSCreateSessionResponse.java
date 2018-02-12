package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSCreateSessionResponse {

	@SerializedName("hdr")
	@Expose
	private WSHdr hdr;
	@SerializedName("result")
	@Expose
	private WSResult result;
	@SerializedName("sessionId")
	@Expose
	private String sessionId;

	public WSHdr getHdr() {
		return hdr;
	}

	public void setHdr(WSHdr hdr) {
		this.hdr = hdr;
	}

	public WSResult getResult() {
		return result;
	}

	public void setResult(WSResult result) {
		this.result = result;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}