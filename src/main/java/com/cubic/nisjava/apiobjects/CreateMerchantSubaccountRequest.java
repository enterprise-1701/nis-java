package com.cubic.nisjava.apiobjects;

import com.cubic.nisjava.apiobjects.WSName;

public class CreateMerchantSubaccountRequest {
	private String userType;
	private String username;
	private String password;
	private WSName name;
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public WSName getName() {
		return name;
	}
	public void setName(WSName name) {
		this.name = name;
	}
	

}
