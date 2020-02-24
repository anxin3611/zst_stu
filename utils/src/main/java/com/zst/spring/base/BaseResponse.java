package com.zst.spring.base;

import com.zst.spring.enums.ResponseCodeEnum;
import lombok.*;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/15 11:53
 * @description 基础响应类
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class BaseResponse<T> extends BaseObject {
    private static final long serialVersionUID = 8978959524864295796L;
    private static ResponseCodeEnum DEFAULT_RESPONSE_ENUM = ResponseCodeEnum.SUCCESS;
    /**
     * 序列号
     */
    @NonNull
    protected String serialNum;
    /**
     * 响应状态码
     */
    @NonNull
    protected Integer code;
    /**
     * 响应消息
     */
    @NonNull
    protected String msg;
    /**
     * 响应数据
     */
    protected T data;

    public static BaseResponse sucess(String serialNum) {
        return new BaseResponse(serialNum, DEFAULT_RESPONSE_ENUM.code, DEFAULT_RESPONSE_ENUM.msg);
    }

    public static <T> BaseResponse<T> sucess(String serialNum, T data) {
        return new BaseResponse(serialNum, DEFAULT_RESPONSE_ENUM.code, DEFAULT_RESPONSE_ENUM.msg, data);
    }

    public static BaseResponse error(String serialNum, ResponseCodeEnum responseCode) {
        return new BaseResponse(serialNum, responseCode.code, responseCode.msg);
    }

    public static BaseResponse error(String serialNum, Integer code, String msg) {
        return new BaseResponse(serialNum, code, msg);
    }

}
