/**
 * Copyright (C), 2024-2024, baidu
 */
package com.autumn.clever.baidu.营销的一些实体类;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * FileName: UserTagsGroup
 * Author:   zhangqiuying
 * Date:     2024/7/29 12:28
 * Description:
 */
@Data
public class UserTagsGroup {

    /**
     * 标签分类，或逻辑的标签集合，即每个list中满足一个即可
     */
    private Map<String, List<Integer>> orLogicUserTags = new HashMap<>();

    /**
     * 标签分类，与逻辑的标签集合，即list中每个tag都必须满足要求
     */
    private List<Integer> andLogicUserTags = new ArrayList<>();

}
