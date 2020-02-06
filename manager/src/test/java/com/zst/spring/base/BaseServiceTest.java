package com.zst.spring.base;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/19 16:09
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BaseServiceTest {
    @Resource
    private BaseService baseService;

    @Test
    public void handleSerialNum() {
        String serialNum = baseService.handleSerialNum();
    }
}