/**
 * Copyright (C), 2024-2024, baidu
 */
package com.autumn.clever.baidu.megrez;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * FileName: SendCouponResp
 * Author:   zhangqiuying
 * Date:     2024/10/10 12:50
 * Description:
 */
@Data
public class SendCouponResp {
    private Long couponTakeId;
    private String couponBatchId;
    private CouponInfoDTO couponInfoDTO;
    private Date sendDate;
    private Map<Integer, String> couponCodes;
    private Integer status;
}
