package com.louts.service;

import com.lotus.common.kit.JsonKit;
import com.lotus.dao.pojo.Permission;
import com.lotus.service.ServiceNativeConfig;
import com.lotus.service.auth.PermissionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceNativeConfig.class)
public class PermissionServiceTest {

    @Autowired
    private PermissionService permissionService;

    @Test
    public void testFindPermission() {
        List<Permission> permission = permissionService.findPermission(1);
        JsonKit.jsonPrint(permission);
    }

    @Test
    public void testFindByRoleId() {
        List<Map<String, Object>> list = permissionService.findByRoleId(1);
        JsonKit.jsonPrint(list);
    }
}
