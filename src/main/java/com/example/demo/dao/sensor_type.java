package com.example.demo.dao;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class sensor_type {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String sensorName;

    private String sensorCode;

    public sensor_type(Integer id, String sensorName, String sensorCode) {
        this.id = id;
        this.sensorName = sensorName;
        this.sensorCode = sensorCode;
    }

    public sensor_type() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName == null ? null : sensorName.trim();
    }

    public String getSensorCode() {
        return sensorCode;
    }

    public void setSensorCode(String sensorCode) {
        this.sensorCode = sensorCode == null ? null : sensorCode.trim();
    }
}