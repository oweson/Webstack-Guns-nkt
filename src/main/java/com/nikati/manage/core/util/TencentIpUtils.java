package com.nikati.manage.core.util;

import com.alibaba.fastjson.JSON;
import com.nikati.manage.core.ipmodel.AdInfo;
import com.nikati.manage.core.ipmodel.Result;
import com.nikati.manage.core.ipmodel.ResultMessage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * @author oweson
 * @date 2022/4/22 22:41
 */


public class TencentIpUtils {

    public static String getAddresses(String ip) throws IOException {
        Request request = new  Request.Builder()
                .url("https://apis.map.qq.com/ws/location/v1/ip?ip="+ip+"&key=EYJBZ-QCKWD-7ZT4E-H723N-SKEXQ-HZBTR")
                .build();
        // new client对象时注意配置的链接与超时时间，根据需要进行调整
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(6, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();

        Response response = client.newCall(request).execute();
        String returnStr = response.body().string();
        if (returnStr != null) {
            // 处理返回的省市区信息
            String[] temp = returnStr.split(",");
            if (temp.length < 3) {
                return "0";
                // 无效IP，局域网测试
            }
            ResultMessage resultMessage = JSON.parseObject(returnStr, ResultMessage.class);
            if (resultMessage.getStatus().equals(0)) {
                StringBuilder sb = new StringBuilder();
                AdInfo result = resultMessage.getResult().getAd_info();
                String nation = result.getNation();
                String province = result.getProvince();
                String city = result.getCity();
                String district = result.getDistrict();
                String adcode = result.getAdcode();
                sb.append(nation + ": " + province + ": " + city + ": " + district + ": " + adcode);
                return sb.toString();
            }

        }
        return "默认ip";
    }


    private static String getResult(String urlStr, String content, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {
            url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setConnectTimeout(10000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(10000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("GET");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());// 打开输出流往对端服务器写数据
            out.writeBytes(content);// 写数据,也就是提交你的表单 name=xxx&pwd=xxx
            out.flush();// 刷新
            out.close();// 关闭输出流
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
            // ,以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();

            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
    }

}
