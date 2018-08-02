package com.lotus.service.auth.impl;

import com.lotus.dao.mapper.PermissionMapper;
import com.lotus.dao.pojo.Permission;
import com.lotus.service.auth.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 用户ID获取用户对应的角色的权限
     *
     * @param userId
     * @return
     */
    @Override
    public List<Permission> findPermission(Integer userId) {
        return permissionMapper.selectByUserId(userId);
    }

    /**
     * 获取所有权限,包含角色对应权限标识符
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Map<String, Object>> findByRoleId(Integer roleId) {
        List<Map<String, Object>> list = permissionMapper.selectByRoleId(roleId);
        return list;
    }
}
