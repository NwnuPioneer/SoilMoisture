package com.example.demo.dao;

import javax.persistence.Transient;
import java.util.Date;

public class weather {
    private Integer id;

    private String city;

    private String condTxt;

    private String tmpMax;

    private String tmpMin;

    private String windDir;

    private String windSc;

    private String hum;

    private String pres;

    private String sunstarttime;

    private String sunendtime;

    private String datetime;

    private Date creatTime;

    @Transient
    private String formatTime;

    public weather(Integer id, String city, String condTxt, String tmpMax, String tmpMin, String windDir, String windSc, String hum, String pres, String sunstarttime, String sunendtime, String datetime, Date creatTime) {
        this.id = id;
        this.city = city;
        this.condTxt = condTxt;
        this.tmpMax = tmpMax;
        this.tmpMin = tmpMin;
        this.windDir = windDir;
        this.windSc = windSc;
        this.hum = hum;
        this.pres = pres;
        this.sunstarttime = sunstarttime;
        this.sunendtime = sunendtime;
        this.datetime = datetime;
        this.creatTime = creatTime;
    }

    public void setFormatTime(String formatTime){this.formatTime=formatTime;}

    public String getFormatTime(){return formatTime;}

    public weather() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCondTxt() {
        return condTxt;
    }

    public void setCondTxt(String condTxt) {
        this.condTxt = condTxt == null ? null : condTxt.trim();
    }

    public String getTmpMax() {
        return tmpMax;
    }

    public void setTmpMax(String tmpMax) {
        this.tmpMax = tmpMax == null ? null : tmpMax.trim();
    }

    public String getTmpMin() {
        return tmpMin;
    }

    public void setTmpMin(String tmpMin) {
        this.tmpMin = tmpMin == null ? null : tmpMin.trim();
    }

    public String getWindDir() {
        return windDir;
    }

    public void setWindDir(String windDir) {
        this.windDir = windDir == null ? null : windDir.trim();
    }

    public String getWindSc() {
        return windSc;
    }

    public void setWindSc(String windSc) {
        this.windSc = windSc == null ? null : windSc.trim();
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum == null ? null : hum.trim();
    }

    public String getPres() {
        return pres;
    }

    public void setPres(String pres) {
        this.pres = pres == null ? null : pres.trim();
    }

    public String getSunstarttime() {
        return sunstarttime;
    }

    public void setSunstarttime(String sunstarttime) {
        this.sunstarttime = sunstarttime == null ? null : sunstarttime.trim();
    }

    public String getSunendtime() {
        return sunendtime;
    }

    public void setSunendtime(String sunendtime) {
        this.sunendtime = sunendtime == null ? null : sunendtime.trim();
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime == null ? null : datetime.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        return "weather{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", condTxt='" + condTxt + '\'' +
                ", tmpMax='" + tmpMax + '\'' +
                ", tmpMin='" + tmpMin + '\'' +
                ", windDir='" + windDir + '\'' +
                ", windSc='" + windSc + '\'' +
                ", hum='" + hum + '\'' +
                ", pres='" + pres + '\'' +
                ", sunstarttime='" + sunstarttime + '\'' +
                ", sunendtime='" + sunendtime + '\'' +
                ", datetime='" + datetime + '\'' +
                ", creatTime=" + creatTime +
                '}';
    }
}