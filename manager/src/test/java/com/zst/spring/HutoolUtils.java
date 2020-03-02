package com.zst.spring;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import org.junit.jupiter.api.Test;

import java.util.Date;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/19 9:56
 * @description 测试工具类
 */
public class HutoolUtils {

    /**
     * 测试Hutool中的DateUtil工具类
     */
    @Test
    public void testHuToolDateUtil() {
        String yyyyMMddHHmmSS = DateUtil.format(new Date(), "yyyyMMddHHmmss");
        System.out.println(yyyyMMddHHmmSS);
    }
    @Test
    public void testIdUtil () {
        String s = IdUtil.randomUUID();
        System.out.println(s);
    }
}
