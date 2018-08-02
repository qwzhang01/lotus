package com.lotus.admin.web;

import com.lotus.common.entity.Page;
import com.lotus.rpc.service.system.ParamRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/param")
public class ParamController {

    @Autowired
    private ParamRpcService paramService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "/param/list";
    }

    @ResponseBody
    @RequestMapping(value = "page", method = RequestMethod.POST)
    public Page page(@RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
                     @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                     @RequestParam(value = "searchKey", required = false) String searchKey) {
        Page<Map<String, Object>> list = paramService.findPageList(searchKey, pageNumber, pageSize);
        return list;
    }
}
