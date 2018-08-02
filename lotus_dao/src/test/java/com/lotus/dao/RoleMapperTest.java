package com.lotus.dao;

import com.lotus.common.kit.JsonKit;
import com.lotus.dao.mapper.RoleMapper;
import com.lotus.dao.pojo.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = DaoNativeConfig.class)
public class RoleMapperTest {
    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void testSelectList() {
        List<Map<String, Object>> list = roleMapper.selectList("系统");
        JsonKit.jsonPrint(list);
    }

    @Test
    public void testSelectByUserId() {
        List<Role> roles = roleMapper.selectByUserId(1);
        JsonKit.jsonPrint(roles);
    }

    @Test
    public void testDelete() {
        Integer count1 = roleMapper.deleteRoleUser(113);
        Integer count2 = roleMapper.deleteRolePermission(113);
        System.out.println(count1);
        System.out.println(count2);
    }

    @Test
    public void testFindAll() {
        List<Map<String, Object>> list = roleMapper.selectRoleUser(null);
        JsonKit.jsonPrint(list);
        list = roleMapper.selectRoleUser(1);
        JsonKit.jsonPrint(list);
    }

    //@Test
    public void testInsertRolePermission() {
        Integer roleId = 113;
        Integer[] permissionIds = {75, 76};
        Integer count = roleMapper.insertRolePermission(roleId, permissionIds);
        System.out.println(count);
    }
}
