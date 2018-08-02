package com.lotus.admin.interceptor;

import com.lotus.rpc.service.system.OptionRpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MenuInterceptor implements HandlerInterceptor {

    //Action之前执行:可以进行编码、安全控制等处理；
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getRequestURI());
        return true;
    }

    //生成视图之前执行，有机会修改ModelAndView；
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (response.getStatus() == 404) {
            modelAndView.setViewName("redirect:/login");
        } else if (handler instanceof HandlerMethod && "GET".equalsIgnoreCase(request.getMethod())) {
            if (modelAndView != null) {
                //设置高亮当前目录
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                Object controller = handlerMethod.getBean();
                String cotrollerName = controller.getClass().getSimpleName().toLowerCase().replace("controller", "");
                if(cotrollerName.contains("$")){
                    cotrollerName = cotrollerName.substring(0, cotrollerName.indexOf("$"));
                }
                modelAndView.addObject("current", cotrollerName);
            }
        }
    }

    //最后执行，可用于释放资源，可以根据ex是否为null判断是否发生了异常，进行日志记录。
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

}