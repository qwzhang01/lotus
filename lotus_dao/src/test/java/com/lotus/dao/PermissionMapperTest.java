package com.lotus.dao;

import com.lotus.common.kit.JsonKit;
import com.lotus.dao.mapper.PermissionMapper;
import com.lotus.dao.pojo.Permission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoNativeConfig.class)
public class PermissionMapperTest {
    @Autowired
    private PermissionMapper permissionMapper;

    @Test
    public void testSelectByUserId() {
        List<Permission> permissions =
                permissionMapper.selectByUserId(367);
        JsonKit.jsonPrint(permissions);
    }

    @Test
    public void testSelectByRoleId() {
        List<Map<String, Object>> per = permissionMapper.selectByRoleId(1);
        JsonKit.jsonPrint(per);
    }
}
