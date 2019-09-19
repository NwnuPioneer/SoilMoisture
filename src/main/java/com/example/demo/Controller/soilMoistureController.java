package com.example.demo.Controller;

import com.example.demo.ServerSocket.HandlerThread;

import com.example.demo.dao.battery;
import com.example.demo.dao.sensor;
import com.example.demo.dao.sensor_type;

import com.example.demo.dao.weather;
import com.example.demo.mapper.sensorMapper;
import com.example.demo.mapper.sensor_typeMapper;
import com.example.demo.mapper.batteryMapper;
import com.example.demo.mapper.weatherMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static java.util.concurrent.Executors.*;

@Controller
public class soilMoistureController{
    private HandlerThread handlerThreads;
    @Resource
    private sensorMapper sensorMapper;
    @Resource
    private sensor_typeMapper sensor_typeMapper;
    @Resource
    private batteryMapper batteryMapper;
    @Resource
    private weatherMapper weatherMapper;


    public static final int PORT = 9092;//监听的端口号9092

    public String init(String sensor_type) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                // 一旦有堵塞, 则表示服务器与客户端获得了连接
                Socket client = serverSocket.accept();
                // 处理这次连接
                //创建一个线程池
                ExecutorService exec = newCachedThreadPool();
                HandlerThread handlerThread=new HandlerThread(client);
                Future f = exec.submit(handlerThread);
                return f.get().toString();
            }
        } catch (Exception e) {
            System.out.println("服务器异常: " + e.getMessage());
            return null;
        }
    }
    //sensor传感器数据存储
    public void sensorInsert(String sensor_type, String hexDate){
        System.out.println("=================");
        System.out.println("sensor_type"+sensor_type);
        String[] databyte = hexDate.split(" ");
        for (int i = 0; i <databyte.length ; i++) {
            System.out.println(i+"========"+databyte[i]);
        }
        //485传感器数据存储
        if(sensor_type.equals("风速")||sensor_type.equals("风向")||sensor_type.equals("光照")||sensor_type.equals("雨量")||sensor_type.equals("PM2.5")||sensor_type.equals("GPRS")||sensor_type.equals("太阳能控制器"))
        {
            String sensor_currentValue = databyte[3]+databyte[4]+databyte[5];
            String sensor_voltageValue = databyte[7]+databyte[8]+databyte[9];
            //将16进制的数转化为10进制的数据
            Long sensor_currentValueNum = Long.parseLong(sensor_currentValue,16);
            Long sensor_voltageValueNum = Long.parseLong(sensor_voltageValue,16);
            //数据存储
            sensor sensor = new sensor();
            sensor.setCreatTime(new Date());
            sensor.setSensorCurrentvalue(String.valueOf(sensor_currentValueNum));
            sensor.setSensorVoltagevalue(String.valueOf(sensor_voltageValueNum));
            sensor.setSensorCode(sensor_type);
            sensorMapper.insert(sensor);
            System.out.println(sensor.getId());
        }
        //其他传感器的数存储
        else if(sensor_type.equals("温湿度")||sensor_type.equals("气压")||sensor_type.equals("土温"))
        {
            String sensor_currentValue = databyte[11]+databyte[12]+databyte[13];
            String sensor_voltageValue = databyte[15]+databyte[16]+databyte[17];
            //将16进制的数转化为10进制的数据
            Long sensor_currentValueNum = Long.parseLong(sensor_currentValue,16);
            Long sensor_voltageValueNum = Long.parseLong(sensor_voltageValue,16);
            //数据存储
            sensor sensor = new sensor();
            sensor.setCreatTime(new Date());
            sensor.setSensorCurrentvalue(String.valueOf(sensor_currentValueNum));
            sensor.setSensorVoltagevalue(String.valueOf(sensor_voltageValueNum));
            sensor.setSensorCode(sensor_type);
            sensorMapper.insert(sensor);

        } else{
            System.out.println("插入失败");

        }
    }
    //电池传感器数据存储
    public void batteryInsert(String battery_type, String hexDate){
        System.out.println("=================");
        System.out.println("sensor_type"+battery_type);
        String[] databyte = hexDate.split(" ");
        for (int i = 0; i <databyte.length ; i++) {
            System.out.println(i+"========"+databyte[i]);
        }
        //电池数据存储
        if(battery_type.equals("电池"))
        {
            String battery_currentValue = databyte[19]+databyte[20]+databyte[21];
            String battery_voltageValue = databyte[23]+databyte[24]+databyte[25];
            String illumination_value = databyte[27]+databyte[28]+databyte[29]+databyte[30];
            //将16进制的数转化为10进制的数据
            Long battery_currentValueNum = Long.parseLong(battery_currentValue,16);
            Long battery_voltageValueNum = Long.parseLong(battery_voltageValue,16);
            Long illumination_valueNum = Long.parseLong(illumination_value,16);
            //数据存储
            battery battery = new battery();
            //battery.setBatteryCode(battery_type);
            battery.setBatteryCurrentvalue(String.valueOf(battery_currentValueNum));
            battery.setBatteryVoltagevalue(String.valueOf(battery_voltageValueNum));
            battery.setCreatTime(new Date());
            battery.setIlluminationValue(String.valueOf(illumination_valueNum));
            batteryMapper.insert(battery);
        }
        else{
            System.out.println("插入失败");

        }
    }

    @RequestMapping(value = "/sensor")
    @ResponseBody
    public void insert_date(){
        String sensortype="气压";
        String sensorData = init("气压");
        System.out.println(sensorData);
        if(sensorData!=null){
            sensorInsert(sensortype,sensorData);
        }
    }
    //加载首页
    @RequestMapping(value = "index")
    public String index(){
        return "index";
    }

    //页面跳转
    @RequestMapping(value = "/toPage",method = RequestMethod.GET)
    public String toPage(HttpServletRequest request, Model model){
        String url = request.getParameter("url");
        return url;
    }

    //sensor传感器页面加载数据
    @RequestMapping(value = "/toPageSensor",method = RequestMethod.GET)
    public String toPageSensor(HttpServletRequest request,Model model){
        String url  = request.getParameter("url");
        //查询数据库所有传感器的数据
        List<sensor> sensors = sensorMapper.selectAll();
        //List<sensor_type> sensor_typeList = sensor_typeMapper.selectAll();
        //sensor_typeList.remove(10);
        Integer total = sensorMapper.selectByTotal();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (sensor sensor : sensors) {
            sensor.setFormatTime(sdf.format(sensor.getCreatTime()));
            if(sensor.getSensorCode().equals("a")){
                sensor.setSensorType("485类型传感器");
            }else{
                sensor.setSensorType("其他类型传感器");
            }

        }
        model.addAttribute("sensorTotal",total);
        model.addAttribute("sensors",sensors);
        //model.addAttribute("sensor_typeList",sensor_typeList);
        return url;
    }

    //电池传感器页面加载数据
    @RequestMapping(value = "/toPageBattery",method = RequestMethod.GET)
    public String toPageBattery(HttpServletRequest request,Model model){
        String url  = request.getParameter("url");
        //查询数据库所有传感器的数据
        List<battery> batteryList = batteryMapper.selectAll();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (battery battery : batteryList) {
            battery.setFormatTime(sdf.format(battery.getCreatTime()));
        }
        //sensor_type sensor_type= sensor_typeMapper.selectByPrimaryKey(11);
        Integer total = batteryMapper.selectTotal();
        model.addAttribute("batteryTotal",total);
        model.addAttribute("batteryList",batteryList);
        //model.addAttribute("sensor_type",sensor_type);
        return url;
    }

    //weather页面加载数据
    @RequestMapping(value = "/toPageWeather",method = RequestMethod.GET)
    public String toPageWeather(HttpServletRequest request,Model model){
        String url  = request.getParameter("url");
        //查询数据库所有传感器的数据
        List<weather> weathers = weatherMapper.selectAll();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (weather weather : weathers) {
            weather.setFormatTime(sdf.format(weather.getCreatTime()));
        }
        Integer total = weatherMapper.selectTotal();
        model.addAttribute("weatherTotal",total);
        model.addAttribute("weatherList",weathers);
        return url;
    }

    //sensor传感器数据添加
    @RequestMapping(value = "/insert_sensor",method = RequestMethod.POST)
    @ResponseBody
    public void insertSensor(HttpServletRequest request,Model model){
        //查询数据库所有传感器的数据
        String sensor_code = request.getParameter("sensor_name");
        String sensorData = init(sensor_code);
        if(sensor_code!=null){
            sensorInsert(sensor_code,sensorData);
        }else{
            return ;
        }
    }

    //电池数据添加
    @RequestMapping(value = "/insert_battery",method = RequestMethod.POST)
    @ResponseBody
    public void insertBattery(HttpServletRequest request,Model model){
        //查询数据库所有电池的数据
        String battery_name = request.getParameter("battery_name");
        String batteryData = init(battery_name);
        if(battery_name!=null){
            batteryInsert(battery_name,batteryData);
        }else{
            return ;
        }
    }
}
