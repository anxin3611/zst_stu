package com.zst.spring.util;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/19 15:08
 * @description 密码工具类
 */
public class SecurityUtils {

    /**
     * 根据密码和密码盐生成加密密码
     *
     * @param password 密码
     * @param salt     密码盐
     * @return java.lang.String
     */
    public static String generatePassword(String password, String salt) {
        if (StringUtils.isBlank(salt)) {
            salt = password.hashCode() + "";
        }
        return SecureUtil.md5(password + SecureUtil.md5(salt));
    }

    /**
     * 生成密码盐
     *
     * @return java.lang.String
     */
    public static String generateSalt() {
        return IdUtil.simpleUUID();
    }
}
