package com.example.demo.Controller;

import com.example.demo.dao.weather;
import com.example.demo.mapper.weatherMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class weatherController {

    @Resource
    private weatherMapper weatherMapper;
    //@Autowired
    private  weather weather;

    //weather数据请求获取
    public static String getweather(String url,String city) {
        //参数字符串，如果拼接在请求链接之后，需要对中文进行 URLEncode   字符集 UTF-8
        String param ="key=0a733454b9694f8fa9cbafc36031eb9a&location="+city;
        StringBuilder sb = new StringBuilder();
        InputStream is = null;
        BufferedReader br = null;
        PrintWriter out = null;
        try {
            //传入接口地址
            URL uri = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) uri.openConnection();
            connection.setRequestMethod("POST");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(10000);
            connection.setRequestProperty("accept", "*/*");
            //发送参数
            connection.setDoOutput(true);
            out = new PrintWriter(connection.getOutputStream());
            out.print(param);
            out.flush();
            //接收结果
            is = connection.getInputStream();
            br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line;

            //缓冲逐行读取
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception ignored) {
        } finally {
            //关闭流
            try {
                if (is != null) {
                    is.close();
                }
                if (br != null) {
                    br.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (Exception ignored) {
            }
        }
        return url;
    }
    //json数据解析并存储
    public List<weather> JsonTestInsert(String Jsondata, String city) throws JSONException {
        JSONObject jsonObject = new JSONObject(Jsondata);
        JSONArray jsonArray = jsonObject.getJSONArray("HeWeather6");
        JSONObject jsonObject1 = jsonArray.getJSONObject(0) ;
        JSONArray jsonArray1 = jsonObject1.getJSONArray("daily_forecast");
        List<weather> weatherList = new ArrayList<weather>();
        for (int i = 0; i < jsonArray1.length(); i++) {
            JSONObject jsonObject2 = jsonArray1.getJSONObject(i);
            weather weather = new weather();
            weather.setCity(city);
            weather.setCondTxt(jsonObject2.getString("cond_txt_d"));
            weather.setDatetime(jsonObject2.getString("date"));
            weather.setHum(jsonObject2.getString("hum"));
            weather.setPres(jsonObject2.getString("pres"));
            weather.setSunstarttime(jsonObject2.getString("sr"));
            weather.setSunendtime(jsonObject2.getString("ss"));
            weather.setTmpMax(jsonObject2.getString("tmp_max"));
            weather.setTmpMin(jsonObject2.getString("tmp_min"));
            weather.setWindDir(jsonObject2.getString("wind_dir"));
            weather.setWindSc(jsonObject2.getString("wind_spd"));
            weather.setCreatTime(new Date());
            weatherList.add(weather);
            weatherMapper.insert(weather);

        }
        return weatherList;
    }
    //天气数据存储接口
    @RequestMapping(value = "/insert_weather",method = RequestMethod.POST)
    @ResponseBody
    public void insertweather(HttpServletRequest request,Model model) throws JSONException {
        String city = request.getParameter("city_name");
        String data = getweather("https://free-api.heweather.net/s6/weather/",city);
        List<weather> weatherList = JsonTestInsert(data,city);
    }

    //删除数据
    @RequestMapping(value = "/deleteweather",method = RequestMethod.GET)
    @ResponseBody
    public void deleteweather(HttpServletRequest request){
        String id = request.getParameter("id");
        weatherMapper.deleteByPrimaryKey(Integer.valueOf(id));
    }
}
