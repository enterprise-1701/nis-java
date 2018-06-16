package com.cubic.nisjava.apiobjects;

public class MerchantErrors {
	private String errorKey;
	private String message;
	
	public String geterrorKey() {
        return errorKey;
	}

	public void seterrorKey(String errorKey) {
        this.errorKey = errorKey;
	}      
    
	public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
