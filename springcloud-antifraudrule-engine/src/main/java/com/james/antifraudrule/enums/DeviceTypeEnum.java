package com.james.antifraudrule.enums;

import lombok.Getter;

/**
 * <br/>
 * Created on 2016/8/4 16:11.
 */
@Getter
public enum DeviceTypeEnum {
    DEVICE_ANDROID("android", "android设备"),
    DEVICE_IOS("ios", "ios设备");

    private String code;
    private String desc;

    private DeviceTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
