package com.cubic.nisjava.apiobjects;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

@SerializedName("userId")
@Expose
private String userId;
@SerializedName("userType")
@Expose
private String userType;
@SerializedName("email")
@Expose
private String email;
@SerializedName("name")
@Expose
private Name name;
@SerializedName("username")
@Expose
private String username;
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

public String getEmail() {
return email;
}

public void setEmail(String email) {
this.email = email;
}

public Name getName() {
return name;
}

public void setName(Name name) {
this.name = name;
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

@Override
public String toString() {
return new ToStringBuilder(this).append("userId", userId).append("userType", userType).append("email", email).append("name", name).append("username", username).append("userStatus", userStatus).append("credentialStatus", credentialStatus).append("securityQAs", securityQAs).append("phone", phone).toString();
}

}