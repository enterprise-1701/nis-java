package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSCreditCard {

	@SerializedName("pgCardId")
	@Expose
	private String pgCardId;
	@SerializedName("maskedPan")
	@Expose
	private String maskedPan;
	@SerializedName("cardExpiryMMYY")
	@Expose
	private String cardExpiryMMYY;
	@SerializedName("nameOnCard")
	@Expose
	private String nameOnCard;
	@SerializedName("creditCardType")
	@Expose
	private String creditCardType;

	public String getPgCardId() {
		return pgCardId;
	}

	public void setPgCardId(String pgCardId) {
		this.pgCardId = pgCardId;
	}

	public String getMaskedPan() {
		return maskedPan;
	}

	public void setMaskedPan(String maskedPan) {
		this.maskedPan = maskedPan;
	}

	public String getCardExpiryMMYY() {
		return cardExpiryMMYY;
	}

	public void setCardExpiryMMYY(String cardExpiryMMYY) {
		this.cardExpiryMMYY = cardExpiryMMYY;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public String getCreditCardType() {
		return creditCardType;
	}

	public void setCreditCardType(String creditCardType) {
		this.creditCardType = creditCardType;
	}

}