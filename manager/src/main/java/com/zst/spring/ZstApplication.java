package com.zst.spring;

import com.zst.spring.repository.AbstractZstRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/10 15:32
 * @description 启动类
 *
 * EnableJpaRepositories 注解，配置 repository 的地址，以及自定义 repository 的配置
 */
@Slf4j
@SpringBootApplication
@EnableSpringConfigured
@EnableJpaRepositories(basePackages = "com.zst.spring.repository", repositoryBaseClass = AbstractZstRepository.class)
public class ZstApplication {

    public static void main(String[] args) {
//        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(ZstApplication.class, args);
    }
}