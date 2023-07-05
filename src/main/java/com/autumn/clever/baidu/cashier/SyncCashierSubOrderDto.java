package com.autumn.clever.baidu.cashier;

import lombok.Data;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-06-16 15:02
 **/
@Data
public class SyncCashierSubOrderDto {

    /**
     * 业务方子订单(主订单)编号
     */
    private String tpOrderId;

    /**
     * 子订单总金额
     */
    private Long totalMoney;

    /**
     * 子订单营销明细信息(Json Array类型)
     */
    private String tpMarketingDetail;

    /**
     * 子订单对应appKey
     */
    private String appKey;

    /**
     * 子订单对应appkey中dealid
     */
    private Long dealId;

    /**
     * 是否标记核销：1-未核销（不结算）,2-已核销（需结算）
     */
    private Integer isConsumed;

    /**
     * 分销结算金额
     */
    private Long splitMoney;

    /**
     * 平台抽佣比例
     */
    private String splitRatio;
}
