package com.zst.spring.base;

import com.zst.spring.enums.ResponseCodeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/2/24 15:06
 * @description ：基础分页响应
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BasePageResponse<T, E> extends BaseResponse<T> {
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
    /**
     * 总条数
     */
    protected Long total;

    public BaseResponse<T> response(T bean, BasePageRequest<E> request, Long total, Integer count) {
        this.setData(bean);
        this.setCode(ResponseCodeEnum.SUCCESS.code);
        this.setMsg(ResponseCodeEnum.SUCCESS.msg);
        this.setSerialNum(request.getSerialNum());
        this.setCurrentPage(request.getCurrentPage());
        this.setPageSize(request.getPageSize());
        this.setTotal(total);
        this.setCount(count);
        return this;
    }
}
