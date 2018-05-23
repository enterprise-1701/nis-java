/***********************************************************************************
* Copyright (c) 2017  CUBIC Transportation Systems. All rights reserved.
* CUBIC PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
***********************************************************************************/
package com.cubic.nisjava.apiobjects;

import java.util.ArrayList;
import java.util.List;

/**
 * @since Feb 23, 2018
 * @author VijayaBhaskar Palem
 */

public class WSCustomerRegisterRequest {
	
	private WSAddressExt address;
	private String dateOfBirth;
	private String email;
	private WSName name;
	private OptionalData optionalData;
	private String password;
	private String username;
	private List<WSPhone> phone = new ArrayList<WSPhone>();
	private String securityQuestion;
	private String securityAnswer;
	private String ivrPin;
	
	public String getIvrPin() {
		return ivrPin;
	}
	public void setIvrPin(String ivrPin) {
		this.ivrPin = ivrPin;
	}
	public void setPhone(List<WSPhone> phone) {
		this.phone = phone;
	}
	public String getSecurityQuestion() {
		return securityQuestion;
	}
	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}
	public String getSecurityAnswer() {
		return securityAnswer;
	}
	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}
	public WSAddressExt getAddress() {
		return address;
	}
	public void setAddress(WSAddressExt address) {
		this.address = address;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
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
	public OptionalData getOptionalData() {
		return optionalData;
	}
	public void setOptionalData(OptionalData optionalData) {
		this.optionalData = optionalData;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public List<WSPhone> getPhone() {
		return phone;
	}
	public void setPhones(List<WSPhone> phone) {
		this.phone = phone;
	}	
}

