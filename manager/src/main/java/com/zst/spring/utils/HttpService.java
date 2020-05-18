/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */

package com.zst.spring.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：demo
 * @date ：Created in 2020/4/1 12:39
 * @description ：http请求服务类
 */
@Data
public class HttpService {
    /**
     * 访问地址
     */
    private String serverUrl;
    /**
     * 连接超时时间，ms
     */
    private int connectTimeout = 120000;
    /**
     * 读取超时时间，ms
     */
    private int readTimeout = 120000;

    /**
     * 构造方法
     *
     * @param serverUrl 访问地址
     */
    public HttpService(String serverUrl) {
        this.serverUrl = serverUrl.trim();
    }

    /**
     * common http请求 返回key-value格式
     *
     * @param serviceUrl 访问地址
     * @param paramers   访问参数
     * @return 返回结果 map类型
     * @throws Exception 异常
     */
    public Map<String, Object> commonService(String serviceUrl, HttpParams paramers) throws Exception {
        if (StringUtils.isBlank(serviceUrl)) {
            return new HashMap<>(0);
        }
        return commonService(serviceUrl, paramers, null);
    }

    /**
     * common http请求，带header参数 返回key-value格式
     *
     * @param serviceUrl 访问地址
     * @param paramers   访问参数
     * @param header     请求头
     * @return 返回结果 map类型
     * @throws Exception 异常
     */
    public Map<String, Object> commonService(String serviceUrl, HttpParams paramers, HttpHeader header) throws Exception {
        String response = service(serviceUrl, paramers, header);
        try {
            Map<String, Object> result = JSONObject.parseObject(response, new TypeReference<Map<String, Object>>() {
            });
            if ((result == null) || (result.isEmpty())) {
                throw new Exception("远程服务返回的数据无法解析");
            }
            return result;
        } catch (Exception e) {
            throw new Exception("返回结果异常,response:" + response, e);
        }
    }

    /**
     * 发送请求的service
     *
     * @param serviceUrl 访问地址
     * @param paramers   访问参数
     * @return 返回结果 String类型
     * @throws Exception 异常
     */
    public String service(String serviceUrl, HttpParams paramers) throws Exception {
        return service(serviceUrl, paramers, null);
    }

    /**
     * 发送请求的service, 带有header
     *
     * @param serviceUrl 访问地址
     * @param paramers   访问参数
     * @param header     请求头
     * @return 返回结果 String类型
     * @throws Exception 异常
     */
    public String service(String serviceUrl, HttpParams paramers, HttpHeader header) throws Exception {
        String url = this.serverUrl + serviceUrl;
        String responseData;
        try {
            responseData = HttpClient.doService(url, paramers, header, this.connectTimeout, this.readTimeout);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return responseData;
    }
}
