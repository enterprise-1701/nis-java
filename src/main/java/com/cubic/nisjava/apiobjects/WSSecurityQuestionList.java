package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WSSecurityQuestionList {

	@SerializedName("securityQuestions")
	@Expose
	private List<WSSecurityQuestion> securityQuestions = null;

	public List<WSSecurityQuestion> getSecurityQuestions() {
		return securityQuestions;
	}

	public void setSecurityQuestions(List<WSSecurityQuestion> securityQuestions) {
		this.securityQuestions = securityQuestions;
	}

	private static String errorMessage;

	public static String getErrorMessage() {
		return errorMessage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((securityQuestions == null) ? 0 : securityQuestions.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null) {
			errorMessage = "obj == null";
			return false;
		}
		if (getClass() != obj.getClass()) {
			errorMessage = String.format("Class DIFFERS: %s == %s", getClass().getName(), obj.getClass().getName());
			return false;
		}
		WSSecurityQuestionList other = (WSSecurityQuestionList) obj;
		if (securityQuestions == null) {
			if (other.securityQuestions != null) {
				errorMessage = "securityQuestions == null";
				return false;
			}
		} else {
			if (other.securityQuestions != null) {
				if ( securityQuestions.size() != other.securityQuestions.size() ) {
					errorMessage = String.format("LIST SIZE DIFFERS: %s == %s",
							securityQuestions.size(), other.securityQuestions.size() );
					return false;
				}
				for ( int i = 0; i < securityQuestions.size(); i++ ) {
					WSSecurityQuestion securityQuestion1 = securityQuestions.get(i);
					WSSecurityQuestion securityQuestion2 = other.securityQuestions.get(i);
					boolean bEqual = securityQuestion1.equals( securityQuestion2 );
					if ( false == bEqual ) {
						errorMessage = WSSecurityQuestion.getErrorMessage();
						return false;
					}
				}
			}
		}
		return true;
	}
}
