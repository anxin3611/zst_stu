/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */

package com.zst.spring.utils;

import com.zst.spring.JdConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：bmp
 * @date ：Created in 2020/4/17 14:32
 * @description ：获取jd-walmart token 工具类
 */
@Slf4j
public class GetTokenUtil {

    private final static String SYMBOL = ":";

    /**
     * 请求jd, 获取token
     *
     * @return 返回的响应字符串
     */
    public static String getToken() {
        HttpParams httpParams = new HttpParams(HttpMethod.POST);

        Map<String, Object> params = httpParams.getParams();
        params.put("appKey", JdConstants.APP_KEY);
        params.put("appSecret", JdConstants.APP_SECRET);
        params.put("grantType", JdConstants.GET_TOKEN_GRANT_TYPE);

        try {
            log.info("获取token");
            String call = new HttpService(JdConstants.API_JOY_BUY_URL).service(JdConstants.GET_TOKEN_URL_PRE, httpParams);
            log.info("获取token返回信息，{}", call);
            return call;
        } catch (Exception e) {
            log.error("获取token时出现异常");
            e.printStackTrace();
            return "get token abnormality";
        }
    }

    /**
     * 刷新token(token默认有效时间为7天)
     *
     * @param refreshToken 刷新码 (刷新码默认有效时间为365天)
     * @return 返回的响应字符串
     */
    public static String getRefreshToken(String refreshToken) {
        HttpParams httpParams = new HttpParams(HttpMethod.POST);

        Map<String, Object> params = httpParams.getParams();
        params.put("appKey", JdConstants.APP_KEY);
        params.put("appSecret", JdConstants.APP_SECRET);
        params.put("refreshToken", refreshToken);
        params.put("grantType", JdConstants.REFRESH_TOKEN_GRANT_TYPE);

        try {
            log.info("刷新token");
            String call = new HttpService(JdConstants.API_JOY_BUY_URL).service(JdConstants.GET_TOKEN_URL_PRE, httpParams);
            log.info("刷新token返回信息，{}", call);
            return call;
        } catch (Exception e) {
            log.error("刷新token时出现异常");
            e.printStackTrace();
            return "refresh token abnormality";
        }
    }

    /**
     * 生成签名
     *
     * @param params 生成签名所需参数
     */
    public static String generateSign(Map<String, Object> params) {
        StringBuilder org = new StringBuilder();
        Map<String, Object> treeMap = new TreeMap<>(params);
        for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
            org.append(entry.getKey()).append(entry.getValue());
        }
        org.append(JdConstants.APP_SECRET);
        return DigestUtils.md5Hex(org.toString());
    }

    /**
     * 获取网关接口api
     *
     * @param accessToken 访问的token
     * @param method      需要调用的接口
     * @param paramsStr   请求参数，json字符串
     */
    public static String call(String accessToken, String method, String paramsStr) {
        HttpParams httpParams = new HttpParams(HttpMethod.POST);

        httpParams.setParams(getStringObjectMap(method, paramsStr));
        try {
            return new HttpService(JdConstants.API_JOY_BUY_URL).service(JdConstants.GET_ROUTER_URL_PRE, httpParams);
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
        sysParams.put("app_key", JdConstants.APP_KEY);
        sysParams.put("method", method);
        sysParams.put("v", JdConstants.VERSION);
        sysParams.put("format", JdConstants.FORMART);
        sysParams.put("timestamp", getServerTime());
        sysParams.put("param", params);
        sysParams.put("sign", generateSign(sysParams));
        return sysParams;
    }

    /**
     * 调用服务的时间
     */
    private static String getServerTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(JdConstants.DATE_FORMAT);
        return sdf.format(new Date());
    }

    /**
     * 将多个参数拼接成key
     *
     * @param keys 需要拼接在一起的字符串
     * @return java.lang.String
     */
    public static String getKey(Object... keys) {
        StringBuilder bud = new StringBuilder();
        for (Object value : keys) {
            bud.append(value)
                    .append(SYMBOL);
        }
        return bud.deleteCharAt(bud.length() - 1).toString();
    }

}
