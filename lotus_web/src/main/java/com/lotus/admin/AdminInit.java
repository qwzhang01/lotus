package com.lotus.admin;

import com.lotus.admin.listener.AppSettingListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.util.Log4jConfigListener;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class AdminInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class, ShiroConfig.class, SchedulingConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return super.createRootApplicationContext();
    }

    @Override
    protected String getServletName() {
        return "lotus_web";
    }

    @Override
    protected Filter[] getServletFilters() {
        //字符集拦截器
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        //
        DelegatingFilterProxy filterProxy = new DelegatingFilterProxy();
        filterProxy.setTargetFilterLifecycle(true);
        filterProxy.setTargetBeanName("shiroFilter");//没有写这个bean的名字,妈的,现在可以了
        return new Filter[]{characterEncodingFilter, filterProxy};
    }

    @Override
    protected void registerContextLoaderListener(ServletContext servletContext) {
        //servletContext.setInitParameter("contextClass", AnnotationConfigWebApplicationContext.class.getName());
        //servletContext.setInitParameter("contextConfigLocation", RootConfig.class.getName());
        //servletContext.addListener(new ContextLoaderListener());

        //设置webAppRootKey
        servletContext.setInitParameter("webAppRootKey", "lotus_web.root");
        //日志监听器
        servletContext.setInitParameter("log4jConfigLocation"
                , "classpath:log4j.properties");
        servletContext.addListener(Log4jConfigListener.class);

        servletContext.addListener(new RequestContextListener());
        // 项目启动后初始化操作
        servletContext.addListener(new AppSettingListener());
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }

    @Override
    protected WebApplicationContext createServletApplicationContext() {
        return super.createServletApplicationContext();
    }
}