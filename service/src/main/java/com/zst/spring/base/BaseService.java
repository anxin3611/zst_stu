package com.zst.spring.base;

import com.zst.spring.enums.IdentityEnums;
import com.zst.spring.enums.ResponseCodeEnum;
import com.zst.spring.exception.ZstRuntimeException;
import com.zst.spring.service.IdentityService;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/19 13:59
 * @description service 的基础类
 */
@Log4j2
@Component
public abstract class BaseService {
    @Resource
    private IdentityService identityService;

    /**
     * 生成请求序列号
     *
     * @param
     * @return java.lang.String
     */
    protected String handleSerialNum() {
        String serialNum = null;
        try {
            serialNum = identityService.serialNum(IdentityEnums.AliasEnums.SERIAL_NUM);
        } catch (Exception e) {
            throw new ZstRuntimeException(null, ResponseCodeEnum.ERROR_1000);
        }
        return serialNum;
    }

    /**
     * 处理异常日志
     *
     * @param e 异常
     * @return void
     */
    protected void handleEx(Exception e) {
        e.printStackTrace();
        log.error(e);
    }
}
