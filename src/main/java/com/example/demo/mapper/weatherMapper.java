package com.example.demo.mapper;

import com.example.demo.dao.weather;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
public interface weatherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(weather record);

    int selectTotal();

    List<weather> selectAll();

    int insertSelective(weather record);

    weather selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(weather record);

    int updateByPrimaryKey(weather record);
}