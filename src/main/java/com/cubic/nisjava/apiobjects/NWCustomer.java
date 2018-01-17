package com.cubic.nisjava.apiobjects;

import com.cubic.backoffice.apiobjects.WSCustomerContact;

/**
 * CRMCustomer
 * Implements the JSON object to create a new Customer via NIS Customer Service API
 */
public class NWCustomer {

	private String customerType;
	private WSCustomerContact contact;
	
	/**
	 * CRMCustomer()
	 * Default constructor
	 */
	public NWCustomer() {
		// Default
	}

	/**
	 * CRMCustomer()
	 * Constructor that takes all parameters to initialize member data
	 * @param customerType - Customer Type
	 * @param contact - WSCustomerContact object with Contact information
	 */
	public NWCustomer(String customerType, WSCustomerContact contact) {
		this.customerType = customerType;
		this.contact = contact;
	}

	/**
	 * @return the customerType
	 */
	public String getCustomerType() {
		return customerType;
	}

	/**
	 * @param customerType the customerType to set
	 */
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	/**
	 * @return the contact
	 */
	public WSCustomerContact getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(WSCustomerContact contact) {
		this.contact = contact;
	}
}
