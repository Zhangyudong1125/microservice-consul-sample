/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package test.james.LbClientTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.james.robbinclient.LbClient;

import lombok.extern.slf4j.Slf4j;
import test.james.base.TestBase;

/**
 * @author militang
 * @version Id: LbClientServiceTest.java, v 0.1 17/9/2 上午11:09 militang Exp $$
 */
@Slf4j
public class LbClientServiceTest extends TestBase {

    @Autowired
    private LbClient lbClient;

    /**
     *
     */
    @Test
    public void getConsulService() {
        String url = lbClient.geturl("account-query");
        log.info("account-core  url is {}", url);

    }

}