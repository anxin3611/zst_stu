package com.zst.spring.ebay;

import com.ebay.api.client.auth.oauth2.CredentialUtil;
import com.ebay.api.client.auth.oauth2.OAuth2Api;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/3/19 21:42
 * @description ：ebay-auth调用
 */
@Configuration
@Log4j2
public class EbayAuth implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 导入ebay配置
        loadCredential();
    }

    public void loadCredential() {
        try {



            CredentialUtil.load(new FileInputStream(ResourceUtils.getFile("classpath:ebay-config.yml")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public OAuth2Api oAuth2Api() {
        return new OAuth2Api();
    }

    static {
        try {
            CredentialUtil.load(new FileInputStream(ResourceUtils.getFile("classpath:ebay-config.yml")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



}
