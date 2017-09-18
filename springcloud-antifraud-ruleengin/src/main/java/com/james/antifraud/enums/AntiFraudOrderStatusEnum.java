/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraud.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author militang
 * @version Id: AntiFraudTypeEnum.java, v 0.1 17/5/16 下午5:50 militang Exp $$
 */
public enum AntiFraudOrderStatusEnum {

                                      INIT("初始化"),

                                      PASS("通过"),

                                      FAIL("失败"),

    ;

    @Getter
    @Setter
    private String desc;

    AntiFraudOrderStatusEnum(String desc) {
        this.desc = desc;
    }

    public static String explain(String desc) {
        for (AntiFraudOrderStatusEnum bussErrorCode : AntiFraudOrderStatusEnum.values()) {
            if (bussErrorCode.getDesc().equals(desc)) {
                return bussErrorCode.getDesc();
            }
        }
        return desc;
    }

}