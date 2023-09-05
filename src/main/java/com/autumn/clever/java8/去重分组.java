package com.autumn.clever.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: sky
 * @description: 去重分组
 * @author: zhangqiuying
 * @create: 2023-08-14 16:48
 **/
public class 去重分组 {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        list.forEach(x -> System.out.print(x + ","));
        System.out.println();
        list = list.stream().distinct().collect(Collectors.toList());
        System.out.println("----------------------------");
        list.forEach(x -> System.out.print(x + ","));
    }
}
