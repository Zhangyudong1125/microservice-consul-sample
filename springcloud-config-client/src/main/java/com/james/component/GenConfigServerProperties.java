/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.component;

import java.util.*;

import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.health.model.HealthService;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.yaml.snakeyaml.Yaml;

/**
 * @author militang
 * @version Id: getConfigServerProperties.java, v 0.1 17/9/13 上午11:17 militang Exp $$
 */
@Component
public class GenConfigServerProperties {

    public Environment getCommonProperties() {
        ConfigServerProVo getconfigServerProVo =this.getconfigServerProVo();
        RestTemplate  restTemplate=getSecureRestTemplate(getconfigServerProVo);
        Environment  environment=  this.getRemoteEnvironment(restTemplate,getconfigServerProVo,getconfigServerProVo.getLabel(),null);
        return environment;
    }

    private RestTemplate getSecureRestTemplate(ConfigServerProVo client) {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setReadTimeout(185000);
        RestTemplate template = new RestTemplate(requestFactory);
        String username = client.getUsername();
        String password = client.getPassword();
        String authorization = client.getAuthorization();
        HashMap headers = new HashMap(client.getHeaders());
        if (password != null && authorization != null) {
            throw new IllegalStateException(
                "You must set either \'password\' or \'authorization\'");
        } else {
            if (password != null) {
                byte[] token = Base64Utils.encode((username + ":" + password).getBytes());
                headers.put("Authorization", "Basic " + new String(token));
            } else if (authorization != null) {
                headers.put("Authorization", authorization);
            }

            if (!headers.isEmpty()) {
                template.setInterceptors(Arrays.asList(
                    new ClientHttpRequestInterceptor[] { new ConfigServicePropertySourceLocator.GenericRequestHeaderInterceptor(
                        headers) }));
            }

            return template;
        }
    }

    private org.springframework.cloud.config.environment.Environment getRemoteEnvironment(RestTemplate restTemplate,
                                                                                          ConfigServerProVo properties,
                                                                                          String label,
                                                                                          String state) {
        String path = "/{name}/{profile}";
        String name = properties.getName();
        String profile = properties.getProfile();
        String token = properties.getToken();
        String uri = properties.getUri();
        String[] args = new String[] { name, profile };
        if (StringUtils.hasText(label)) {
            args = new String[] { name, profile, label };
            path = path + "/{label}";
        }

        ResponseEntity response = null;

        try {
            HttpHeaders result = new HttpHeaders();
            if (StringUtils.hasText(token)) {
                result.add(ConfigClientProperties.TOKEN_HEADER, token);
            }

            if (StringUtils.hasText(state)) {
                result.add(ConfigClientProperties.STATE_HEADER, state);
            }

            HttpEntity entity = new HttpEntity((Void) null, result);
            response = restTemplate.exchange(uri + path, HttpMethod.GET, entity,
                org.springframework.cloud.config.environment.Environment.class, args);
        } catch (HttpClientErrorException var14) {
            if (var14.getStatusCode() != HttpStatus.NOT_FOUND) {
                throw var14;
            }
        }

        if (response != null && response.getStatusCode() == HttpStatus.OK) {
            org.springframework.cloud.config.environment.Environment result1 = (org.springframework.cloud.config.environment.Environment) response
                .getBody();
            return result1;
        } else {
            return null;
        }
    }

    private String getServerUrl(String serviceName) {
        com.ecwid.consul.v1.ConsulClient client = new com.ecwid.consul.v1.ConsulClient(
            "http://localhost", 8500);
        Response<List<HealthService>> healthyServices = client.getHealthServices(serviceName, true,
            QueryParams.DEFAULT);

        if (healthyServices.getValue().size() <= 0) {
            throw new RuntimeException("can't fine service :[" + serviceName + "]");
        }
        HealthService healthService = healthyServices.getValue().get(0);

        String url = "http://" + healthService.getService().getAddress() + ":"
                     + healthService.getService().getPort();

        return url;

        //System.out.println(url);

    }

    private ConfigServerProVo getconfigServerProVo(){
        try {
            Yaml yaml = new Yaml();
            Map<String, Map<String,Map<String,String>>> contactMap = yaml.loadAs(
                    this.getClass().getClassLoader().getResourceAsStream("bootstrap.yml"), Map.class);
            Map  map= (Map)contactMap.get("spring");
            Map applicationNameMap=(Map)map.get("application");
            String appName=(String)applicationNameMap.get("name");
            //String profile = System.getProperty("spring.cloud.consul.discovery.tags");
            //-Dserver.port=3088 -Dswagger.enabled=true -Dspring.cloud.consul.discovery.tags=dev


            Map  map2=(Map)map.get("cloud");
            Map  map3=(Map) map2.get("config");
            String name="common";//  (String)map3.get("name");
            String profile= "dev";// (String)map3.get("profile");
            String label=  (String)map3.get("label");
            Map  cofigserverinfoMap=  (Map)map3.get("discovery");
            String configserverid=  (String)cofigserverinfoMap.get("serviceId");

            String serverurl=getServerUrl(configserverid);

            ConfigServerProVo configClientProperties = new ConfigServerProVo();
            configClientProperties.setLabel(label);
            configClientProperties.setProfile(profile);
            configClientProperties.setName(name);
            configClientProperties.setUri(serverurl);
            return  configClientProperties;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }

    // org.springframework.cloud.config.environment.Environment result = this.getRemoteEnvironment(restTemplate, properties, label.trim(), state);

    public  static  void main(String args[]){
        Environment  environment=  new GenConfigServerProperties().getCommonProperties();
        System.out.println(environment);
        PropertySource propertySource= environment.getPropertySources().get(0);
        propertySource.getSource().entrySet().forEach(item->{

        });
        System.out.println(propertySource);


    }


}