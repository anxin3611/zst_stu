package com.zst.spring.controller;

import com.zst.spring.domain.CorporationDO;
import com.zst.spring.service.CorporationService;
import com.zst.spring.util.base.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ZST
 * @version 1.0
 * @date 2020/1/10 15:30
 * @description
 */
@RestController
public class CorporationController {

    @Resource
    private CorporationService corporationService;

    @GetMapping("/test")
    public BaseResponse test() {
        return BaseResponse.SUCCESS("");
    }
}
