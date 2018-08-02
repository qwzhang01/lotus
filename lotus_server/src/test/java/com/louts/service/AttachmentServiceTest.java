package com.louts.service;

import com.lotus.common.kit.JsonKit;
import com.lotus.service.ServiceNativeConfig;
import com.lotus.service.attachment.AttachmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceNativeConfig.class)
public class AttachmentServiceTest {
    @Autowired
    private AttachmentService attachmentService;

    @Test
    public void testFindList() {
        List<Map<String, Object>> list = attachmentService.findList("", 1, "");
        JsonKit.jsonPrint(list);
        list = attachmentService.findList("", 1, "圆满");
        JsonKit.jsonPrint(list);
    }
}
