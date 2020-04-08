package com.zst.spring.ebay;

import com.ebay.api.client.auth.oauth2.OAuth2Api;
import com.ebay.api.client.auth.oauth2.model.Environment;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/3/18 11:54
 * @description ：
 */
@SpringBootTest
class EbayConfigTest {
    @Resource
    private EbayConfig config;

    @Test
    public void test() throws IOException {
        OAuth2Api oAuth2Api = new OAuth2Api();
        oAuth2Api.getApplicationToken(Environment.SANDBOX, Arrays.asList());
    }


}