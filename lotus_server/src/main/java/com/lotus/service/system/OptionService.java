package com.lotus.service.system;

import com.lotus.common.entity.Page;
import com.lotus.dao.pojo.Option;

import java.math.BigDecimal;
import java.util.Map;

public interface OptionService {

    Option getByKey(String key);

    Option getById(Integer optionId);

    String get(String key);

    Integer getInt(String key);

    BigDecimal getBigDecimal(String key);

    Page<Map<String, Object>> findPageList(String searchKey, Integer pageNumber, Integer pageSize);

    boolean save(Option splash);
}
