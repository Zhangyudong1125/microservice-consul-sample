/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author militang
 * @version Id: ConfigServerApp.java, v 0.1 17/9/4 上午10:31 militang Exp $$
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigServerStartApp {
    public static void main(String[] args) {
        new SpringApplicationBuilder(ConfigServerStartApp.class).web(true).run(args);
    }
}
