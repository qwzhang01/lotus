package com.louts.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotus.common.entity.Page;
import com.lotus.service.ServiceNativeConfig;
import com.lotus.service.content.AudioAttachmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceNativeConfig.class)
public class AudioAttachmentServiceTest {
    @Autowired
    private AudioAttachmentService audioAttachmentService;

    @Test
    public void testPageList() {
        Page<Map<String, Object>> page = audioAttachmentService.findPageList(-1, 1, 0);
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(page));
        } catch (JsonProcessingException e) {
            System.out.println("Json转换失败");
        }
    }

    @Test
    public void testAudioDetail() {
        Map<String, Object> detail = audioAttachmentService.getAudioDetail(169);
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(detail));
        } catch (JsonProcessingException e) {
            System.out.println("Json转换失败");
        }
    }
}
