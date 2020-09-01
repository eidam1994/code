package com.framework.util;

import com.alibaba.fastjson.JSONObject;
import com.framework.constant.HttpStatus;

import java.io.*;
import java.net.*;

/**
 * @description: 获取数据工具类
 * @author: xingyuzhang
 * @create: 2020-09-01 14:31
 */
public class DataUtils {
    
    public static JSONObject getMovieJson(String url) {
        try {
            URL realUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestProperty("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", RandomValueUtils.getRandomUserAgent());
            // 建立实际的连接
            connection.connect();
            //请求成功
            StringBuilder sbf = new StringBuilder();
            if (connection.getResponseCode() == HttpStatus.SUCCESS) {
                InputStream is = connection.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                String strRead = null;
                while ((strRead = reader.readLine()) != null) {
                    sbf.append(strRead);
                    System.out.println(strRead);
                }
                reader.close();
                //转换成json数据处理
                // getHttpJson函数的后面的参数1，表示返回的是json数据，2表示http接口的数据在一个（）中的数据
                JSONObject jsonObject = JSONObject.parseObject(sbf.toString());
                return jsonObject;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
}
