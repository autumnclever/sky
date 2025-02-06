/**
 * Copyright (C), 2024-2024, baidu
 */
package com.autumn.clever.baidu;

import com.autumn.clever.baidu.营销的一些实体类.CouponInfo;
import com.autumn.clever.baidu.营销的一些实体类.UserTagDTO;
import com.autumn.clever.baidu.营销的一些实体类.UserTagsGroup;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * FileName: 营销新人标签校验
 * Author:   zhangqiuying
 * Date:     2024/7/29 12:27
 * Description:
 */
public class 营销新人标签校验 {

    public static void main(String[] args) {
        CouponInfo couponInfo = new CouponInfo();
        String userTag = "{\"limitUserTagList\":[],\"limitUserTagGroups\":[[4],[3,6,7,9,10,11,13],[10018]]}";
        couponInfo.setLimitUserTag(userTag);
        UserTagDTO userTagDTO = com.autumn.clever.goodcoder.JacksonUtil.parseObject(
                couponInfo.getLimitUserTag(), UserTagDTO.class);

        List<Integer> userTagList = Lists.newArrayList();
        userTagList.add(3);
        userTagList.add(4);
        boolean result = userTagsMatchDiscountsLimit(userTagDTO, userTagList);
        System.out.println(result);
    }

    public static boolean userTagsMatchDiscountsLimit(UserTagDTO userTagDTO, List<Integer> userTagList) {
        if (Objects.isNull(userTagDTO)) {
            return true;
        }
        boolean result = userTagsMatchDiscountsLimit(userTagDTO.getLimitUserTagList(),
                userTagDTO.getLimitUserTagGroups(), userTagList);
        return result;
    }

    public static boolean userTagsMatchDiscountsLimit(List<Integer> limitUserTagList,
                                                      List<List<Integer>> limitUserTagGroups,
                                                      List<Integer> userTagList) {
        // 无任何限制标签，无限制，直接可使用该优惠
        if (CollectionUtils.isEmpty(limitUserTagList) && CollectionUtils.isEmpty(limitUserTagGroups)) {
            return true;
        }
        // 优惠有限制，但用户没标签，直接不符合要求
        if (CollectionUtils.isEmpty(userTagList)) {
            return false;
        }

        // limitUserTagList，所有标签都需满足，才能使用该优惠
        if (CollectionUtils.isNotEmpty(limitUserTagList)
                && !Sets.newHashSet(userTagList).containsAll(limitUserTagList)) {
            return false;
        }

        if (CollectionUtils.isNotEmpty(limitUserTagGroups)) {
            // 分组
            Pair<UserTagsGroup, UserTagsGroup> userTagsGroupPair =
                    groupUserTags(limitUserTagGroups, userTagList);
            if (!orLogicSatisfy(userTagsGroupPair.getLeft(), userTagsGroupPair.getRight())) {
                return false;
            }
        }

        return true;
    }

    /**
     * 第二版
     * 将人群标签按照优惠上的设置进行分组
     * return left - 优惠限制标签分组  right - 用户标签分组
     */
    private static Pair<UserTagsGroup, UserTagsGroup> groupUserTags(List<List<Integer>> limitUserTagGroups,
                                                                    List<Integer> userTagList) {
        // 这里limitUserTagGroups, userTagList不可能为空了
        UserTagsGroup userTagsGroup = new UserTagsGroup();
        UserTagsGroup limitUserTagsGroup = new UserTagsGroup();
        for (int i = 0; i < limitUserTagGroups.size(); i++) {
            List<Integer> limitGroup = limitUserTagGroups.get(i);
            if (CollectionUtils.isEmpty(limitGroup)) {
                continue;
            }
            // 用户标签组和优惠券限制组共用同一个key
            String key = "group" + i;
            limitUserTagsGroup.getOrLogicUserTags().put(key, limitGroup);
            // 用户标签分组
            List<Integer> matchedOrLogicUserTags = userTagList.stream()
                    .filter(limitGroup::contains)
                    .collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(matchedOrLogicUserTags)) {
                userTagsGroup.getOrLogicUserTags().put(key, matchedOrLogicUserTags);
            }
        }

        return Pair.of(limitUserTagsGroup, userTagsGroup);
    }

    /**
     * 或逻辑是否满足条件
     *
     * @return true - 分组限制满足  false - 分组限制不满足
     */
    private static boolean orLogicSatisfy(UserTagsGroup limitUserTagsGroup, UserTagsGroup userTagsGroup) {
        if (limitUserTagsGroup == null
                || MapUtils.isEmpty(limitUserTagsGroup.getOrLogicUserTags())) {
            return true;
        }
        // 若或逻辑的限制不为空，但人无或逻辑的标签，那么不符合
        if (MapUtils.isEmpty(userTagsGroup.getOrLogicUserTags())) {
            return false;
        }
        // 以优惠上的限制进行遍历
        for (Map.Entry<String, List<Integer>> entry : limitUserTagsGroup.getOrLogicUserTags().entrySet()) {
            List<Integer> limitOrLogicUserTags = entry.getValue();
            // 限制为空的直接不用管
            if (CollectionUtils.isNotEmpty(limitOrLogicUserTags)) {
                List<Integer> orLogicUserTags = userTagsGroup.getOrLogicUserTags().get(entry.getKey());
                if (CollectionUtils.isEmpty(orLogicUserTags)) {
                    return false;
                }
                Set<Integer> commonSet = Sets.intersection(Sets.newHashSet(limitOrLogicUserTags),
                        Sets.newHashSet(orLogicUserTags));
                // 无一个交集直接返回错误
                if (CollectionUtils.isEmpty(commonSet)) {
                    return false;
                }
            }
        }

        return true;
    }
}
