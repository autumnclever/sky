/**
 * Copyright (C), 2024-2025, baidu
 */
package com.autumn.clever.java8;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * FileName: ListsDemo
 * Author:   zhangqiuying
 * Date:     2025/3/11 17:00
 * Description:
 */
public class ListsDemo {


    public static void main(String[] args) {

        List<List<Integer>> tagIdList = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6)
        );

        List<List<Integer>> tagIdList2 = Arrays.asList(
                Arrays.asList(1, 10458, 10466, 10527),
                Arrays.asList(10458),
                Arrays.asList(10466, 10527),
                Arrays.asList(10466, 10527)
        );

        Map<Integer, CrowdsGenerateInfo> userTagMap = new HashMap<>();
        CrowdsGenerateInfo crowdsGenerateInfo = new CrowdsGenerateInfo();
        crowdsGenerateInfo.setTagId(4);
        crowdsGenerateInfo.setCrowdNum(4L);
        crowdsGenerateInfo.setTagId(6);
        crowdsGenerateInfo.setTagId(6);
        userTagMap.put(4, crowdsGenerateInfo);
        userTagMap.put(6, crowdsGenerateInfo);

        Map<Integer, CrowdsGenerateInfo> userTagMap2 = new HashMap<>();
        CrowdsGenerateInfo crowdsGenerateInfo1 = new CrowdsGenerateInfo();
        crowdsGenerateInfo.setTagId(1);
        crowdsGenerateInfo.setCrowdNum(23423L);
        userTagMap2.put(1, crowdsGenerateInfo1);

        CrowdsGenerateInfo crowdsGenerateInfo2 = new CrowdsGenerateInfo();
        crowdsGenerateInfo.setTagId(2);
        crowdsGenerateInfo.setCrowdNum(23423L);
        userTagMap2.put(2, crowdsGenerateInfo2);

        CrowdsGenerateInfo crowdsGenerateInfo3 = new CrowdsGenerateInfo();
        crowdsGenerateInfo.setTagId(10466);
        crowdsGenerateInfo.setCrowdNum(11251764L);
        userTagMap2.put(10466, crowdsGenerateInfo3);

        CrowdsGenerateInfo crowdsGenerateInfo4 = new CrowdsGenerateInfo();
        crowdsGenerateInfo.setTagId(10458);
        crowdsGenerateInfo.setCrowdNum(10458L);
        userTagMap2.put(10458, crowdsGenerateInfo4);

        tagIdList = tagIdList.stream()
                .filter(subList -> {
                            List<Integer> list = subList.stream()
                                    .filter(tagId ->
                                            userTagMap.containsKey(tagId) && userTagMap.get(tagId).getCrowdNum() != null)
                                    .collect(Collectors.toList());
                            return CollectionUtils.isNotEmpty(list);
                        }
                ).collect(Collectors.toList());

        long minSum = tagIdList.stream()
                .mapToLong(subList -> subList.stream()
                        .filter(tagId ->
                                userTagMap.containsKey(tagId) && userTagMap.get(tagId).getCrowdNum() != null)
                        .map(tagId -> userTagMap.get(tagId).getCrowdNum())
                        .mapToLong(Long::longValue)
                        .sum()
                )
                .min()
                .orElse(0); // 处理空列表情况，默认返回0

        System.out.println("最小和为: " + minSum); //
    }

}
