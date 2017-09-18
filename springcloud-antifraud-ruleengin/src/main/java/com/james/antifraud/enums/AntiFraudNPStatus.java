package com.james.antifraud.enums;

import lombok.Getter;

/**
 * Created by JamesTang on 17/3/16.
 */
@Getter
public enum AntiFraudNPStatus {

    //注册


    //登录
    LOGIN_DEVICE_EXCEPTION("LOGIN_1_UMID", "异常设备登录"),
    LOGIN_IP_EXCEPTION("LOGIN_2_IP", "异常IP登陆"),
    RISK_EXCEPTION_001("RISK_EXCEPTION_001","风控连接失败"),
    RISK_EXCEPTION_002("RISK_EXCEPTION_002","风控处理异常");

    private String code;
    private String desc;

    AntiFraudNPStatus(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String explainRiskEventDesc(String code) {
        for (AntiFraudNPStatus antiFraudNPStatus : AntiFraudNPStatus.values()) {
            if (antiFraudNPStatus.code.equals(code)) {
                return antiFraudNPStatus.desc;
            }
        }
        return null;
    }
}
