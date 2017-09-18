package com.james.antifraud.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by JamesTang on 16/9/3.
 * 拒绝原因:1-信用风险，2-欺诈风险，3-主动取消，4-无法联系
 */
public enum BSRejectReasonEnum {

    CREDIT_RISK("1", "信用风险"),
    CHEAT_RISK("2", "欺诈风险"),
    CANCEL("3", "主动取消"),
    NOT_CONTACT("4", "无法联系"),

    ;

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String desc;

    BSRejectReasonEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String explain(String errorCode) {
        for (BSRejectReasonEnum bussErrorCode : BSRejectReasonEnum.values()) {
            if (errorCode.equals(bussErrorCode.getCode())) {
                return bussErrorCode.getDesc();
            }
        }
        return errorCode;
    }
}
