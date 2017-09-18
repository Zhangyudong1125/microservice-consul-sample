/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraud.dto.ruleresdto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author militang
 * @version Id: RiskRuleResDto.java, v 0.1 17/9/18 下午3:48 militang Exp $$
 */
@Data
public class RiskRuleResDto implements Serializable {
    private String ruleid;

    private String ruledesc;
}