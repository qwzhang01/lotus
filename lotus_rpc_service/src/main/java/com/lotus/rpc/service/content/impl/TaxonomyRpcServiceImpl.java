package com.lotus.rpc.service.content.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lotus.rpc.service.content.TaxonomyRpcService;
import com.lotus.service.content.TaxonomyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service(version = "1.0.0")
public class TaxonomyRpcServiceImpl implements TaxonomyRpcService {

    @Autowired
    private TaxonomyService taxonomyService;

    /**
     * 根据手机栏目获取内容类型
     *
     * @param appColumn
     * @return
     */
    @Override
    public List<Map<String, Object>> findByAppColumn(String appColumn) {
        return taxonomyService.findByAppColumn(appColumn);
    }
}
