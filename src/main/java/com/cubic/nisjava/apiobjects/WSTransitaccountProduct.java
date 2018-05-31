package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSTransitaccountProduct {

	@SerializedName("productSku")
	@Expose
	private String productSku;
	@SerializedName("productTypeId")
	@Expose
	private String productTypeId;
	@SerializedName("productFamilyId")
	@Expose
	private String productFamilyId;
	@SerializedName("productName")
	@Expose
	private String productName;
	@SerializedName("validityStartDtm")
	@Expose
	private String validityStartDtm;
	@SerializedName("validityEndDtm")
	@Expose
	private String validityEndDtm;
	@SerializedName("validityDuration")
	@Expose
	private Integer validityDuration;
	@SerializedName("validityDurationType")
	@Expose
	private String validityDurationType;
	@SerializedName("price")
	@Expose
	private Integer price;
	@SerializedName("supportsAutoload")
	@Expose
	private Boolean supportsAutoload;

	public String getProductSku() {
		return productSku;
	}

	public void setProductSku(String productSku) {
		this.productSku = productSku;
	}

	public String getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(String productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getProductFamilyId() {
		return productFamilyId;
	}

	public void setProductFamilyId(String productFamilyId) {
		this.productFamilyId = productFamilyId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getValidityStartDtm() {
		return validityStartDtm;
	}

	public void setValidityStartDtm(String validityStartDtm) {
		this.validityStartDtm = validityStartDtm;
	}

	public String getValidityEndDtm() {
		return validityEndDtm;
	}

	public void setValidityEndDtm(String validityEndDtm) {
		this.validityEndDtm = validityEndDtm;
	}

	public Integer getValidityDuration() {
		return validityDuration;
	}

	public void setValidityDuration(Integer validityDuration) {
		this.validityDuration = validityDuration;
	}

	public String getValidityDurationType() {
		return validityDurationType;
	}

	public void setValidityDurationType(String validityDurationType) {
		this.validityDurationType = validityDurationType;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Boolean getSupportsAutoload() {
		return supportsAutoload;
	}

	public void setSupportsAutoload(Boolean supportsAutoload) {
		this.supportsAutoload = supportsAutoload;
	}

	private static String errorMessage;

	public static String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * Define the hashCode in terms of the member variables so it matches the equals method.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((productFamilyId == null) ? 0 : productFamilyId.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((productSku == null) ? 0 : productSku.hashCode());
		result = prime * result + ((productTypeId == null) ? 0 : productTypeId.hashCode());
		result = prime * result + ((supportsAutoload == null) ? 0 : supportsAutoload.hashCode());
		result = prime * result + ((validityDuration == null) ? 0 : validityDuration.hashCode());
		result = prime * result + ((validityDurationType == null) ? 0 : validityDurationType.hashCode());
		return result;
	}

	/**
	 * Define the equals method in terms of the member variables. 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null) {
			errorMessage = "obj == null";
			return false;
		}
		if (getClass() != obj.getClass()) {
			errorMessage = String.format( "Classes are different: %s vs. %s", getClass().getName(), obj.getClass().getName() );
			return false;
		}
		WSTransitaccountProduct other = (WSTransitaccountProduct) obj;
		if (price == null) {
			if (other.price != null) {
				errorMessage = String.format( "Price %s != %s", price, other.price );
				return false;
			}
		} else if (!price.equals(other.price)) {
			errorMessage = String.format( "Price %s != %s", price, other.price );
			return false;
		}
		if (productFamilyId == null) {
			if (other.productFamilyId != null) {
				errorMessage = String.format( "Product Family Id %s != %s", productFamilyId, other.productFamilyId );
				return false;
			}
		} else if (!productFamilyId.equals(other.productFamilyId)) {
			errorMessage = String.format( "Product Family Id %s != %s", productFamilyId, other.productFamilyId );
			return false;
		}
		if (productName == null) {
			if (other.productName != null) {
				errorMessage = String.format( "Product Name %s != %s", productName, other.productName );
				return false;
			}
		} else if (!productName.equals(other.productName)) {
			errorMessage = String.format( "Product Name %s != %s", productName, other.productName );
			return false;
		}
		if (productSku == null) {
			if (other.productSku != null) {
				errorMessage = String.format( "Product SKU %s != %s", productSku, other.productSku );
				return false;
			}
		} else if (!productSku.equals(other.productSku)) {
			errorMessage = String.format( "Product SKU %s != %s", productSku, other.productSku );
			return false;
		}
		if (productTypeId == null) {
			if (other.productTypeId != null) {
				errorMessage = String.format( "Product Type Id %s != %s", productTypeId, other.productTypeId );
				return false;
			}
		} else if (!productTypeId.equals(other.productTypeId)) {
			errorMessage = String.format( "Product Type Id %s != %s", productTypeId, other.productTypeId );
			return false;
		}
		if (supportsAutoload == null) {
			if (other.supportsAutoload != null) {
				errorMessage = String.format( "Supports Autoload %s != %s", supportsAutoload, other.supportsAutoload );
				return false;
			}
		} else if (!supportsAutoload.equals(other.supportsAutoload)) {
			errorMessage = String.format( "Supports Autoload %s != %s", supportsAutoload, other.supportsAutoload );
			return false;
		}
		if (validityDuration == null) {
			if (other.validityDuration != null) {
				errorMessage = String.format( "Validity Duration %s != %s", validityDuration, other.validityDuration );
				return false;
			}
		} else if (!validityDuration.equals(other.validityDuration)) {
			errorMessage = String.format( "Validity Duration %s != %s", validityDuration, other.validityDuration );
			return false;
		}
		if (validityDurationType == null) {
			if (other.validityDurationType != null) {
				errorMessage = String.format( "Validity Duration Type %s != %s", validityDurationType, other.validityDurationType );
				return false;
			}
		} else if (!validityDurationType.equals(other.validityDurationType)) {
			errorMessage = String.format( "Validity Duration Type %s != %s", validityDurationType, other.validityDurationType );
			return false;
		}
		return true;
	}

}
