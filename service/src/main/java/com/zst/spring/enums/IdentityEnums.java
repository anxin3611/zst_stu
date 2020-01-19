package com.zst.spring.enums;

/**
 * @author Item233
 * @version 1.0
 * @date 2020/1/16 11:43
 * @description 序列号相关枚举值
 */
public enum IdentityEnums {

    ;

    public enum AliasEnums {
        /**
         * 请求序列号
         */
        SERIAL_NUM("requestSerialNum", "请求序列号"),
        ;

        public String alias;
        private String desc;

        AliasEnums(String alias, String desc) {
            this.alias = alias;
            this.desc = desc;
        }
    }

    public enum GetTypeEnums {
        /**
         * UUID
         */
        UUID(0, "UUID去除-的字符串"),
        /**
         * 每日生成，序列号的日期会每日变化，序号会从1开始重新生成
         */
        EVERY_DAY(1, "每日生成"),
        ;

        public int code;
        public String desc;

        GetTypeEnums(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }
    }

    public enum DateTypeEnums {
        /**
         * 包含年月日时分秒
         */
        FULL_DATE("{yyyy}{MM}{DD}{HH}{II}{SS}"),
        ;

        public String dateType;

        DateTypeEnums(String dateType) {
            this.dateType = dateType;
        }
    }
}
