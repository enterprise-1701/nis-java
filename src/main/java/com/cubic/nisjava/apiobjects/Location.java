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
"locationId",
"name",
"description",
"gpsCoordinate",
"status"
})
public class Location {

@JsonProperty("locationId")
private Integer locationId;
@JsonProperty("name")
private String name;
@JsonProperty("description")
private String description;
@JsonProperty("gpsCoordinate")
private GpsCoordinate gpsCoordinate;
@JsonProperty("status")
private String status;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("locationId")
public Integer getLocationId() {
return locationId;
}

@JsonProperty("locationId")
public void setLocationId(Integer locationId) {
this.locationId = locationId;
}

@JsonProperty("name")
public String getName() {
return name;
}

@JsonProperty("name")
public void setName(String name) {
this.name = name;
}

@JsonProperty("description")
public String getDescription() {
return description;
}

@JsonProperty("description")
public void setDescription(String description) {
this.description = description;
}

@JsonProperty("gpsCoordinate")
public GpsCoordinate getGpsCoordinate() {
return gpsCoordinate;
}

@JsonProperty("gpsCoordinate")
public void setGpsCoordinate(GpsCoordinate gpsCoordinate) {
this.gpsCoordinate = gpsCoordinate;
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