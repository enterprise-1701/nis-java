package com.cubic.nisjava.apiobjects;



import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class WSPaymentDetail {

	@SerializedName("paymentDetailsId")
	@Expose
	private Integer paymentDetailsId;

	@SerializedName("paymentAmount")
	@Expose
	private Integer paymentAmount;

	@SerializedName("paymentType")
	@Expose
	private String paymentType;

	@SerializedName("paymentTypeDescription")
	@Expose
	private String paymentTypeDescription;

	@SerializedName("cardType")
	@Expose
	private String cardType;

	@SerializedName("maskedPan")
	@Expose
	private String maskedPan;

	@SerializedName("paymentDate")
	@Expose
	private String paymentDate;

	public Integer getPaymentDetailsId() {
		return paymentDetailsId;
	}

	public void setPaymentDetailsId(Integer paymentDetailsId) {
		this.paymentDetailsId = paymentDetailsId;
	}

	public Integer getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(Integer paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentTypeDescription() {
		return paymentTypeDescription;
	}

	public void setPaymentTypeDescription(String paymentTypeDescription) {
		this.paymentTypeDescription = paymentTypeDescription;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getMaskedPan() {
		return maskedPan;
	}

	public void setMaskedPan(String maskedPan) {
		this.maskedPan = maskedPan;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

}
