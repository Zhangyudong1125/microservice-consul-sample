/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraudrule.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author militang
 * @version Id: AntiFraudTypeEnum.java, v 0.1 17/5/16 下午5:50 militang Exp $$
 */
public enum AntiFraudTypeEnum {

    LOGIN_EVENT("登录事件", "PAY.LOGIN"),

    AUTHAPPLY_EVENT("授信申请", "PAY.APPLY"),

    REG_EVENT("注册事件", "PAY.REG"),

    AUTH_EVENT_NO_FEE("授权事件免费","PAY.AUTH1"),
    AUTH_EVENT_FEE("授权事件收费","PAY.AUTH2"),

    ACTIVE_EVENT("激活事件", ""),

    WITHDRAW_EVENT("提现事件", "PAY.WITHDRAW"),

    PROMOTE_EVENT("提额事件", "PAY.ADDLIMIT"),

    DYAMICCODE_SEND("动码发送", "PAY.SMS"),;

    @Getter
    @Setter
    private String desc;
    @Getter
    @Setter
    private String ename;

    AntiFraudTypeEnum(String desc, String ename) {
        this.desc = desc;
        this.ename = ename;
    }

    public static AntiFraudTypeEnum explainAntiFraudType(String ename) {
        for (AntiFraudTypeEnum fraudTypeEnum : AntiFraudTypeEnum.values()) {
            if (fraudTypeEnum.ename.equals(ename)) {
                return fraudTypeEnum;
            }
        }
        return null;
    }

    public static String explainRiskEventName(String fraudTypeName) {
        for (AntiFraudTypeEnum antiFraudTypeEnum : AntiFraudTypeEnum.values()) {
            if (antiFraudTypeEnum.name().equals(fraudTypeName)) {
                return antiFraudTypeEnum.ename;
            }
        }
        return null;
    }

    public static AntiFraudTypeEnum explainRiskEventEnum(String fraudTypeName) {
        for (AntiFraudTypeEnum antiFraudTypeEnum : AntiFraudTypeEnum.values()) {
            if (antiFraudTypeEnum.name().equals(fraudTypeName)) {
                return antiFraudTypeEnum;
            }
        }
        return null;
    }

    public static String explain(String desc) {
        for (AntiFraudTypeEnum bussErrorCode : AntiFraudTypeEnum.values()) {
            if (bussErrorCode.getDesc().equals(desc)) {
                return bussErrorCode.getDesc();
            }
        }
        return desc;
    }


}