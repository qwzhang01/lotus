package com.lotus.service.content;


import java.util.List;
import java.util.Map;

public interface TaxonomyService {
    /**
     * 根据手机栏目获取内容类型
     *
     * @param appColumn
     * @return
     */
    List<Map<String, Object>> findByAppColumn(String appColumn);
}
