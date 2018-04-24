package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSCXSDocument {

	@SerializedName("eulaId")
	@Expose
	private Integer eulaId;
	@SerializedName("eulaDocumentId")
	@Expose
	private Integer eulaDocumentId;
	@SerializedName("document")
	@Expose
	private String document;
	@SerializedName("format")
	@Expose
	private String format;
	@SerializedName("locale")
	@Expose
	private WSCXSLocale locale;
	@SerializedName("fileName")
	@Expose
	private String fileName;

	public Integer getEulaId() {
		return eulaId;
	}

	public void setEulaId(Integer eulaId) {
		this.eulaId = eulaId;
	}

	public Integer getEulaDocumentId() {
		return eulaDocumentId;
	}

	public void setEulaDocumentId(Integer eulaDocumentId) {
		this.eulaDocumentId = eulaDocumentId;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public WSCXSLocale getLocale() {
		return locale;
	}

	public void setLocale(WSCXSLocale locale) {
		this.locale = locale;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((document == null) ? 0 : document.hashCode());
		result = prime * result + ((eulaDocumentId == null) ? 0 : eulaDocumentId.hashCode());
		result = prime * result + ((eulaId == null) ? 0 : eulaId.hashCode());
		result = prime * result + ((fileName == null) ? 0 : fileName.hashCode());
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + ((locale == null) ? 0 : locale.hashCode());
		return result;
	}
	
	private static String errorMessage;

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
	
		WSCXSDocument other = (WSCXSDocument) obj;
		if (document == null) {
			if (other.document != null) {
				errorMessage = String.format( "document %s != %s", document, other.document );
				return false;
			}
		} else if (!document.equals(other.document)) {
			errorMessage = String.format( "document %s != %s", document, other.document );
			return false;
		}
		
		if (eulaDocumentId == null) {
			if (other.eulaDocumentId != null) {
				errorMessage = String.format( "eulaDocumentId %s != %s", eulaDocumentId, other.eulaDocumentId );
				return false;
			}
		} else if (!eulaDocumentId.equals(other.eulaDocumentId)) {
			errorMessage = String.format( "eulaDocumentId %s != %s", eulaDocumentId, other.eulaDocumentId );
			return false;
		}
		
		if (eulaId == null) {
			if (other.eulaId != null) {
				errorMessage = String.format( "eulaId %s != %s", eulaId, other.eulaId );
				return false;
			}
		} else if (!eulaId.equals(other.eulaId)) {
			errorMessage = String.format( "eulaId %s != %s", eulaId, other.eulaId );
			return false;
		}
		
		if (fileName == null) {
			if (other.fileName != null) {
				errorMessage = String.format( "fileName %s != %s", fileName, other.fileName );
				return false;
			}
		} else if (!fileName.equals(other.fileName)) {
			errorMessage = String.format( "fileName %s != %s", fileName, other.fileName );
			return false;
		}
		
		if (format == null) {
			if (other.format != null) {
				errorMessage = String.format( "format %s != %s", format, other.format );
				return false;
			}
		} else if (!format.equals(other.format)) {
			errorMessage = String.format( "format %s != %s", format, other.format );
			return false;
		}
		
		if (locale == null) {
			if (other.locale != null) {
				errorMessage = WSCXSLocale.getErrorMessage(); 
				return false;
			}
		} else if (!locale.equals(other.locale)) {
			errorMessage = WSCXSLocale.getErrorMessage(); 
			return false;
		}
		return true;
	}

}
