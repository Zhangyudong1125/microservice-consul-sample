package com.james.listener;

import java.util.Properties;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;
import com.james.component.GenConfigServerProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.config.environment.Environment;

/*import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Base64Utils;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.kv.model.GetValue;*/
/*import moxie.cloud.service.common.constants.BaseConst;
import moxie.cloud.service.common.constants.BasePropKeys;*/

@Slf4j
public class LogbackContextListener extends ContextAwareBase
                                    implements LoggerContextListener, LifeCycle {
    private boolean started = false;

    @Override
    public void start() {
        if (started) {
            return;
        }

        //将BASE_LOG_PATH添加到log上下文
        String serviceName;
        Properties bootStrapProps = new Properties();

        GenConfigServerProperties genConfigServerProperties = new GenConfigServerProperties();

        Environment environment = genConfigServerProperties.getCommonProperties();
        /* try {
            bootStrapProps.load(this.getClass().getClassLoader().getResourceAsStream("bootstrap.properties"));
            serviceName = bootStrapProps.getProperty(BasePropKeys.SERVICE_NAME);
            getContext().putProperty(BaseConst.LOG_PATH, "/data/appLogs/" + serviceName);
        } catch (Throwable e) {
            log.error("加载本地bootstrap.properties异常", e);
            return;
        }
        
        //加载common及application配置
        String serviceTag = System.getProperty(BasePropKeys.SERVICE_TAG);
        Properties props = new Properties();
        if (serviceTag != null && !serviceTag.equalsIgnoreCase("local")) {
            ConsulClient consulClient = new ConsulClient();
        
            String consulKey = "common/" + serviceTag + "/config";
            loadPropertyFromConsul(consulClient, consulKey, props);
            //将common配置设置到System属性中
            props.entrySet().forEach(prop -> System.setProperty((String) prop.getKey(), (String) prop.getValue()));
        
            consulKey = "service/" + serviceName + "/" + serviceTag + "/config";
            loadPropertyFromConsul(consulClient, consulKey, props);
        } else {
            try {
                props.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
            } catch (Throwable e) {
                log.error("加载本地application.properties异常", e);
            }
        }*/

        //将props配置添加到log上下文
        environment.getPropertySources().get(0).getSource().entrySet().forEach(
            prop -> getContext().putProperty((String) prop.getKey(), (String) prop.getValue()));
        //props.entrySet().forEach(prop -> getContext().putProperty((String) prop.getKey(), (String) prop.getValue()));
        //props.clear();

        started = true;
    }

    /*private void loadPropertyFromConsul(ConsulClient consulClient, String consulKey, Properties props) {
        GetValue value;
        if (StringUtils.isBlank(System.getProperty("consul.token"))) {
            value = consulClient.getKVValue(consulKey).getValue();
        } else {
            value = consulClient.getKVValue(consulKey, System.getProperty("consul.token")).getValue();
        }
    
        if (value != null) {
            try {
                props.load(new StringReader(new String(Base64Utils.decodeFromString(value.getValue()), "UTF-8")));
            } catch (Throwable e) {
                log.error("加载consul key:common/log/config配置异常", e);
            }
        } else {
            log.error("无法从consul找到key:{}", consulKey);
        }
    }*/

    @Override
    public void stop() {
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    @Override
    public boolean isResetResistant() {
        return true;
    }

    @Override
    public void onStart(LoggerContext context) {
    }

    @Override
    public void onReset(LoggerContext context) {
    }

    @Override
    public void onStop(LoggerContext context) {
    }

    @Override
    public void onLevelChange(Logger logger, Level level) {
    }
}
