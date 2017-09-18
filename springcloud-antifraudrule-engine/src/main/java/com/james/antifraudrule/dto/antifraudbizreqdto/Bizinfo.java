/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraudrule.dto.antifraudbizreqdto;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

//import com.moxie.commons.annotation.JsonMosaic;
//import com.moxie.commons.annotation.JsonMosaic;

/**
 * @author militang
 * @version Id: Bizinfo.java, v 0.1 17/5/16 下午10:25 militang Exp $$
 */
@Data
@ApiModel(value = "业务信息")
public class Bizinfo implements Serializable {
    @ApiModelProperty(value = "业务流水号")
    private String  bizSeq;
    @ApiModelProperty(value = "机构号")
    private String  orgCode;
    @ApiModelProperty(value = "产品号")
    private String  productCode;
    // @JsonMosaic(start = 6,length = 8)
    @ApiModelProperty(value = "银行卡号")
    private String  bankCardNo;
    @ApiModelProperty(value = "银行卡绑定手机号")
    private String  bankCardPhone;
    @ApiModelProperty(value = "申请金额")
    private Integer applyAmt;
    @ApiModelProperty(value = "事件类型")
    private String  action;
}