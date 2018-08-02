package com.lotus.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lotus.common.entity.Page;
import com.lotus.common.kit.PageKit;
import com.lotus.dao.mapper.ParamMapper;
import com.lotus.service.system.ParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ParamServiceImpl implements ParamService {

    @Autowired
    private ParamMapper paramMapper;

    @Override
    public Page<Map<String, Object>> findPageList(String searchKey, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Map<String, Object>> list = paramMapper.selectList(searchKey);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        return PageKit.pageComplete(pageInfo);
    }

    @Override
    public String getCodeName(String type, String code) {
        return paramMapper.selectByTypeAndCode(type, code);
    }
}
