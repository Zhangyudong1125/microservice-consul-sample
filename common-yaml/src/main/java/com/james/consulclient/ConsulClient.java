/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.consulclient;

import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.health.model.HealthService;

import java.util.List;

/**
 * @author militang
 * @version Id: ConsulClient.java, v 0.1 17/9/13 下午9:16 militang Exp $$
 */
public class ConsulClient {

    public static void main(String args[]) {
        //new YamlSample().yemltest();

        com.ecwid.consul.v1.ConsulClient client = new com.ecwid.consul.v1.ConsulClient(
            "http://localhost", 8500);

        Response<List<HealthService>> healthyServices = client
            .getHealthServices("springcloud-config-server", true, QueryParams.DEFAULT);

        HealthService healthService = healthyServices.getValue().get(0);

        String url = "http://" + healthService.getService().getAddress() + ":"
                     + healthService.getService().getPort();

        System.out.println(url);

    }
}