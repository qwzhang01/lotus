package com.lotus.admin.web;

import com.lotus.common.entity.AjaxResult;
import com.lotus.dao.pojo.Option;
import com.lotus.rpc.service.system.OptionRpcService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/set")
@Controller
public class SetController {

    @Autowired
    private OptionRpcService optionService;

    @RequiresPermissions(value = {"设置引导页-设置"}, logical = Logical.OR)
    @RequestMapping(value = "/splash", method = RequestMethod.GET)
    public String splash(Model model) {
        String splash = optionService.get("splash");
        model.addAttribute("splash", splash);
        return "set/app_page";
    }
    @RequiresPermissions(value = {"设置引导页-设置"}, logical = Logical.OR)
    @ResponseBody
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public AjaxResult splash(@RequestParam("splashPath") String splashPath) {
        Option splash = optionService.getByKey("splash");
        if (splash == null) {
            splash = new Option();
        }
        splash.setOptionValue(splashPath);
        splash.setOptionKey("splash");
        if (optionService.save(splash)) {
            return AjaxResult.success("保存成功");
        }
        return AjaxResult.error("保存失败");
    }
}
