package com.zst.spring.base;

import lombok.Data;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/3/23 9:58
 * @description ：ebay getTokenByCode 错误信息
 */
@Data
public class ErrorMessage {
    private String error;

    private String errorDescription;
}
