package com.lotus.api;

import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.lotus.rpc.service.DubboBaseConfig;
import com.lotus.rpc.service.attachment.AttachmentRpcService;
import com.lotus.rpc.service.content.AudioAttachmentRpcService;
import com.lotus.rpc.service.content.ContentRpcService;
import com.lotus.rpc.service.system.OptionRpcService;
import com.lotus.rpc.service.system.ParamRpcService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.lotus.rpc.service")
public class RpcApiServiceConfig extends DubboBaseConfig {

    @Bean
    public ReferenceBean<ContentRpcService> contentRpcService() {
        ReferenceBean<ContentRpcService> ref = new ReferenceBean<>();
        ref.setInterface(ContentRpcService.class);
        return makeAll(ref);
    }
    @Bean
    public ReferenceBean<AudioAttachmentRpcService> AaudioAttachmentRpcService() {
        ReferenceBean<AudioAttachmentRpcService> ref = new ReferenceBean<>();
        ref.setInterface(AudioAttachmentRpcService.class);
        return makeAll(ref);
    }
    @Bean
    public ReferenceBean<AttachmentRpcService> attachmentRpcService() {
        ReferenceBean<AttachmentRpcService> ref = new ReferenceBean<>();
        ref.setInterface(AttachmentRpcService.class);
        return makeAll(ref);
    }

    @Bean
    public ReferenceBean<OptionRpcService> optionRpcService() {
        ReferenceBean<OptionRpcService> ref = new ReferenceBean<>();
        ref.setInterface(OptionRpcService.class);
        return makeAll(ref);
    }

    @Bean
    public ReferenceBean<ParamRpcService> paramRpcService() {
        ReferenceBean<ParamRpcService> ref = new ReferenceBean<>();
        ref.setInterface(ParamRpcService.class);
        return makeAll(ref);
    }

    private ReferenceBean makeAll(ReferenceBean ref){
        ref.setVersion("1.0.0");
        ref.setTimeout(5000);
        ref.setRetries(3);
        ref.setCheck(false);
        return ref;
    }
}
