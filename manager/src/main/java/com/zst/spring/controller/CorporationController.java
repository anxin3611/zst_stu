package com.zst.spring.controller;

import com.zst.spring.domain.CorporationDO;
import com.zst.spring.service.CorporationService;
import com.zst.spring.base.BaseResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/10 15:30
 * @description
 */
@RestController
@RequestMapping("/corporation")
public class CorporationController {
    @Resource
    private CorporationService corporationService;

    @GetMapping("/list")
    public BaseResponse<List<CorporationDO>> list() {
        return corporationService.findAll();
    }
}
