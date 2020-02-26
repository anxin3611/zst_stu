package com.zst.spring.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/2/26 14:15
 * @description ：分页请求基类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageRequest<T> extends BaseRequest<T> {
    private static final long serialVersionUID = 1668006370102018034L;
    private Integer currentPage;
    private Integer pageSize;
}
