package com.james.easyrule;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.james.RuleEngineApp;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(classes = { RuleEngineApp.class }, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestBase {

    @Before
    public void init() {
        System.setProperty("server.port", "8071");
        System.setProperty("service.tag", "local");

    }

    static {

    }

}
