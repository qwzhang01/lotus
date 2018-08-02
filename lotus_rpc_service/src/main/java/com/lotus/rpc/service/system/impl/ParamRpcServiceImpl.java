package com.lotus.rpc.service.system.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lotus.common.entity.Page;
import com.lotus.rpc.service.system.ParamRpcService;
import com.lotus.service.system.ParamService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

@Service(version = "1.0.0")
public class ParamRpcServiceImpl implements ParamRpcService {

    @Autowired
    private ParamService paramService;

    @Override
    public Page<Map<String, Object>> findPageList(String searchKey, Integer pageNumber, Integer pageSize) {
        return paramService.findPageList(searchKey, pageNumber, pageSize);
    }

    @Override
    public String getCodeName(String type, String code) {
        return paramService.getCodeName(type, code);
    }
}
