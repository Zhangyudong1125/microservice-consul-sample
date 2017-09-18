package com.james.antifraudrule.enums;

/**
 * Created with IntelliJ IDEA
 * User: JamesTang
 * Date: 2017/3/23
 * Time: 19:35
 */
public enum LimitChangeEnum {
    NOTIFY_AMOUNT("APPLY", "授信额度"),
    INCREASE_AMOUNT("INCREASE", "提额"),
    WITHDRAW("WITHDRAW", "提现"),
    CONSUME("CONSUME", "消费"),
    CANCEL("CANCEL", "撤销"),
    REFUND("REFUND", "退款"),
    MANUAL_REPAY("MANUAL_REPAY", "主动还款"),
    AUTO_REPAY("AUTO_REPAY", "自动还款");

    String key;
    String value;

    private LimitChangeEnum(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }
}