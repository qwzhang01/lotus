package com.lotus.admin.web;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DefaultController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        model.put("name", "index");
        return "index";
    }

    /**
     * 测试权限
     *
     * @param model
     * @return
     */
    @RequiresPermissions(value = {"内容管理-置顶1"}, logical = Logical.OR)
    @RequestMapping(value = "/auth", method = RequestMethod.GET)
    public String testAuth(ModelMap model) {
        model.put("name", "testAuth");
        return "index";
    }
}