/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraudrule.dto.variablevo;

import lombok.Data;

/**
 * @author militang
 * @version Id: ContentRec.java, v 0.1 17/9/19 下午5:24 militang Exp $$
 */
@Data
public class ContentRec {

    private String conTentName;
    private String content;
    private int    cnt;
    private long   timeslong;

    public void increascnt() {
        cnt++;
    }

    public ContentRec(String setStr) {
        String[] splistr = setStr.split(":");
        conTentName = splistr[0];
        content = splistr[1];
        timeslong = Long.valueOf(splistr[2]).longValue();
        cnt = Integer.valueOf(splistr[3]).intValue();
    }

    public String toSetString() {
        String setstr = conTentName + ":" + content + ":" + timeslong + ":" + cnt;
        return setstr;
    }

}