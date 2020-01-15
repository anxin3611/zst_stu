package com.zst.spring.util.enums;

/**
 * @author ZST
 * @version 1.0
 * @date 2020/1/15 13:05
 * @description 响应码枚举类
 */
public enum ResponseCodeEnum {
    /**
     * 响应成功
     */
    SUCCESS(200, "success"),
    ;

    public Integer code;
    public String msg;

    private ResponseCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
