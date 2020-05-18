/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */

package com.zst.spring;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：bmp
 * @date ：Created in 2020/4/17 14:35
 * @description ：常量类
 */
public class JdConstants {
    /**
     * https
     */
    public static final String HTTPS = "https";
    /**
     * https 开始的索引
     */
    public static final Integer HTTPS_START_INDEX = 0;
    /**
     * https 结尾的索引
     */
    public static final Integer HTTPS_END_INDEX = 5;
    /**
     * 获取token正常返回的响应码
     */
    public static final Integer TOKEN_SUCCESS_CODE = 200;
    /**
     * 获取token过期返回的响应码
     */
    public static final Integer TOKEN_EXPIRE_STATE_CODE = 200010;
    /**
     * 获取token所需授权类型
     */
    public static final String GET_TOKEN_GRANT_TYPE = "client_credentials";
    /**
     * 刷新token所需授权类型
     */
    public static final String REFRESH_TOKEN_GRANT_TYPE = "refresh_token";
    /**
     * 版本
     */
    public static final String VERSION = "V1";
    /**
     * 格式
     */
    public static final String FORMART = "json";
    /**
     * 调用接口所需的appKey
     */
    public static final String APP_KEY = "app_20190801000000462089";
    /**
     * 调用接口所需的appSecret
     */
    public static final String APP_SECRET = "924de099c91122710bee1574641a428626a0a5829";
    /**
     * jd后台唯一的商铺id 京东平台上 仓库编号（美东KY）
     */
    public static final Long JD_STORE_ID = 2456498L;
    /**
     * 计算月销量模型 规定的天数
     */
    public static final int DAY = 30;
    /**
     * 获取token时所需要的时间字符串
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss Z";
    /**
     * 访问jd
     */
    public static final String API_JOY_BUY_URL = "https://api.joybuy.com";
    /**
     * 获取token所需的url
     */
    public static final String GET_TOKEN_URL_PRE = "/token";
    /**
     * 获取网关所需url
     */
    public static final String GET_ROUTER_URL_PRE = "/router";

}
