package com.zst.spring.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author      ：Zhaoshaoting
 * @version     ：V1.0
 * @program     ：bmp
 * @date        ：Created in 2020/4/9 8:47
 * @description ：启动项目运行的内容
 */
@Component
@Slf4j
public class ApplicationRunnerImpl implements ApplicationRunner {
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("通过实现ApplicationRunner接口，在spring boot项目启动后打印参数");
    }
}