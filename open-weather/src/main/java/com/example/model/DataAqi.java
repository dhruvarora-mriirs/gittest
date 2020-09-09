package com.example.model;

import java.sql.Time;
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
"uid",
"aqi",
"time",
"station"
})
public class DataAqi {

@JsonProperty("uid")
private Integer uid;
@JsonProperty("aqi")
private String aqi;
@JsonProperty("time")
private Time time;
@JsonProperty("station")
private Station station;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("uid")
public Integer getUid() {
return uid;
}

@JsonProperty("uid")
public void setUid(Integer uid) {
this.uid = uid;
}

@JsonProperty("aqi")
public String getAqi() {
return aqi;
}

@JsonProperty("aqi")
public void setAqi(String aqi) {
this.aqi = aqi;
}

@JsonProperty("time")
public Time getTime() {
return time;
}

@JsonProperty("time")
public void setTime(Time time) {
this.time = time;
}

@JsonProperty("station")
public Station getStation() {
return station;
}

@JsonProperty("station")
public void setStation(Station station) {
this.station = station;
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