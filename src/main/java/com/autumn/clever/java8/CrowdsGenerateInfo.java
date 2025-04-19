/**
 * Copyright (C), 2024-2025, baidu
 */
package com.autumn.clever.java8;

import lombok.Data;

/**
 * FileName: CrowdsGenerateInfo
 * Author:   zhangqiuying
 * Date:     2025/3/11 17:02
 * Description:
 */
@Data
public class CrowdsGenerateInfo {

    /**
     * 人群标签ID
     */
    private Integer tagId;

    /**
     * 人群数量
     */
    private Long crowdNum;

    /**
     * 核心活跃人群数量
     */
    private Long crowdNumCoreActive;
}
