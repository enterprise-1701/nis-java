package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSSecurityQuestion {

	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("value")
	@Expose
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	private static String errorMessage;

	public static String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
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
		WSSecurityQuestion other = (WSSecurityQuestion) obj;
		if (name == null) {
			if (other.name != null) {
				errorMessage = String.format("Name DIFFERS: %s == %s", name, other.name );
				return false;
			}
		} else if (!name.equals(other.name)) {
			errorMessage = String.format("Name DIFFERS: %s == %s", name, other.name );
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
