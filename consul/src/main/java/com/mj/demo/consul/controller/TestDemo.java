package com.mj.demo.consul.controller;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class TestDemo {

    public static void main(String[] args) {

        //校验身份证和登录名
        testcheckUserInfo();

        //同步用户
        testSyncUserInfo();


    }

    /**
     * @Author
     * @Description 校验身份证号码和登录名是否被使用
     * @Date  2018/8/28
     **/
    public static void testcheckUserInfo(){
        String url = "http://localhost:8082/uaac-service/rest/user/checkUserInfo"; //正式使用时请将ip和端口换成生产环境的ip和端口
        String sfzh="340102199003075997";
        String loginName = "niki8888";

        url=url+"?sfzh="+sfzh+"&loginName="+loginName;
        String result = doGet(url);
        System.out.println(result);
    }


    /**
     * @Author
     * @Description 同步用户信息
     * @Date  2018/8/28
     **/
    public static void testSyncUserInfo() {

        String userInfo = "{\"loginName\": \"niki8888\",\"sfzh\": \"340102199003075997\",\"name\": \"王柳5\",\"orgId\": \"adsfadfadfadfadsfadf\"}";
        String url = "http://localhost:8082/uaac-service/rest/user/syncUserInfo";



        String para=userInfo;
        String result = doPost(url, para);
        System.out.println(result);
    }



    public static String doGet(String urlStr)
    {
        URL url = null;
        HttpURLConnection conn = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try
        {
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(10000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            if (conn.getResponseCode() == 200)
            {
                is = conn.getInputStream();
                baos = new ByteArrayOutputStream();
                int len = -1;
                byte[] buf = new byte[128];

                while ((len = is.read(buf)) != -1)
                {
                    baos.write(buf, 0, len);
                }
                baos.flush();
                String content = new String(baos.toByteArray(),"utf-8");
                return content;
            } else
            {
                throw new RuntimeException(" responseCode is not 200 ... ");
            }

        } catch (Exception e)
        {
           e.printStackTrace();

        } finally
        {
            try
            {
                if (is != null)
                    is.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                if (baos != null)
                    baos.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            conn.disconnect();
        }

        return null ;

    }


    public static String doPost(String url, String param)
    {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try
        {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl
                    .openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "application/json");

            conn.setRequestProperty("charset", "utf-8");
            conn.setUseCaches(false);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(10000);

            if (param != null && !param.trim().equals(""))
            {
                // 获取URLConnection对象对应的输出流
                out = new PrintWriter(conn.getOutputStream());
                // 发送请求参数
                out.print(param);
                // flush输出流的缓冲
                out.flush();
            }
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null)
            {
                result += line;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        // 使用finally块来关闭输出流、输入流
        finally
        {
            try
            {
                if (out != null)
                {
                    out.close();
                }
                if (in != null)
                {
                    in.close();
                }
            } catch (IOException ex)
            {
                ex.printStackTrace();
            }
        }
        return result;
    }




}
