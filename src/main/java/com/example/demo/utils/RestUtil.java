package com.example.demo.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestUtil {

    //get
    public void getMethod(String url) throws IOException {
        URL restURL = new URL(url);

        HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();

        conn.setRequestMethod("GET"); // POST GET PUT DELETE
        conn.setRequestProperty("Accept", "application/json");

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
    }

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的URL
     * @param query 请求参数是json
     * @throws IOException
     */
    public void postMethod(String url, String query) throws IOException {
        URL restURL = new URL(url);
        // 通过远程url连接对象打开连接
        HttpURLConnection conn = (HttpURLConnection) restURL.openConnection();
        // 设置连接请求方式
        conn.setRequestMethod("POST");
        // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
        conn.setRequestProperty("Content-Type", "application/json");
        // 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0（令牌）
        conn.setRequestProperty("Authorization", "eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1OTg5NDcyMTEsInRpbWVzdGFtcCI6MTU5ODk0MzYxMTUxMn0.08eh9kisOZ7h3K2IXDy86PrkXPgu1soQarER3FNYltg");
        // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
        conn.setDoOutput(true);
        // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
        conn.setDoInput(true);
        // 设置连接主机服务器超时时间：5000毫秒
        conn.setConnectTimeout(5000);
        // 设置读取主机服务器返回数据超时时间：8000毫秒
        conn.setReadTimeout(8000);
        // 通过连接对象获取一个输出流
        PrintStream ps = new PrintStream(conn.getOutputStream());
        // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
        ps.write(query.getBytes());
        ps.print(query);
        ps.close();

        // 对输入流对象进行包装:charset根据工作项目组的要求来设置
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        // 关闭资源
        br.close();
        // 断开与远程地址url的连接
        conn.disconnect();
    }

    public static void main(String[] args) {
        RestUtil restUtil = new RestUtil();
        try {
            restUtil.getMethod("Http://esl-hz.zkong.com:9999/user/getErpPublicKey");

            String url = "Http://esl-hz.zkong.com:9999/led/excuteLed";
            String query = "{\n" +
                    "    \"creatTime\": \"2020-9-1 10:23:20\",  \n" +
                    "    \"delTime\": \"2020-9-2 10:23:19\",    \n" +
                    "    \"excuteTime\": \"\",  \n" +
                    "    \"id\": 146,     \n" +
                    "    \"isFlag\": 0,  \n" +
                    "    \"lightCololr\": \"8\",  \n" +
                    "    \"lightTime\": \"60\",  \n" +
                    "    \"operater\": \"静静\",  \n" +
                    "    \"pageSize\": 10,  \n" +
                    "    \"storeId\":1594794906741 ,  \n" +
                    "    \"strategyName\": \"闪灯策略\",   \n" +
                    "    \"targetExecution\": \"514/西瓜\",    \n" +
                    "\"lightFrequency\":\"1\"  \n" +
                    "}"; //json格式
            restUtil.postMethod(url, query);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}