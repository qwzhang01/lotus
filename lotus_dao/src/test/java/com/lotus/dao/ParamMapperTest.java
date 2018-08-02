package com.lotus.dao;

import com.lotus.common.kit.JsonKit;
import com.lotus.dao.mapper.ParamMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoNativeConfig.class)
public class ParamMapperTest {
    @Autowired
    private ParamMapper paramMapper;

    @Test
    public void testSelectList() {
        List<Map<String, Object>> list = paramMapper.selectList(null);
        JsonKit.jsonPrint(list);
        list = paramMapper.selectList("包括");
        JsonKit.jsonPrint(list);
    }
}
