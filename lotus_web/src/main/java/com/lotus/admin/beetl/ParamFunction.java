package com.lotus.admin.beetl;

import com.lotus.rpc.service.system.OptionRpcService;
import com.lotus.rpc.service.system.ParamRpcService;
import org.beetl.core.Context;
import org.beetl.core.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParamFunction implements Function {

    @Autowired
    private OptionRpcService optionService;
    @Autowired
    private ParamRpcService paramService;
    @Override
    public Object call(Object[] paras, Context context) {
        if (paras.length > 1) {
            return paramService.getCodeName(paras[0].toString(), paras[1].toString());
        }
        if (paras.length == 1) {
            return optionService.get(paras[0].toString());
        }
        return null;
    }
}
