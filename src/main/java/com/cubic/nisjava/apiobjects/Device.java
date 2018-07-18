package com.cubic.nisjava.apiobjects;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"deviceId",
"location",
"deviceSerialNumber",
"nickName",
"status"
})
public class Device {

@JsonProperty("deviceId")
private String deviceId;
@JsonProperty("location")
private Location location;
@JsonProperty("deviceSerialNumber")
private String deviceSerialNumber;
@JsonProperty("nickName")
private String nickName;
@JsonProperty("status")
private String status;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("deviceId")
public String getDeviceId() {
return deviceId;
}

@JsonProperty("deviceId")
public void setDeviceId(String deviceId) {
this.deviceId = deviceId;
}

@JsonProperty("location")
public Location getLocation() {
return location;
}

@JsonProperty("location")
public void setLocation(Location location) {
this.location = location;
}

@JsonProperty("deviceSerialNumber")
public String getDeviceSerialNumber() {
return deviceSerialNumber;
}

@JsonProperty("deviceSerialNumber")
public void setDeviceSerialNumber(String deviceSerialNumber) {
this.deviceSerialNumber = deviceSerialNumber;
}

@JsonProperty("nickName")
public String getNickName() {
return nickName;
}

@JsonProperty("nickName")
public void setNickName(String nickName) {
this.nickName = nickName;
}

@JsonProperty("status")
public String getStatus() {
return status;
}

@JsonProperty("status")
public void setStatus(String status) {
this.status = status;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}