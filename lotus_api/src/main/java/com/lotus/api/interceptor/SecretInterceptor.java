package com.lotus.api.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotus.api.entity.LotusResult;
import com.lotus.api.param.StaticParam;
import com.lotus.api.util.PropertyUtil;
import com.lotus.common.kit.JsonKit;
import com.lotus.common.kit.SecretKit;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 安全校验拦截器
 * @author zqw
 */
public class SecretInterceptor implements HandlerInterceptor {
    private static final Log logger = LogFactory.getLog(SecretInterceptor.class);

    /**
     * 控制器执行前执行
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (PropertyUtil.isDevModel()) {
            Map<String, String[]> parameterMap = request.getParameterMap();
            logger.info("打印URL参数：" + JsonKit.toJson(parameterMap));
        }
        try {
            if (Boolean.parseBoolean(PropertyUtil.getProperty("devModel", "false"))) {
                return true;
            }

            String appid = request.getHeader("appid");
            String timestamp = request.getHeader("timestamp");
            String signature = request.getHeader("signature");
            if(!StaticParam.isAppId(appid)){
                printTips(response, "APPID错误");
                return false;
            }
            boolean result = SecretKit.validateAPIRequest(appid, timestamp, signature, StaticParam.TOKEN);
            if (result) {
                return true;
            }
            printTips(response, "校验失败");
        } catch (IOException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return false;
    }

    /**
     * 控制器执行后，视图渲染前执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * 视图渲染后执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    private void printTips(HttpServletResponse response, String tips) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(LotusResult.error(tips));
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(json);
    }
}