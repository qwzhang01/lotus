package com.lotus.rpc.service.content;


import java.util.List;
import java.util.Map;

public interface TaxonomyRpcService {
    /**
     * 根据手机栏目获取内容类型
     *
     * @param appColumn
     * @return
     */
    List<Map<String, Object>> findByAppColumn(String appColumn);
}
