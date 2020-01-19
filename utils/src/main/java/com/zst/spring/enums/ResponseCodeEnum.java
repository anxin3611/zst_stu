package com.zst.spring.enums;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/15 13:05
 * @description 响应码枚举类
 */
public enum ResponseCodeEnum {
    /**
     * 响应成功
     */
    SUCCESS(200, "success"),
    ERROR_1001(1001, "s% 获取失败"),
    ERROR_2001(2001, "s% 保存失败"),
    ;

    public Integer code;
    public String msg;

    private ResponseCodeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
