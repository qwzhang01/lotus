package com.louts.service;

import com.lotus.service.ServiceNativeConfig;
import com.lotus.service.redis.CacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceNativeConfig.class)
public class RedisCacheServiceTest {

    @Autowired
    private CacheService cacheService;

    @Test
    public void testGet(){
        String qw = cacheService.get("qw");
        System.out.println(qw);
    }

    @Test
    public void setGet(){
        cacheService.set("qw", "qwzhang01");
    }
}
