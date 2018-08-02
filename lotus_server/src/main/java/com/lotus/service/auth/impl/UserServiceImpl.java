package com.lotus.service.auth.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lotus.common.entity.Page;
import com.lotus.common.kit.PageKit;
import com.lotus.dao.mapper.UserMapper;
import com.lotus.dao.pojo.User;
import com.lotus.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author zqw
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 根据用户名获取系统用户
     *
     * @param username
     * @return
     */
    @Override
    public User getByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    /**
     * 分页查询用户列表
     *
     * @return
     */
    @Override
    public Page<Map<String, Object>> findPageList(String userName, Integer pageNumber, Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Map<String, Object>> list = userMapper.selectList(userName);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(list);
        return PageKit.pageComplete(pageInfo);
    }

    /**
     * 根据ID获取用户实体
     *
     * @param userId
     * @return
     */
    @Override
    public User getById(Integer userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    /**
     * 新增用户实体
     *
     * @param user
     * @return
     */
    @Override
    @Transactional
    public Boolean add(User user, Integer[] roleIds) {
        user.setIsonline(0);
        user.setDeletemark(1);
        int count = userMapper.insert(user);
        if (roleIds != null && roleIds.length > 0) {
            userMapper.insertUserRole(user.getUserid(), roleIds);
        }
        return count == 0;
    }

    /**
     * 更新用户实体，如果不传角色ID，则为单独更新用户信息
     *
     * @param user
     * @return
     */
    @Override
    @Transactional
    public Boolean update(User user, Integer[] roleIds) {
        user.setDeletemark(1);
        user.setIsonline(0);
        //先请后存
        if (roleIds != null && roleIds.length > 0) {
            userMapper.deleteUserRole(user.getUserid());
            userMapper.insertUserRole(user.getUserid(), roleIds);
        }
        return userMapper.updateByPrimaryKeySelective(user) == 1;
    }

    /**
     * 删除用户
     *
     * @param user
     * @return
     */
    @Override
    @Transactional
    public Boolean delete(User user) {
        userMapper.deleteUserRole(user.getUserid());
        int count = userMapper.deleteByPrimaryKey(user.getUserid());
        return count == 1;
    }

    /**
     * 判断用户名是否重复
     *
     * @param username
     * @param userId
     * @return
     */
    @Override
    public boolean isUserNameConflict(String username, Integer userId) {
        User user = getByUsername(username);
        if (user != null) {
            if (userId != null && userId != 0) {
                //编辑时，排除自己
                return !user.getUserid().equals(userId);
            }
            return true;
        }
        return false;
    }
}