package com.example.demo.Controller;

import com.example.demo.dao.battery;
import com.example.demo.dao.sensor;
import com.example.demo.dao.sensor_type;
import com.example.demo.mapper.batteryMapper;
import com.example.demo.mapper.sensor_typeMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author dushikang
 * @version 1.0
 * @date 2019/9/18 22:38
 */
@Controller
public class sensorController {

    private battery battery;
    private com.example.demo.dao.sensor sensor;
    private sensor_type sensor_type;

    @Resource
    private com.example.demo.mapper.batteryMapper batteryMapper;
    @Resource
    private com.example.demo.mapper.sensor_typeMapper sensor_typeMapper;

//    //页面跳转
//    @RequestMapping(value = "/toPage",method = RequestMethod.GET)
//    public String toPage(HttpServletRequest request, Model model){
//        String url = request.getParameter("url");
//        return url;
//    }
/*    //电池传感器页面加载数据
    @RequestMapping(value = "/toPageBattery",method = RequestMethod.GET)
    public String toPageBattery(HttpServletRequest request, Model model){
        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXx");
        String url  = request.getParameter("url");
        //查询数据库所有传感器的数据
        List<battery> batteryList = batteryMapper.selectAll();
        for (battery bt:batteryList
             ) {
            System.out.println(bt.getId());
        }
        sensor_type sensor_type= sensor_typeMapper.selectByPrimaryKey(11);
        System.out.println(sensor_type.getSensorName());
        Integer total = batteryMapper.selectTotal();
        System.out.println(total);

        model.addAttribute("batteryTotal",total);

        model.addAttribute("batteryList",batteryList);
        model.addAttribute("sensor_type",sensor_type);
        return "battery.html";
    }*/
}
