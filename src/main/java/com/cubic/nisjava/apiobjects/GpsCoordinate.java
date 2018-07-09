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
"latitude",
"longitude",
"confidence"
})
public class GpsCoordinate {

@JsonProperty("latitude")
private String latitude;
@JsonProperty("longitude")
private String longitude;
@JsonProperty("confidence")
private Integer confidence;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("latitude")
public String getLatitude() {
return latitude;
}

@JsonProperty("latitude")
public void setLatitude(String latitude) {
this.latitude = latitude;
}

@JsonProperty("longitude")
public String getLongitude() {
return longitude;
}

@JsonProperty("longitude")
public void setLongitude(String longitude) {
this.longitude = longitude;
}

@JsonProperty("confidence")
public Integer getConfidence() {
return confidence;
}

@JsonProperty("confidence")
public void setConfidence(Integer confidence) {
this.confidence = confidence;
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