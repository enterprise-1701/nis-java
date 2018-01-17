package com.cubic.nisjava.apiobjects;

public class CRMCustomerContactPassword {
	private String customerId;
	private String contactId;
	private String oldPassword;
	private String newPassword;
	
	public CRMCustomerContactPassword() {
		// Default
	}

	public CRMCustomerContactPassword (String customerId, String contactId, String oldPassword, String newPassword){
		this.customerId = customerId;
		this.contactId = contactId;
		this.oldPassword = oldPassword;
		this.newPassword = newPassword;
	}
	
	public String getCustomerId() {
        return customerId;
	}

	public void setCustomerId(String customerId) {
        this.customerId = customerId;
	}
	
	public String getContactId() {
        return contactId;
	}

	public void setContactId(String contactId) {
        this.contactId = contactId;
	} 
	
	public String getNewPassword() {
        return newPassword;
	}

	public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
	}      
    
	public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
