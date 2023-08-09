package com.autumn.clever.baidu.finance;

/**
 * @program: sky
 * @package: com.autumn.clever.baidu.finance
 * @title: CouponBizTypeEnum
 * @description:
 * @author: zhangqiuying
 * @create: 2023-08-03 21:30
 **/
public enum CouponBizTypeEnum {

    PLATFORM(1, "平台券"),
    BUSSINESS(2, "商家券");

    private int code;
    private String desc;

    CouponBizTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
