package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSOneAccountInfo {

	@SerializedName("oneAccountId")
	@Expose
	private Integer oneAccountId;
	@SerializedName("accountStatus")
	@Expose
	private WSAccountStatus accountStatus;
	@SerializedName("purse")
	@Expose
	private List<WSPurse> purse = null;
	@SerializedName("linkedAccounts")
	@Expose
	private List<Object> linkedAccounts = null;

	public Integer getOneAccountId() {
		return oneAccountId;
	}

	public void setOneAccountId(Integer oneAccountId) {
		this.oneAccountId = oneAccountId;
	}

	public WSAccountStatus getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(WSAccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}

	public List<WSPurse> getPurse() {
		return purse;
	}

	public void setPurse(List<WSPurse> purse) {
		this.purse = purse;
	}

	public List<Object> getLinkedAccounts() {
		return linkedAccounts;
	}

	public void setLinkedAccounts(List<Object> linkedAccounts) {
		this.linkedAccounts = linkedAccounts;
	}

}
