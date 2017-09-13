/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.controller;

/*import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;*/


import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author militang
 * @version Id: HelloController.java, v 0.1 17/8/25 下午6:00 militang Exp $$
 */

@RestController
@RefreshScope
public class ConfigController {




    @RequestMapping("/confighello")
    public String from() {
        return "this is configServerhello";
    }
}
