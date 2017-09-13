/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author militang
 * @version Id: App.java, v 0.1 17/9/1 上午11:17 militang Exp $$
 */
@EnableScheduling
@SpringBootApplication

public class RuleEngineApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(RuleEngineApp.class).web(true).run(args);
    }
}