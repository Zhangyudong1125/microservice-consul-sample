/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.ruleengine.antifraudrules.abs;

import lombok.Data;

import java.util.Map;

/**
 * @author militang
 * @version Id: AbsAntiFraudRules.java, v 0.1 17/9/15 下午5:22 militang Exp $$
 */
@Data
public class AbsAntiFraudRules {

    /**
     * 风控探针的参数传入
     */
    protected Map<String, String> params;

    protected Object variable;



}