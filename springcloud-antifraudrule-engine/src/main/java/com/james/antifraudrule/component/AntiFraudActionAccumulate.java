/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraudrule.component;

import com.google.common.base.Strings;
import com.james.antifraudrule.dto.antifraudbizreqdto.AntiFraudObj;
import com.james.antifraudrule.dto.variablevo.ContentRec;
import com.james.antifraudrule.enums.AntiFraudTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author militang
 * @version Id: AntiFraudActionAccumulate.java, v 0.1 17/9/19 上午9:18 militang Exp $$
 */
@Component
@Slf4j
public class AntiFraudActionAccumulate {

    @Autowired
    RedissonClient        redissonClient;

    @Autowired
    private RedisTemplate redisTemplate;

    public void AccuExcute(AntiFraudObj antiFraudObj) {

        String action = antiFraudObj.getBizinfo().getAction();

        String emergeNo = antiFraudObj.getActor().getContactPhone1();

        String devicePrint = antiFraudObj.getLocation().getDeveiceFingerprint();

        String gps = antiFraudObj.getLocation().getGpsDesc();
        String ip = antiFraudObj.getLocation().getIp();

        String contractNo = antiFraudObj.getActor().getContractNo();

        String city = antiFraudObj.getLocation().getCity();

        if (action.equals(AntiFraudTypeEnum.LOGIN_EVENT.name())) {
            //城市非近30天TOP2城市登录累计
            eventDayAccumulate(action, contractNo, city, "city", 30);
            //录设备非近30天TOP2常登录设备
            eventDayAccumulate(action, contractNo, devicePrint, "devicePrint", 30);
            /*申请时所用IP近3小时注册或申请用户数≥10
             申请时所用IP近12小时注册或申请用户数≥20
             申请时所用IP近72小时注册或申请用户数≥30*/
            eventHourAccumulate(action, null, ip, "ip", 72);
            /*申请时使用设备近12小时登录用户数≥2
              申请时使用设备近7天登录用户数≥3*/
            eventHourAccumulate(action, null, devicePrint, "devicePrint", 168);
        }

        if (action.equals(AntiFraudTypeEnum.REG_EVENT.name())) {
            //申请时所用IP近72小时注册或申请用户数≥30
            eventHourAccumulate(action, null, ip, "ip", 72);
        }

        if (action.equals(AntiFraudTypeEnum.AUTHAPPLY_EVENT.name())) {
            eventHourAccumulate(action, null, emergeNo, "emergeNo", 3); //紧急联系人
            /*
            申请时使用设备近3小时申贷用户数≥2
            申请时使用设备近7天申贷用户数≥3*/
            eventHourAccumulate(action, null, devicePrint, "devicePrint", 168);
            eventHourAccumulate(action, null, ip, "ip", 72);
        }

    }

    private void eventHourAccumulate(String action, String contractNo, String mark, String markName,
                                     int longterm) {
        int windowTimehrss = Long.valueOf(System.currentTimeMillis() / 1000 / (3600)).intValue();
        String contentKey = action + ":";
        if (!Strings.isNullOrEmpty(contractNo)) {
            contentKey = contentKey + contractNo + ":";
        }
        contentKey = contentKey + markName + ":" + mark;

        RLock lock = redissonClient.getLock(contentKey);
        try {
            lock.lock();
            //申请时点登录IP所在城市非近30天TOP2常用登录地且登录设备非近30天TOP2常登录设备
            //   if (redisTemplate.opsForSet().isMember(cityloginkey,))

            String convalue_pre = markName + ":" + mark + ":" + windowTimehrss + ":";
            //Set<String> set = redisTemplate.opsForSet().members(cityloginkey);

            Set<String> set = redisTemplate.opsForZSet().range(contentKey, 0, -1);

            boolean iscontain = false;
            for (String loingstr : set) {
                ContentRec loginRec = new ContentRec(loingstr);
                if (loginRec.getTimeslong() + longterm < windowTimehrss) {
                    redisTemplate.opsForZSet().remove(contentKey, loingstr);
                } else if (loingstr.startsWith(convalue_pre)) {
                    loginRec.increascnt();
                    redisTemplate.opsForZSet().remove(contentKey, loingstr);
                    redisTemplate.opsForZSet().add(contentKey, loginRec.toSetString(),
                        loginRec.getCnt());
                    redisTemplate.expire(contentKey, longterm, TimeUnit.HOURS);
                    iscontain = true;
                }
            }
            if (!iscontain) {
                convalue_pre = convalue_pre + "1";
                redisTemplate.opsForZSet().add(contentKey, convalue_pre, 1);
                redisTemplate.expire(contentKey, longterm, TimeUnit.HOURS);
            }

        } catch (Exception ex) {
            log.error("Error occurred");
        } finally {
            lock.unlock();
        }

    }

    private void eventDayAccumulate(String action, String contractNo, String mark, String markName,
                                    int daycnt) {
        int windowTimeDays = Long.valueOf(System.currentTimeMillis() / 1000 / (3600 * 24))
            .intValue();
        //String cityloginkey = action + ":" + contractNo + ":" + markName;

        String contentKey = action + ":";
        if (!Strings.isNullOrEmpty(contractNo)) {
            contentKey = contentKey + contractNo + ":";
        }
        contentKey = contentKey + markName + ":" + mark;

        RLock lock = redissonClient.getLock(contentKey);
        try {
            lock.lock();
            //申请时点登录IP所在城市非近30天TOP2常用登录地且登录设备非近30天TOP2常登录设备
            //   if (redisTemplate.opsForSet().isMember(cityloginkey,))

            String convalue_pre = markName + ":" + mark + ":" + windowTimeDays + ":";
            //Set<String> set = redisTemplate.opsForSet().members(cityloginkey);

            Set<String> set = redisTemplate.opsForZSet().range(contentKey, 0, -1);

            boolean iscontain = false;
            for (String loingstr : set) {
                ContentRec loginRec = new ContentRec(loingstr);
                if (loginRec.getTimeslong() + daycnt < windowTimeDays) {
                    redisTemplate.opsForZSet().remove(contentKey, loingstr);
                } else if (loingstr.startsWith(convalue_pre)) {
                    loginRec.increascnt();
                    redisTemplate.opsForZSet().remove(contentKey, loingstr);
                    redisTemplate.opsForZSet().add(contentKey, loginRec.toSetString(),
                        loginRec.getCnt());
                    redisTemplate.expire(contentKey, daycnt, TimeUnit.DAYS);
                    iscontain = true;
                }
            }
            if (!iscontain) {
                convalue_pre = convalue_pre + "1";
                redisTemplate.opsForZSet().add(contentKey, convalue_pre, 1);
                redisTemplate.expire(contentKey, daycnt, TimeUnit.DAYS);
            }

        } catch (Exception ex) {
            log.error("Error occurred");
        } finally {
            lock.unlock();
        }
    }

}