package com.example.demo.dao;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;

public class battery {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String batteryCurrentvalue;

    private String batteryVoltagevalue;

    private String illuminationValue;

    private Date creatTime;

    private String batteryCode;

    public battery(Integer id, String batteryCurrentvalue, String batteryVoltagevalue, String illuminationValue, Date creatTime, String batteryCode) {
        this.id = id;
        this.batteryCurrentvalue = batteryCurrentvalue;
        this.batteryVoltagevalue = batteryVoltagevalue;
        this.illuminationValue = illuminationValue;
        this.creatTime = creatTime;
        this.batteryCode = batteryCode;
    }

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

    public String getIlluminationValue() {
        return illuminationValue;
    }

    public void setIlluminationValue(String illuminationValue) {
        this.illuminationValue = illuminationValue == null ? null : illuminationValue.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getBatteryCode() {
        return batteryCode;
    }

    public void setBatteryCode(String batteryCode) {
        this.batteryCode = batteryCode == null ? null : batteryCode.trim();
    }

    @Override
    public String toString() {
        return "battery{" +
                "id=" + id +
                ", batteryCurrentvalue='" + batteryCurrentvalue + '\'' +
                ", batteryVoltagevalue='" + batteryVoltagevalue + '\'' +
                ", illuminationValue='" + illuminationValue + '\'' +
                ", creatTime=" + creatTime +
                ", batteryCode='" + batteryCode + '\'' +
                '}';
    }
}