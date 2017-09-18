package com.james.antifraud.enums;

import lombok.Getter;

/**
 *  授信标识枚举
 *
 * @author JamesTang
 * @description
 * @data 2017/03/31.
 */
public enum BSVerifyPolicyCodeEnum {

    FLAG_BLOCK("BLOCK","授信阻断"),
    FLAG_NO_WITHDRAW("NO_WITHDRAW","禁止提现");

    @Getter
    private String key;

    @Getter
    private String value;

    BSVerifyPolicyCodeEnum(String key,String value){
        this.key = key;
        this.value = value;
    }

    public static BSVerifyPolicyCodeEnum valueOfKey(String key){
        for(BSVerifyPolicyCodeEnum bsVerifyPolicyCodeEnum:values()){
            if(bsVerifyPolicyCodeEnum.key.equals(key)){
                return bsVerifyPolicyCodeEnum;
            }
        }
        return null;
    }
}
