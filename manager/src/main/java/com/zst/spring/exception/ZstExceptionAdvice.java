package com.zst.spring.exception;

import com.zst.spring.util.base.BaseResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/15 13:51
 * @description
 * 注解ControllerAdvice  拦截异常并统一处理
 * 注解RestControllerAdvice 拦截异常并统一处理
 */
@ControllerAdvice
public class ZstExceptionAdvice {

    @ExceptionHandler(ZstRuntimeException.class)
    public BaseResponse<Void> handleZstException(ZstRuntimeException e) {
        return BaseResponse.error(e.getSerialNum(), e.getCode(), e.getMessage());
    }
}