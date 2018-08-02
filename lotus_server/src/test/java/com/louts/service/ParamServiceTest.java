package com.louts.service;

import com.lotus.common.entity.Page;
import com.lotus.common.kit.JsonKit;
import com.lotus.service.ServiceNativeConfig;
import com.lotus.service.system.ParamService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceNativeConfig.class)
public class ParamServiceTest {
    @Autowired
    private ParamService paramService;

    @Test
    public void testPage() {
        Page<Map<String, Object>> list = paramService.findPageList(null, 1, 10);
        JsonKit.jsonPrint(list);
    }
}
