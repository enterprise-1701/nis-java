package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSTransitaccountProductList {

	@SerializedName("maxAddTransitValue")
	@Expose
	private Integer maxAddTransitValue;
	@SerializedName("products")
	@Expose
	private List<WSTransitaccountProduct> products = null;

	public Integer getMaxAddTransitValue() {
		return maxAddTransitValue;
	}

	public void setMaxAddTransitValue(Integer maxAddTransitValue) {
		this.maxAddTransitValue = maxAddTransitValue;
	}

	public List<WSTransitaccountProduct> getProducts() {
		return products;
	}

	public void setProducts(List<WSTransitaccountProduct> products) {
		this.products = products;
	}

}