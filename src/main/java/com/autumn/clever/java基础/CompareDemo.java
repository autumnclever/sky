/**
 * Copyright (C), 2024-2024, baidu
 */
package com.autumn.clever.java基础;

import org.apache.commons.lang.math.NumberUtils;

/**
 * FileName: CompareDemo
 * Author:   zhangqiuying
 * Date:     2024/6/26 15:32
 * Description:
 */
public class CompareDemo {

    public static void main(String[] args) {
        Long liveContentAuthorIdTakeNum = 0L;
        Long takeLimitNum = 0L;
        boolean result = liveContentAuthorIdTakeNum.compareTo(takeLimitNum) < NumberUtils.INTEGER_ONE;
        System.out.println(result);
    }
}
