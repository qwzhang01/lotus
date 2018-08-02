package com.lotus.admin.web;

import com.lotus.admin.shiro.ShiroCrypt;
import com.lotus.admin.util.SessionUtil;
import com.lotus.admin.util.ValidatorEntity;
import com.lotus.common.entity.AjaxResult;
import com.lotus.common.entity.Page;
import com.lotus.dao.pojo.User;
import com.lotus.rpc.service.auth.RoleRpcService;
import com.lotus.rpc.service.auth.UserRpcService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

/**
 * 用户管理
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRpcService userService;
    @Autowired
    private RoleRpcService roleService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "/user/list";
    }

    @RequiresPermissions(value = "用户管理", logical = Logical.OR)
    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.POST)
    public Page page(@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                     @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                     @RequestParam(value = "username", required = false) String username) {
        Page<Map<String, Object>> list = userService.findPageList(username, pageNumber, pageSize);
        return list;
    }

    @RequiresPermissions(value = {"用户管理-新增", "用户管理-编辑"}, logical = Logical.OR)
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form(@RequestParam(value = "userId", defaultValue = "0", required = false) Integer userId, Model model) {
        if (0 != userId) {
            User user = userService.getById(userId);
            model.addAttribute(user);
        }
        List<Map<String, Object>> roleList = roleService.findList(0 == userId ? null : userId);
        model.addAttribute("roleList", roleList);
        return "/user/form";
    }

    @RequiresPermissions(value = {"用户管理-新增", "用户管理-编辑"}, logical = Logical.OR)
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(User user, Integer[] roleId, RedirectAttributes redirectAttributes) {
        if (StringUtils.isAnyBlank(user.getUsername(), user.getRealname(), user.getContactway())) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("参数错误"));
            return "redirect:/user/form";
        }
        if (userService.isUserNameConflict(user.getUsername(), user.getUserid())) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("用户名已经存在，请重试"));
            return "redirect:/user/form";
        }
        if (roleId == null || roleId.length <= 0) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("参数错误"));
            return "redirect:/user/form";
        }
        Boolean result = false;
        if (user.getUserid() != null && user.getUserid() != 0) {
            result = userService.update(user, roleId);
        } else {
            user.setPassword(ShiroCrypt.encrypt("000000"));//默认用户密码000000
            result = userService.add(user, roleId);
        }
        if (result) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.success("保存成功"));
        } else {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("保存失败"));
        }
        return "redirect:/user/list";
    }

    /**
     * 用户自己修改密码
     * @return
     */
    @RequestMapping(value = "resetpw/form", method = RequestMethod.POST)
    public String resetpw() {
        return "user/resetpw";
    }

    /**
     * 用户自己修改密码
     * @param password
     * @param confirmPassword
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "resetpw", method = RequestMethod.POST)
    public AjaxResult resetpw(@RequestParam(value = "password") String password,
                              @RequestParam(value = "confirmPassword") String confirmPassword) {
        if (StringUtils.isAnyBlank(password, confirmPassword)) {
            return AjaxResult.error("密码不能为空");
        }
        if (!confirmPassword.equals(password)) {
            return AjaxResult.error("两次密码不一致");
        }
        String userName = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.getByUsername(userName);
        if (user == null) {
            SessionUtil.clearSession();
            return AjaxResult.error("登陆用户数据有误");
        }
        user.setPassword(ShiroCrypt.encrypt(password));
        Boolean result = userService.update(user, null);
        if (result) {
            SessionUtil.clearSession();
            SecurityUtils.getSubject().logout();
            return AjaxResult.success("修改成功");
        } else {
            return AjaxResult.error("修改失败");
        }
    }

    /**
     * 后台列表将密码重置为000000
     * @param userId
     * @param redirectAttributes
     * @return
     */
    @RequiresPermissions(value = {"用户管理-重置密码"}, logical = Logical.OR)
    @RequestMapping(value = "reset/pw", method = RequestMethod.GET)
    public String resetpw(@RequestParam(value = "userId", required = false, defaultValue = "0") Integer userId,
                          RedirectAttributes redirectAttributes){
        if(userId == 0){
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("用户ID不能为空"));
            return "redirect:/user/list";
        }
        User user = userService.getById(userId);
        if(user == null){
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("用户不存在"));
            return "redirect:/user/list";
        }
        user.setPassword(ShiroCrypt.encrypt("000000"));//默认用户密码000000
        Boolean result = userService.update(user, null);
        if(result) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.success("重置成功"));
        }else {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("重置失败"));
        }
        return "redirect:/user/list";
    }

    @RequiresPermissions(value = {"用户管理-删除"}, logical = Logical.OR)
    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "userId", required = false, defaultValue = "0") Integer userId,
                         RedirectAttributes redirectAttributes) {
        if (userId == 0) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("用户ID不能为空"));
            return "redirect:/user/list";
        }
        User user = userService.getById(userId);
        if (user == null) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("删除的用户不存在"));
            return "redirect:/user/list";
        }
        boolean result = userService.delete(user);
        if (result) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.success("删除成功"));
        } else {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("删除失败"));
        }
        return "redirect:/user/list";
    }

    @RequiresPermissions(value = {"用户管理-新增", "用户管理-编辑"}, logical = Logical.OR)
    @RequestMapping(value = "valid", method = RequestMethod.POST)
    @ResponseBody
    public ValidatorEntity valid(@RequestParam(value = "userId", required = false) Integer userId, @RequestParam("username") String username) {
        boolean isConflict = userService.isUserNameConflict(username, userId);
        if (isConflict) {
            return ValidatorEntity.error();
        }
        return ValidatorEntity.success();
    }
}