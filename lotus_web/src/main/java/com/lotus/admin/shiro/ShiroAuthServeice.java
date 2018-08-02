package com.lotus.admin.shiro;

import com.lotus.common.kit.StrKit;
import com.lotus.dao.pojo.Permission;
import com.lotus.dao.pojo.Role;
import com.lotus.dao.pojo.User;
import com.lotus.rpc.service.auth.PermissionRpcService;
import com.lotus.rpc.service.auth.RoleRpcService;
import com.lotus.rpc.service.auth.UserRpcService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShiroAuthServeice {

    @Resource
    private UserRpcService userService;
    @Resource
    private RoleRpcService roleService;
    @Resource
    private PermissionRpcService permissionService;

    public ShiroUser getUser(String loginName) {
        User user = userService.getByUsername(loginName);
        if (user == null) {
            return null;
        }
        ShiroUser shiroUser = new ShiroUser();
        shiroUser.setId(user.getUserid());
        shiroUser.setPassword(user.getPassword());
        shiroUser.setUsername(user.getUsername());
        return shiroUser;
    }

    public List<String> getRoles(ShiroUser shiroUser) {
        List<Role> roleList = roleService.findRole(shiroUser.getId());
        List<String> roles = new ArrayList<>();
        if (roleList != null && roleList.size() > 0) {
            for (Role role : roleList) {
                roles.add(role.getRolename());
            }
        }
        return roles;
    }

    public List<String> getPermissions(ShiroUser shiroUser) {
        List<Permission> permissionList = permissionService.findPermission(shiroUser.getId());
        List<String> permissions = new ArrayList<>();
        if (permissionList != null && permissionList.size() > 0) {
            for (Permission permission : permissionList) {
                String menu = permission.getMenu();
                if (!permissions.contains(menu)) {
                    permissions.add(menu);
                }
                String submenu = permission.getSubmenu();
                StringBuilder rpermi = new StringBuilder();
                if (StrKit.notBlank(submenu)) {
                    if (!permissions.contains(submenu)) {
                        permissions.add(submenu);
                    }
                    rpermi.append(submenu + "-");
                } else {
                    rpermi.append(menu + "-");
                }
                permissions.add(rpermi + permission.getOperate());
            }
        }
        return permissions;
    }

    public boolean login(ShiroUser shiroUser) {
        //获取token
        UsernamePasswordToken token = new UsernamePasswordToken(shiroUser.getUsername(), shiroUser.getPassword());
        token.setRememberMe(shiroUser.isRemberMe());

        //获取当前的Subject
        Subject currentUser = SecurityUtils.getSubject();
        //在调用了login方法后,SecurityManager会收到AuthenticationToken,并将其发送给已配置的Realm执行必须的认证检查
        //每个Realm都能在必要时对提交的AuthenticationTokens作出反应
        //所以这一步在调用login(token)方法时,它会走到MyRealm.doGetAuthenticationInfo()方法中,具体验证方式详见此方法
        try {
            currentUser.login(token);
        } catch (AuthenticationException e) {
            token.clear();
            return false;
        }
        return true;
    }
}