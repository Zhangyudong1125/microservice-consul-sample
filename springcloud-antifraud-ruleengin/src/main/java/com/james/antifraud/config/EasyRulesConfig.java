/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraud.config;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.RulesEngineBuilder;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;



/**
 * @author militang
 * @version Id: EasyRulesConfi g.java, v 0.1 17/9/11 下午3:42 militang Exp $$
 */
@Configuration
public class EasyRulesConfig {

    //    bean id="rule"class="samples.spring.DummyRule"scope="prototype"/>
    //    bean id="rule"class="samples.spring.DummyRule"scope="prototype"/>

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RulesEngine rullesEngine() {
        RulesEngine rulesEngine = RulesEngineBuilder.aNewRulesEngine().build();//.getObject();//  rulesEngineFactoryBean
      //  rulesEngine.registerRule(rule());
        return rulesEngine;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RulesEngine rulesEngine() {
        RulesEngine rulesEngine = RulesEngineBuilder.aNewRulesEngine().build();//.getObject();//  rulesEngineFactoryBean
        //rulesEngine.registerRule(rule());
        return rulesEngine;
    }

}