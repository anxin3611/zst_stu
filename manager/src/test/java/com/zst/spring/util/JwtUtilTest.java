package com.zst.spring.util;

import org.junit.jupiter.api.Test;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/3/4 18:37
 * @description ：
 */
class JwtUtilTest {

    @Test
    void token() {
        System.out.println(JwtUtil.token());
    }

    @Test
    void verify() {
        boolean jljl = JwtUtil.verify("jljl");
        System.out.println(jljl);
    }
}