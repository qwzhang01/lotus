package com.lotus.dao.mapper;

import com.lotus.dao.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoleMapper extends com.lotus.dao.gmapper.RoleMapper {

    /**
     * 获取角色列表
     *
     * @return
     */
    List<Map<String, Object>> selectList(@Param("roleName") String roleName);

    /**
     * 根据用户ID查询对应的角色
     *
     * @param userId
     * @return
     */
    List<Role> selectByUserId(@Param("userId") Integer userId);

    /**
     * 根据角色ID，删除角色用户中间表
     *
     * @param roleId
     */
    Integer deleteRoleUser(@Param("roleId") Integer roleId);

    /**
     * 根据角色ID，删除角色权限中间表
     *
     * @param roleId
     */
    Integer deleteRolePermission(@Param("roleId") Integer roleId);

    /**
     * 获取所有角色，并表示改角色是不是对应用户的角色
     *
     * @param userId
     * @return
     */
    List<Map<String, Object>> selectRoleUser(@Param("userId") Integer userId);

    /**
     * 新增角色权限
     *
     * @param roleId
     * @param permissionId
     * @return
     */
    Integer insertRolePermission(@Param("roleId") Integer roleId, @Param("permissionIds") Integer[] permissionId);
}
