package com.lotus.admin.util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

public class SessionUtil {
    private static Logger log = LoggerFactory.getLogger(SessionUtil.class);

    public static void clearSession() {
        try {
            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpSession session = attrs.getRequest().getSession();
            if (session != null) {
                Enumeration<String> em = session.getAttributeNames();
                if (em != null) {
                    while (em.hasMoreElements()) {
                        session.removeAttribute(em.nextElement().toString());
                    }
                }
            }
        } catch (Exception ex) {
            log.error(ExceptionUtils.getStackTrace(ex));
        }
    }
}
