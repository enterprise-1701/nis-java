package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSCatalog {

	@SerializedName("maxAddValue")
	@Expose
	private Integer maxAddValue;
	@SerializedName("storedValue")
	@Expose
	private List<WSStoredValue> storedValue = null;
	@SerializedName("product")
	@Expose
	private List<WSProduct> product = null;

	public Integer getMaxAddValue() {
		return maxAddValue;
	}

	public void setMaxAddValue(Integer maxAddValue) {
		this.maxAddValue = maxAddValue;
	}

	public List<WSStoredValue> getStoredValue() {
		return storedValue;
	}

	public void setStoredValue(List<WSStoredValue> storedValue) {
		this.storedValue = storedValue;
	}

	public List<WSProduct> getProduct() {
		return product;
	}

	public void setProduct(List<WSProduct> product) {
		this.product = product;
	}

}
