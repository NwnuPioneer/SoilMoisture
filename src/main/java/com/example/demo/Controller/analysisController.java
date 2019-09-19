package com.example.demo.Controller;

import com.example.demo.dao.battery;
import com.example.demo.dao.sensor;
import com.example.demo.dao.weather;
import com.example.demo.mapper.batteryMapper;
import com.example.demo.mapper.sensorMapper;
import com.example.demo.mapper.sensor_typeMapper;
import com.example.demo.mapper.weatherMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.plugin2.util.SystemUtil;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author dushikang
 * @version 1.0
 * @date 2019/9/17 13:20
 */
@Controller
public class analysisController {

    @Resource
    private com.example.demo.mapper.sensorMapper sensorMapper;
    @Resource
    private com.example.demo.mapper.batteryMapper batteryMapper;
    @Resource
    private com.example.demo.mapper.weatherMapper weatherMapper;

    /**
     * 天气预报图表
     * @return
     */
    @RequestMapping(value = "/toAnayWeather")
    public String analyWeather(){
        System.out.println("这儿是天气预报");
        return "analyWeather";
    }

    /**
     * 传感器功耗显示图表
     */
    @RequestMapping(value = "/toAnaySenor")
    public String anaySenor(){
        return "analySensor";
    }

    /**
     * 电池与光照值显示图表
     * @return
     */
    @RequestMapping(value = "/toAnayBattery")
    public String anayBattery(){
        return "analyBattery";
    }

    /**
     * 数据可视化页面获取传感器数据
     * @return
     */
    @RequestMapping("/getAnaSenData")
    @ResponseBody
    public Map<String,Object> getAnaSenData(){
        Map<String,Object> result=new HashMap<String,Object>();
        List<sensor> sensorList=sensorMapper.selectAll();
        List<sensor> sensorA=new ArrayList<sensor>();
        List<sensor> sensorB=new ArrayList<sensor>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (sensor sensor : sensorList) {
            sensor.setFormatTime(sdf.format(sensor.getCreatTime()));
            if(sensor.getSensorCode().equals("a")) {
                System.out.println(sensor.getSensorType());
                sensorA.add(sensor);
            }else{
                System.out.println(sensor.getSensorType());
                sensorB.add(sensor);
            }
        }

        String[] strTime=new String[sensorA.size()];
        int[] current=new int[sensorA.size()];
        int[] voltage=new int[sensorA.size()];
        for (int i = 0; i < sensorA.size(); i++) {
            strTime[i]=sensorA.get(i).getFormatTime();
            current[i]=Integer.parseInt(sensorA.get(i).getSensorCurrentvalue());
            voltage[i]=Integer.parseInt(sensorA.get(i).getSensorVoltagevalue());
        }
        result.put("current",current);
        result.put("voltage",voltage);
        result.put("strTime",strTime);

        return result;
    }

    @RequestMapping("/getAnaBatData")
    @ResponseBody
    public Map<String,Object> getAnaBatData(){
        Map<String,Object> result=new HashMap<String,Object>();
        List<battery> batteryList = batteryMapper.selectAll();

        int[] eValue=new int[batteryList.size()];
        int[] sunshine=new int[batteryList.size()];
        int[] solarVoltage=new int[batteryList.size()];
        String[] strTime=new String[batteryList.size()];
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        for (int i = 0; i < batteryList.size(); i++) {
            strTime[i]=sdf.format(batteryList.get(i).getCreatTime());
            eValue[i]=Integer.parseInt(batteryList.get(i).getElectricityValue());
            sunshine[i]=Integer.parseInt(batteryList.get(i).getIlluminationValue());
            solarVoltage[i]=Integer.parseInt(batteryList.get(i).getSolarVoltagevalue());
        }

        result.put("eValue",eValue);
        result.put("sunshine",sunshine);
        result.put("solarVoltage",solarVoltage);
        result.put("strTime",strTime);
        return result;
    }

    @RequestMapping("/getAnaWeaData")
    @ResponseBody
    public Map<String,Object> getAnaWeaData(){
        Map<String,Object> result=new HashMap<String,Object>();
        List<weather> weathers = weatherMapper.selectAll();
        for (int i = 0; i < weathers.size(); i++) {
            if(!weathers.get(i).getCity().equals("北京")){//选择特定城市的天气情况
                weathers.remove(i);
            }
            if(compareDate(weathers.get(i).getDatetime())!=1){//选择当前日期之后的未来七天天气
                weathers.remove(i);
            }
        }
        System.out.println(weathers.size());
        Collections.sort(weathers, new Comparator<weather>() {
            @Override
            public int compare(weather o1, weather o2) {
                return o2.getId()-o1.getId();//降序排列
            }
        });
        //只取七天的数据
        List<weather> tempList=new ArrayList<weather>();
        if(weathers.size()>6){
            for (weather weather : weathers) {
                tempList.add(weather);
            }
            weathers.clear();
            for (int i = 6; i >=0 ; i--) {
                weathers.add(tempList.get(i));
            }
        }

        int[] hTmp=new int[weathers.size()];
        int[] lTmp=new int[weathers.size()];
        int[] windSc=new int[weathers.size()];
        int[] hum=new int[weathers.size()];
        int[] pres=new int[weathers.size()];
        String[] xData=new String[weathers.size()];

        for (int i = 0; i < weathers.size(); i++) {
            hTmp[i]=Integer.parseInt(weathers.get(i).getTmpMax());
            lTmp[i]=Integer.parseInt(weathers.get(i).getTmpMin());
            windSc[i]=Integer.parseInt(weathers.get(i).getWindSc());
            hum[i]=Integer.parseInt(weathers.get(i).getHum());
            pres[i]=Integer.parseInt(weathers.get(i).getPres());

            xData[i]=weathers.get(i).getDatetime()+":"+weathers.get(i).getCondTxt();
        }

        result.put("htmp",hTmp);
        result.put("lTmp",lTmp);
        result.put("windSc",windSc);
        result.put("hum",hum);
        result.put("pres",pres);
        result.put("xData",xData);

        return result;
    }

    /**
     * 比较记录时间和当前时间，当record为当前时间之后，返回1
     * @param record
     * @return
     */
    public static int compareDate(String record){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String nowDate=sdf.format(new Date());;
        String[] nowStr=nowDate.split("-");
        String[] recordStr=record.split("-");
        if(Integer.parseInt(nowStr[0])>Integer.parseInt(recordStr[0])){
            return 0;//天气记录小于当前日期年
        }else{
            if(Integer.parseInt(nowStr[1])>Integer.parseInt(recordStr[1])){
                return 0;//天气记录小于当前月
            }else{
                if (Integer.parseInt(nowStr[2])>Integer.parseInt(recordStr[2])){
                    return 0;//天气记录小于当前日
                }else{
                    return 1;
                }
            }
        }
    }
}
