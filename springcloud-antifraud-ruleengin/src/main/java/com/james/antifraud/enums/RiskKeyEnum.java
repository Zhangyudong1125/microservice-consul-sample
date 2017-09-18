package com.james.antifraud.enums;

import lombok.Getter;

/**
 * 获取认证项组件名称Enum
 * JamesTang
 */
public enum RiskKeyEnum {

    DEFAULT_AUTHGETID("DEFAULT_AUTHGETID", "默认获取认证键"),

    VJ_FUNDCREDIT_AUTHGETID("VJ_FUNDCREDIT_AUTHGETID", "蔚捷公积金贷取认证键");

    /**
     * @param code 名称
     * @return 枚举类
     */
    public static RiskKeyEnum explain(String code) {

        for (RiskKeyEnum accountAttributeEnum : RiskKeyEnum.values()) {
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

    RiskKeyEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
