/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraud.dto.antifraudbizreqdto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author militang
 * @version Id: Location.java, v 0.1 17/5/16 下午10:28 militang Exp $$
 */
@Data
@ApiModel(value = "位置信息")
public class Location implements Serializable {
    @ApiModelProperty(value = "经度")
    private String longitude;
    @ApiModelProperty(value = "纬度")
    private String latitude;
    @ApiModelProperty(value = "GPS描述")
    private String gpsDesc;
    @ApiModelProperty(value = "城市")
    private String city;
    @ApiModelProperty(value = "省份")
    private String province;
    @ApiModelProperty(value = "IP地址")
    private String ip;
    @ApiModelProperty(value = "操作系统类型")
    private String os;
    @ApiModelProperty(value = "设备指纹")
    private String deveiceFingerprint;
    @ApiModelProperty(value = "wifi信息")
    private String wifiInfo;
    @ApiModelProperty(value = "渠道")
    private String channel;

}