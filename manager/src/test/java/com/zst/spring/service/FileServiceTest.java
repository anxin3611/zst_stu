package com.zst.spring.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/3/10 15:25
 * @description ：
 */
@SpringBootTest
class FileServiceTest {
    @Resource
    private FileService fileService;
    @Test
    void test1() {
        fileService.test();
    }
}