/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraud;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author militang
 * @version Id: App.java, v 0.1 17/9/18 下午2:39 militang Exp $$
 */

@SpringBootApplication
public class AntiFraudRuleEngineApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(AntiFraudRuleEngineApp.class).web(true).run(args);
    }
}