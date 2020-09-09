package com.example.model.Response;

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
"pm25",
"pm10"
})
public class Daily {

@JsonProperty("pm25")
private List<Pollutants> pm25 = null;
@JsonProperty("pm10")
private List<Pollutants> pm10 = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("pm25")
public List<Pollutants> getPm25() {
return pm25;
}

@JsonProperty("pm25")
public void setPm25(List<Pollutants> pm25) {
this.pm25 = pm25;
}

@JsonProperty("pm10")
public List<Pollutants> getPm10() {
return pm10;
}

@JsonProperty("pm10")
public void setPm10(List<Pollutants> pm10) {
this.pm10 = pm10;
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