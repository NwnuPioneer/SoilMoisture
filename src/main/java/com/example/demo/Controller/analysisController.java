package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dushikang
 * @version 1.0
 * @date 2019/9/17 13:20
 */
@Controller
public class analysisController {
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
}
