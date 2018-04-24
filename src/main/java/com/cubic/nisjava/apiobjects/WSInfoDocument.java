package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSInfoDocument {

	@SerializedName("info")
	@Expose
	private WSCXSInfo info;
	@SerializedName("document")
	@Expose
	private WSCXSDocument document;

	public WSCXSInfo getInfo() {
		return info;
	}

	public void setInfo(WSCXSInfo info) {
		this.info = info;
	}

	public WSCXSDocument getDocument() {
		return document;
	}

	public void setDocument(WSCXSDocument document) {
		this.document = document;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((document == null) ? 0 : document.hashCode());
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		return result;
	}

	private static String errorMessage = "";
	
	public static String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			errorMessage = "this == obj";
			return true;
		}
		
		if (obj == null) {
			errorMessage = "obj == null";
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			errorMessage = String.format( "Classes are different: %s vs. %s", getClass().getName(), obj.getClass().getName() );
			return false;
		}
		
		WSInfoDocument other = (WSInfoDocument) obj;
		if (document == null) {
			if (other.document != null) {
				errorMessage += WSCXSDocument.getErrorMessage();
				return false;
			}
		} else if (!document.equals(other.document)) {
			errorMessage += WSCXSDocument.getErrorMessage();
			return false;
		}
		
		if (info == null) {
			if (other.info != null) {
				errorMessage += WSCXSInfo.getErrorMessage();
				return false;
			}
		} else if (!info.equals(other.info)) {
			errorMessage += WSCXSInfo.getErrorMessage();
			return false;
		}
		return true;
	}

}
