/**
 * Copyright (C), 2024-2025, baidu
 */
package com.autumn.clever.baidu.perkrule;

import lombok.Data;

import java.util.List;

/**
 * FileName: PerkRuleInfo
 * Author:   zhangqiuying
 * Date:     2025/2/18 17:52
 * Description:
 */
@Data
public class PerkRuleInfo {

    /**
     * 自增主键 - 规则 id
     */
    private Long id;

    /**
     * 规则开始时间，s 级时间戳
     */
    private Long startTime;

    /**
     * 规则结束时间，s 级时间戳
     */
    private Long endTime;

    /**
     * 补贴金额上限，单位: 元
     */
    private Long threshold;

    /**
     * 预算 id 列表
     */
    private List<Long> budgetIDList;
}
