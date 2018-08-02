package com.lotus.api.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lotus.api.entity.LotusResult;
import com.lotus.api.web.util.HttpRequest;
import org.junit.Test;

import java.util.Arrays;

public class ContentControllerTest {
    private final static boolean isDev = false;

    private final static String[] devHost = {"http://localhost:8080/api"};
    private final static String[] proHost = {
            "http://bb.yguixi.com:8081/api",//API代理
    };

    private final static String[] testHost = isDev ? devHost : proHost;

    static {
        System.out.println(Arrays.toString(testHost));
    }

    @Test
    public void testList() throws Exception {
        for (String url : testHost) {
            String param = "sticky=0&pageNumber=-1&pageSize=0";
            String json = HttpRequest.sendPost(url + "/list/recomm", param);
            System.out.println(json);
            ObjectMapper mapper = new ObjectMapper();
            LotusResult result = mapper.readValue(json, LotusResult.class);
            System.out.println(result.getMessage());
        }
    }

    @Test
    public void testAudio() throws Exception {
        for (String url : testHost) {
            String param = "&pageNumber=-1&pageSize=10";
            String json = HttpRequest.sendPost(url + "/audio/-1", param);
            System.out.println(json);
            ObjectMapper mapper = new ObjectMapper();
            LotusResult result = mapper.readValue(json, LotusResult.class);
            System.out.println(result.getMessage());
        }
    }

    @Test
    public void testAudioDetail() throws Exception {
        for (String url : testHost) {
            String json = HttpRequest.sendPost(url + "/audio/detail/137", null);
            System.out.println(json);
            ObjectMapper mapper = new ObjectMapper();
            LotusResult result = mapper.readValue(json, LotusResult.class);
            System.out.println(result.getMessage());
        }
    }

    @Test
    public void testArticleDetail() throws Exception {
        for (String url : testHost) {
            String json = HttpRequest.sendPost(url + "/article/detail/39", null);
            System.out.println(json);
            ObjectMapper mapper = new ObjectMapper();
            LotusResult result = mapper.readValue(json, LotusResult.class);
            System.out.println(result.getMessage());
        }
    }
}