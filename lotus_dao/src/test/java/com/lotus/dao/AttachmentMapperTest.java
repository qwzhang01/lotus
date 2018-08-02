package com.lotus.dao;

import com.lotus.common.kit.JsonKit;
import com.lotus.dao.mapper.AttachmentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoNativeConfig.class)
public class AttachmentMapperTest {
    @Autowired
    private AttachmentMapper attachmentMapper;

    @Test
    public void testGetByName() {
        List<Map<String, Object>> list = attachmentMapper.selectList("", 1, "/1/", "");
        JsonKit.jsonPrint(list);
        list = attachmentMapper.selectList("", null, null, "圆满");
        JsonKit.jsonPrint(list);
    }

    @Test
    public void testSelectSameName() {
        String name = attachmentMapper.selectSameTitle(1, "大圆满前行", null);
        System.out.println(name);
        name = attachmentMapper.selectSameTitle(1, "大圆满前行", 1);
        System.out.println(name);
    }
}
