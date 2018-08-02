package com.lotus.admin.shiro;


import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户的认证信息以及权限信息
 */
public class LotusShiroRealm extends AuthorizingRealm {

    @Resource
    private ShiroAuthServeice shiroAuthServeice;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        ShiroUser shiroUser = shiroAuthServeice.getUser(token.getUsername());
        if (shiroUser != null) {
            return new SimpleAuthenticationInfo(shiroUser.getUsername(), shiroUser.getPassword(), getName());
        }
        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String loginName = (String) super.getAvailablePrincipal(principals);
        ShiroUser shiroUser = shiroAuthServeice.getUser(loginName);
        if (shiroUser != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //获取所有角色
            List<String> roles = shiroAuthServeice.getRoles(shiroUser);
            info.addRoles(roles);
            //获取所有权限
            List<String> permissions = shiroAuthServeice.getPermissions(shiroUser);
            info.addStringPermissions(permissions);

            return info;
        }
        return null;
    }
}