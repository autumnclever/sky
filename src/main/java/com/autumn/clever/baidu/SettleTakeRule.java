package com.autumn.clever.baidu;

import org.apache.commons.lang.math.NumberUtils;
import org.assertj.core.util.Lists;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @program: sky
 * @description: 佣金批量修改
 * @author: zhangqiuying
 * @create: 2023-05-18 17:52
 **/
public class SettleTakeRule {

    public static void main(String[] args) {
//        String str = "新开店平台佣金减免，按%d%%收取";
//        DecimalFormat decimalFormat = new DecimalFormat("#%");
//        String printStr = String.format(str, decimalFormat.format(4));
//        String printStr = String.format(str, 4);
//        System.out.println(printStr);

//        String str1 = "%s平台佣金减免，按原比例的%d%%收取";

//        String printStr2 = String.format(str1, null, null);
//        System.out.println(printStr2);

        SettleDiscountRuleBindShop bindShop1 = new SettleDiscountRuleBindShop(1, 10);
        SettleDiscountRuleBindShop bindShop2 = new SettleDiscountRuleBindShop(2, 20);
        List<SettleDiscountRuleBindShop> discountRuleList = Lists.newArrayList(bindShop1, bindShop2);
        List<SettleDiscountRuleBindShop> maxDiscountRuleList = discountRuleList.stream()
                .filter(Objects::nonNull)
                .filter(discountRule -> !NumberUtils.INTEGER_ZERO.equals(discountRule.getRuleRatio()))
                .sorted(Comparator.comparing(SettleDiscountRuleBindShop::getRuleRatio))
                .limit(1)
                .collect(Collectors.toList());

        for (SettleDiscountRuleBindShop bindShop : maxDiscountRuleList) {
            System.out.println(bindShop.getRuleRatio());
        }
    }
}
