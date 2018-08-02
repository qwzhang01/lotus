package com.lotus.admin.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AppSettingListener implements HttpSessionListener, ServletContextListener, ServletRequestListener {

    Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void contextInitialized(ServletContextEvent sce) {
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        //我们把创建的session封装在一个map中
        Map<String, HttpSession> map =(Map<String, HttpSession>) se.getSession().getServletContext().getAttribute("onLines");
        if(map==null){
            //说明这是第一次访问是，需要自己new 一个对象
            //采用集合上锁，采用java 自带的上锁函数
            map= Collections.synchronizedMap(new HashMap<String, HttpSession>());
            se.getSession().getServletContext().setAttribute("onLines", map);
        }
        //以session 的id为key,session对象为value存在map中
        map.put(se.getSession().getId(), se.getSession());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        Map<String, HttpSession> map =(Map<String, HttpSession>) se.getSession().getServletContext().getAttribute("onLines");
        map.remove(se.getSession().getId());
    }
}
