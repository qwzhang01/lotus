package com.lotus.api.web.util;

import java.util.List;

import com.lotus.api.param.StaticParam;
import com.lotus.common.kit.CryptKit;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.Set;

public class RestTempleUtil {

    /**
     * 获取浏览器的cookie，将其塞入header中
     */
    public static HttpHeaders getHeader(){
        HttpHeaders headers = new HttpHeaders();
        long ts = System.currentTimeMillis();
        headers.add("appid", StaticParam.APPID[0]);
        headers.add("timestamp", String.valueOf(ts));
        headers.add("signature", CryptKit.sha1(StaticParam.APPID[0] + ts + StaticParam.TOKEN));
        return headers;
    }

    /**
     * 获取浏览器的cookie，将其塞入header中
     */
    public static HttpHeaders getHeader(WebDriver driver){
        HttpHeaders headers = new HttpHeaders();
        Set<Cookie> cookies = driver.manage().getCookies();//获取浏览器cookies
        List<String> cookieList = new ArrayList<String>();
        for(Cookie cookie:cookies){ //将浏览器cookies放入list中
            //System.out.println("当前cookies为:" +  cookie.getDomain() + " " + cookie.getName() + ":" + cookie.getValue());
            cookieList.add(cookie.getName() + "=" + cookie.getValue());
        }
        //System.out.println("cookie为：" + cookieList.toString());
        headers.put(HttpHeaders.COOKIE,cookieList); //将cookie放入header
        //headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); //post表单 ，如果是个json则设置为MediaType.APPLICATION_JSON
        long ts = System.currentTimeMillis();
        headers.add("appid", StaticParam.APPID[0]);
        headers.add("timestamp", String.valueOf(ts));
        headers.add("signature", CryptKit.sha1(StaticParam.APPID[0] + ts + StaticParam.TOKEN));
        return headers;
    }
}
