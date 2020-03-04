package com.zst.spring.util.sign;

import com.zst.spring.enums.SignEnum;
import org.junit.jupiter.api.Test;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/3/4 17:52
 * @description ：
 */
class DsaUtilTest {

    @Test
    void plantWide() {
        try {
            DsaUtil.plantWide(SignEnum.DSA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}