package com.cubic.nisjava.apiobjects;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"devices"
})
public class MerchantDeviceConfigResponse {

@JsonProperty("devices")
private List<Device> devices = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();
private WSHdr hdr;
public WSHdr getHdr() {
	return hdr;
}

public void setHdr(WSHdr hdr) {
	this.hdr = hdr;
}

public void setAdditionalProperties(Map<String, Object> additionalProperties) {
	this.additionalProperties = additionalProperties;
}

@JsonProperty("devices")
public List<Device> getDevices() {
return devices;
}

@JsonProperty("devices")
public void setDevices(List<Device> devices) {
this.devices = devices;
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