package com.lotus.rpc.service;

import com.alibaba.dubbo.config.spring.ServiceBean;
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
import com.lotus.service.ServiceNativeConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 这里纯粹是为了试验
 * 本来想直接把原来的service当做dubbo的服务用，
 * 但是加了缓存注解或者事物注解的service方法都无法通过dubbo发布出去
 * 所以有多写了一层，不知道这个问题有没有破的可能。先这么着 重点感受一下dubbo
 */
@Configuration
@Import(ServiceNativeConfig.class)
public class RpcServerProvider extends DubboBaseConfig {
    @Bean
    public ServiceBean<AttachmentRpcService> attachmentRpcServiceExport(AttachmentRpcService attachmentRpcService) {
        ServiceBean<AttachmentRpcService> serviceBean = new ServiceBean<AttachmentRpcService>();
        serviceBean.setInterface(AttachmentRpcService.class.getName());
        serviceBean.setRef(attachmentRpcService);
        return makeALl(serviceBean);
    }

    @Bean
    public ServiceBean<FolderRpcService> folderServiceExport(FolderRpcService folderRpcService) {
        ServiceBean<FolderRpcService> serviceBean = new ServiceBean<FolderRpcService>();
        serviceBean.setInterface(FolderRpcService.class.getName());
        serviceBean.setRef(folderRpcService);
        return makeALl(serviceBean);
    }

    @Bean
    public ServiceBean<PermissionRpcService> permissionServiceExport(PermissionRpcService permissionRpcService) {
        ServiceBean<PermissionRpcService> serviceBean = new ServiceBean<PermissionRpcService>();
        serviceBean.setInterface(PermissionRpcService.class.getName());
        serviceBean.setRef(permissionRpcService);
        return makeALl(serviceBean);
    }

    @Bean
    public ServiceBean<RoleRpcService> roleServiceExport(RoleRpcService roleRpcService) {
        ServiceBean<RoleRpcService> serviceBean = new ServiceBean<RoleRpcService>();
        serviceBean.setInterface(RoleRpcService.class.getName());
        serviceBean.setRef(roleRpcService);
        return makeALl(serviceBean);
    }

    @Bean
    public ServiceBean<UserRpcService> userServiceExport(UserRpcService userRpcService) {
        ServiceBean<UserRpcService> serviceBean = new ServiceBean<UserRpcService>();
        serviceBean.setInterface(UserRpcService.class.getName());
        serviceBean.setRef(userRpcService);
        return makeALl(serviceBean);
    }

    @Bean
    public ServiceBean<AudioAttachmentRpcService> audioAttachmentServiceExport(AudioAttachmentRpcService audioAttachmentRpcService) {
        ServiceBean<AudioAttachmentRpcService> serviceBean = new ServiceBean<AudioAttachmentRpcService>();
        serviceBean.setInterface(AudioAttachmentRpcService.class.getName());
        serviceBean.setRef(audioAttachmentRpcService);
        return makeALl(serviceBean);
    }

    @Bean
    public ServiceBean<ContentRpcService> contentServiceExport(ContentRpcService contentRpcService) {
        ServiceBean<ContentRpcService> serviceBean = new ServiceBean<ContentRpcService>();
        serviceBean.setInterface(ContentRpcService.class.getName());
        serviceBean.setRef(contentRpcService);
        return makeALl(serviceBean);
    }

    @Bean
    public ServiceBean<TaxonomyRpcService> taxonomyServiceExport(TaxonomyRpcService taxonomyRpcService) {
        ServiceBean<TaxonomyRpcService> serviceBean = new ServiceBean<TaxonomyRpcService>();
        serviceBean.setInterface(TaxonomyRpcService.class.getName());
        serviceBean.setRef(taxonomyRpcService);
        return makeALl(serviceBean);
    }

    @Bean
    public ServiceBean<OptionRpcService> optionServiceExport(OptionRpcService optionService) {
        ServiceBean<OptionRpcService> serviceBean = new ServiceBean<OptionRpcService>();
        serviceBean.setInterface(OptionRpcService.class.getName());
        serviceBean.setRef(optionService);
        return makeALl(serviceBean);
    }

    @Bean
    public ServiceBean<ParamRpcService> paramServiceExport(ParamRpcService paramService) {
        ServiceBean<ParamRpcService> serviceBean = new ServiceBean<ParamRpcService>();
        serviceBean.setInterface(ParamRpcService.class.getName());
        serviceBean.setRef(paramService);
        return makeALl(serviceBean);
    }

    private ServiceBean makeALl(ServiceBean serviceBean) {
        serviceBean.setProxy("javassist");
        serviceBean.setVersion("myversion");
        serviceBean.setTimeout(5000);
        serviceBean.setRetries(3);
        return serviceBean;
    }
}