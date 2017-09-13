/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.component;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

/**
 * @author militang
 * @version Id: RefreshConfigServerProperties.java, v 0.1 17/9/4 下午4:35 militang Exp $$
 */
@Component
@Slf4j
public class RefreshConfigServerProperties {

    @Autowired
    private RestTemplate restTemplate;

    //@Value()
    @Value("${server.port}")
    private String       svrport;

    //@Scheduled(cron = "5 * * * * ?")
    @Scheduled(fixedRate = 30000)
    public void propertrefresh() {
        log.info("job   excutting {}", DateTime.now().toString());
        String url = "http://localhost:" + svrport + "/refresh";
        ResponseEntity<String> resultstr = restTemplate.postForEntity(url, null, String.class);
        String resultstrinfo = resultstr.getBody();
        //    URI refreshrsult = restTemplate.postForLocation("http://localhost:" + svrport + "/refresh",
        //      null);
        log.info("resultstrinfo ={}", resultstrinfo);
    }

}