package com.autumn.clever.baidu.cashier;

import lombok.Data;
import lombok.ToString;

/**
 * 收银台返回的订单数据
 *
 * @program: sky
 * @description: 收银台返回的订单数据
 * @author: zhangqiuying
 * @create: 2023-06-16 11:30
 **/
@Data
@ToString(callSuper = true)
public class CashierOrderData {

    /**
     * 业务方订单号
     */
    private String tpOrderId;

    /**
     * 收银台订单号
     */
    private Long orderId;
}
