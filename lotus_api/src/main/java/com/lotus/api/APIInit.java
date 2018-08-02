package com.lotus.api;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import org.springframework.web.util.Log4jConfigListener;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

public class APIInit extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{RootConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        //字符集拦截器
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return new Filter[]{characterEncodingFilter};
    }

    /**
     * 这里好像设置的是web.xml中的 context-param
     *
     * @param servletContext
     */
    @Override
    protected void registerContextLoaderListener(ServletContext servletContext) {
        //设置webAppRootKey
        servletContext.setInitParameter("webAppRootKey", "lotus_api.root");
        //设置log4j配置文件位置
        servletContext.setInitParameter("log4jConfigLocation"
                , "classpath:log4j.properties");
        //noinspection deprecation
        servletContext.addListener(Log4jConfigListener.class);
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
    }

    @Override
    protected String getServletName() {
        return "lotus_api";
    }
}
