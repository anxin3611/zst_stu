package com.zst.spring.util.sign;

import cn.hutool.core.codec.Base64;
import com.zst.spring.enums.SignEnum;
import com.zst.spring.util.SecurityUtils;

import java.security.KeyPair;
import java.security.Signature;
import java.security.interfaces.ECPrivateKey;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/3/3 15:39
 * @description ：RSA加密方式工具类
 */
public class EcdsaUtil {

    /**
     * 密钥长度
     */
    public static int KEY_SIZE_512 = 512;
    /**
     * 摘要
     */
    public static String STR_MSG = "hold on";

    public static void plantWide(SignEnum signEnum) throws Exception {
        // 1. 初始化密钥
        KeyPair keyPair = SecurityUtils.initKey(signEnum.keyFactory);

        // 2. 签名
        byte[] sign = sign(STR_MSG, (ECPrivateKey) keyPair.getPrivate(), signEnum);

        // 数字签名格式转换，以便报文传输用
        String signStr = Base64.encode(sign);
        System.out.println("privateKey : " + signStr);

        // 公钥字符串，提供给对方验签
        String publicKeyStr = Base64.encode(keyPair.getPublic().getEncoded());
        System.out.println("publicKey : " + publicKeyStr);

        // 3. 验证签名
        boolean verify = verify(Base64.decode(signStr), Base64.decode(publicKeyStr), signEnum);
        System.out.println(verify);
    }

    /**
     * 2.执行签名，私钥签名
     *
     * @param data          签名数据
     * @param rsaPrivateKey rsaPrivateKey
     * @param signEnum      签名的参数
     * @return byte[] 签名后二进制的数据
     */
    public static byte[] sign(String data, ECPrivateKey rsaPrivateKey, SignEnum signEnum) throws Exception {
        Signature signature = Signature.getInstance(signEnum.signType);
        signature.initSign(SecurityUtils.getPrivateKey(rsaPrivateKey.getEncoded(), signEnum));
        signature.update(STR_MSG.getBytes());

        return signature.sign();
    }

    /**
     * 公钥验证签名，摘要+签名串+公钥
     *
     * @param sign         签名
     * @param rsaPublicKey 公钥
     * @param signEnum     签名参数
     * @return boolean 验证结果
     */
    public static boolean verify(byte[] sign, byte[] rsaPublicKey, SignEnum signEnum) throws Exception {
        Signature signature = Signature.getInstance(signEnum.signType);
        signature.initVerify(SecurityUtils.getPublicKey(rsaPublicKey, signEnum));
        signature.update(STR_MSG.getBytes());

        return signature.verify(sign);
    }

}
