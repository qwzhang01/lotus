package com.lotus.common.kit;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class MapKit {
    private static Logger log = LoggerFactory.getLogger(MapKit.class);

    public static Map<String, Object> objToMap(Object obj) {
        Map<String, Object> reMap = new HashMap<String, Object>();
        if (obj == null) {
            return null;
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                try {
                    Field f = obj.getClass().getDeclaredField(fields[i].getName());
                    f.setAccessible(true);
                    Object o = f.get(obj);
                    reMap.put(fields[i].getName(), o);
                } catch (NoSuchFieldException e) {
                    log.error(ExceptionUtils.getStackTrace(e));
                } catch (IllegalArgumentException e) {
                    log.error(ExceptionUtils.getStackTrace(e));
                } catch (IllegalAccessException e) {
                    log.error(ExceptionUtils.getStackTrace(e));
                }
            }
        } catch (SecurityException e) {
            log.error(ExceptionUtils.getStackTrace(e));
        }
        return reMap;
    }
}
