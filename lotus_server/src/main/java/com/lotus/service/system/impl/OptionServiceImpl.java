package com.lotus.service.system.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lotus.common.entity.Page;
import com.lotus.common.kit.PageKit;
import com.lotus.dao.mapper.OptionMapper;
import com.lotus.dao.pojo.Option;
import com.lotus.service.system.OptionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class OptionServiceImpl implements OptionService {

    @Autowired
    private OptionMapper optionMapper;

    @CacheEvict(value = "Option", key = "'get' + #key")
    public void update(String key) {
        System.out.println("移除缓存中此用户号[" + key + "]对应的用户名的缓存");
    }

    @CacheEvict(value = "Option", allEntries = true)
    public void removeAll() {
        System.out.println("移除缓存中的所有数据");
    }

    @Override
    @CacheEvict(value = "Option", key = "'get' + #option.getOptionKey()")
    public boolean save(Option option) {
        if (option.getId() != null && option.getId() != 0) {
            return optionMapper.updateByPrimaryKeySelective(option) == 1;
        }
        return optionMapper.insertSelective(option) == 1;
    }

    @Override
    public Option getByKey(String key) {
        return optionMapper.selectModelByOptionKey(key);
    }

    @Override
    public Option getById(Integer optionId) {
        Option option = optionMapper.selectByPrimaryKey(optionId);
        return option;
    }

    @Override
    @Cacheable(value = "Option", key = "'get' + #key")
    public String get(String key) {
        String value = optionMapper.selectByOptionKey(key);
        return value;
    }

    @Override
    public Integer getInt(String key) {
        String value = get(key);
        if (StringUtils.isNoneBlank(value)) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return 0;
            }
        }
        return 0;
    }

    @Override
    public BigDecimal getBigDecimal(String key) {
        String value = get(key);
        if (StringUtils.isNoneBlank(value)) {
            try {
                return new BigDecimal(value);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return new BigDecimal("0");
            }
        }
        return new BigDecimal("0");
    }

    @Override
    public Page<Map<String, Object>> findPageList(String searchKey, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Map<String, Object>> list = optionMapper.selectList(searchKey);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        return PageKit.pageComplete(pageInfo);
    }


}
