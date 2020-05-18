package com.zst.spring.utils;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：Zhaoshaoting
 * @version ：V2.0
 * @program ：my-spring2
 * @date ：Created in 20/5/17 15:35
 * @description ：
 */
class FourpxUtilTest {

    @Test
    void generateSign() {
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();

        Map<String, Object> stringObjectMap = FourpxUtil.getStringObjectMap("fu.wms.inventory.getlog", "{}");
        System.out.println("stringObjectMap = " + stringObjectMap);
    }

    @Test
    void test() {
        String call = FourpxUtil.call("fu.wms.inventory.getlog", "{}");
        System.out.println("call = " + call);
    }
}