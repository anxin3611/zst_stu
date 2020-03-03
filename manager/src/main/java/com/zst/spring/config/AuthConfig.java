package com.zst.spring.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/3/3 9:13
 * @description ：认证相关配置
 */
@Configuration
public class AuthConfig {
    public static final ThreadLocal<String> TOKEN = new ThreadLocal<>();

    public static String sign() {

        return null;
    }

    public void remove() {
        TOKEN.remove();
    }
}
