package com.lotus.dao.mapper;

import com.lotus.dao.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface PermissionMapper extends com.lotus.dao.gmapper.PermissionMapper {
    /**
     * 获取用户对应的所有权限
     *
     * @param userId
     * @return
     */
    List<Permission> selectByUserId(@Param("userId") Integer userId);

    /**
     * 根据角色ID获取所有权限
     *
     * @param roleId
     * @return
     */
    List<Map<String, Object>> selectByRoleId(@Param("roleId") Integer roleId);
}