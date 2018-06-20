package com.cubic.nisjava.apiobjects;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ForgotPassword {

@SerializedName("username")
@Expose
private String username;
@SerializedName("securityQuestionAnswers")
@Expose
private List<SecurityQuestionAnswer> securityQuestionAnswers = null;

public String getUsername() {
return username;
}

public void setUsername(String username) {
this.username = username;
}

public List<SecurityQuestionAnswer> getSecurityQuestionAnswers() {
return securityQuestionAnswers;
}

public void setSecurityQuestionAnswers(List<SecurityQuestionAnswer> securityQuestionAnswers) {
this.securityQuestionAnswers = securityQuestionAnswers;
}

@Override
public String toString() {
return new ToStringBuilder(this).append("username", username).append("securityQuestionAnswers", securityQuestionAnswers).toString();
}

}