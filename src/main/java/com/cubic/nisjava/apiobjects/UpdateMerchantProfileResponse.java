package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class UpdateMerchantProfileResponse {

@SerializedName("user")
@Expose
private User user;

public User getUser() {
return user;
}

public void setUser(User user) {
this.user = user;
}

@Override
public String toString() {
return new ToStringBuilder(this).append("user", user).toString();
}

}