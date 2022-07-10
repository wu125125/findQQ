package com.guo.controller;

import java.io.IOException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.guo.entity.PostObject;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class HttpClientHelper {
    private  final static String url ="https://zy.xywlapi.cc/qqphone?";
    private static HttpClient httpClient ;
    static{
         httpClient = new HttpClient();
         httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(60000);
    }
    public static String sendPost(String phone) throws HttpException, IOException {
        // 创建httpClient实例对象
        //HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        //httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建post请求方法实例对象
        PostMethod postMethod = new PostMethod(url+phone);
        // 设置post请求超时时间
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 30000);
        postMethod.addRequestHeader("Content-Type", "application/json");
        httpClient.executeMethod(postMethod);//发起请求
        String result = postMethod.getResponseBodyAsString();
        JSONObject jsonObject = JSONObject.parseObject(result);
        //打印服务器返回的方法
        postMethod.releaseConnection();
        if(jsonObject.get("qq")==null){
            return "电话: " +phone.substring(phone.indexOf("=")+1,phone.length()) + " QQ: " +"未查到对应账号"  + "\n";
        }else{
            return "电话: " + jsonObject.get("") + " QQ: " +jsonObject.get("qq")  + "\n";
        }

    }
}
