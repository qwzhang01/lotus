package com.lotus.dao;

import com.lotus.common.kit.JsonKit;
import com.lotus.dao.mapper.TaxonomyMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoNativeConfig.class)
public class TaxonomyMapperTest {
    @Autowired
    private TaxonomyMapper taxonomyMapper;

    @Test
    public void testSelectByAppColumn() {
        List<Map<String, Object>> list = taxonomyMapper.selectByAppColumn("recomm");
        JsonKit.jsonPrint(list);
    }
}
