package com.example.demo.dao;

import java.util.Date;

public class sensor {
    private Integer id;

    private String sensorCode;

    private String sensorCurrentvalue;

    private String sensorVoltagevalue;

    private Date creatTime;

    public sensor(Integer id, String sensorCode, String sensorCurrentvalue, String sensorVoltagevalue, Date creatTime) {
        this.id = id;
        this.sensorCode = sensorCode;
        this.sensorCurrentvalue = sensorCurrentvalue;
        this.sensorVoltagevalue = sensorVoltagevalue;
        this.creatTime = creatTime;
    }


    public sensor() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSensorCode() {
        return sensorCode;
    }

    public void setSensorCode(String sensorCode) {
        this.sensorCode = sensorCode == null ? null : sensorCode.trim();
    }

    public String getSensorCurrentvalue() {
        return sensorCurrentvalue;
    }

    public void setSensorCurrentvalue(String sensorCurrentvalue) {
        this.sensorCurrentvalue = sensorCurrentvalue == null ? null : sensorCurrentvalue.trim();
    }

    public String getSensorVoltagevalue() {
        return sensorVoltagevalue;
    }

    public void setSensorVoltagevalue(String sensorVoltagevalue) {
        this.sensorVoltagevalue = sensorVoltagevalue == null ? null : sensorVoltagevalue.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        return "sensor{" +
                "id=" + id +
                ", sensorCode='" + sensorCode + '\'' +
                ", sensorCurrentvalue='" + sensorCurrentvalue + '\'' +
                ", sensorVoltagevalue='" + sensorVoltagevalue + '\'' +
                ", creatTime=" + creatTime +
                '}';
    }
}