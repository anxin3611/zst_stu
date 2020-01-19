package com.zst.spring.service;

import com.zst.spring.base.BaseResponse;
import com.zst.spring.domain.UserDO;
import com.zst.spring.util.SecurityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/19 14:08
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceTest {
    @Resource
    private UserService userService;
    @Test
    public void save() {
        UserDO userDO = new UserDO();
        userDO.setUsername("admin");
        String password = "zst0213@";
        String salt = SecurityUtils.generateSalt();
        // 生成salt, UUID 去掉-
        userDO.setSalt(salt);
        String securityPassword = SecurityUtils.generatePassword(password, salt);
        System.out.println(securityPassword);
        userDO.setPassword(securityPassword);
        BaseResponse<UserDO> save = userService.save(userDO);
        UserDO data = save.getData();
        System.out.println(data.toString());
    }
}