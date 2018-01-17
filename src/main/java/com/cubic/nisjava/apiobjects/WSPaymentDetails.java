package com.cubic.nisjava.apiobjects;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WSPaymentDetails {

	@SerializedName("paymentDetails")
	@Expose
	private List<WSPaymentDetail> paymentDetails = null;

	@SerializedName("totalAmount")
	@Expose
	private Integer totalAmount;

	public List<WSPaymentDetail> getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(List<WSPaymentDetail> paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

}