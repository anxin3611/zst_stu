package com.zst.spring.controller;

import com.zst.spring.base.BaseResponse;
import com.zst.spring.enums.IdentityEnums;
import com.zst.spring.enums.ResponseCodeEnum;
import com.zst.spring.service.IdentityService;
import com.zst.spring.vo.request.TestVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/3/9 19:34
 * @description ：测试的controller
 */
@RestController
public class TestController {
    @Resource
    private IdentityService identityService;

    @PostMapping("/test")
    public BaseResponse<String> test(TestVO vo) {
        return BaseResponse.sucess(identityService.serialNum(IdentityEnums.AliasEnums.SERIAL_NUM), vo.getCurrentUser());
    }

    @GetMapping("/test1")
    public BaseResponse<String> test1(TestVO vo) {
        return BaseResponse.sucess(identityService.serialNum(IdentityEnums.AliasEnums.SERIAL_NUM), vo.getCurrentUser());
    }

    @GetMapping("/ex")
    public BaseResponse testEx() {



        return BaseResponse.error(identityService.serialNum(IdentityEnums.AliasEnums.SERIAL_NUM), ResponseCodeEnum.ERROR_1000);
    }

    @PostMapping("/testFile")
    public BaseResponse<Object> testFile(MultipartFile[] files) {
        return BaseResponse.sucess(identityService.serialNum(IdentityEnums.AliasEnums.SERIAL_NUM), files.length);
    }
}
