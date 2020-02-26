package com.zst.spring.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/2/26 14:13
 * @desc 请求基础类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseRequest<T> extends BaseObject {
    private static final long serialVersionUID = 8865537927584892075L;
    private String serialNum;
    private T bean;
}
