package com.cubic.nisjava.apiobjects;

import java.util.List;

import com.cubic.backoffice.apiobjects.WSAdjustProductLineItem;

/**
 * CRMOrderAdjustment
 * Implements the JSON object to Add a new Order via NIS Customer Service API
 */
public class CSOrderAdjustment {

	private String customerId;
	private String unregisteredEmail;
	private String clientRefId;
	private List<WSAdjustProductLineItem> productLineItems;
	private String reasonCode;
	private String notes;
	private int financiallyResponsibleOperatorId;
	
	/**
	 * CRMOrderAdjustment()
	 * Default constructor
	 */
	public CSOrderAdjustment() {
		// Default
	}
	
	/**
	 * CRMOrderAdjustment()
	 * Constructor that takes all parameters to initialize member data
	 * @param oneaccountId - Unique identifier for the one account
	 * @param subsystemId - Unique identifier for the subsystem where the travel token is registered
	 * @param accountRef - Account number of the travel token associated within the given subsystem
	 * @param subsystemAccountNickname - Specifies a nickname to be associated with the subsystem account
	 */
	public CSOrderAdjustment(String customerId, String unregisteredEmail, String clientRefId, List<WSAdjustProductLineItem> productLineItems,
			String reasonCode, String notes, int financiallyResponsibleOperatorId) {
		this.customerId = customerId;
		this.unregisteredEmail = unregisteredEmail;
		this.clientRefId = clientRefId;
		this.productLineItems = productLineItems;
		this.reasonCode = reasonCode;
		this.notes = notes;
		this.financiallyResponsibleOperatorId = financiallyResponsibleOperatorId;
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the unregisteredEmail
	 */
	public String getUnregisteredEmail() {
		return unregisteredEmail;
	}

	/**
	 * @param unregisteredEmail the unregisteredEmail to set
	 */
	public void setUnregisteredEmail(String unregisteredEmail) {
		this.unregisteredEmail = unregisteredEmail;
	}

	/**
	 * @return the clientRefId
	 */
	public String getClientRefId() {
		return clientRefId;
	}

	/**
	 * @param clientRefId the clientRefId to set
	 */
	public void setClientRefId(String clientRefId) {
		this.clientRefId = clientRefId;
	}

	/**
	 * @return the productLineItems
	 */
	public List<WSAdjustProductLineItem> getProductLineItems() {
		return productLineItems;
	}

	/**
	 * @param productLineItems the productLineItems to set
	 */
	public void setProductLineItems(List<WSAdjustProductLineItem> productLineItems) {
		this.productLineItems = productLineItems;
	}

	/**
	 * @return the reasonCode
	 */
	public String getReasonCode() {
		return reasonCode;
	}

	/**
	 * @param reasonCode the reasonCode to set
	 */
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	/**
	 * @return the notes
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * @param notes the notes to set
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * @return the financiallyResponsibleOperatorId
	 */
	public int getFinanciallyResponsibleOperatorId() {
		return financiallyResponsibleOperatorId;
	}

	/**
	 * @param financiallyResponsibleOperatorId the financiallyResponsibleOperatorId to set
	 */
	public void setFinanciallyResponsibleOperatorId(int financiallyResponsibleOperatorId) {
		this.financiallyResponsibleOperatorId = financiallyResponsibleOperatorId;
	}
}
