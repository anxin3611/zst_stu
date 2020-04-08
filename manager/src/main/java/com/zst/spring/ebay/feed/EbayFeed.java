package com.zst.spring.ebay.feed;

import com.ebay.feed.api.Feed;
import com.ebay.feed.api.FeedImpl;
import com.ebay.feed.enums.FeedTypeEnum;
import com.ebay.feed.model.feed.download.GetFeedResponse;
import com.ebay.feed.model.feed.operation.feed.FeedRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/3/26 9:53
 * @description ：
 */
@Component
@Slf4j
public class EbayFeed {

    @Value("${token}")
    private String token;

    public void feed() {
        FeedRequest.FeedRequestBuilder builder = new FeedRequest.FeedRequestBuilder();
        builder.feedScope("ALL_ACTIVE")
                .categoryId("1")
                .siteId("EBAY_US")
                .token(token)
                .type(FeedTypeEnum.ITEM);

        Feed feed = new FeedImpl();

        GetFeedResponse response = feed.get(builder.build(), "E:\\ebayxml\\feed");
        log.info(response.toString());

    }
}
