package com.zst.spring.vo.request;

import com.zst.spring.base.BaseObject;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/2/26 14:19
 * @description ：获取企业列表的请求类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CorpListRequest extends BaseObject {
    private static final long serialVersionUID = -7715684132936564342L;
    @ApiModelProperty(name = "企业ID")
    private Short corpId;
}
