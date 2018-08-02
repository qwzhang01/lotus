package com.lotus.api.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotus.api.entity.LotusResult;
import com.lotus.api.util.PropertyUtil;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExceptionHandler implements HandlerExceptionResolver {

    private static final Log logger = LogFactory.getLog(ExceptionHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception exception) {
        logger.error(ExceptionUtils.getStackTrace(exception));
        try {
            ObjectMapper mapper = new ObjectMapper();
            LotusResult error = LotusResult.error(exception.getMessage());
            if (Boolean.parseBoolean(PropertyUtil.getProperty("devModel", "false"))) {
                error = LotusResult.error(exception.getMessage(), ExceptionUtils.getStackTrace(exception));
            }
            String json = mapper.writeValueAsString(error);
            ModelAndView mv = new ModelAndView();
            response.setStatus(HttpStatus.OK.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Cache-Control", "no-cache, must-revalidate");
            response.getWriter().write(json);
            return mv;
        } catch (IOException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
        }
        return null;
    }
}