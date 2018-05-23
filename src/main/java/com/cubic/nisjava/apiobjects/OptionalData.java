package com.cubic.nisjava.apiobjects;

public class OptionalData {

	private String securityQuestion;
	private String securityAnswer;
	private String ivrPin;
	
	public String getIvrPin() {
		return ivrPin;
	}

	public void setIvrPin(String ivrPin) {
		this.ivrPin = ivrPin;
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

}
