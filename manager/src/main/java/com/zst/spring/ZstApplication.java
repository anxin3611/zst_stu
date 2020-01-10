package com.zst.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZST
 * @version 1.0
 * @date 2020/1/10 15:32
 * @description
 */
@Slf4j
@SpringBootApplication
public class ZstApplication {
    public static void main(String[] args) {
        log.info("加载配置......");
        SpringApplication.run(ZstApplication.class, args);
    }
}