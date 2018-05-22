package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSNISContact {

	@SerializedName("contactId")
	@Expose
	private String contactId;
	@SerializedName("contactType")
	@Expose
	private String contactType;
	@SerializedName("name")
	@Expose
	private WSName name;
	@SerializedName("address")
	@Expose
	private WSAddress address;
	@SerializedName("phone")
	@Expose
	private List<WSPhone> phone = null;
	@SerializedName("email")
	@Expose
	private String email;
	@SerializedName("dateOfBirth")
	@Expose
	private String dateOfBirth;
	@SerializedName("personalIdentifierInfo")
	@Expose
	private WSPersonalIdentifierInfo personalIdentifierInfo;
	@SerializedName("username")
	@Expose
	private String username;
	@SerializedName("pin")
	@Expose
	private String pin;
	@SerializedName("securityQAs")
	@Expose
	private List<WSSecurityQA> securityQAs = null;
	@SerializedName("credentialStatus")
	@Expose
	private String credentialStatus;
	@SerializedName("contactStatus")
	@Expose
	private String contactStatus;

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public WSName getName() {
		return name;
	}

	public void setName(WSName name) {
		this.name = name;
	}

	public WSAddress getAddress() {
		return address;
	}

	public void setAddress(WSAddress address) {
		this.address = address;
	}

	public List<WSPhone> getPhone() {
		return phone;
	}

	public void setPhone(List<WSPhone> phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public WSPersonalIdentifierInfo getPersonalIdentifierInfo() {
		return personalIdentifierInfo;
	}

	public void setPersonalIdentifierInfo(WSPersonalIdentifierInfo personalIdentifierInfo) {
		this.personalIdentifierInfo = personalIdentifierInfo;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public List<WSSecurityQA> getSecurityQAs() {
		return securityQAs;
	}

	public void setSecurityQAs(List<WSSecurityQA> securityQAs) {
		this.securityQAs = securityQAs;
	}

	public String getCredentialStatus() {
		return credentialStatus;
	}

	public void setCredentialStatus(String credentialStatus) {
		this.credentialStatus = credentialStatus;
	}

	public String getContactStatus() {
		return contactStatus;
	}

	public void setContactStatus(String contactStatus) {
		this.contactStatus = contactStatus;
	}

}