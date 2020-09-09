package com.example.model.Response;

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
"pm25",
"co",
"dew",
"h",
"no2",
"o3",
"p",
"pm10",
"so2",
"t",
"w",
"wg"
})
public class Iaqi {

@JsonProperty("pm25")
private Pollutant pm25;
@JsonProperty("co")
private Pollutant co;
@JsonProperty("dew")
private Pollutant dew;
@JsonProperty("h")
private Pollutant h;
@JsonProperty("no2")
private Pollutant no2;
@JsonProperty("o3")
private Pollutant o3;
@JsonProperty("p")
private Pollutant p;
@JsonProperty("pm10")
private Pollutant pm10;
@JsonProperty("so2")
private Pollutant so2;
@JsonProperty("t")
private Pollutant t;
@JsonProperty("w")
private Pollutant w;
@JsonProperty("wg")
private Pollutant wg;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("pm25")
public Pollutant getPm25() {
return pm25;
}

@JsonProperty("pm25")
public void setPm25(Pollutant pm25) {
this.pm25 = pm25;
}

@JsonProperty("co")
public Pollutant getCo() {
return co;
}

@JsonProperty("co")
public void setCo(Pollutant co) {
this.co = co;
}

@JsonProperty("dew")
public Pollutant getDew() {
return dew;
}

@JsonProperty("dew")
public void setDew(Pollutant dew) {
this.dew = dew;
}

@JsonProperty("h")
public Pollutant getH() {
return h;
}

@JsonProperty("h")
public void setH(Pollutant h) {
this.h = h;
}

@JsonProperty("no2")
public Pollutant getNo2() {
return no2;
}

@JsonProperty("no2")
public void setNo2(Pollutant no2) {
this.no2 = no2;
}

@JsonProperty("o3")
public Pollutant getO3() {
return o3;
}

@JsonProperty("o3")
public void setO3(Pollutant o3) {
this.o3 = o3;
}

@JsonProperty("p")
public Pollutant getP() {
return p;
}

@JsonProperty("p")
public void setP(Pollutant p) {
this.p = p;
}

@JsonProperty("pm10")
public Pollutant getPm10() {
return pm10;
}

@JsonProperty("pm10")
public void setPm10(Pollutant pm10) {
this.pm10 = pm10;
}

@JsonProperty("so2")
public Pollutant getSo2() {
return so2;
}

@JsonProperty("so2")
public void setSo2(Pollutant so2) {
this.so2 = so2;
}

@JsonProperty("t")
public Pollutant getT() {
return t;
}

@JsonProperty("t")
public void setT(Pollutant t) {
this.t = t;
}

@JsonProperty("w")
public Pollutant getW() {
return w;
}

@JsonProperty("w")
public void setW(Pollutant w) {
this.w = w;
}

@JsonProperty("wg")
public Pollutant getWg() {
return wg;
}

@JsonProperty("wg")
public void setWg(Pollutant wg) {
this.wg = wg;
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
