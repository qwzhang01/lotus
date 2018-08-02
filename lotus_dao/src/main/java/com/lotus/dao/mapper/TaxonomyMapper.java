package com.lotus.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface TaxonomyMapper extends com.lotus.dao.gmapper.TaxonomyMapper {

    /**
     * 根据手机栏目获取内容类型
     *
     * @param contentType
     * @return
     */
    List<Map<String, Object>> selectByAppColumn(@Param("contentType") String contentType);
}