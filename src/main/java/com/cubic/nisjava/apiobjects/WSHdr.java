package com.cubic.nisjava.apiobjects;

import org.apache.commons.lang3.builder.ToStringBuilder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSHdr {

	@SerializedName("result")
	@Expose
	private String result;
	
	@SerializedName("uid")
	@Expose
	private String uid;
	
	@SerializedName("errorKey")
	@Expose
	private String errorKey;
	
	@SerializedName("fieldName")
	@Expose
	private String fieldName;	
	
	@SerializedName("errorMessage")
	@Expose
	private String errorMessage;

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getErrorKey() {
		return errorKey;
	}

	public void setErrorKey(String errorKey) {
		this.errorKey = errorKey;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String message) {
        this.errorMessage = message;
    }

    @Override
    public String toString() {
    return new ToStringBuilder(this).append("result", result).append("uid", uid).append("fieldName", fieldName).append("errorKey", errorKey).append("errorMessage", errorMessage).toString();
    }
    
}
