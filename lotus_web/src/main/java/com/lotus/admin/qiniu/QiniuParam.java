package com.lotus.admin.qiniu;

import com.lotus.rpc.service.system.OptionRpcService;
import com.qiniu.common.Zone;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 七牛配置参数
 */
@Service
public class QiniuParam {

    @Resource
    private OptionRpcService optionService;

    public String getAK() {
        return optionService.get("qiniu_ak");
    }

    public String getSK() {
        return optionService.get("qiniu_sk");
    }

    public String getBUCKET() {
        return optionService.get("qiniu_bucket");
    }

    public String getRootPath() {
        return optionService.get("qiniu_path");
    }

    public Zone getZONE() {
        return Zone.zone2();
    }
}