package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSNameSuffixList {

	@SerializedName("nameSuffixes")
	@Expose
	private List<WSNameSuffix> nameSuffixes = null;

	public List<WSNameSuffix> getNameSuffixes() {
		return nameSuffixes;
	}

	public void setNameSuffixes(List<WSNameSuffix> nameSuffixes) {
		this.nameSuffixes = nameSuffixes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nameSuffixes == null) ? 0 : nameSuffixes.hashCode());
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
		WSNameSuffixList other = (WSNameSuffixList) obj;
		if (nameSuffixes == null) {
			if (other.nameSuffixes != null) {
				errorMessage = "nameSuffixes == null";
				return false;
			}
		} else {
			if (other.nameSuffixes != null) {
				if ( nameSuffixes.size() != other.nameSuffixes.size() ) {
					errorMessage = String.format("LIST SIZE DIFFERS: %s == %s", nameSuffixes.size(), other.nameSuffixes.size() );
					return false;
				}
				for ( int i = 0; i < nameSuffixes.size(); i++ ) {
					WSNameSuffix nameSuffix1 = nameSuffixes.get(i);
					WSNameSuffix nameSuffix2 = other.nameSuffixes.get(i);
					boolean bEqual = nameSuffix1.equals( nameSuffix2 );
					if ( false == bEqual ) {
						errorMessage = WSNameSuffix.getErrorMessage();
						return false;
					}
				}
			}
		}
		return true;
	}
}