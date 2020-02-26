package com.zst.spring.controller;

import com.zst.spring.base.BaseResponse;
import com.zst.spring.base.BasePageRequest;
import com.zst.spring.service.CorporationService;
import com.zst.spring.vo.request.CorpListRequest;
import com.zst.spring.vo.response.CorporationResponse;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/10 15:30
 * @description 合作企业
 */
@Api(tags = "合作企业-corporation")
@RestController
@RequestMapping("/corporation")
public class CorporationController {

    /**
     * @Api 描述整个类的分类
     * @ApiOperation 描述方法
     * @ApiResponses 在默认response基础上添加新Response说明
     * @ApiImplicitParam(name = "id",value = "用户ID",dataType = "int",paramType = "path") 在方法上描述接口参数，需要指定paramType，paramType 有五个可选值 ： path, query, body, header, form
     * @ApiImplicitParams({
     * @ApiImplicitParam(name = "id",value = "用户ID",paramType = "path",dataType = "int"),
     * @ApiImplicitParam(name = "userName",value = "用户名称",paramType = "form",dataType = "string")
     * }) 描述多个参数
     */

    @Resource
    private CorporationService corporationService;

    @PostMapping("/list")
    @ApiOperation(value = "合作企业列表", notes = "获取合作企业列表")
    public BaseResponse<List<CorporationResponse>> list(@RequestBody BasePageRequest<CorpListRequest> request) {
        return corporationService.findAll(request);
    }

    @GetMapping("/detail")
    @ApiOperation(value = "获取企业详情", notes = "根据id获取企业详情")
    @ApiImplicitParam(name = "id", value = "企业ID", dataType = "short", paramType = "query")
    public BaseResponse<CorporationResponse> detail(Short id) {
        return corporationService.findById(id);
    }
}
