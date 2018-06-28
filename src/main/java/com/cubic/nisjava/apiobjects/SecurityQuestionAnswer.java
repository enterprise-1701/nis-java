package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SecurityQuestionAnswer {

@SerializedName("securityQuestion")
@Expose
private String securityQuestion;
@SerializedName("securityAnswer")
@Expose
private String securityAnswer;

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

@Override
public String toString() {
return new ToStringBuilder(this).append("securityQuestion", securityQuestion).append("securityAnswer", securityAnswer).toString();
}

}