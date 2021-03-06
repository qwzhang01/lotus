package com.lotus.common.kit;

/**
 * 实体工具类
 *
 * @author L.cm
 * email: 596392912@qq.com
 * site:http://www.dreamlu.net
 * date 2015年4月26日下午5:10:42
 */
public class BeanKit {
    /**
     * 实例化对象
     *
     * @param clazz 类
     * @param <T>   type parameter
     * @return 对象
     */
    public static <T> T newInstance(Class<?> clazz) {
        try {
            return (T) clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
