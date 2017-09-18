package com.james.antifraudrule.enums;

import lombok.Getter;

/**
 * 回调组件名称Enum
 */
public enum CallbackBizEnum {

                             DEFAULT_CALLBACK("DEFAULT_CALLBACK", "默认回调通知器"),

                             VJ_FUNDCREDIT_CALLBACK("VJ_FUNDCREDIT_CALLBACK", "蔚捷公积金贷回调");

    /**
     * @param code 名称
     * @return 枚举类
     */
    public static CallbackBizEnum explain(String code) {

        for (CallbackBizEnum accountAttributeEnum : CallbackBizEnum.values()) {
            if (accountAttributeEnum.code.equals(code)) {
                return accountAttributeEnum;
            }
        }
        return null;
    }

    @Getter
    private String code;
    @Getter
    private String desc;

    CallbackBizEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
