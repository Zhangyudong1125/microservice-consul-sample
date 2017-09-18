/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraudrule.dto.antifraudbizreqdto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author militang
 * @version Id: AntiFraudObj.java, v 0.1 17/5/16 下午10:25 militang Exp $$
 */

@Data
public class AntiFraudObj implements Serializable {

    private Actor         actor;

    private Bizinfo       bizinfo;

    private Location      location;

    private ExtraAuthInfo extraauthinfo;

    private String        actiontime;
}