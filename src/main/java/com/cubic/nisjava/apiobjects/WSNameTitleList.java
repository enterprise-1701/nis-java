package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSNameTitleList {

	@SerializedName("nameTitles")
	@Expose
	private List<WSNameTitle> nameTitles = null;

	public List<WSNameTitle> getNameTitles() {
		return nameTitles;
	}

	public void setNameTitles(List<WSNameTitle> nameTitles) {
		this.nameTitles = nameTitles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nameTitles == null) ? 0 : nameTitles.hashCode());
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
		WSNameTitleList other = (WSNameTitleList) obj;
		if (nameTitles == null) {
			if (other.nameTitles != null) {
				errorMessage = "nameSuffixes == null";
				return false;
			}
		} else {
			if (other.nameTitles != null) {
				if ( nameTitles.size() != other.nameTitles.size() ) {
					errorMessage = String.format("LIST SIZE DIFFERS: %s == %s", nameTitles.size(), other.nameTitles.size() );
					return false;
				}
				for ( int i = 0; i < nameTitles.size(); i++ ) {
					WSNameTitle nameTitle1 = nameTitles.get(i);
					WSNameTitle nameTitle2 = other.nameTitles.get(i);
					boolean bEqual = nameTitle1.equals( nameTitle2 );
					if ( false == bEqual ) {
						errorMessage = WSNameTitle.getErrorMessage();
						return false;
					}
				}
			}
		}
		return true;
	}

	
}