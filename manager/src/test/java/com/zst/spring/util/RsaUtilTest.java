package com.zst.spring.util;

import com.zst.spring.enums.SignEnum;
import com.zst.spring.util.sign.RsaUtil;
import org.junit.jupiter.api.Test;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/3/3 15:44
 * @description ：
 */
class RsaUtilTest {

    @Test
    void testInitKey() {
        try {
            RsaUtil.plantWide(SignEnum.DSA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}