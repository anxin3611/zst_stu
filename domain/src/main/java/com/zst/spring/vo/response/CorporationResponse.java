package com.zst.spring.vo.response;

import com.zst.spring.base.BaseObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/2/26 13:17
 * @desc 企业相关响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(description = "企业信息响应")
public class CorporationResponse extends BaseObject {
    private static final long serialVersionUID = -175352292111458284L;
    @ApiModelProperty(value = "企业ID")
    private Short corpId;
    @ApiModelProperty(value = "企业名称")
    private String corpName;
}
