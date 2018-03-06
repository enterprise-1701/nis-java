package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSStoredValue {

	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("amount")
	@Expose
	private Integer amount;
	@SerializedName("pageNumber")
	@Expose
	private Integer pageNumber;
	@SerializedName("buttonNumber")
	@Expose
	private Integer buttonNumber;
	@SerializedName("loadType")
	@Expose
	private List<String> loadType = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getButtonNumber() {
		return buttonNumber;
	}

	public void setButtonNumber(Integer buttonNumber) {
		this.buttonNumber = buttonNumber;
	}

	public List<String> getLoadType() {
		return loadType;
	}

	public void setLoadType(List<String> loadType) {
		this.loadType = loadType;
	}

}
