package com.lotus.admin.web;

import com.lotus.common.entity.AjaxResult;
import com.lotus.common.entity.Page;
import com.lotus.dao.pojo.Option;
import com.lotus.rpc.service.system.OptionRpcService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/option")
public class OptionController {

    @Autowired
    private OptionRpcService optionService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "/option/list";
    }

    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.POST)
    public Page page(@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                     @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                     @RequestParam(value = "searchKey", required = false) String searchKey) {
        Page<Map<String, Object>> list = optionService.findPageList(searchKey, pageNumber, pageSize);
        return list;
    }

    @RequiresPermissions(value = "配置-编辑", logical = Logical.OR)
    @RequestMapping(value = "/edit/{optionId}", method = RequestMethod.POST)
    public String edit(@PathVariable("optionId") Integer optionId, Model model){
        Option option = optionService.getById(optionId);
        model.addAttribute("option", option);
        return "option/edit";
    }

    @RequiresPermissions(value = "配置-编辑", logical = Logical.OR)
    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public AjaxResult update(Option model){
        if (model.getId() == null || model.getId() == 0) {
            return AjaxResult.error("编辑失败");
        }
        if (StringUtils.isAnyBlank(model.getOptionKey(), model.getOptionValue())) {
            return AjaxResult.error("参数错误");
        }
        boolean result = optionService.save(model);
        if (result) {
            return AjaxResult.success("编辑成功");
        }
        return AjaxResult.error("编辑失败");
    }
}
