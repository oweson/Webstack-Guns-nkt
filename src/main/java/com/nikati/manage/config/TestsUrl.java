package com.nikati.manage.config;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class TestsUrl {
    public static void main(String[] args) {

        //testUrl("http://1.3.3.3/test");
        // 1 最好使用下面这个，上面那个超时时间不定，所以可能会导致卡住的情况
        testUrlWithTimeOut("http://1.3.3.3", 2000);
    }


    /**
     * 1 url监测
     */
    public static Integer testUrl(String urlString) {

        long lo = System.currentTimeMillis();
        URL url;
        try {
            url = new URL(urlString);
            InputStream in = url.openStream();
            System.out.println("连接可用");
        } catch (Exception e1) {
            System.out.println("连接打不开!");
            url = null;
            return 0;
        }

        System.out.println(System.currentTimeMillis() - lo);
        return 1;
    }

    /**
     * 2 url监测，建议使用
     */
    public static Integer testUrlWithTimeOut(String urlString, int timeOutMillSeconds) {
        long lo = System.currentTimeMillis();
        URL url;
        try {
            url = new URL(urlString);
            URLConnection co = url.openConnection();
            co.setConnectTimeout(timeOutMillSeconds);
            co.connect();
            System.out.println("连接可用");
        } catch (Exception e1) {
            // todo 执行业务逻辑
            System.out.println("连接打不开!");
            url = null;
            return 0;
        }
        System.out.println(System.currentTimeMillis() - lo);
        return 1;
    }


}
