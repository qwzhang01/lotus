package com.lotus.rpc.service.auth.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.lotus.common.entity.Page;
import com.lotus.dao.pojo.Role;
import com.lotus.rpc.service.auth.RoleRpcService;
import com.lotus.service.auth.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
@Service(version = "1.0.0")
public class RoleRpcServiceImpl implements RoleRpcService {
    @Autowired
    private RoleService roleService;

    /**
     * 根据用户ID获取用户角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<Role> findRole(Integer userId) {
        return roleService.findRole(userId);
    }

    /**
     * 获取角色分页列表
     *
     * @param roleName
     * @param pageNumber
     * @param pageSize
     * @return
     */
    @Override
    public Page<Map<String, Object>> findPageList(String roleName, Integer pageNumber, Integer pageSize) {
        return roleService.findPageList(roleName, pageNumber, pageSize);
    }

    /**
     * 根据ID获取实体
     *
     * @param roleId
     * @return
     */
    @Override
    public Role getById(Integer roleId) {
        return roleService.getById(roleId);
    }

    @Override
    public Boolean add(Role role) {
        return roleService.add(role);
    }

    @Override
    public Boolean update(Role role) {
        return roleService.update(role);
    }

    /**
     * 删除
     *
     * @param role
     * @return
     */
    @Override
    public boolean delete(Role role) {
        return roleService.delete(role);
    }

    /**
     * 获取所有角色
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> findList(Integer userId) {
        return roleService.findList(userId);
    }

    /**
     * 分配顾问
     *
     * @param role
     * @param permissionId
     * @return
     */
    @Override
    public boolean assginPermission(Role role, Integer[] permissionId) {
        return roleService.assginPermission(role, permissionId);
    }
}
