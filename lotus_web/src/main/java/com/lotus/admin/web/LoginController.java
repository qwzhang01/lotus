package com.lotus.admin.web;

import com.lotus.admin.shiro.ShiroAuthServeice;
import com.lotus.admin.shiro.ShiroCrypt;
import com.lotus.admin.shiro.ShiroMethod;
import com.lotus.admin.shiro.ShiroUser;
import com.lotus.admin.util.CaptchaUtil;
import com.lotus.admin.util.SessionUtil;
import com.lotus.common.entity.AjaxResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class LoginController {

    @Resource
    private ShiroAuthServeice shiroAuthServeice;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        if (ShiroMethod.authenticated()) {
            return "redirect:/";
        } else {
            return "login";
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(ShiroUser shiroUser, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isAnyBlank(shiroUser.getUsername(), shiroUser.getPassword(), shiroUser.getCaptcha())) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("用户名或密码验证码都均能为空"));
            return "redirect:/login";
        }
        if (!CaptchaUtil.validateCaptcha(shiroUser.getCaptcha(), request)) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("验证码错误"));
            return "redirect:/login";
        }
        ShiroUser checkUser = shiroAuthServeice.getUser(shiroUser.getUsername());
        if (checkUser == null) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("用户不存在，请核对后重新登录"));
            return "redirect:/login";
        }
        String encrypt = ShiroCrypt.encrypt(shiroUser.getPassword());
        if (!checkUser.getPassword().equals(encrypt)) {
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("密码错误，请核对后重新登录"));
            return "redirect:/login";
        }
        boolean isLogin = shiroAuthServeice.login(shiroUser);
        if (isLogin) {
            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpSession session = attrs.getRequest().getSession();
            String appUser = (String) session.getAttribute("appUserName");
            if (appUser == null) {
                session.setAttribute("appUserName", shiroUser.getUsername());
            }
            redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.success("登陆成功"));
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.error("登陆失败"));
        return "redirect:/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(RedirectAttributes redirectAttributes) {
        SecurityUtils.getSubject().logout();
        SessionUtil.clearSession();
        redirectAttributes.addFlashAttribute("redirectMsg", AjaxResult.success("退出成功"));
        return "redirect:/login";
    }

    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    @ResponseBody
    public void captcha(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CaptchaUtil.outputCaptcha(request, response);
    }

    @RequestMapping("/403")
    public String unauthorizedRole() {
        return "403";
    }

    @RequestMapping("/help")
    public String help() {
        return "help";
    }
}