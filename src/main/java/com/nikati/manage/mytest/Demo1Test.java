package com.nikati.manage.mytest;

import java.net.URL;
import java.net.URLConnection;

public class Demo1Test {
    public static void main(String[] args) {
        String urlString = "http://192.168.101.205:1997/";
        URL url;

        try {

            url = new URL(urlString);

            URLConnection co = url.openConnection();

            co.setConnectTimeout(8000);

            co.connect();

            System.out.println("连接可用");

        } catch (Exception e1) {

            System.out.println("连接打不开!");

            url = null;

        }
    }
}
