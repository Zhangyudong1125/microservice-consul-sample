package com.james.listener;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ApplicationEventListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof ApplicationEnvironmentPreparedEvent) {
           /* BaseProps.setEnvironment(((ApplicationEnvironmentPreparedEvent) applicationEvent).getEnvironment());
            String logCheck1 = ((LoggerContext) LoggerFactory.getILoggerFactory()).getProperty(BaseConst.LOG_CHECK + "1");
            String logCheck2 = ((LoggerContext) LoggerFactory.getILoggerFactory()).getProperty(BaseConst.LOG_CHECK + "2");
            if (StringUtils.isBlank(logCheck1) || StringUtils.isBlank(logCheck2)) {
                throw new RuntimeException("您的logback.xml配置文件不符合公司规范哦,亲");
            }*/
        }
    }
}
