/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */

package com.zst.spring.ebay;

import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：bmp
 * @date ：Created in 2020/2/19 14:22
 * @description ：通过php调用amazon提交接口
 */
//@Component
public class EbayUtil {

    @Resource
    private RestTemplate restTemplate;

    /**
     * 调取php请求的post方法
     *
     * @param url    php接口地址
     * @param params 接口参数
     * @return java.lang.String 返回的结果字符串
     */
    public void doPost(String url, String params) throws IOException {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id", "id");
        HttpHeaders header = new HttpHeaders();
        // 需求需要传参为form-data格式
        header.setContentType(MediaType.MULTIPART_FORM_DATA);
//        header.add("");
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, header);
        JSONObject response = restTemplate.postForObject(url, httpEntity, JSONObject.class);
    }

    /**
     * 将输入流转换成字符串
     *
     * @param is 输入流
     * @return java.lang.String 结果字符串
     */
    private String parseJson(InputStream is) throws IOException {
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }
        return responseStrBuilder.toString();
    }
}
