package com.zst.spring.base;

import lombok.extern.log4j.Log4j2;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/19 13:59
 * @description service 的基础类
 */
@Log4j2
public abstract class BaseService {

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
