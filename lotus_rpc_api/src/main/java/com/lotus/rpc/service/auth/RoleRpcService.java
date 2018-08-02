package com.lotus.rpc.service.auth;


import com.lotus.common.entity.Page;
import com.lotus.dao.pojo.Role;

import java.util.List;
import java.util.Map;

public interface RoleRpcService {
    /**
     * 根据用户ID获取用户角色
     *
     * @param userId
     * @return
     */
    List<Role> findRole(Integer userId);

    /**
     * 获取角色分页列表
     *
     * @param roleName
     * @param pageNumber
     * @param pageSize
     * @return
     */
    Page<Map<String, Object>> findPageList(String roleName, Integer pageNumber, Integer pageSize);

    /**
     * 根据ID获取实体
     *
     * @param roleId
     * @return
     */
    Role getById(Integer roleId);

    Boolean add(Role role);

    Boolean update(Role role);

    /**
     * 删除
     *
     * @param role
     * @return
     */
    boolean delete(Role role);

    /**
     * 获取所有角色
     *
     * @return
     */
    List<Map<String, Object>> findList(Integer userId);

    /**
     * 分配顾问
     *
     * @param role
     * @param permissionId
     * @return
     */
    boolean assginPermission(Role role, Integer[] permissionId);
}
