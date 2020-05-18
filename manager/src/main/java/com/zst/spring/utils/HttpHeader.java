/*
 * Copyright (c) 2020-2030 SiShun.Co.Ltd. All Rights Reserved.
 */

package com.zst.spring.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：demo
 * @date ：Created in 2020/4/1 12:35
 * @description ：请求头
 */
public class HttpHeader {

    private final Map<String, String> params = new HashMap<>();

    /**
     * 添加请求头
     *
     * @param name  请求头名称
     * @param value 请求头value
     */
    public void addParam(String name, String value) {
        this.params.put(name, value);
    }

    public Map<String, String> getParams() {
        return this.params;
    }
}
