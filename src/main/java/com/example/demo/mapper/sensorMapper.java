package com.example.demo.mapper;

import com.example.demo.dao.sensor;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface sensorMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(sensor record);

    int insertSelective(sensor record);

    sensor selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(sensor record);

    int updateByPrimaryKey(sensor record);

    int selectByTotal();

    List<sensor> selectAll();
}