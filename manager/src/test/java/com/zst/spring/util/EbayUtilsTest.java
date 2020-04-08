package com.zst.spring.util;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/3/24 8:50
 * @description ：
 */
class EbayUtilsTest {

    @Test
    void scopes() {
        List<String> scopes = EbayUtils.scopes("");
        System.out.println(scopes);
    }
}