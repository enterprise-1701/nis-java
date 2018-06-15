package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSCompleteRegistrationResponse {

	@SerializedName("hdr")
	@Expose
	private WSHdr hdr;
	@SerializedName("oneAccountId")
	@Expose
	private Integer oneAccountId;

	public WSHdr getHdr() {
		return hdr;
	}

	public void setHdr(WSHdr hdr) {
		this.hdr = hdr;
	}

	public Integer getOneAccountId() {
		return oneAccountId;
	}

	public void setOneAccountId(Integer oneAccountId) {
		this.oneAccountId = oneAccountId;
	}

}
