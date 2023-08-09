package com.autumn.clever.baidu.finance;

import lombok.Data;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-08-03 21:20
 **/
@Data
public class CouponInfo {

    /**
     * 券批次id
     */
    private String batchId;

    /**
     * 下单分摊金额
     */
    private String splitAmount;

    private String budgetUnId;

    private CouponBizTypeEnum couponBizType;
}
