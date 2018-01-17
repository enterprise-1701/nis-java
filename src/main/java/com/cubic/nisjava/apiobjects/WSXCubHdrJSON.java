package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSXCubHdrJSON {

	@SerializedName("hdr")
	@Expose
	private WSHdr hdr;

	public WSHdr getHdr() {
		return hdr;
	}

	public void setHdr(WSHdr hdr) {
		this.hdr = hdr;
	}

}

