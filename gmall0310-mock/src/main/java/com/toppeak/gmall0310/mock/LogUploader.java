package com.toppeak.gmall0310.mock;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LogUploader {
    public static void sendLogStream(String log)  {
        try {
            //不同的日志类型对应不同的URL
            URL  url = new URL("http://linux01/logger/log");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //设置请求方式为post
            conn.setRequestMethod("POST");

            //时间头用来供server进行时钟校对
            conn.setRequestProperty("clientTime",System.currentTimeMillis() + "");

            //允许上传数据
            conn.setDoOutput(true);

            //设置请求的头信息，设置内容类型为JSON

            /**
             * Post请求的两种编码格式：application/x-www-form-urlencoded和multipart/form-data - 简书
             * https://www.jianshu.com/p/53b5bd0f1d44
             * 编码格式就是application/x-www-form-urlencoded
             * （将键值对的参数用&连接起来，如果有空格，将空格转换为+加号；有特殊符号，将特殊符号转换为ASCII HEX值）
             */
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            System.out.println("upload"+log);

            //输出流
            OutputStream out = conn.getOutputStream();
            out.write(("logString="+log).getBytes());
            out.flush();
            out.close();
            int code = conn.getResponseCode();
            System.out.println(code);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
