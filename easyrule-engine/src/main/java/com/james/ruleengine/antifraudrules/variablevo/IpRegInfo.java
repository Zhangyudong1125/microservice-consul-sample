/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.ruleengine.antifraudrules.variablevo;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;

/**
 * @author militang
 * @version Id: IpRegInfo.java, v 0.1 17/9/15 下午6:07 militang Exp $$
 */
@Data
public class IpRegInfo {

    private Map<Integer, regedinof> ipreginfos = Maps.newHashMap();

    @Data
    class regedinof {
        int hours;
        int cnt;
    }
}