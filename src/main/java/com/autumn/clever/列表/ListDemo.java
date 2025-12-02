/**
 * Copyright (C), 2024-2025, baidu
 */
package com.autumn.clever.列表;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * FileName: ListDemo
 * Author:   zhangqiuying
 * Date:     2025/11/14 13:23
 * Description:
 */
public class ListDemo {
    public static void main(String[] args) {
        List<Integer> list1 = Lists.newArrayList(1, 2, 3);
        List<Integer> list2 = Lists.newArrayList();
        list1.retainAll(list2);
        System.out.println(list1);
    }
}
