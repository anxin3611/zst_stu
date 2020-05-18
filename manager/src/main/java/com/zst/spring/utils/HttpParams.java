/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */

package com.zst.spring.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：demo
 * @date ：Created in 2020/4/1 12:36
 * @description ：请求参数
 */
public class HttpParams {
    private Map<String, Object> params = new HashMap<>();
    private final HttpMethod httpMethod;
    private String jsonParams = "";

    public HttpParams(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public static HttpParams httpPostParams() {
        return new HttpParams(HttpMethod.POST);
    }

    public static HttpParams httpGetParams() {
        return new HttpParams(HttpMethod.GET);
    }

    public static HttpParams httpPutParams() {
        return new HttpParams(HttpMethod.PUT);
    }

    public void addParam(String name, String value) {
        this.params.put(name, value);
    }

    public void setParams(Map<String,Object> params) {
        this.params = params;
    }

    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    public String getQueryString(String charset) throws IOException {
        if (this.params.isEmpty()) {
            return null;
        }
        StringBuilder query = new StringBuilder();
        Set<Map.Entry<String, Object>> entries = this.params.entrySet();

        for (Map.Entry<String, Object> entry : entries) {
            String name = entry.getKey();
            String value = entry.getValue().toString();
            query.append("&").append(name).append("=").append(URLEncoder.encode(value, charset));
        }
        return query.substring(1);
    }

    public UrlEncodedFormEntity getUrlEncodedFormEntity(String charset) throws IOException {
        if (this.params.isEmpty()) {
            return null;
        }

        List<NameValuePair> pairList = new ArrayList<NameValuePair>(this.params.size());
        for (Map.Entry<String, Object> entry : this.params.entrySet()) {
            NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry
                    .getValue().toString());
            pairList.add(pair);
        }
        return new UrlEncodedFormEntity(pairList, charset);
    }

    public boolean isJson() {
        return StringUtils.isNotEmpty(this.jsonParams);
    }

    public Map<String, Object> getParams() {
        return this.params;
    }

    @Override
    public String toString() {
        return "HttpParamers " + JSON.toJSONString(this);
    }

    public String getJsonParams() {
        return this.jsonParams;
    }

    public void setJsonParams() {
        this.jsonParams = JSON.toJSONString(this.params);
    }

    public void setJsonParams(String jsonString) {
        this.jsonParams = jsonString;
    }

}
