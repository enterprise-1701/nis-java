package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSCardSummaryResponse {

	@SerializedName("hdr")
	@Expose
	private WSHdr hdr;
	@SerializedName("result")
	@Expose
	private WSResult result;
	@SerializedName("serialNumber")
	@Expose
	private String serialNumber;
	@SerializedName("cardSummary")
	@Expose
	private WSCardSummary cardSummary;

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

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public WSCardSummary getCardSummary() {
		return cardSummary;
	}

	public void setCardSummary(WSCardSummary cardSummary) {
		this.cardSummary = cardSummary;
	}

}
