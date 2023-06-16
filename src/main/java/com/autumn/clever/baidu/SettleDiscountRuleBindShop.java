package com.autumn.clever.baidu;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-05-30 17:53
 **/
public class SettleDiscountRuleBindShop {

    private Integer id;

    private Integer ruleRatio;

    public SettleDiscountRuleBindShop(Integer id, Integer ruleRatio) {
        this.id = id;
        this.ruleRatio = ruleRatio;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRuleRatio() {
        return ruleRatio;
    }

    public void setRuleRatio(Integer ruleRatio) {
        this.ruleRatio = ruleRatio;
    }
}
