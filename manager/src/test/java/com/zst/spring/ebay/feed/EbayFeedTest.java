package com.zst.spring.ebay.feed;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/3/26 9:57
 * @description ：
 */
@SpringBootTest
class EbayFeedTest {

    @Resource
    private EbayFeed ebayFeed;
    @Test
    void feed() {
        ebayFeed.feed();
    }
}