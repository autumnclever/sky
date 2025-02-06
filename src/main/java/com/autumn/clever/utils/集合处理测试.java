/**
 * Copyright (C), 2024-2024, baidu
 */
package com.autumn.clever.utils;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * FileName: 集合处理测试
 * Author:   zhangqiuying
 * Date:     2024/4/19 17:26
 * Description:
 */
public class 集合处理测试 {

    public static final List<Integer> NEED_CHECK_INSTRUCT_DATA_RULE_TYPE_LIST =
            Lists.newArrayList(46, 47, 48, 49);

    public static void main(String[] args) {
        List<Integer> list1 = Lists.newArrayList(12, 13, 14, 46, 47, 48, 49);
        System.out.println("list1:" + list1);
        boolean result = list1.retainAll(NEED_CHECK_INSTRUCT_DATA_RULE_TYPE_LIST);
        System.out.println(result);
        System.out.println("list1:" + list1);

        List<Integer> list2 = Lists.newArrayList(12, 13, 14);
        System.out.println("list2:" + list2);
        boolean result2 = list2.removeAll(NEED_CHECK_INSTRUCT_DATA_RULE_TYPE_LIST);
        System.out.println(result2);
        System.out.println("list2:" + list2);

        List<Integer> list3 = Lists.newArrayList(12, 13, 14, 46, 47, 48, 49);

    }
}
