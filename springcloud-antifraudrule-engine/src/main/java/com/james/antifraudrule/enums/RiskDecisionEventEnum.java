package com.james.antifraudrule.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by JamesTang on 17/3/3.
 */
public enum RiskDecisionEventEnum {

                                   WITHDRAW_EVENT("提现事件"),

                                   LOGIN_EVENT("登录事件"),

                                   AUTHAPPLY_EVENT("授信申请");

    @Getter
    @Setter
    private String desc;

    RiskDecisionEventEnum(String desc) {
        this.desc = desc;
    }

    public static RiskDecisionEventEnum explain(String eventName) {
        for (RiskDecisionEventEnum bussErrorCode : RiskDecisionEventEnum.values()) {
            if (eventName.equals(bussErrorCode.name())) {
                return bussErrorCode;
            }
        }
        return null;
    }
}
