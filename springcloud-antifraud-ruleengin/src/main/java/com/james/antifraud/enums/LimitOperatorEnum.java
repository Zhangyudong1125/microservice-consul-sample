/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2016 All Rights Reserved.
 */
package com.james.antifraud.enums;

import lombok.Getter;

/**
 * @author militang
 * @version Id: LimitOperatorEnum.java, v 0.1 16/11/3 上午10:03 militang Exp $$
 */
public enum LimitOperatorEnum {
    LIMIT_NEWAPPLY("LIMIT_NEWAPPLY", "额度申请"),

    LIMIT_PROMOTE("LIMIT_PROMOTE", "提升额度"),

    LIMIT_AGAIN_APPLY("LIMIT_AGAIN_APPLY", "二次授信"),

    PRE_ACTIVATION("PRE_ACTIVATION", "额度预激活"),

    CASH_ACTIVATION("CASH_ACTIVATION", "现金贷额度激活"),

    CASH_LOAN("CASH_LOAN", "现金贷贷款申请"),

    CONSUME_APPLY("CONSUME_APPLY","消费分期申请"),

    RECOVER_LIMIT("RECOVER_LIMIT", "恢复额度"),

    LIMIT_RESET("LIMIT_RESET", "恢复重置"),

    REFRESH_LIMIT("REFRESH_LIMIT", "刷新额度"),
    ACCUMULATE_LIMIT("ACCUMULATE_LIMIT","累加额度"),

    USE_LIMIT("USE_LIMIT", "使用额度");

    @Getter
    String key;
    @Getter
    String value;

    LimitOperatorEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public static LimitOperatorEnum getEnumByEgname(String egname) {
        for (LimitOperatorEnum e : LimitOperatorEnum.values()) {
            if (e.key.equals(egname)) {
                return e;
            }
        }
        return null;
    }
}