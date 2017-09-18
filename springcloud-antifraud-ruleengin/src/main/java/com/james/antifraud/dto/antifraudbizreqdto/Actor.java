/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraud.dto.antifraudbizreqdto;

import java.io.Serializable;

//import com.moxie.commons.annotation.JsonMosaic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author militang
 * @version Id: Actor.java, v 0.1 17/5/16 下午10:23 militang Exp $$
 */
@Data
@ApiModel(value = "行为人信息")
public class Actor implements Serializable {

    @ApiModelProperty(value = "客户号")
    private String contractNo;
    @ApiModelProperty(value = "手机号")
    private String mobilePhone;
    @ApiModelProperty(value = "身份证号")
    //@JsonMosaic(start = 6,length = 8)
    private String idNo;
    @ApiModelProperty(value = "公司名")
    private String companyName;
    @ApiModelProperty(value = "公司地址")
    private String companyAddress;
    @ApiModelProperty(value = "住址")
    private String userAddress;
    @ApiModelProperty(value = "紧急联系人1")
    private String contactPhone1;
    @ApiModelProperty(value = "紧急联系人2")
    private String contactPhone2;


}