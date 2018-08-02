package com.lotus.rpc.service.auth.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.lotus.common.entity.Page;
import com.lotus.dao.pojo.User;
import com.lotus.rpc.service.auth.UserRpcService;
import com.lotus.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
@Service(version = "1.0.0")
public class UserRpcServiceImpl implements UserRpcService {
    @Autowired
    private UserService userService;

    /**
     * 根据用户名获取系统用户
     *
     * @param userName
     * @return
     */
    @Override
    public User getByUsername(String userName) {
        return userService.getByUsername(userName);
    }

    /**
     * 分页查询用户列表
     *
     * @return
     */
    @Override
    public Page<Map<String, Object>> findPageList(String userName, Integer pageNumber, Integer pageSize) {
        return userService.findPageList(userName, pageNumber, pageSize);
    }

    /**
     * 根据ID获取用户实体
     *
     * @param userId
     * @return
     */
    @Override
    public User getById(Integer userId) {
        return userService.getById(userId);
    }

    /**
     * 新增用户实体
     *
     * @param user
     * @return
     */
    @Override
    public Boolean add(User user, Integer[] roleIds) {
        return userService.add(user, roleIds);
    }

    /**
     * 更新用户实体
     *
     * @param user
     * @return
     */
    @Override
    public Boolean update(User user, Integer[] roleIds) {
        return userService.update(user, roleIds);
    }

    /**
     * 删除用户
     *
     * @param user
     * @return
     */
    @Override
    public Boolean delete(User user) {
        return userService.delete(user);
    }

    /**
     * 判断用户名是否重复
     *
     * @param username
     * @param userid
     * @return
     */
    @Override
    public boolean isUserNameConflict(String username, Integer userid) {
        return userService.isUserNameConflict(username, userid);
    }
}
