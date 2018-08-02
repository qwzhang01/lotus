package com.lotus.service.auth;

import com.lotus.common.entity.Page;
import com.lotus.dao.pojo.User;

import java.util.Map;

public interface UserService {
    /**
     * 根据用户名获取系统用户
     *
     * @param userName
     * @return
     */
    User getByUsername(String userName);

    /**
     * 分页查询用户列表
     *
     * @return
     */
    public Page<Map<String, Object>> findPageList(String userName, Integer pageNumber, Integer pageSize);

    /**
     * 根据ID获取用户实体
     *
     * @param userId
     * @return
     */
    User getById(Integer userId);

    /**
     * 新增用户实体
     *
     * @param user
     * @return
     */
    Boolean add(User user, Integer[] roleIds);

    /**
     * 更新用户实体
     *
     * @param user
     * @return
     */
    Boolean update(User user, Integer[] roleIds);

    /**
     * 删除用户
     *
     * @param user
     * @return
     */
    Boolean delete(User user);

    /**
     * 判断用户名是否重复
     *
     * @param username
     * @param userid
     * @return
     */
    boolean isUserNameConflict(String username, Integer userid);
}
