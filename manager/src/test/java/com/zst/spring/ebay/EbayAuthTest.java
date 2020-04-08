package com.zst.spring.ebay;

import com.zst.spring.service.SysConfigService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.io.IOException;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/3/19 21:51
 * @description ：
 */
@SpringBootTest
class EbayAuthTest {
    @Resource
    private SysConfigService sysConfigService;
    @Test
    void getAuthorizaRestfulUrl() {
        String authorizaRestfulUrl = sysConfigService.authRestfulUrl("ebay_sandbox");
        System.out.println(authorizaRestfulUrl);

    }

    @Test
    public void gettokenByCode() throws IOException {
        String s = sysConfigService.getTokenByCode("v%5E1.1%23i%5E1%23f%5E0%23p%5E3%23r%5E1%23I%5E3%23t%5EUl41Xzg6NjQ1REI4MzI5RjU1N0U4REZBNjZGRTU5Mzg2OTEwNzVfMF8xI0VeMTI4NA%3D%3D");
        System.out.println(s);

    }

    @Test
    void getApplicationToken() {
        String applicationToken = sysConfigService.getApplicationToken();
        System.out.println(applicationToken);
    }

    @Test
    void reRefreshToken() {
        String freshToken = "v^1.1#i^1#f^0#p^3#r^1#I^3#t^Ul4xMF81OkNDQTY3NEFBQkZDQzM1RkFDNEJCRjQ3Rjc1RkZCOTlDXzBfMSNFXjEyODQ=";
        String s = sysConfigService.reRefreshToken(freshToken);
        System.out.println(s);
    }
}