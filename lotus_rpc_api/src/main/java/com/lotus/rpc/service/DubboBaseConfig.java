package com.lotus.rpc.service;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.lotus.common.kit.PropUtil;
import com.lotus.common.rewrite.AnnotationBeanRewrite;
import org.springframework.context.annotation.Bean;

public class DubboBaseConfig {

    // 帮助文档
    // http://blog.csdn.net/xiejx618/article/details/50422621
    // private static final String APPLICATION_NAME = "free_heart_api_provider";
    // private static final String REGISTRY_ADDRESS = "zookeeper://120.25.199.222:2181";
    // private static final String ANNOTATION_PACKAGE = "com.qw.free.heart.rpc.impl";

    /*与<dubbo:application/>相当.*/
    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(PropUtil.getProperty("dubbo.application.name"));
        applicationConfig.setLogger("slf4j");
        return applicationConfig;
    }

    /*与<dubbo:registry/>相当*/
    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(PropUtil.getProperty("dubbo.registry.address"));
        return registryConfig;
    }

    /*与<dubbo:annotation/>相当.提供方扫描带有@com.alibaba.dubbo.config.annotation.Reference的注解类*/
    @Bean
    public AnnotationBeanRewrite annotationBean() {
        AnnotationBeanRewrite annotationBean = new AnnotationBeanRewrite();
        annotationBean.setPackage(PropUtil.getProperty("dubbo.package"));
        return annotationBean;
    }

    /*与<dubbo:protocol/>相当*/
    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig("dubbo", 20880);
        //默认为hessian2,但不支持无参构造函数类,而这种方式的效率很低
        protocolConfig.setSerialization("java");
        return protocolConfig;
    }
}
