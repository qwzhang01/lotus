package com.lotus.admin.resolver;

import com.lotus.common.entity.AjaxResult;
import com.lotus.common.kit.MapKit;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ExceptionResolver extends SimpleMappingExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception ex) {

        doResolveException(request, response, handler, ex);

        // 认证失败
        if (ex instanceof AuthenticationException) {
            return new ModelAndView("redirect:/login");
        }

        //授权失败
        if (ex instanceof UnauthorizedException) {
            return new ModelAndView("redirect:/403");
        }
        System.out.println(ExceptionUtils.getStackTrace(ex));

        ModelAndView modelAndView = new ModelAndView();
        if (handler instanceof HandlerMethod && "GET".equalsIgnoreCase(request.getMethod())) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Object controller = handlerMethod.getBean();
            String cotrollerName = controller.getClass().getSimpleName().toLowerCase().replace("controller", "");
            modelAndView.setViewName("error");
            modelAndView.addObject("current", cotrollerName);
            modelAndView.addObject("appTitle", "菩提心随身电台");
            modelAndView.addObject("msg", ex.toString().replaceAll("\n", "<br/>"));
            modelAndView.addObject("detail", ExceptionUtils.getStackTrace(ex));
        } else if (handler instanceof HandlerMethod && "POST".equalsIgnoreCase(request.getMethod())) {
            MappingJackson2JsonView view = new MappingJackson2JsonView();
            Map<String, Object> errorResult = MapKit.objToMap(AjaxResult.error(ex.getMessage()));
            view.setAttributesMap(errorResult);
        }
        return modelAndView;
    }
}
