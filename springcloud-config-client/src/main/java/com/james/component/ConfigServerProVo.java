/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.component;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.config.client.ConfigClientProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author militang
 * @version Id: ConfigServerProVo.java, v 0.1 17/9/13 下午10:23 militang Exp $$
 */
@Data
public class ConfigServerProVo {
    public static final String  PREFIX       = "spring.cloud.config";
    public static final String  TOKEN_HEADER = "X-Config-Token";
    public static final String  STATE_HEADER = "X-Config-State";
    private boolean             enabled      = true;
    private String              profile      = "default";
    @Value("${spring.application.name:application}")
    private String              name;
    private String              label;
    private String              username;
    private String              password;
    private String              uri          = "http://localhost:8888";
    private String              token;
    private String              authorization;
    private Map<String, String> headers      = new HashMap();
}