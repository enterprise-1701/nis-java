package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSCXSLocale {

	@SerializedName("cxsLocaleId")
	@Expose
	private Integer cxsLocaleId;
	@SerializedName("cxsLanguage")
	@Expose
	private String cxsLanguage;
	@SerializedName("cxsLocaleTag")
	@Expose
	private String cxsLocaleTag;

	public Integer getCxsLocaleId() {
		return cxsLocaleId;
	}

	public void setCxsLocaleId(Integer cxsLocaleId) {
		this.cxsLocaleId = cxsLocaleId;
	}

	public String getCxsLanguage() {
		return cxsLanguage;
	}

	public void setCxsLanguage(String cxsLanguage) {
		this.cxsLanguage = cxsLanguage;
	}

	public String getCxsLocaleTag() {
		return cxsLocaleTag;
	}

	public void setCxsLocaleTag(String cxsLocaleTag) {
		this.cxsLocaleTag = cxsLocaleTag;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cxsLanguage == null) ? 0 : cxsLanguage.hashCode());
		result = prime * result + ((cxsLocaleId == null) ? 0 : cxsLocaleId.hashCode());
		result = prime * result + ((cxsLocaleTag == null) ? 0 : cxsLocaleTag.hashCode());
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
			errorMessage = "Obj is null.";
			return false;
		}
		if (getClass() != obj.getClass()) {
			errorMessage = String.format( "Classes are different: %s vs. %s", getClass().getName(), obj.getClass().getName() );
			return false;
		}
		WSCXSLocale other = (WSCXSLocale) obj;
		if (cxsLanguage == null) {
			if (other.cxsLanguage != null) {
				errorMessage = String.format( "CXS Language %s != %s", cxsLanguage, other.cxsLanguage );
				return false;
			}
		} else if (!cxsLanguage.equals(other.cxsLanguage)) {
			errorMessage = String.format( "CXS Language %s != %s", cxsLanguage, other.cxsLanguage );
			return false;
		}
		if (cxsLocaleId == null) {
			if (other.cxsLocaleId != null) {
				errorMessage = String.format( "CXS Locale Id %s != %s", cxsLocaleId, other.cxsLocaleId );
				return false;
			}
		} else if (!cxsLocaleId.equals(other.cxsLocaleId)) {
			errorMessage = String.format( "CXS Locale Id %s != %s", cxsLocaleId, other.cxsLocaleId );
			return false;
		}
		if (cxsLocaleTag == null) {
			if (other.cxsLocaleTag != null) {
				errorMessage = String.format( "CXS Locale Tag %s != %s", cxsLocaleTag, other.cxsLocaleTag );
				return false;
			}
		} else if (!cxsLocaleTag.equals(other.cxsLocaleTag)) {
			errorMessage = String.format( "CXS Locale Tag %s != %s", cxsLocaleTag, other.cxsLocaleTag );
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		WSCXSLocale one = new WSCXSLocale();
		one.setCxsLanguage( "English" );
		one.setCxsLocaleId( 1 );
		one.setCxsLocaleTag("en");
		
		WSCXSLocale two = new WSCXSLocale();
		two.setCxsLanguage( "English" );
		two.setCxsLocaleId( 1 );
		two.setCxsLocaleTag("en");
		
		boolean bEqual = one.equals( two );
		String theErrorMessage = WSCXSLocale.getErrorMessage();
		if ( bEqual == false ) {
			System.out.println( "THEY ARE NOT EQUAL" );
		    System.out.println( "Error message:" + theErrorMessage );
		}
		else {
			System.out.println( "THEY ARE EQUAL" );
		}
	}
}
