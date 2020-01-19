package com.zst.spring.service;

import com.zst.spring.enums.IdentityEnums;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/19 9:26
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class IdentityServiceTest {
    @Resource
    private IdentityService identityService;

    @Test
    public void serialNum() {
        String serialNum = identityService.serialNum(IdentityEnums.AliasEnums.SERIAL_NUM);
        System.out.println(serialNum);
    }
}