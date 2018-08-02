package com.lotus.admin.web;

import com.lotus.admin.web.util.HttpRequest;
import org.junit.Test;

import java.util.Arrays;

public class WebTest {
    private final static boolean isDev = false;

    private final static String[] devHost = {"http://localhost:8081"};
    private final static String[] proHost = {
            "http://bb.yguixi.com:8080",//WEB代理
    };
    private final static String[] testHost = isDev ? devHost : proHost;
    static {
        System.out.println(Arrays.toString(testHost));
    }
    @Test
    public void testWeb(){
        for (String url : testHost) {
            String json = HttpRequest.sendGet(url, null);
            System.out.println(json);
        }
    }
}
