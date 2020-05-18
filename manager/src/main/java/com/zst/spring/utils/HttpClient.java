/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */

package com.zst.spring.utils;

import com.zst.spring.JdConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Map;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：demo
 * @date ：Created in 2020/4/1 12:36
 * @description ：HttpClient
 */
@Slf4j
public class HttpClient {
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final String JSON_CONTENT_FORM = "application/json;charset=UTF-8";
    public static final String CONTENT_FORM = "application/x-www-form-urlencoded;charset=UTF-8";

    private static final PoolingHttpClientConnectionManager CONN_MGR;
    private static final RequestConfig REQUEST_CONFIG;
    private static final int MAX_TIMEOUT = 0;
    private static final int RETRY_COUNT = 0;
    private static final boolean RETRY_ENABLE = false;

    static {
        CONN_MGR = new PoolingHttpClientConnectionManager();
        // setValidateAfterInactivity 毫秒为单位
        CONN_MGR.setValidateAfterInactivity(5000);
        CONN_MGR.setMaxTotal(100);
        CONN_MGR.setDefaultMaxPerRoute(CONN_MGR.getMaxTotal());
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        REQUEST_CONFIG = configBuilder.build();
    }

    /**
     * 发送http请求统一服务
     *
     * @param url            访问路径，例：http://localhost:9090/zst
     * @param httpParams     请求参数
     * @param header         请求头
     * @param connectTimeout 连接超时时间
     * @param socketTimeOut  读取超时时间
     * @return 远程后的返回值，String类型
     * @throws Exception 抛出异常
     */
    public static String doService(String url, HttpParams httpParams, HttpHeader header, int connectTimeout, int socketTimeOut) throws Exception {
        HttpMethod httpMethod = httpParams.getHttpMethod();
        switch (httpMethod) {
            case PUT:
                return doPut(url, httpParams, header, connectTimeout, socketTimeOut);
            case POST:
                if (url.startsWith(JdConstants.HTTPS)) {
                    return doPostS(url, httpParams, header, connectTimeout, socketTimeOut);
                } else {
                    return doPost(url, httpParams, header, connectTimeout, socketTimeOut);
                }
            default:
                return doGet(url, httpParams, header, connectTimeout, socketTimeOut);
        }
    }

    /**
     * get方法
     *
     * @param url            访问路径，例：http://localhost:9090/zst
     * @param params         请求参数
     * @param header         请求头
     * @param connectTimeout 连接超时时间
     * @param socketTimeOut  读取超时时间
     * @return 远程后的返回值，String类型
     * @throws IOException 抛出异常
     */
    public static String doGet(String url, HttpParams params, HttpHeader header, int connectTimeout, int socketTimeOut) throws IOException {
        String responseData = "";
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            String query = params.getQueryString(DEFAULT_CHARSET);
            // 拼接url
            url = buildGetUrl(url, query);
            HttpGet httpGet = new HttpGet(url);
            // 设置请求头
            setHeader(httpGet, header);
            // http访问的客户端
            DefaultHttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler(RETRY_COUNT, RETRY_ENABLE);
            final int retryCount = retryHandler.getRetryCount();
            log.debug("方法{}，重试次数 {}", params.toString(), retryCount);
            httpClient = HttpClients.custom().setRetryHandler(retryHandler).build();
            httpGet.setConfig(getRequestConfig(connectTimeout, socketTimeOut));
            // 客户端执行GET请求
            httpResponse = httpClient.execute(httpGet);
            HttpEntity resEntity = httpResponse.getEntity();
            responseData = EntityUtils.toString(resEntity, DEFAULT_CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(httpClient, httpResponse);
        }
        return responseData;
    }

    /**
     * post方法
     *
     * @param url            访问路径，例：http://localhost:9090/zst
     * @param params         请求参数
     * @param header         请求头
     * @param connectTimeout 连接超时时间
     * @param socketTimeOut  读取超时时间
     * @return 远程后的返回值，String类型
     * @throws IOException 抛出异常
     */
    public static String doPost(String url, HttpParams params, HttpHeader header, int connectTimeout, int socketTimeOut) throws IOException {
        // 返回数据
        String responseData = "";
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            String query;
            HttpPost httpPost = new HttpPost(url);
            // 设置请求头
            setHeader(httpPost, header);
            if (params.isJson()) {
                //json数据
                httpPost.setHeader(HTTP.CONTENT_TYPE, JSON_CONTENT_FORM);
                query = params.getJsonParams();
            } else {
                //表单数据
                httpPost.setHeader(HTTP.CONTENT_TYPE, CONTENT_FORM);
                query = params.getQueryString(DEFAULT_CHARSET);
            }
            if (null != query && !("").equals(query)) {
                HttpEntity reqEntity = new StringEntity(query);
                httpPost.setEntity(reqEntity);
            }
            DefaultHttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler(RETRY_COUNT, RETRY_ENABLE);
            final int retryCount = retryHandler.getRetryCount();
            log.debug("方法{}，重试次数 {}", params.toString(), retryCount);
            httpClient = HttpClients.custom().setRetryHandler(retryHandler).build();
            httpPost.setConfig(getRequestConfig(connectTimeout, socketTimeOut));
            httpResponse = httpClient.execute(httpPost);
            HttpEntity resEntity = httpResponse.getEntity();
            responseData = EntityUtils.toString(resEntity, DEFAULT_CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(httpClient, httpResponse);
        }
        return responseData;
    }

    /**
     * put方法
     *
     * @param url            访问路径，例：http://localhost:9090/zst
     * @param params         请求参数
     * @param header         请求头
     * @param connectTimeout 连接超时时间
     * @param socketTimeOut  读取超时时间
     * @return 远程后的返回值，String类型
     * @throws IOException 抛出异常
     */
    public static String doPut(String url, HttpParams params, HttpHeader header, int connectTimeout, int socketTimeOut) throws IOException {
        // 返回数据
        String responseData = "";
        // 创建Httpclient对象
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse httpResponse = null;
        try {
            String query = null;
            // 创建Http Put请求
            HttpPut httpPut = new HttpPut(url);
            // 设置请求头
            setHeader(httpPut, header);
            if (params.isJson()) {
                httpPut.setHeader(HTTP.CONTENT_TYPE, JSON_CONTENT_FORM);
                query = params.getJsonParams();
            }
            if (null != query && !("").equals(query)) {
                // 创建请求内容
                StringEntity entity = new StringEntity(query, ContentType.APPLICATION_JSON);
                httpPut.setEntity(entity);
            }
            DefaultHttpRequestRetryHandler retryHandler = new DefaultHttpRequestRetryHandler(RETRY_COUNT, RETRY_ENABLE);
            final int retryCount = retryHandler.getRetryCount();
            log.debug("方法{}，重试次数 {}", params.toString(), retryCount);
            httpClient = HttpClients.custom().setRetryHandler(retryHandler).build();
            httpPut.setConfig(getRequestConfig(connectTimeout, socketTimeOut));
            // 执行http请求
            httpResponse = httpClient.execute(httpPut);
            responseData = EntityUtils.toString(httpResponse.getEntity(), DEFAULT_CHARSET);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeResource(httpClient, httpResponse);
        }
        return responseData;
    }

    /**
     * 发送https请求
     *
     * @param url            访问路径，例：http://localhost:9090/zst
     * @param paramers       请求参数
     * @param header         请求头
     * @param connectTimeout 连接超时时间
     * @param socketTimeOut  读取超时时间
     * @return JSONObject(通过JSONObject.get ( key)的方式获取json对象的属性值)
     */
    public static String doPostS(String url, HttpParams paramers, HttpHeader header, int connectTimeout, int socketTimeOut) {
        DefaultHttpRequestRetryHandler requestRetryHandler = new DefaultHttpRequestRetryHandler(RETRY_COUNT, RETRY_ENABLE);
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSslConnSocketFactory()).setConnectionManager(CONN_MGR).setDefaultRequestConfig(REQUEST_CONFIG).setRetryHandler(requestRetryHandler).build();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        String httpStr = null;

        try {
            String query = "";
            httpPost.setConfig(REQUEST_CONFIG);
            // 设置请求头
            setHeader(httpPost, header);
            if (paramers.isJson()) {
                //json数据
                httpPost.setHeader(HTTP.CONTENT_TYPE, JSON_CONTENT_FORM);
                httpPost.setConfig(getRequestConfig(connectTimeout, socketTimeOut));
                query = paramers.getJsonParams();
            } else {
                //表单数据
//                httpPost.setHeader(HTTP.CONTENT_TYPE, CONTENT_FORM);
                httpPost.setEntity(paramers.getUrlEncodedFormEntity(StandardCharsets.UTF_8.name()));
            }
            if (null != query && !("").equals(query)) {
                HttpEntity reqEntity = new StringEntity(query);
                httpPost.setEntity(reqEntity);
            }
            response = httpClient.execute(httpPost);
            int retryCount = requestRetryHandler.getRetryCount();
            log.debug("重试次数 is {}", retryCount);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.SC_OK) {
                return null;
            }
            HttpEntity entity = response.getEntity();
            if (entity == null) {
                return null;
            }
            httpStr = EntityUtils.toString(entity, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException( e.getMessage());
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    private static SSLConnectionSocketFactory createSslConnSocketFactory() {
        /**
         * 创建
         *
         * @param
         * @return org.apache.http.conn.ssl.SSLConnectionSocketFactory
         */
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, (TrustStrategy) (chain, authType) -> true).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, (arg0, arg1) -> true);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }


    /**
     * 字节数组转换为十六进制字符串
     *
     * @param b byte[] 需要转换的字节数组
     * @return String 十六进制字符串
     */
    public static String byte2hex(byte b[]) {
        if (b == null) {
            throw new IllegalArgumentException(
                    "Argument b ( byte array ) is null! ");
        }
        StringBuilder hs = new StringBuilder();
        String stmp = "";
        for (byte value : b) {
            stmp = Integer.toHexString(value & 0xff);
            if (stmp.length() == 1) {
                hs.append("0").append(stmp);
            } else {
                hs.append(stmp);
            }
        }
        return hs.toString().toUpperCase();
    }


    /**
     * 关闭资源
     *
     * @param httpClient   httpClient
     * @param httpResponse httpResponse
     * @throws IOException 异常
     */
    private static void closeResource(CloseableHttpClient httpClient, CloseableHttpResponse httpResponse) throws IOException {
        if (null != httpResponse) {
            httpResponse.close();
        }
        if (null != httpClient) {
            httpClient.close();
        }
    }

    /**
     * 设置RequestConfig
     *
     * @param connectTimeout 建连时间, 如果没有connectTimeout，建立tcp链接时，阻塞，假死
     * @param socketTimeOut  等待数据的时间, 如果没有socketTimeout，已经建立的tcp链接，在传输过程中，发送的报文可能会因为网络中断引起程序阻塞，假死; socket建连时，如果网络层不可达，会直接抛异常，而不是等connectTimeout耗尽
     * @return RequestConfig
     */
    private static RequestConfig getRequestConfig(int connectTimeout, int socketTimeOut) {
        return RequestConfig.custom().setSocketTimeout(socketTimeOut).setConnectTimeout(connectTimeout).build();
    }

    /**
     * 设置请求头
     *
     * @param httpRequestBase httpRequestBase
     * @param header          设置的请求头
     */
    private static void setHeader(HttpRequestBase httpRequestBase, HttpHeader header) {
        if (header != null) {
            Map<String, String> headerMap = header.getParams();
            if (null != headerMap && !headerMap.isEmpty()) {
                headerMap.forEach(httpRequestBase::setHeader);
            }
        }
    }

    /**
     * 拼接get请求的url
     *
     * @param url   访问路径，例：http://localhost:9090/zst
     * @param query get请求的参数
     * @return 拼接好的url
     */
    private static String buildGetUrl(String url, String query) {
        if (null == query || ("").equals(query)) {
            return url;
        }
        StringBuilder newUrl = new StringBuilder(url);
        boolean hasQuery = url.contains("?");
        boolean hasPrepend = (url.endsWith("?")) || (url.endsWith("&"));
        if (!hasPrepend) {
            if (hasQuery) {
                newUrl.append("&");
            } else {
                newUrl.append("?");
            }
        }
        newUrl.append(query);
        return newUrl.toString();
    }
}
