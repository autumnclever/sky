package com.autumn.clever.baidu.grapeTask推送收银台;

/**
 * @program: sky
 * @package: com.autumn.clever.baidu.grapeTask推送收银台
 * @title: ReturnCouponEnum
 * @description:
 * @author: zhangqiuying
 * @create: 2023-11-03 17:00
 **/
public enum ReturnCouponEnum {

    TAKE(1, "领取"),
    USE(2, "使用"),
    ORDER_FAIL(3, "订单失败退回"),
    ORDER_CANCEL(4, "订单取消退回"),
    AFTER_SALE(5, "售后退回"),
    TAKING(6, "领取中"),
    FAIL(7, "领取失败"),
    INVALID(8, "主动失效");

    private int code;
    private String desc;

    private ReturnCouponEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
