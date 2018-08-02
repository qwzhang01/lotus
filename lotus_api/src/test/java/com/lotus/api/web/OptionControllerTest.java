package com.lotus.api.web;


import com.lotus.api.web.util.HttpRequest;
import com.lotus.api.web.util.RestTempleUtil;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class OptionControllerTest {

    private final static boolean isDev = false;

    private final static String devHost = "http://localhost:8080/api";
    private final static String proHost = "http://bb.yguixi.com:8081/api";

    private final static String testHost = isDev ? devHost : proHost;

    static {
        System.out.println(testHost);
    }

    @Test
    public void getOption() {
        String json = HttpRequest.sendPost(testHost + "/option/splash", null);
        System.out.println(json);

        RestTemplate rest = new RestTemplate();
        //header
        HttpHeaders header = RestTempleUtil.getHeader();
        //param
        MultiValueMap<String,String> param = new LinkedMultiValueMap<String, String>();//参数放入一个map中，restTemplate不能用hashMap
        HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<MultiValueMap<String,String>>(param,header);//将参数和header组成一个请求
        String result = rest.postForObject(testHost + "/option/splash", request, String.class);
        System.out.println(result);
    }
}
