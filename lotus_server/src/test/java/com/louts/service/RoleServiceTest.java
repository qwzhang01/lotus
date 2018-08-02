package com.louts.service;

import com.lotus.common.entity.Page;
import com.lotus.common.kit.JsonKit;
import com.lotus.dao.pojo.Role;
import com.lotus.service.ServiceNativeConfig;
import com.lotus.service.auth.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceNativeConfig.class)
public class RoleServiceTest {
    @Autowired
    private RoleService roleService;

    @Test
    public void testFindPaggeList() {
        Page<Map<String, Object>> page = roleService.findPageList(null, 1, 10);
        JsonKit.jsonPrint(page);
    }

    @Test
    public void testFindAll() {
        List<Map<String, Object>> role = roleService.findList(1);
        JsonKit.jsonPrint(role);
        role = roleService.findList(null);
        JsonKit.jsonPrint(role);
    }

    @Test
    public void testFindRole() {
        List<Role> role = roleService.findRole(1);
        JsonKit.jsonPrint(role);
    }

    @Test
    public void testGetById() {
        Role role = roleService.getById(1);
        JsonKit.jsonPrint(role);
    }

    @Test
    public void testAdd() {
        Role role = new Role();
        role.setRolename("test");
        role.setDescription("test");
        Role roleOld = roleService.getById(113);
        if (roleOld == null) {
            role.setRoleid(113);
        }
        Boolean result = roleService.add(role);
        assertTrue(result);
    }

    @Test
    public void testUpdate() {
        Role role = roleService.getById(113);
        role.setDescription("test1");
        Boolean result = roleService.update(role);
        assertTrue(result);
    }

    @Test
    public void testDelete() {
        Role role = roleService.getById(233);
        if (role == null) {
            role = new Role();
            role.setRoleid(233);
        }
        boolean result = roleService.delete(role);
        System.out.println(result);
    }

    //@Test
    public void testAssginPermission() {
        Role role = new Role();
        role.setRoleid(113);
        boolean assginPermission = roleService.assginPermission(role, new Integer[]{76, 75});
        System.out.println(assginPermission);
    }
}
