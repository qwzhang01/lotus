package com.louts.service;

import com.lotus.common.entity.Page;
import com.lotus.common.kit.JsonKit;
import com.lotus.dao.pojo.User;
import com.lotus.service.ServiceNativeConfig;
import com.lotus.service.auth.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceNativeConfig.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testFindPageList() {
        Page<Map<String, Object>> page = userService.findPageList(null, 1, 10);
        JsonKit.jsonPrint(page);
    }

    @Test
    public void testGetByUsername() {
        User user = userService.getByUsername("admin");
        JsonKit.jsonPrint(user);
    }

    //todo add update  delete 怎么做测试
    //@Test
    public void testAddUpdate() {
        User u = new User();
        u.setUsername("222222");
        Boolean add = userService.add(u, null);
        Boolean update = userService.update(u, null);
        System.out.println(add);
        System.out.println(update);
    }

    //@Test
    public void testDelete() {
        User u = new User();
        u.setUserid(374);
        userService.delete(u);
    }
}
