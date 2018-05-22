package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSCustomerInfo {

	@SerializedName("customerId")
	@Expose
	private String customerId;
	@SerializedName("customerType")
	@Expose
	private String customerType;
	@SerializedName("customerStatus")
	@Expose
	private WSCustomerStatus customerStatus;
	@SerializedName("contacts")
	@Expose
	private List<WSNISContact> contacts = null;
	@SerializedName("addresses")
	@Expose
	private List<WSAddress_> addresses = null;
	@SerializedName("insertedDtm")
	@Expose
	private String insertedDtm;
	@SerializedName("business")
	@Expose
	private WSBusiness business;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public WSCustomerStatus getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(WSCustomerStatus customerStatus) {
		this.customerStatus = customerStatus;
	}

	public List<WSNISContact> getContacts() {
		return contacts;
	}

	public void setContacts(List<WSNISContact> contacts) {
		this.contacts = contacts;
	}

	public List<WSAddress_> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<WSAddress_> addresses) {
		this.addresses = addresses;
	}

	public String getInsertedDtm() {
		return insertedDtm;
	}

	public void setInsertedDtm(String insertedDtm) {
		this.insertedDtm = insertedDtm;
	}

	public WSBusiness getBusiness() {
		return business;
	}

	public void setBusiness(WSBusiness business) {
		this.business = business;
	}

}
