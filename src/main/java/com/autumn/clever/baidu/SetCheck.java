package com.autumn.clever.baidu;

import com.google.common.base.Splitter;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-06-21 11:35
 **/
public class SetCheck {
    public static final Splitter COMMA_SPLITTER = Splitter.on(",").omitEmptyStrings().trimResults();

    public static void main(String[] args) {
        String calculateRestrict = "a,b,c,d,e,f";


        Set<String> calculateRestrictItemSet = new HashSet<>();
        COMMA_SPLITTER.split(calculateRestrict).forEach(calculateRestrictItemSet::add);

        System.out.print("calculateRestrictItemSet: ");
        for (String item : calculateRestrictItemSet) {
            System.out.print(item + "-");
        }
        System.out.println("----------------");

        Set<String> takeCouponWhiteRestrictItemSet = new HashSet<>();
        takeCouponWhiteRestrictItemSet.add("a");
        takeCouponWhiteRestrictItemSet.add("b");

        System.out.print("takeCouponWhiteRestrictItemSet: ");
        for (String item : takeCouponWhiteRestrictItemSet) {
            System.out.print(item + "-");
        }
        System.out.println("----------------");

        takeCouponWhiteRestrictItemSet.retainAll(calculateRestrictItemSet);

        System.out.print("calculateRestrictItemSet: ");
        for (String item : calculateRestrictItemSet) {
            System.out.print(item + "-");
        }
        System.out.println("----------------");

        System.out.print("takeCouponWhiteRestrictItemSet: ");
        for (String item : takeCouponWhiteRestrictItemSet) {
            System.out.print(item + "-");
        }
        System.out.println("----------------");

        Set<String> takeCouponBlackRestrictItemSet = new HashSet<>();
        takeCouponBlackRestrictItemSet.add("a");
        takeCouponBlackRestrictItemSet.add("d");

        System.out.print("takeCouponBlackRestrictItemSet: ");
        for (String item : takeCouponBlackRestrictItemSet) {
            System.out.print(item + "-");
        }
        System.out.println("----------------");

        // 算价 item 元素 vs 黑名单集合, 取交集
        takeCouponBlackRestrictItemSet.retainAll(calculateRestrictItemSet);

        System.out.print("takeCouponBlackRestrictItemSet: ");
        for (String item : takeCouponBlackRestrictItemSet) {
            System.out.print(item + "-");
        }
        System.out.println("----------------");

        System.out.print("calculateRestrictItemSet: ");
        for (String item : calculateRestrictItemSet) {
            System.out.print(item + "-");
        }
        System.out.println("----------------");
    }
}
