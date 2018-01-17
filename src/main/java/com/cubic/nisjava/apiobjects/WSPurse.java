package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSPurse {

	@SerializedName("purseId")
	@Expose
	private Integer purseId;

	@SerializedName("nickname")
	@Expose
	private String nickname;

	@SerializedName("balance")
	@Expose
	private Integer balance;

	@SerializedName("purseStatus")
	@Expose
	private WSPurseStatus purseStatus;

	public Integer getPurseId() {
		return purseId;
	}

	public void setPurseId(Integer purseId) {
		this.purseId = purseId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public WSPurseStatus getPurseStatus() {
		return purseStatus;
	}

	public void setPurseStatus(WSPurseStatus purseStatus) {
		this.purseStatus = purseStatus;
	}

}
