/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */

package com.zst.spring;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：bmp
 * @date ：Created in 2020/4/17 14:35
 * @description ：4px常量类
 */
public class FourpxConstants {
    /**
     * https
     */
    public static final String HTTPS = "https";
    /**
     * 获取token正常返回的响应码
     */
    public static final Integer TOKEN_SUCCESS_CODE = 200;
    /**
     * 获取token过期返回的响应码
     */
    public static final Integer TOKEN_EXPIRE_STATE_CODE = 200010;
    /**
     * 版本
     */
    public static final String VERSION = "1.0.0";
    /**
     * 格式
     */
    public static final String FORMART = "json";
    /**
     * 调用接口所需的appKey
     */
    public static final String APP_KEY = "03c9b8c0-f1db-4ae5-9062-8e107716734e";
    /**
     * 调用接口所需的appSecret
     */
    public static final String APP_SECRET = "4f2ce7f2-b6a8-444f-af81-f855ce8828c9";
    /**
     * 访问4px，沙箱环境
     */
    public static final String API_4PX_URL = "http://open.sandbox.4px.com";
    /**
     * 获取网关所需url
     */
    public static final String API_ROUTER_URL = "/router/api/service";

    public static final String PARAM_NAME = "param";

}
