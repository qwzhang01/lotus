package com.lotus.service.content.impl;

import com.lotus.dao.mapper.TaxonomyMapper;
import com.lotus.service.content.TaxonomyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaxonomyServiceImpl implements TaxonomyService {

    @Autowired
    private TaxonomyMapper taxonomyMapper;

    @Override
    public List<Map<String, Object>> findByAppColumn(String appColumn) {
        return taxonomyMapper.selectByAppColumn(appColumn);
    }
}
