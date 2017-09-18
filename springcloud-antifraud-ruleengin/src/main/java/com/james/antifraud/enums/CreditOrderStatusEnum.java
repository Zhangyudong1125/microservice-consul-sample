package com.james.antifraud.enums;

import lombok.Getter;

/**
 * 状态枚举类
 */
public enum CreditOrderStatusEnum {

    SUCCESS("SUCCESS", "成功"),
    FAIL("FAIL", "失败"),
    APPROVE("APPROVE","审核中"),
    MAUNUAL_APPROVE("MAUNUAL_APPROVE","人工信审核中");

    @Getter
    String key;
    @Getter
    String value;

    CreditOrderStatusEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * @param code 名称
     * @return 枚举类
     */
    public static CreditOrderStatusEnum explain(String code) {

        for (CreditOrderStatusEnum accountAttributeEnum : CreditOrderStatusEnum.values()) {
            if (accountAttributeEnum.key.equals(code)) {
                return accountAttributeEnum;
            }
        }
        return null;
    }

}
