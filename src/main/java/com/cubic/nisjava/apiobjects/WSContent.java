package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSContent {

	@SerializedName("key")
	@Expose
	private String key;
	@SerializedName("value")
	@Expose
	private String value;
	
	private static String diff;	

	public static String getDiff() {
		return diff;
	}

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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WSContent other = (WSContent) obj;
		if (key == null) {
			if (other.key != null) {
				diff = String.format("BAD KEY - EXPECTED null, FOUND %s", other.key );
				return false;
			}
		} else if (!key.equals(other.key)) {
			diff = String.format("BAD KEY - EXPECTED %s, FOUND %s", this.key, other.key );
			return false;
		}
		if (value == null) {
			if (other.value != null) {
				diff = String.format("BAD VALUE - EXPECTED null, FOUND %s", other.value );
				return false;
			}
		} else if (!value.equals(other.value)) {
			diff = String.format("BAD VALUE - EXPECTED %s, FOUND %s", this.value, other.value );
			return false;
		}
		return true;
	}

}
