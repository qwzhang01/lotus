package com.louts.service;

import com.lotus.common.entity.Page;
import com.lotus.common.kit.JsonKit;
import com.lotus.service.ServiceNativeConfig;
import com.lotus.service.system.OptionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceNativeConfig.class)
public class OptionServiceTest {
    @Autowired
    private OptionService optionService;

    @Test
    public void testPage() {
        Page<Map<String, Object>> list = optionService.findPageList(null, 1, 10);
        JsonKit.jsonPrint(list);
    }

    @Test
    public void testGet() {
        String value = optionService.get("download_btn_href");
        System.out.println(value);
    }
}
