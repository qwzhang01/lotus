package com.lotus.rpc.service.system.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lotus.common.entity.Page;
import com.lotus.dao.pojo.Option;
import com.lotus.rpc.service.system.OptionRpcService;
import com.lotus.service.system.OptionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Map;

@Service(version = "1.0.0")
public class OptionRpcServiceImpl implements OptionRpcService {

    @Autowired
    private OptionService optionService;

    @Override
    public boolean save(Option option) {
        return optionService.save(option);
    }

    @Override
    public Option getByKey(String key) {
        return optionService.getByKey(key);
    }

    @Override
    public Option getById(Integer optionId) {
        Option option = optionService.getById(optionId);
        return option;
    }

    @Override
    public String get(String key) {
        String value = optionService.get(key);
        return value;
    }

    @Override
    public Integer getInt(String key) {
        return optionService.getInt(key);
    }

    @Override
    public BigDecimal getBigDecimal(String key) {
        return optionService.getBigDecimal(key);
    }

    @Override
    public Page<Map<String, Object>> findPageList(String searchKey, Integer pageNumber, Integer pageSize) {
        return optionService.findPageList(searchKey, pageNumber, pageSize);
    }
}
