/**
 * Copyright (C), 2024-2025, baidu
 */
package com.autumn.clever.baidu.perkrule;

/**
 * FileName: PerkRule
 * Author:   zhangqiuying
 * Date:     2025/2/18 17:51
 * Description:
 */
public class PerkRule {
    public static void main(String[] args) {
        PerkRuleInfo perkRuleInfo = new PerkRuleInfo();
        perkRuleInfo.setEndTime(1747129219L);

        long expireTime = getPerkRuleExpireTime(perkRuleInfo);
        System.out.println("过期时间点：" + expireTime);
    }

    private static long getPerkRuleExpireTime(PerkRuleInfo perkRuleInfo) {
        if (perkRuleInfo == null || perkRuleInfo.getEndTime() == null) {
            return 0L;
        }
        return Math.toIntExact(perkRuleInfo.getEndTime() + 60 * 60);
    }
}
