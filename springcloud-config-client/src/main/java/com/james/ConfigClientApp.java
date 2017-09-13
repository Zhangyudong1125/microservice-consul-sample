/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author militang
 * @version Id: App.java, v 0.1 17/9/1 上午11:17 militang Exp $$
 */
@EnableScheduling
@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
public class ConfigClientApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigClientApp.class).web(true).run(args);
    }
}