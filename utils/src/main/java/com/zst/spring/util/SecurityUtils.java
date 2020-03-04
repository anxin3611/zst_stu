package com.zst.spring.util;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.zst.spring.Constants;
import com.zst.spring.enums.SignEnum;
import org.apache.commons.lang3.StringUtils;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

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


    /**
     * 1.初始化密钥
     *
     * @param keyFactory 签名类型
     * @return java.security.KeyPair
     */
    public static KeyPair initKey(String keyFactory) throws NoSuchAlgorithmException {
        KeyPairGenerator rsa = KeyPairGenerator.getInstance(keyFactory);
        rsa.initialize(Constants.KEY_SIZE_512);
        return rsa.genKeyPair();
    }

    /**
     * 获取 PKCS8EncodedKeySpec
     *
     * @param encoded 私钥字节码
     * @return java.security.spec.PKCS8EncodedKeySpec
     */
    public static PrivateKey getPrivateKey(byte[] encoded, SignEnum signEnum) throws Exception {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(encoded);

        KeyFactory keyFactory = KeyFactory.getInstance(signEnum.keyFactory);
        return keyFactory.generatePrivate(pkcs8EncodedKeySpec);
    }

    /**
     * 获取公钥
     *
     * @param rsaPublicKey
     * @param signEnum
     * @return java.security.PublicKey
     */
    public static PublicKey getPublicKey(byte[] rsaPublicKey, SignEnum signEnum) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey);

        KeyFactory keyFactory = KeyFactory.getInstance(signEnum.keyFactory);
        return keyFactory.generatePublic(x509EncodedKeySpec);
    }


}
