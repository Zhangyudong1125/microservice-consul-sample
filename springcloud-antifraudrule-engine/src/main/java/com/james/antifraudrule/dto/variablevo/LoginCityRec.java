/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraudrule.dto.variablevo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author militang
 * @version Id: LoginCityRec.java, v 0.1 17/9/19 上午10:52 militang Exp $$
 */
@Data
public class LoginCityRec implements Serializable {

    String city;
    int    cnt;
    //Map<Map> cityLogInMap
    //city

    //  CITY    key=biz+contractNo+day    value=city,cnt

    //DEVICE key=biz+contractNo+day      VALUE=device ,cnt

}

//申请时点登录IP所在城市非近30天TOP2常用登录地且登录设备非近30天TOP2常登录设备