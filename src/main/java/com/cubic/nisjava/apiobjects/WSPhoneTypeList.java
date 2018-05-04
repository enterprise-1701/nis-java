package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSPhoneTypeList {

	@SerializedName("phoneTypes")
	@Expose
	private List<WSPhoneType> phoneTypes = null;

	public List<WSPhoneType> getPhoneTypes() {
		return phoneTypes;
	}

	public void setPhoneTypes(List<WSPhoneType> phoneTypes) {
		this.phoneTypes = phoneTypes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((phoneTypes == null) ? 0 : phoneTypes.hashCode());
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
		WSPhoneTypeList other = (WSPhoneTypeList) obj;
		if (phoneTypes == null) {
			if (other.phoneTypes != null) {
				errorMessage = "phoneTypes == null";
				return false;
			}
		} else {
			if (other.phoneTypes != null) {
				if ( phoneTypes.size() != other.phoneTypes.size() ) {
					errorMessage = String.format("LIST SIZE DIFFERS: %s == %s", phoneTypes.size(), other.phoneTypes.size() );
					return false;
				}
				for ( int i = 0; i < phoneTypes.size(); i++ ) {
					WSPhoneType phoneTypes1 = phoneTypes.get(i);
					WSPhoneType phoneTypes2 = other.phoneTypes.get(i);
					boolean bEqual = phoneTypes1.equals( phoneTypes2 );
					if ( false == bEqual ) {
						errorMessage = WSPhoneType.getErrorMessage();
						return false;
					}
				}
			}
		}
		return true;
	}
}