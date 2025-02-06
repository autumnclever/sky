/**
 * Copyright (C), 2024-2024, baidu
 */
package com.autumn.clever.baidu.营销的一些实体类;

import lombok.Data;

import java.util.List;

/**
 * FileName: UserTagDTO
 * Author:   zhangqiuying
 * Date:     2024/7/29 12:30
 * Description:
 */
@Data
public class UserTagDTO {

    /**
     * 限制使用的用户人群标识；null或者空列表，代表不限制；具体取值参考UserTagEnum
     */
    private List<Integer> limitUserTagList;

    /**
     * 限制使用的用户人群标识组；null或者空列表，代表不限制；同组或逻辑限制，不同组与逻辑限制
     */
    private List<List<Integer>> limitUserTagGroups;
}
