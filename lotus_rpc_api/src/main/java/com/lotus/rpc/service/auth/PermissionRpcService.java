package com.lotus.rpc.service.auth;

import com.lotus.dao.pojo.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionRpcService {
    /**
     * 用户ID获取用户对应的角色的权限
     *
     * @param userId
     * @return
     */
    List<Permission> findPermission(Integer userId);

    /**
     * 获取所有权限,包含角色是否拥有该权限标识
     *
     * @param roleId
     * @return
     */
    List<Map<String, Object>> findByRoleId(Integer roleId);
}