package com.lotus.rpc.service.auth.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lotus.dao.pojo.Permission;
import com.lotus.rpc.service.auth.PermissionRpcService;
import com.lotus.service.auth.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author qwzh110
 */
@Service(version = "1.0.0")
public class PermissionRpcServiceImpl implements PermissionRpcService {
    @Autowired
    private PermissionService permissionService;

    /**
     * 用户ID获取用户对应的角色的权限
     *
     * @param userId
     * @return
     */
    @Override
    public List<Permission> findPermission(Integer userId) {
        return permissionService.findPermission(userId);
    }

    /**
     * 获取所有权限,包含角色是否拥有该权限标识
     *
     * @param roleId
     * @return
     */
    @Override
    public List<Map<String, Object>> findByRoleId(Integer roleId) {
        return permissionService.findByRoleId(roleId);
    }
}