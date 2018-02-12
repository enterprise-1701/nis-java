package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSCardSummary {

	@SerializedName("fareProducts")
	@Expose
	private List<Integer> fareProducts = null;
	@SerializedName("riderClassId")
	@Expose
	private Integer riderClassId;
	@SerializedName("expirationDateTime")
	@Expose
	private String expirationDateTime;
	@SerializedName("hotlistState")
	@Expose
	private String hotlistState;
	@SerializedName("svRemainingAmount")
	@Expose
	private Integer svRemainingAmount;
	@SerializedName("svFareInstrumentId")
	@Expose
	private Integer svFareInstrumentId;
	@SerializedName("svAutoloadSetup")
	@Expose
	private String svAutoloadSetup;
	@SerializedName("svThresholdAmount")
	@Expose
	private Integer svThresholdAmount;
	@SerializedName("svRecurringAmount")
	@Expose
	private Integer svRecurringAmount;
	@SerializedName("svRecurringDateTime")
	@Expose
	private String svRecurringDateTime;
	@SerializedName("totalPasses")
	@Expose
	private Integer totalPasses;
	@SerializedName("cscRegisteredFlag")
	@Expose
	private Boolean cscRegisteredFlag;
	@SerializedName("depositId")
	@Expose
	private Integer depositId;
	@SerializedName("maxAddValue")
	@Expose
	private Integer maxAddValue;
	@SerializedName("maxCancelValue")
	@Expose
	private Integer maxCancelValue;
	@SerializedName("testFlag")
	@Expose
	private Boolean testFlag;
	@SerializedName("historyList")
	@Expose
	private List<Object> historyList = null;

	public List<Integer> getFareProducts() {
		return fareProducts;
	}

	public void setFareProducts(List<Integer> fareProducts) {
		this.fareProducts = fareProducts;
	}

	public Integer getRiderClassId() {
		return riderClassId;
	}

	public void setRiderClassId(Integer riderClassId) {
		this.riderClassId = riderClassId;
	}

	public String getExpirationDateTime() {
		return expirationDateTime;
	}

	public void setExpirationDateTime(String expirationDateTime) {
		this.expirationDateTime = expirationDateTime;
	}

	public String getHotlistState() {
		return hotlistState;
	}

	public void setHotlistState(String hotlistState) {
		this.hotlistState = hotlistState;
	}

	public Integer getSvRemainingAmount() {
		return svRemainingAmount;
	}

	public void setSvRemainingAmount(Integer svRemainingAmount) {
		this.svRemainingAmount = svRemainingAmount;
	}

	public Integer getSvFareInstrumentId() {
		return svFareInstrumentId;
	}

	public void setSvFareInstrumentId(Integer svFareInstrumentId) {
		this.svFareInstrumentId = svFareInstrumentId;
	}

	public String getSvAutoloadSetup() {
		return svAutoloadSetup;
	}

	public void setSvAutoloadSetup(String svAutoloadSetup) {
		this.svAutoloadSetup = svAutoloadSetup;
	}

	public Integer getSvThresholdAmount() {
		return svThresholdAmount;
	}

	public void setSvThresholdAmount(Integer svThresholdAmount) {
		this.svThresholdAmount = svThresholdAmount;
	}

	public Integer getSvRecurringAmount() {
		return svRecurringAmount;
	}

	public void setSvRecurringAmount(Integer svRecurringAmount) {
		this.svRecurringAmount = svRecurringAmount;
	}

	public String getSvRecurringDateTime() {
		return svRecurringDateTime;
	}

	public void setSvRecurringDateTime(String svRecurringDateTime) {
		this.svRecurringDateTime = svRecurringDateTime;
	}

	public Integer getTotalPasses() {
		return totalPasses;
	}

	public void setTotalPasses(Integer totalPasses) {
		this.totalPasses = totalPasses;
	}

	public Boolean getCscRegisteredFlag() {
		return cscRegisteredFlag;
	}

	public void setCscRegisteredFlag(Boolean cscRegisteredFlag) {
		this.cscRegisteredFlag = cscRegisteredFlag;
	}

	public Integer getDepositId() {
		return depositId;
	}

	public void setDepositId(Integer depositId) {
		this.depositId = depositId;
	}

	public Integer getMaxAddValue() {
		return maxAddValue;
	}

	public void setMaxAddValue(Integer maxAddValue) {
		this.maxAddValue = maxAddValue;
	}

	public Integer getMaxCancelValue() {
		return maxCancelValue;
	}

	public void setMaxCancelValue(Integer maxCancelValue) {
		this.maxCancelValue = maxCancelValue;
	}

	public Boolean getTestFlag() {
		return testFlag;
	}

	public void setTestFlag(Boolean testFlag) {
		this.testFlag = testFlag;
	}

	public List<Object> getHistoryList() {
		return historyList;
	}

	public void setHistoryList(List<Object> historyList) {
		this.historyList = historyList;
	}

}
