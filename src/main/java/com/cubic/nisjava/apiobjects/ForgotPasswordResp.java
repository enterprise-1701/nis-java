package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ForgotPasswordResp {

@SerializedName("verificationToken")
@Expose
private String verificationToken;
@SerializedName("tokenExpiryDateTime")
@Expose
private String tokenExpiryDateTime;
@SerializedName("customerId")
@Expose
private String customerId;
@SerializedName("contactId")
@Expose
private String contactId;

public String getVerificationToken() {
return verificationToken;
}

public void setVerificationToken(String verificationToken) {
this.verificationToken = verificationToken;
}

public String getTokenExpiryDateTime() {
return tokenExpiryDateTime;
}

public void setTokenExpiryDateTime(String tokenExpiryDateTime) {
this.tokenExpiryDateTime = tokenExpiryDateTime;
}

public String getCustomerId() {
return customerId;
}

public void setCustomerId(String customerId) {
this.customerId = customerId;
}

public String getContactId() {
return contactId;
}

public void setContactId(String contactId) {
this.contactId = contactId;
}

@Override
public String toString() {
return new ToStringBuilder(this).append("verificationToken", verificationToken).append("tokenExpiryDateTime", tokenExpiryDateTime).append("customerId", customerId).append("contactId", contactId).toString();
}

}