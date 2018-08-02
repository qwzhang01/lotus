package com.lotus.api.param;

import java.util.Arrays;

public class StaticParam {
    public static String[] APPID = {
            "a88c6a0f8b0d4bc87d5226fe4a097631",//Andriod APPID
            "0d1178c7fe1940599747195befb5ea63",//微信小程序 APPID
            "ce19405195bea6717fb5ea63f0d17178"//IOS APPID
    };
    public static String TOKEN = "lotus#2017";

    public static boolean isAppId(String appId){
        return Arrays.asList(APPID).contains(appId);
    }
}