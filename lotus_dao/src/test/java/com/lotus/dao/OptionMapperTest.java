package com.lotus.dao;

import com.lotus.common.kit.JsonKit;
import com.lotus.dao.mapper.OptionMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoNativeConfig.class)
public class OptionMapperTest {

    @Autowired
    private OptionMapper optionMapper;

    @Test
    public void testSelectList() {
        List<Map<String, Object>> list = optionMapper.selectList(null);
        JsonKit.jsonPrint(list);
        list = optionMapper.selectList("download_btn_href");
        JsonKit.jsonPrint(list);
    }

    @Test
    public void test() {
        String desc = optionMapper.selectByOptionKey("download_btn_desc");
        System.out.println(desc);
    }
}
