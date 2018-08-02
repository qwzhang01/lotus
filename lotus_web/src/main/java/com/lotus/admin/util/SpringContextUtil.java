package com.lotus.admin.util;

import com.lotus.admin.WebConfig;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;


public class SpringContextUtil {
    /**
     * Spring应用上下文环境
     */
    private static AnnotationConfigWebApplicationContext ctx;

    static {
        ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebConfig.class);
        ctx.refresh();
    }

    /**
     * 私有构造函数，不允许实例化
     */
    private SpringContextUtil() {
    }

    /**
     * 获取指定Bean的实例
     *
     * @param name 要获取Bean的名称
     * @return Bean object
     */
    public static Object getBean(String name) {
        return ctx.getBean(name);
    }


}
