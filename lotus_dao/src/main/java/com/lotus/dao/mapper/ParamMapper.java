package com.lotus.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ParamMapper extends com.lotus.dao.gmapper.ParamMapper {

    List<Map<String, Object>> selectList(@Param("searchKey") String searchKey);

    String selectByTypeAndCode(@Param(value = "type") String type, @Param(value = "code") String code);
}