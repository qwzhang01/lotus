package com.lotus.admin;

import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.lotus.rpc.service.DubboBaseConfig;
import com.lotus.rpc.service.attachment.AttachmentRpcService;
import com.lotus.rpc.service.attachment.FolderRpcService;
import com.lotus.rpc.service.auth.PermissionRpcService;
import com.lotus.rpc.service.auth.RoleRpcService;
import com.lotus.rpc.service.auth.UserRpcService;
import com.lotus.rpc.service.content.AudioAttachmentRpcService;
import com.lotus.rpc.service.content.ContentRpcService;
import com.lotus.rpc.service.content.TaxonomyRpcService;
import com.lotus.rpc.service.system.OptionRpcService;
import com.lotus.rpc.service.system.ParamRpcService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.lotus.rpc.service")
public class RpcApiWebServiceConfig extends DubboBaseConfig {

    @Bean
    public ReferenceBean<ContentRpcService> contentRpcService() {
        ReferenceBean<ContentRpcService> ref = new ReferenceBean<>();
        ref.setInterface(ContentRpcService.class);
        return makeAll(ref);
    }
    @Bean
    public ReferenceBean<AudioAttachmentRpcService> audioAttachmentRpcService() {
        ReferenceBean<AudioAttachmentRpcService> ref = new ReferenceBean<>();
        ref.setInterface(AudioAttachmentRpcService.class);
        return makeAll(ref);
    }
    @Bean
    public ReferenceBean<TaxonomyRpcService> taxonomyRpcService() {
        ReferenceBean<TaxonomyRpcService> ref = new ReferenceBean<>();
        ref.setInterface(TaxonomyRpcService.class);
        return makeAll(ref);
    }
    @Bean
    public ReferenceBean<FolderRpcService> folderRpcService() {
        ReferenceBean<FolderRpcService> ref = new ReferenceBean<>();
        ref.setInterface(FolderRpcService.class);
        return makeAll(ref);
    }
    @Bean
    public ReferenceBean<AttachmentRpcService> attachmentRpcService() {
        ReferenceBean<AttachmentRpcService> ref = new ReferenceBean<>();
        ref.setInterface(AttachmentRpcService.class);
        return makeAll(ref);
    }
    @Bean
    public ReferenceBean<UserRpcService> userRpcService() {
        ReferenceBean<UserRpcService> ref = new ReferenceBean<>();
        ref.setInterface(UserRpcService.class);
        return makeAll(ref);
    }
    @Bean
    public ReferenceBean<PermissionRpcService> permissionRpcService() {
        ReferenceBean<PermissionRpcService> ref = new ReferenceBean<>();
        ref.setInterface(PermissionRpcService.class);
        return makeAll(ref);
    }
    @Bean
    public ReferenceBean<RoleRpcService> roleRpcService() {
        ReferenceBean<RoleRpcService> ref = new ReferenceBean<>();
        ref.setInterface(RoleRpcService.class);
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
