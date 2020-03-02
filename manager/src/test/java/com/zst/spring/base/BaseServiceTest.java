package com.zst.spring.base;

import com.zst.spring.service.CorporationService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/19 16:09
 * @description
 */
@SpringBootTest
public class BaseServiceTest {
    @Resource
    private CorporationService baseService;

    @Test
    public void handleSerialNum() {
        String serialNum = baseService.handleSerialNum();
        System.out.println(serialNum);
    }
}