/**
 * Copyright (C), 2024-2024, baidu
 */
package com.autumn.clever.baidu.megrez;

/**
 * FileName: CouponSendCodeEnum
 * Author:   zhangqiuying
 * Date:     2024/10/10 12:59
 * Description:
 */
public enum CouponSendCodeEnum {

    SUCCESS(200, 200, "成功"),

    COUPON_ALREADY_OFFLINE(400, 700003, "优惠券已下线"),

    COUPON_USER_NO_STOCK(401, 700004, "您的领取次数已经用完咯"),

    COUPON_NO_STOCK(402, 700013, "优惠劵领取次数达到上限"),

    COUPON_TAKE_TOO_BUSY(403, 700017, "活动太火爆请稍后重试"),

    COUPON_ALREADY_TAKE(404, 700018, "优惠劵已领取"),

    COUPON_TAKE_TIME_END(405, 700019, "优惠劵领取时间已结束"),

    COUPON_TAKE_TIME_NOT_START(406, 700020, "优惠劵领取时间未到"),

    COUPON_EXPIRE(407, 700021, "优惠劵已过期"),

    COUPON_USER_APP_NOT_MATCH_DESC(408, 7000018, "领取优惠券平台不匹配"),

    COUPON_NOT_EXIST(409, 70000, "优惠券不存在"),

    COUPON_GET_COUPON_CODE_ERROR(410, 700039, "领劵获取劵码失败"),

    COUPON_TAKE_CHANNEL_ERROR(411, 700036, "优惠券不能在该渠道领取"),

    COUPON_DAY_TAKE_LIMIT_ERROR(412, 700037, "您今日的领取次数已经用完咯"),

    COUPON_DAY_TAKEN_LIMIT_ERROR(413, 700073, "该券每天的领取次数达到上限"),

    COUPON_AMOUNT_LIMIT_ERROR(414, 700076, "优惠券发放金额达到上限"),

    COUPON_CAN_NOT_TAKE_STREAM_ERROR(415, 700091, "该流量渠道无法领取"),

    COUPON_SEND_CONTENT_LIVE_AUTHOR_ID_LIMIT_ERROR(700092, 700092, "优惠券主播发放次数达到上限"),

    COUPON_SEND_DISCOUNT_PACKAGE_USER_LIMIT_ERROR(700093, 700093, "优惠包发放次数达到上限"),

    ERROR(5000, -1, "其他领劵问题");

    private Integer code;

    private Integer plateCode;

    private String desc;

    CouponSendCodeEnum(Integer code, Integer plateCode, String desc) {
        this.code = code;
        this.plateCode = plateCode;
        this.desc = desc;
    }

    public static CouponSendCodeEnum getByPlateCode(Integer code) {
        if (code == null) {
            return ERROR;
        }
        for (CouponSendCodeEnum couponSendCodeEnum : CouponSendCodeEnum.values()) {
            if (couponSendCodeEnum.plateCode.equals(code)) {
                return couponSendCodeEnum;
            }
        }
        return ERROR;
    }
}
