/**
 * Copyright (C), 2024-2024, baidu
 */
package com.autumn.clever.baidu;

import lombok.Getter;

/**
 * FileName: 发券step
 * Author:   zhangqiuying
 * Date:     2024/7/9 17:42
 * Description:
 */
public class 发券step {
    public static void main(String[] args) {
        int step = 0;
        step = step | CouponDeductStepEnum.DISCOUNT_PACKAGE_SOURCE_ID_LIMIT.getStep();
        System.out.println(step);
        step = step | CouponDeductStepEnum.DISCOUNT_PACKAGE_CUID_LIMIT.getStep();
        System.out.println(step);
        step = step | CouponDeductStepEnum.DISCOUNT_PACKAGE_SOURCE_ID_LIMIT.getStep();
        System.out.println(step);
        step = step | CouponDeductStepEnum.DISCOUNT_PACKAGE_CUID_LIMIT.getStep();
        System.out.println(step);
        step = step | CouponDeductStepEnum.USER_LIMIT.getStep();
        System.out.println(step);

        boolean isReachSourceIdPackage = (step & CouponDeductStepEnum.DISCOUNT_PACKAGE_SOURCE_ID_LIMIT.getStep())
                == CouponDeductStepEnum.DISCOUNT_PACKAGE_SOURCE_ID_LIMIT.getStep();
//        step = step & CouponDeductStepEnum.DISCOUNT_PACKAGE_SOURCE_ID_LIMIT.getStep();
        System.out.println(isReachSourceIdPackage);

        boolean isReachCuIdPackage = (step & CouponDeductStepEnum.DISCOUNT_PACKAGE_CUID_LIMIT.getStep())
                == CouponDeductStepEnum.DISCOUNT_PACKAGE_CUID_LIMIT.getStep();
//        step = step & CouponDeductStepEnum.DISCOUNT_PACKAGE_CUID_LIMIT.getStep();
        System.out.println(isReachCuIdPackage);

        boolean isReachUser = (step & CouponDeductStepEnum.USER_LIMIT.getStep())
                == CouponDeductStepEnum.USER_LIMIT.getStep();
//        step = step & CouponDeductStepEnum.USER_LIMIT.getStep();
        System.out.println(isReachUser);
    }
}

@Getter
enum CouponDeductStepEnum {
    USER_LIMIT(1, "用户的领取限制"),
    USER_DAY_LIMIT(2, "用户每天的领取限制"),
    COUPON_LIMIT(4, "优惠劵库存的限制"),
    TODAY_LIMIT(8, "优惠劵当天库存的限制"),
    COUPON_SEND_AMOUNT(16, "优惠券发放金额"),
    CONTENT_LIVE_AUTHOR_ID_LIMIT(32, "直播带货场景，主播id 领取限制: contentType=3时，authorId(主播 id)领取次数限制"),
    DISCOUNT_PACKAGE_SOURCE_ID_LIMIT(64, "用户-优惠包-sourceId 维度领取限制"),
    DISCOUNT_PACKAGE_CUID_LIMIT(128, "用户-优惠包-passId 维度领取限制"),
    ;

    private Integer step;
    private String desc;

    CouponDeductStepEnum(Integer step, String desc) {
        this.step = step;
        this.desc = desc;
    }
}