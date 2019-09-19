package com.example.demo.dao;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Transient;
import java.util.Date;

public class battery {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String batteryCurrentvalue;

    private String batteryVoltagevalue;

    private String solarCurrentvalue;

    private String solarVoltagevalue;

    private String illuminationValue;

    private String electricityValue;

    private Date creatTime;

    @Transient
    private String formatTime;

    public battery(Integer id, String batteryCurrentvalue, String batteryVoltagevalue, String solarCurrentvalue, String solarVoltagevalue, String illuminationValue, String electricityValue, Date creatTime) {
        this.id = id;
        this.batteryCurrentvalue = batteryCurrentvalue;
        this.batteryVoltagevalue = batteryVoltagevalue;
        this.solarCurrentvalue = solarCurrentvalue;
        this.solarVoltagevalue = solarVoltagevalue;
        this.illuminationValue = illuminationValue;
        this.electricityValue = electricityValue;
        this.creatTime = creatTime;
    }


    public void setFormatTime(String formatTime){this.formatTime=formatTime;}

    public String getFormatTime(){return formatTime;}

    public battery() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBatteryCurrentvalue() {
        return batteryCurrentvalue;
    }

    public void setBatteryCurrentvalue(String batteryCurrentvalue) {
        this.batteryCurrentvalue = batteryCurrentvalue == null ? null : batteryCurrentvalue.trim();
    }

    public String getBatteryVoltagevalue() {
        return batteryVoltagevalue;
    }

    public void setBatteryVoltagevalue(String batteryVoltagevalue) {
        this.batteryVoltagevalue = batteryVoltagevalue == null ? null : batteryVoltagevalue.trim();
    }

    public String getSolarCurrentvalue() {
        return solarCurrentvalue;
    }

    public void setSolarCurrentvalue(String solarCurrentvalue) {
        this.solarCurrentvalue = solarCurrentvalue == null ? null : solarCurrentvalue.trim();
    }

    public String getSolarVoltagevalue() {
        return solarVoltagevalue;
    }

    public void setSolarVoltagevalue(String solarVoltagevalue) {
        this.solarVoltagevalue = solarVoltagevalue == null ? null : solarVoltagevalue.trim();
    }

    public String getIlluminationValue() {
        return illuminationValue;
    }

    public void setIlluminationValue(String illuminationValue) {
        this.illuminationValue = illuminationValue == null ? null : illuminationValue.trim();
    }

    public String getElectricityValue() {
        return electricityValue;
    }

    public void setElectricityValue(String electricityValue) {
        this.electricityValue = electricityValue == null ? null : electricityValue.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}