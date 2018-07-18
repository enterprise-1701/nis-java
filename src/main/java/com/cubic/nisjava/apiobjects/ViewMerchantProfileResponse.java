package com.cubic.nisjava.apiobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ViewMerchantProfileResponse {

@SerializedName("hdr")
@Expose
private WSHdr hdr;

public WSHdr getHdr() {
return hdr;
}

public void setHdr(WSHdr hdr) {
this.hdr = hdr;
}

@Override
public String toString() {
return new ToStringBuilder(this).append("hdr", hdr).toString();
}

}