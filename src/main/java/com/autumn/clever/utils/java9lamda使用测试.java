/**
 * Copyright (C), 2024-2024, baidu
 */
package com.autumn.clever.utils;

import org.apache.commons.compress.utils.Lists;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * FileName: java9lamda使用测试
 * Author:   zhangqiuying
 * Date:     2024/4/10 17:10
 * Description:
 */
public class java9lamda使用测试 {


    public static void main(String[] args) {

        /**
         * flatMap 自动去重
         */
        List<List<Integer>> idLists = Lists.newArrayList();
        List<Integer> idList1 = Arrays.asList(1, 2, 3, 1, 2, 3);
        List<Integer> idList2 = Arrays.asList(1, 2, 3);
        idLists.add(idList1);
        idLists.add(idList2);
        Set<Integer> idSet = getDiscountPackageInfoMap(idLists);
        System.out.println(idSet);
    }

    private static Set<Integer> getDiscountPackageInfoMap(List<List<Integer>> idLists) {
        // todo: 7.促销包 id 类型转换
        Set<Integer> discountPackageIdSet = idLists
                .stream()
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
        return discountPackageIdSet;
    }
}
