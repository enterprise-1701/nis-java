package com.cubic.nisjava.apiobjects;

public class WSPatronAuthenticateResponse {
	private String patronAccountId;
	private String authCode;
	public String getPatronAccountId() {
		return patronAccountId;
	}
	public void setPatronAccountId(String patronAccountId) {
		this.patronAccountId = patronAccountId;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	public WSHdr getHdr() {
		return hdr;
	}
	public void setHdr(WSHdr hdr) {
		this.hdr = hdr;
	}
	private WSHdr hdr;

}
