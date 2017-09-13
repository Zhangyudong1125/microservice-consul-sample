/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.component;

import java.util.Arrays;
import java.util.HashMap;

import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * @author militang
 * @version Id: getConfigServerProperties.java, v 0.1 17/9/13 上午11:17 militang Exp $$
 */
@Component
public class GenConfigServerProperties {

    public Environment getCommonProperties() {

        return null;

    }

    private RestTemplate getSecureRestTemplate(ConfigClientProperties client) {
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
                                                                                          ConfigClientProperties properties,
                                                                                          String label,
                                                                                          String state) {
        String path = "/{name}/{profile}";
        String name = properties.getName();
        String profile = properties.getProfile();
        String token = properties.getToken();
        String uri = properties.getRawUri();
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

    // org.springframework.cloud.config.environment.Environment result = this.getRemoteEnvironment(restTemplate, properties, label.trim(), state);

}