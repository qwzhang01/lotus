package com.lotus.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lotus.common.entity.Page;
import com.lotus.common.kit.PageKit;
import com.lotus.dao.mapper.ContentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoNativeConfig.class)
public class ContentMapperTest {
    @Autowired
    private ContentMapper contentMapper;

    @Test
    public void testPageList() {
        PageHelper.startPage(2, 2);
        List<Map<String, Object>> list = contentMapper.selectList("recomm", null);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        Page<Map<String, Object>> page = PageKit.pageComplete(pageInfo);
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(page));
        } catch (JsonProcessingException e) {
            System.out.println("Json转换失败");
        }
    }
}
