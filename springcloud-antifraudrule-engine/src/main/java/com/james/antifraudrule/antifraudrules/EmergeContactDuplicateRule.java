/**
 * Truderal.com.cn Inc.
 * Copyright (c) 2016-2017 All Rights Reserved.
 */
package com.james.antifraudrule.antifraudrules;

import com.james.antifraudrule.antifraudrules.abs.AbsAntiFraudRule;
import com.james.antifraudrule.dto.ruleresdto.RiskRuleResDto;
import com.james.antifraudrule.dto.variablevo.ContentRec;
import com.james.antifraudrule.enums.AntiFraudTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author militang
 * @version Id: EmergeContactDuplicateRule.java, v 0.1 17/9/21 下午1:59 militang Exp $$
 */

@Rule(name = "紧急联系人重合")
@Slf4j
@Component("G00107")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class EmergeContactDuplicateRule<T> extends AbsAntiFraudRule {






    @Override
    @Condition
    public boolean when() {

        String emergeNo = super.getAntiFraudObj().getActor().getContactPhone1();
        String contentKey = AntiFraudTypeEnum.AUTHAPPLY_EVENT.name() + ":emergeNo:" + emergeNo;
        //String contentKey = "LOGN_EVENT:86000000000110:devicePrint:ABS87DJJR777D"  action + ":contractNo:"+markName + ":" + mark;*/;
        //String convalue = "fingerprint:ABS87DJJR777D:677637763:6";
        Set<String> emergeNoApplySet = redisTemplate.opsForZSet().range(contentKey, 0, -1);
        int emgetNocnt = 0;
        for (String contentstr : emergeNoApplySet) {
            ContentRec contentRec = new ContentRec(contentstr);
            int windowTimehrss = Long.valueOf(System.currentTimeMillis() / 1000 / (3600))
                .intValue();
            if (contentRec.getTimeslong() + 3 >= windowTimehrss) {
                emgetNocnt = +contentRec.getCnt();
            }
            if (emgetNocnt > 3) {
                return true;
            }
        }
        return false;
    }

    @Override
    @Action
    public void then() throws Exception {
        try {
            log.info("EmergeContactDuplicateRule has been executed");
            RiskRuleResDto riskRuleResDto = new RiskRuleResDto();
            riskRuleResDto.setRuleid(getRuleid());
            riskRuleResDto.setRuledesc("触发 紧急联系人重合 规则");
            result = (T) riskRuleResDto; // assign your result here
            executed = true;
        } catch (Exception e) {
            // executed flag will remain false if an exception occurs
            throw e;
        }
    }
}