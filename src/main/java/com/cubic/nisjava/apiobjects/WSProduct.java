package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSProduct {

	@SerializedName("fareInstrumentId")
	@Expose
	private Integer fareInstrumentId;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("nameShort")
	@Expose
	private String nameShort;
	@SerializedName("supportsAutoload")
	@Expose
	private Boolean supportsAutoload;
	@SerializedName("price")
	@Expose
	private Integer price;
	@SerializedName("pageNumber")
	@Expose
	private Integer pageNumber;
	@SerializedName("buttonNumber")
	@Expose
	private Integer buttonNumber;

	public Integer getFareInstrumentId() {
		return fareInstrumentId;
	}

	public void setFareInstrumentId(Integer fareInstrumentId) {
		this.fareInstrumentId = fareInstrumentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameShort() {
		return nameShort;
	}

	public void setNameShort(String nameShort) {
		this.nameShort = nameShort;
	}

	public Boolean getSupportsAutoload() {
		return supportsAutoload;
	}

	public void setSupportsAutoload(Boolean supportsAutoload) {
		this.supportsAutoload = supportsAutoload;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
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

}
