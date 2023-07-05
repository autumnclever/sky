package com.autumn.clever.baidu.cashier;

import lombok.Data;

import java.util.List;

/**
 * 推子订单收银台response
 *
 * @program: sky
 * @description: 推子订单收银台response
 * @author: zhangqiuying
 * @create: 2023-06-16 11:29
 **/
@Data
public class CashierOrderSyncResponseDto {

    /**
     * 编码
     */
    private Integer errno;

    /**
     * 消息
     */
    private List<CashierOrderData> data;

    /**
     * 数据
     */
    private String msg;

    public static CashierOrderSyncResponseDto success() {
        CashierOrderSyncResponseDto responseDto = new CashierOrderSyncResponseDto();
        responseDto.setErrno(CashierConstants.CASHIER_SUCCESS_STATUS);
        return responseDto;
    }
}
