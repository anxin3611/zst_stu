package com.zst.spring.ebay;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：demo
 * @date ：Created in 2020/3/18 11:13
 * @description ：
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "ebay.sandbox")
public class EbayConfig {
    private String appId;
    private String devId;
    private String certId;
}
