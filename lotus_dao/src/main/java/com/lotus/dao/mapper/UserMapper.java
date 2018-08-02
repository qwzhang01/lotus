package com.lotus.dao.mapper;

import com.lotus.dao.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper extends com.lotus.dao.gmapper.UserMapper {

    /**
     * 分页查询用户信息
     *
     * @return
     */
    List<Map<String, Object>> selectList(@Param("username") String username);

    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     */
    User selectByUsername(@Param("username") String username);

    /**
     * 删除用户角色中间表
     *
     * @param userId
     */
    Integer deleteUserRole(@Param("userId") Integer userId);

    /**
     * 批量插入用户角色中间表
     *
     * @param userId
     * @param roleIds
     * @return
     */
    Integer insertUserRole(@Param("userId") Integer userId, @Param("roleIds") Integer[] roleIds);
}