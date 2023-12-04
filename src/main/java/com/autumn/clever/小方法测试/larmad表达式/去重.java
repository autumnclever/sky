package com.autumn.clever.小方法测试.larmad表达式;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-11-07 16:03
 **/
public class 去重 {
    public static void main(String[] args) {
        List<Long> needDoMutexSpuIdList = Lists.newArrayList();
        needDoMutexSpuIdList.addAll(Lists.newArrayList(1L, 2L, 3L, 1L, 2L, 3L));
        needDoMutexSpuIdList.forEach(System.out::println);
        System.out.println("=========================");
        needDoMutexSpuIdList = needDoMutexSpuIdList.stream().distinct().collect(Collectors.toList());
        for (Long spuId : needDoMutexSpuIdList){
            System.out.println(spuId);
        }
    }
}
