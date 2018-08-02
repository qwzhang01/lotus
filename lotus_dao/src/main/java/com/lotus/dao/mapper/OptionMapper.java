package com.lotus.dao.mapper;

import com.lotus.dao.pojo.Option;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface OptionMapper extends com.lotus.dao.gmapper.OptionMapper {
    String selectByOptionKey(String key);

    List<Map<String, Object>> selectList(@Param("searchKey") String searchKey);

    /**
     * 根据实体键获取实体
     *
     * @param key
     * @return
     */
    Option selectModelByOptionKey(String key);
}