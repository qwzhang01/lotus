package com.lotus.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotus.common.kit.JsonKit;
import com.lotus.dao.mapper.UserMapper;
import com.lotus.dao.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoNativeConfig.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList() {
        List<Map<String, Object>> list = userMapper.selectList(null);
        JsonKit.jsonPrint(list);
    }

    @Test
    public void testSelectByUsername() {
        User user = userMapper.selectByUsername("admin");
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(user));
        } catch (JsonProcessingException e) {
            System.out.println("Json转换失败");
        }
    }

    //@Test
    public void testInsert() {
        User u = new User();
        u.setUsername("00000");
        u.setPassword("000000");
        u.setContactway("111111");
        u.setIsonline(1);
        u.setDeletemark(1);
        int i = userMapper.insert(u);
        System.out.println(i);
        JsonKit.jsonPrint(u);
    }

    //@Test
    public void testInsertUserRole() {
        Integer count = userMapper.insertUserRole(376, new Integer[]{141, 140, 139});
        System.out.println(count);
    }
}
