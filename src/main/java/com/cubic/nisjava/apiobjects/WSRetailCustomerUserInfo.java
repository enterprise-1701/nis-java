/**
 * @author 203610
 * Jun 15, 2018
 */
package com.cubic.nisjava.apiobjects;

import java.util.List;

import com.cubic.backoffice.apiobjects.WSSecurityQA;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSRetailCustomerUserInfo 
{	
	@SerializedName("userType")
	@Expose
	private String userType;
	
	@SerializedName("employeeName")
	@Expose
	private WSName employeeName;

	@SerializedName("employeeId")
	@Expose
	private Integer employeeId;
	
	@SerializedName("username")
	@Expose
	private String username;
	
	@SerializedName("userId")
	@Expose
	private String userId;
	
	@SerializedName("name")
	@Expose
	private WSName name;
	
	@SerializedName("userStatus")
	@Expose
	private String userStatus;
	
	@SerializedName("credentialStatus")
	@Expose
	private String credentialStatus;
	
	@SerializedName("securityQAs")
	@Expose
	private List<WSSecurityQA> securityQAs = null;
	
	@SerializedName("phone")
	@Expose
	private List<WSPhone> phone = null;
	
	@SerializedName("email")
	@Expose
	private String email;	
	
	public Integer getEmployeeId() {
	return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
	this.employeeId = employeeId;
	}

	public WSName getEmployeeName() {
	return employeeName;
	}

	public void setEmployeeName(WSName employeeName) {
	this.employeeName = employeeName;
	}

	public String getUserId() {
	return userId;
	}

	public void setUserId(String userId) {
	this.userId = userId;
	}

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

	public String getUserStatus() {
	return userStatus;
	}

	public void setUserStatus(String userStatus) {
	this.userStatus = userStatus;
	}

	public String getCredentialStatus() {
	return credentialStatus;
	}

	public void setCredentialStatus(String credentialStatus) {
	this.credentialStatus = credentialStatus;
	}

	public List<WSSecurityQA> getSecurityQAs() {
	return securityQAs;
	}

	public void setSecurityQAs(List<WSSecurityQA> securityQAs) {
	this.securityQAs = securityQAs;
	}

	public List<WSPhone> getPhone() {
	return phone;
	}

	public void setPhone(List<WSPhone> phone) {
	this.phone = phone;
	}

	public String getEmail() {
	return email;
	}

	public void setEmail(String email) {
	this.email = email;
	}
	
	public WSName getName() {
		return name;
	}

	public void setName(WSName name) {
		this.name = name;
	}
}
