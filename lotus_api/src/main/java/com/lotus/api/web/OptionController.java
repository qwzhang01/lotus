package com.lotus.api.web;

import com.lotus.api.entity.LotusResult;
import com.lotus.rpc.service.system.OptionRpcService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = "配置项管理", description = "获取系统配置")
@Controller
public class OptionController {

    @Autowired
    private OptionRpcService optionService;

    /**
     * 获取设置项
     *
     * @param key
     * @return
     */
    @ApiOperation(value = "获取设置项", httpMethod = "POST", response = LotusResult.class, notes = "根据设置的键获取对应的设置值")
    @RequestMapping(value = "/api/option/{key}", method = RequestMethod.POST)
    @ResponseBody
    public LotusResult get(@PathVariable("key") String key) {
        String settingValue = optionService.get(key);
        if (StringUtils.isBlank(settingValue)) {
            return LotusResult.error("获取失败");
        }
        return LotusResult.success(optionService.get("qiniu_path") + "/" + settingValue);
    }
}
