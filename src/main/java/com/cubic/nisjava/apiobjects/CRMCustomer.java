package com.cubic.nisjava.apiobjects;

import com.cubic.backoffice.apiobjects.WSCustomerContact;

/**
 * CRMCustomer
 * Implements the JSON object to create a new Customer via NIS Customer Service API
 */
public class CRMCustomer {

	private String customerType;
	private WSCustomerContact contact;
	
	/**
	 * CRMCustomer()
	 * Default constructor
	 */
	public CRMCustomer() {
		// Default
	}
	
	/**
	 * CRMCustomer()
	 * Constructor that takes all parameters to initialize member data
	 * @param customerType - Customer Type
	 * @param contact - WSCustomerContactInfo object with Contact information
	 */
	public CRMCustomer(String customerType, WSCustomerContact contact) {
		this.customerType = customerType;
		this.setContact(contact);
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public WSCustomerContact getContact() {
		return contact;
	}

	public void setContact(WSCustomerContact contact) {
		this.contact = contact;
	}
}
