/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.robbinclient;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * @author militang
 * @version Id: LbClient.java, v 0.1 17/9/2 上午10:59 militang Exp $$
 */

@Slf4j
@Component
public class LbClient {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    public String geturl(String serviceName) {
        URI uri = getUrlString(serviceName);
        return uri.toString();
    }

    private URI getUrlString(String serviceName) {

        URI uri = null;
        try {
            ServiceInstance instance = loadBalancerClient.choose(serviceName);
            uri = instance.getUri();
            log.info(uri.toString());
        } catch (RuntimeException e) {
            log.error("can't find service {}", serviceName);
            // uri = URI.create(fallbackUri);
        }

        return uri;

    }

}