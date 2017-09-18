/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraud.dto.variablevo;

import java.util.Map;

import com.google.common.collect.Maps;

import lombok.Data;

/**
 * @author militang
 * @version Id: IpRegInfo.java, v 0.1 17/9/15 下午6:07 militang Exp $$
 */
@Data
public class IpRegInfo {

    private Map<Integer, Regedinof> ipreginfos = Maps.newHashMap();

    @Data
    public class Regedinof {
        int hours;
        int cnt;
    }
}