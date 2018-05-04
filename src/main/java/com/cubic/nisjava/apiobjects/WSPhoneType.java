package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSPhoneType {

	@SerializedName("key")
	@Expose
	private String key;
	@SerializedName("value")
	@Expose
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	private static String errorMessage;

	public static String getErrorMessage() {
		return errorMessage;
	}	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null) {
			errorMessage = "obj == null";
			return false;
		}
		if (getClass() != obj.getClass()) {
			errorMessage = String.format("Class DIFFERS: %s == %s", getClass().getName(), obj.getClass().getName());
			return false;
		}
		WSPhoneType other = (WSPhoneType) obj;
		if (key == null) {
			if (other.key != null) {
				errorMessage = String.format("Key DIFFERS: %s == %s", key, other.key );
				return false;
			}
		} else if (!key.equals(other.key)) {
			errorMessage = String.format("Key DIFFERS: %s == %s", key, other.key );
			return false;
		}
		if (value == null) {
			if (other.value != null) {
				errorMessage = String.format("Value DIFFERS: %s == %s", value, other.value );
				return false;
			}
		} else if (!value.equals(other.value)) {
			errorMessage = String.format("Value DIFFERS: %s == %s", value, other.value );
			return false;
		}
		return true;
	}
}