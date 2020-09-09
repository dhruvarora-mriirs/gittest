package com.example.model.Response;

import java.util.HashMap;
import java.util.Map;
import com.example.model.Time;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "idx",
    "aqi",
    "attributions",
    "time",
    "city",
    "dominentpol",
    "iaqi",
    "forecast"
})
public class Datas {

    @JsonProperty("idx")
    private Integer idx;
    @JsonProperty("aqi")
    private Integer aqi;
    @JsonProperty("time")
    private Time time;
    @JsonProperty("city")
    private City city;
    @JsonProperty("iaqi")
    private Iaqi iaqi;
    @JsonProperty("forecast")
    private Forecast forecast;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("idx")
    public Integer getIdx() {
        return idx;
    }

    @JsonProperty("idx")
    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    @JsonProperty("aqi")
    public Integer getAqi() {
        return aqi;
    }

    @JsonProperty("aqi")
    public void setAqi(Integer aqi) {
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

    @JsonProperty("city")
    public City getCity() {
        return city;
    }

    @JsonProperty("city")
    public void setCity(City city) {
        this.city = city;
    }

    @JsonProperty("iaqi")
    public Iaqi getIaqi() {
        return iaqi;
    }

    @JsonProperty("iaqi")
    public void setIaqi(Iaqi iaqi) {
        this.iaqi = iaqi;
    }

    @JsonProperty("forecast")
    public Forecast getForecast() {
        return forecast;
    }

    @JsonProperty("forecast")
    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
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