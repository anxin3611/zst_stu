/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */

package com.zst.spring.utils;

import com.zst.spring.FourpxConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Slf4j
public class FourpxUtil {

    /**
     * 连接后缀
     */
    private static StringBuilder URL_SUFFIX;

    /**
     * 生成签名
     *
     * @param params 生成签名所需参数
     */
    public static String generateSign(Map<String, Object> params) {
        StringBuilder org = new StringBuilder();
        Map<String, Object> treeParams = new TreeMap<>(params);
        treeParams.forEach((k, v) -> {
            if (!FourpxConstants.PARAM_NAME.equals(k)) {
                org.append(k).append(v);
            }
        });
        org.append(params.get(FourpxConstants.PARAM_NAME)).append(FourpxConstants.APP_SECRET);
        return DigestUtils.md5Hex(org.toString());
    }

    /**
     * 获取网关接口api
     *
     * @param method    需要调用的接口
     * @param paramsStr 所需参数
     */
    public static String call(String method, String paramsStr) {
        HttpParams httpParams = new HttpParams(HttpMethod.POST);
        Map<String, Object> stringObjectMap = getStringObjectMap(method, paramsStr);
        httpParams.setJsonParams((String) stringObjectMap.get(FourpxConstants.PARAM_NAME));
        try {
            return new HttpService(FourpxConstants.API_4PX_URL).service(FourpxConstants.API_ROUTER_URL + URL_SUFFIX.toString(), httpParams);
        } catch (Exception e) {
            log.error("请求异常");
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    /**
     * 封装调用参数
     *
     * @param method 调用的方法
     * @param params 调用方法的参数
     * @return java.util.Map<java.lang.String, java.lang.Object> 返回封装的参数
     */
    public static Map<String, Object> getStringObjectMap(String method, String params) {
        Map<String, Object> sysParams = new HashMap<>(10);
        // 方法名
        sysParams.put("method", method);
        sysParams.put("app_key", FourpxConstants.APP_KEY);
        // 接口版本
        sysParams.put("v", FourpxConstants.VERSION);
        // 时间戳
        sysParams.put("timestamp", System.currentTimeMillis());
        // 提交的业务数据。默认为json格式
        sysParams.put("format", FourpxConstants.FORMART);
        // 请求参数，body
        sysParams.put(FourpxConstants.PARAM_NAME, params);
        // 获取签名
        sysParams.put("sign", generateSign(sysParams));
        // 设置请求连接后缀
        setUrlSuffix(sysParams);
        return sysParams;
    }

    /**
     * 设置请求连接的后缀
     *
     * @param sysParams 请求参数的map集合
     */
    private static void setUrlSuffix(Map<String, Object> sysParams) {
        URL_SUFFIX = new StringBuilder("?");
        sysParams.forEach((k, v) -> {
            // 拼接param以外的公共参数到请求连接上
            if (!FourpxConstants.PARAM_NAME.equals(k)) {
                URL_SUFFIX.append(k).append("=").append(v).append("&");
            }
        });
        String s = URL_SUFFIX.toString();
        URL_SUFFIX = new StringBuilder(s.substring(0, s.length() - 1));
    }
}
