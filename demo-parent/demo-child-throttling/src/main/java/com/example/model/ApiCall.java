package com.example.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import java.io.Serializable;

@Entity

public class ApiCall implements Serializable {

    @Id
    @GeneratedValue

    private Long id;

    private String keyy;
  
    private Long timeStamp;

    public ApiCall() {

    }

    public ApiCall(String keyy, Long timeStamp) {
        this.keyy = keyy;
        this.timeStamp = timeStamp;
    }

    public ApiCall(Long id, String keyy, Long timeStamp) {
        this.id = id;
        this.keyy = keyy;
        this.timeStamp = timeStamp;
    }

    public String getKeyy() {
        return keyy;
    }

    public void setKeyy(String keyy) {
        this.keyy = keyy;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}