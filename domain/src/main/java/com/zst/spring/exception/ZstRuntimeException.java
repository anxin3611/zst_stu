package com.zst.spring.exception;

import com.zst.spring.enums.ResponseCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/15 13:58
 * @description 异常处理类
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ZstRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -6125463307805040568L;
    private String serialNum;
    private Integer code;
    private String message;

    public ZstRuntimeException(String serialNum, ResponseCodeEnum codeEnum, Object... params) {
        this.serialNum = serialNum;
        this.code = codeEnum.code;
        if (params == null){
            this.message = codeEnum.msg;
        } else {
            this.message = String.format(codeEnum.msg, params);
        }
    }
}
