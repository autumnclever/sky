/**
 * Copyright (C), 2024-2025, baidu
 */
package com.autumn.clever.baidu.enums;

import lombok.Getter;

import java.util.concurrent.TimeUnit;

/**
 * FileName: RedisKeyEnum
 * Author:   zhangqiuying
 * Date:     2025/4/19 19:23
 * Description: 缓存key 枚举
 */
@Getter
public enum RedisKeyEnum {

    /**
     * 类型：string
     * 过期时间：无
     * BDT_SYNC_FINISH_20240702 标记今天八大条的数据同步完成，不需要在执行了
     */
    BDT_SYNC_FINISH_KEY("BDT_SYNC_FINISH", -1),

    /**
     * 类型：string
     * 过期时间：默认10分钟
     * BDT_JOB_LOCK 统一任务锁，确保同时仅有一个任务在执行
     */
    BDT_SYNC_LOCK_KEY("BDT_JOB_LOCK", (int) TimeUnit.MINUTES.toSeconds(10L)),

    /**
     * 类型：string
     * 过期时间：3天
     * CP_BDT_JOB_INC_KEY_20240702 整型自增Key用来做缓存后缀；防止同一天的任务重复执行，数据不一致
     */
    CP_BDT_JOB_INC_KEY("CP_BDT_JOB_INC_KEY", (int) TimeUnit.DAYS.toSeconds(3L)),

    /**
     * 类型：string
     * 过期时间：无
     * CP_BDT_KEY :  的值(CP_BDT_DATA_20240702_1)确定了缓存的前缀，每次成功执行任务后会更新该值
     */
    CP_BDT_KEY("CP_BDT_KEY", -1),

    /**
     * 类型：string
     * 过期时间：3天
     * CP_BDT_KEY 存储的值 ， 举例 ：CP_BDT_DATA_20240702_101
     */
    CP_BDT_DATA("CP_BDT_DATA", (int) TimeUnit.DAYS.toSeconds(3L)),

    /**
     * 类型：string
     * 过期时间：无
     * BDT_SYNC_FINISH_20250418 标记今天【投流>80%pass商家】的数据同步完成，不需要在执行了
     */
    TRAFFIC_INVEST_MERCHANT_FINISH_KEY("TRAFFIC_INVEST_MERCHANT_FINISH", -1),

    /**
     * 类型：string
     * 过期时间：默认10分钟
     * TRAFFIC_INVEST_MERCHANT_LOCK 统一任务锁，确保同时仅有一个任务在执行
     */
    TRAFFIC_INVEST_MERCHANT_LOCK_KEY("TRAFFIC_INVEST_MERCHANT_LOCK", (int) TimeUnit.MINUTES.toSeconds(10L)),

    /**
     * 类型：string
     * 过期时间：3天
     * TRAFFIC_INVEST_MERCHANT_INC_KEY_20250418 整型自增Key用来做缓存后缀；防止同一天的任务重复执行，数据不一致
     */
    TRAFFIC_INVEST_MERCHANT_INC_KEY("TRAFFIC_INVEST_MERCHANT_INC_KEY", (int) TimeUnit.DAYS.toSeconds(3L)),

    /**
     * 类型：string
     * 过期时间：无
     * TRAFFIC_INVEST_MERCHANT_KEY: 的值(TRAFFIC_INVEST_MERCHANT_DATA_20240418_1)确定了缓存的前缀，每次成功执行任务后会更新该值
     */
    TRAFFIC_INVEST_MERCHANT_KEY("TRAFFIC_INVEST_MERCHANT_KEY", -1),

    /**
     * 类型：string
     * 过期时间：3天
     * TRAFFIC_INVEST_MERCHANT_KEY 存储的值，举例 ：TRAFFIC_INVEST_MERCHANT_DATA_20240418_1
     */
    TRAFFIC_INVEST_MERCHANT_DATA("TRAFFIC_INVEST_MERCHANT_DATA", (int) TimeUnit.DAYS.toSeconds(3L)),
    ;

    // key前缀
    private String prefix;
    // 默认过期时间
    private Integer defaultExpireTime;

    RedisKeyEnum(String prefix, Integer defaultExpireTime) {
        this.prefix = prefix;
        this.defaultExpireTime = defaultExpireTime;
    }

    /**
     * 拼接redis key
     *
     * @param keys 变量
     * @return
     */
    public String getRedisKey(String... keys) {
        StringBuffer sb = new StringBuffer();
        sb.append(getPrefix());
        // 后面的参数
        if (keys != null) {
            for (String key : keys) {
                if (key != null) {
                    sb.append("_").append(key);
                }
            }
        }
        return sb.toString();
    }

    /**
     * 拼接redis key
     *
     * @param keys 变量
     * @return
     */
    public String getRedisKey(Object... keys) {
        StringBuffer sb = new StringBuffer();
        sb.append(getPrefix());
        // 后面的参数
        if (keys != null) {
            for (Object key : keys) {
                if (key != null) {
                    sb.append("_").append(key);
                }
            }
        }
        return sb.toString();
    }
}
