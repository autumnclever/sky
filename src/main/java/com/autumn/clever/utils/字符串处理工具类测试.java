package com.autumn.clever.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class 字符串处理工具类测试 {

    private static String ACTIVITY_RULE_CHECK_PRODUCT_CHARGE_BACK_RATE_ERROR = "商品退单率需 %s %s %%";

    public static void main(String[] args) {
        // 占位符
        String compare = ">=";
        String ruleValue = "12.2342";
        String str = String.format(ACTIVITY_RULE_CHECK_PRODUCT_CHARGE_BACK_RATE_ERROR, compare, ruleValue);
        System.out.println(str);

        String empty = "";
        String[] strings = StringUtils.split(empty, ',');
        for (String str1 : strings) {
            System.out.println(str1);
        }
        System.out.println("---------");

        String empty1 = "12321";
        String[] strings1 = StringUtils.split(empty1, ',');
        for (String str1 : strings1) {
            System.out.println(str1);
        }
        System.out.println("---------");

        String empty2 = "12321, 23e423";
        String[] strings2 = StringUtils.split(empty2, ",");
        for (String str1 : strings2) {
            System.out.println(str1);
        }
        System.out.println("---------");

//        String empty3 = null;
//        String[] strings3 = StringUtils.split(empty3, ',');
//        for (String str1 : strings3) {
//            System.out.println(str1);
//        }
//        System.out.println("---------");

        String empty4 = "12321, 234234";
        String[] strings4 = StringUtils.split(empty4, ",");
//        for (String str1 : strings4) {
//            System.out.println(str1);
//        }
//        System.out.println("---------");
        // 优惠包id > 0 有效
        Set<Long> packageIdList = Arrays.stream(strings4)
                .filter(Objects::isNull)
                .map(String::trim)
                .filter(StringUtils::isNumeric)
                .map(Long::valueOf)
                .filter(packageId -> packageId > 0)
                .collect(Collectors.toSet());
        for (Long str1 : packageIdList) {
            System.out.println(str1);
        }
//        System.out.println("---------");

        // trim 测试，去掉开头结尾的空格
        String string = " 2342234";
        System.out.println(string);
        string = string.trim();
        System.out.println(string);
    }
}
