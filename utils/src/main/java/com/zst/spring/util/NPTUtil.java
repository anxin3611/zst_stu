/*
 * Copyright (c) 2020-2030 Sishun.Co.Ltd. All Rights Reserved.
 */

package com.zst.spring.util;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * @author ：weiwei
 * @version ：V1.0
 * @program ：vevor-bmp
 * @date ：Created in 2020/1/24 11:55 上午
 * @description ：object NPT check utils
 */
public class NPTUtil {
    /**
     * 字符串是否可以用
     *
     * @param str String
     * @return true:不为空可以使用  false:null或者空或者全是空格，无法使用
     */
    public static boolean isStringUsable(String str) {
        return !(StringUtils.isEmpty(str) || StringUtils.isBlank(str));
    }

    /**
     * 对象数组是否有用
     *
     * @param array Object[] 类型
     * @return true:不为空可以使用  false:null或者空或者长度为0，无用
     */
    public static boolean isArrayUsable(Object[] array) {
        if (ObjectUtils.isEmpty(array)) {
            return false;
        } else {
            int length = array.length;
            int nullNo = 0;
            for (Object val : array) {
                if (val == null) {
                    nullNo++;
                }
            }
            return nullNo != length;
        }
    }
}
