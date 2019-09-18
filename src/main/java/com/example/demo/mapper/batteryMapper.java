package com.example.demo.mapper;

import com.example.demo.dao.battery;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface batteryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(battery record);

    int insertSelective(battery record);

    battery selectByPrimaryKey(Integer id);

    List<battery> selectAll();

    int selectTotal();

    int updateByPrimaryKeySelective(battery record);

    int updateByPrimaryKey(battery record);
}