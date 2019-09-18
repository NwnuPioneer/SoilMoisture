package com.example.demo.mapper;

import com.example.demo.dao.sensor_type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface sensor_typeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(sensor_type record);

    int insertSelective(sensor_type record);

    sensor_type selectByName(String sensor_name);

    List<sensor_type> selectAll();

    int selectTotal();


    sensor_type selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(sensor_type record);

    int updateByPrimaryKey(sensor_type record);
}