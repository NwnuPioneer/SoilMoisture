package com.example.demo;

import com.example.demo.dao.sensor;
import com.example.demo.mapper.sensorMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import com.example.demo.mapper.sensorMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {


	private com.example.demo.mapper.sensorMapper sensorMapper;

	@Test
	public void contextLoads() {
/*		sensor sensor = new sensor();
		sensor.setSensorCode("1");
		sensor.setSensorCurrentvalue("1");
		sensor.setCreatTime(new Date());
		sensor.setSensorVoltagevalue("1");
		sensorMapper.insert(sensor);*/
	}

}
