package com.james.antifraud.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by JamesTang on 16/9/3.
 * rejectReasonDetail：拒绝原因明细 (01-涉嫌大额套现，02-资金用途不良，03-分期债务过高，04-信用记录不良；
 * 11-不是本人申请，12-隐藏卡片数量，13-频繁申请卡片，14-消费无理攀升，15-涉及敏感消费，16-涉及司法诉讼；
 * 21-申请时通过APP取消，22-电审中要求取消，23-费用过高取消，24-申请流程复杂取消；
 * 31-手机无人接听，32-手机关机，33-手机停机，34-手机非本人)
 */
public enum BSRejectDetailEnum {

    CREDIT_RISK1("01", "涉嫌大额套现"),
    CREDIT_RISK2("02", "资金用途不良"),
    CREDIT_RISK3("03", "分期债务过高"),
    CREDIT_RISK4("04", "信用记录不良"),


    CHEAT_RISK1("11", "不是本人申请"),
    CHEAT_RISK2("12", "隐藏卡片数量"),
    CHEAT_RISK3("13", "频繁申请卡片"),
    CHEAT_RISK4("14", "消费无理攀升"),
    CHEAT_RISK5("15", "涉及敏感消费"),
    CHEAT_RISK6("16", "涉及司法诉讼"),

    CANCEL1("21", "申请时通过APP取消"),
    CANCEL2("22", "电审中要求取消"),
    CANCEL3("23", "费用过高取消"),
    CANCEL4("23", "申请流程复杂取消"),

    NOT_CONTACT1("31", "手机无人接听"),
    NOT_CONTACT2("32", "手机关机"),
    NOT_CONTACT3("33", "手机停机"),
    NOT_CONTACT4("34", "手机非本人"),


            ;

    @Getter
    @Setter
    private String code;

    @Getter
    @Setter
    private String desc;

    BSRejectDetailEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String explain(String errorCode) {
        for (BSRejectDetailEnum bussErrorCode : BSRejectDetailEnum.values()) {
            if (bussErrorCode.getCode().equals(errorCode)) {
                return bussErrorCode.getDesc();
            }
        }
        return errorCode;
    }
}
