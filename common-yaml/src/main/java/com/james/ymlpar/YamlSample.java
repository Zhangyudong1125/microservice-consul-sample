/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.ymlpar;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.yaml.snakeyaml.Yaml;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.QueryParams;
import com.ecwid.consul.v1.Response;
import com.ecwid.consul.v1.health.model.HealthService;

/**
 * @author militang
 * @version Id: YamlSample.java, v 0.1 17/9/13 下午4:54 militang Exp $$
 */
public class YamlSample {

    public void yemltest() {
        try {
            Yaml yaml = new Yaml();
             Map<String, Map<String,Map<String,String>>> contactMap = yaml.loadAs(
                this.getClass().getClassLoader().getResourceAsStream("bootstrap.yml"), Map.class);

             Map  map= (Map)contactMap.get("spring");
             Map  map2=(Map)map.get("cloud");


             Map  map3=(Map) map2.get("config");
            String name=  (String)map3.get("name");
            String profile=  (String)map3.get("profile");
            String label=  (String)map3.get("label");

            Map  cofigserverinfoMap=  (Map)map3.get("discovery");
            String configserverid=  (String)cofigserverinfoMap.get("serviceId");

           //  Map  map2=(Map<Map<String,String>>) map.get("cloud");


             //List  map3= (List<Map<String,String>>) map.get("config");
//

            Properties bootpro = yaml.loadAs(
                this.getClass().getClassLoader().getResourceAsStream("bootstrap.yml"),
                Properties.class);
            System.out.println(contactMap);
            String result = yaml.dump(contactMap);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String args[]) {
        new YamlSample().yemltest();

    }
}