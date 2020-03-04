package com.zst.spring.enums;

/**
 * @author ：Zhaoshaoting
 * @version ：V1.0
 * @program ：my-spring2
 * @date ：Created in 2020/3/3 16:40
 * @description ：加密参数的枚举类
 */
public enum SignEnum {
    /**
     * RSA
     */
    RSA("RSA", "MD5withRSA"),
    /**
     * DSA
     */
    DSA("DSA", "SHA1withDSA"),
    /**
     * EC
     */
    EC("EC", "SHA1withECDSA"),
    ;

    public String keyFactory;
    public String signType;

    private SignEnum(String keyFactory, String signType) {
        this.keyFactory = keyFactory;
        this.signType = signType;
    }
}
