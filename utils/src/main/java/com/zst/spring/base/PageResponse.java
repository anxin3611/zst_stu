package com.zst.spring.base;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/2/24 15:06
 * @description ：基础分页响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class PageResponse<T> extends BaseResponse<T> {
    private static final long serialVersionUID = -944317993572718224L;
    /**
     * 当前页
     */
    protected int currentPage;
    /**
     * 每页条数
     */
    protected int pageSize;
    /**
     * 总页数
     */
    protected int totalPage;
    /**
     * 当前页条数
     */
    protected int count;
}
