package com.lotus.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotus.dao.mapper.AudioAttachmentMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoNativeConfig.class)
public class AudioAttachmentMapperTest {
    @Autowired
    private AudioAttachmentMapper audioAttachmentMapper;

    @Test
    public void testList() {
        List<Map<String, Object>> list = audioAttachmentMapper.selectList(34);
        if (list != null && list.size() > 0) {
            System.out.println(list.size());
            ObjectMapper mapper = new ObjectMapper();
            try {
                System.out.println(mapper.writeValueAsString(list));
            } catch (JsonProcessingException e) {
                System.out.println("Json转换失败");
            }
        }
    }

    @Test
    public void testSelectById() {
        Map<String, Object> map = audioAttachmentMapper.selectById(168);
        if (map != null) {
            ObjectMapper mapper = new ObjectMapper();
            try {
                System.out.println(mapper.writeValueAsString(map));
            } catch (JsonProcessingException e) {
                System.out.println("Json转换失败");
            }
        }
    }

    @Test
    public void testSelectRandom() {
        Integer contentId = audioAttachmentMapper.selectRandom("recomm");
        System.out.println(contentId);
    }
}