package com.james.listener;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import lombok.extern.slf4j.Slf4j;

/**
 * JamesTang
 *
 * Sample for  SpringBoot Start
 */

@Slf4j
@Component
public class ApplicationReadyListener {
    @EventListener
    public void handleApplicationReadyEvent(ApplicationReadyEvent event) {
        ApplicationContext context = event.getApplicationContext();
        RequestMappingHandlerAdapter handlerAdapter = context.getBean(RequestMappingHandlerAdapter.class);
        List<HandlerMethodArgumentResolver> resolvers = new ArrayList<>();
       // resolvers.add(new RenamingProcessor(handlerAdapter));
        resolvers.addAll(handlerAdapter.getArgumentResolvers());
        handlerAdapter.setArgumentResolvers(resolvers);
/*        String serviceTag = BaseProps.serviceTag();
        if (serviceTag.equals(BaseConst.TAG_LOCAL)) {
            log.info("服务以本地模式启动...");
            return;
        }*/

/*        String serviceId = BaseProps.serviceId();
        log.info("服务以tag[{}]启动, serviceId={}, 将注册到consul...", BaseProps.serviceTag(), serviceId);*/
       /* ConsulClient consul = context.getBean(ConsulClient.class);
        try {
            NewService newService = new NewService();
            newService.setId(serviceId);
            newService.setName(BaseProps.serviceName());
            newService.setPort(BaseProps.serverPort());
            newService.setTags(Lists.newArrayList(BaseProps.serviceTag()));

            NewService.Check check = new NewService.Check();
            check.setHttp(BaseProps.consulCheckUrl());
            check.setInterval(BaseProps.consulCheckInterval() + "s");
            check.setDeregisterCriticalServiceAfter("12h"); // 12小时后清理critical状态的service
            newService.setCheck(check);

            consul.agentServiceRegister(newService);
            log.info("服务[{}]成功注册到consul...", BaseProps.serviceName());
        } catch (Throwable e) {
            log.error("服务注册consul过程异常", e);
        }*/
    }
}
