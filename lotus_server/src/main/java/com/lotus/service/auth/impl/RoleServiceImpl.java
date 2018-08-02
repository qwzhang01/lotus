package com.lotus.service.auth.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lotus.common.entity.Page;
import com.lotus.common.kit.PageKit;
import com.lotus.dao.mapper.RoleMapper;
import com.lotus.dao.pojo.Role;
import com.lotus.service.auth.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 获取所有角色,并表示改角色是否是对应用户的角色
     *
     * @return
     */
    @Override
    public List<Map<String, Object>> findList(Integer userId) {
        return roleMapper.selectRoleUser(userId);
    }

    /**
     * 分配顾问
     *
     * @param role
     * @param permissionId
     * @return
     */
    @Override
    @Transactional
    public boolean assginPermission(Role role, Integer[] permissionId) {
        roleMapper.deleteRolePermission(role.getRoleid());//先清后存
        if (permissionId != null && permissionId.length > 0) {
            Integer count = roleMapper.insertRolePermission(role.getRoleid(), permissionId);
            return count == permissionId.length;
        }
        return true;
    }

    /**
     * 根据用户ID获取用户角色
     *
     * @param userId
     * @return
     */
    @Override
    public List<Role> findRole(Integer userId) {
        return roleMapper.selectByUserId(userId);
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
        PageHelper.startPage(pageNumber, pageSize);
        List<Map<String, Object>> list = roleMapper.selectList(roleName);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        return PageKit.pageComplete(pageInfo);
    }

    /**
     * 根据ID获取实体
     *
     * @param roleId
     * @return
     */
    @Override
    public Role getById(Integer roleId) {
        return roleMapper.selectByPrimaryKey(roleId);
    }

    @Override
    public Boolean add(Role role) {
        int num = roleMapper.insert(role);
        return num == 1;
    }

    @Override
    public Boolean update(Role role) {
        int num = roleMapper.updateByPrimaryKey(role);
        return num == 1;
    }

    /**
     * 删除
     *
     * @param role
     * @return
     */
    @Override
    @Transactional
    public boolean delete(Role role) {
        roleMapper.deleteRoleUser(role.getRoleid());
        roleMapper.deleteRolePermission(role.getRoleid());
        int count = roleMapper.deleteByPrimaryKey(role.getRoleid());
        return count == 1;
    }
}