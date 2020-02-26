package com.zst.spring;

import org.junit.Test;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/19 9:10
 * @description 简单的测试类
 */

public class SimpleTest {

    @Test
    public void testSystemCurrentMills() {
        // 毫秒值
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testLength() {
        int length = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImV4cCI6MTU4MjcxNjE0NiwidXNlcm5hbWUiOiJhZG1pbiJ9.aI--362GM_7_aYFJOnMUWY9Hena4w96NQ9vqm2Tp_sY".length();
        System.out.println(length);
    }

    @Test
    public void testLength1() {
        int length = "d7d09ce3865f40ed9c85d0ee5b752758".length();
        System.out.println(length);
    }

}
