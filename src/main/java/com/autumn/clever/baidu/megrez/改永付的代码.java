/**
 * Copyright (C), 2024-2024, baidu
 */
package com.autumn.clever.baidu.megrez;

import org.apache.logging.log4j.util.Strings;

/**
 * FileName: 改永付的代码
 * Author:   zhangqiuying
 * Date:     2024/10/10 12:49
 * Description:
 */
public class 改永付的代码 {

    public static void main(String[] args) {
//        String str = convertSendCouponResponse(null, null);
//        System.out.println(str);
//        SendCouponResp sendCouponResp = new SendCouponResp();
//        String str1 = convertSendCouponResponse(null, sendCouponResp);
//        System.out.println(str1);
//
//        String str2 = convertSendCouponResponse(SendCouponResultEnum.SUCCESS, sendCouponResp);
//        System.out.println(str2);
//
//        String str3 = convertSendCouponResponse(SendCouponResultEnum.COUPON_ALREADY_TAKE, sendCouponResp);
//        System.out.println(str2);

//        String errorStr = convertSendCouponResponse(SendCouponResultEnum.ERROR, null);
//        System.out.println(errorStr);
//
//        String errorStr2 = convertSendCouponResponse2(CouponSendCodeEnum.ERROR, null);
//        System.out.println(errorStr2);

//        SendCouponResp sendCouponResp = new SendCouponResp();
//
//        String errorStr3 = convertSendCouponResponse(SendCouponResultEnum.COUPON_ALREADY_TAKE, sendCouponResp);
//        System.out.println(errorStr3);
//
//        String errorStr4 = convertSendCouponResponse2(CouponSendCodeEnum.COUPON_ALREADY_TAKE, sendCouponResp);
//        System.out.println(errorStr4);

        String errorStr5 = convertSendCouponResponse(SendCouponResultEnum.ERROR, null);
        System.out.println(errorStr5);

        String errorStr6 = convertSendCouponResponse2(CouponSendCodeEnum.ERROR, null);
        System.out.println(errorStr6);
    }

    /**
     * 中台发劵统一返回结果
     *
     * @param sendCouponResultEnum 发券返回enum
     * @param sendCouponResp       发券统一返回
     * @return result
     */
    public static String convertSendCouponResponse(
            SendCouponResultEnum sendCouponResultEnum, SendCouponResp sendCouponResp) {
        String result = null;
        if (sendCouponResultEnum == SendCouponResultEnum.SUCCESS) {
            result = String.valueOf(sendCouponResp.getCouponTakeId());
            Long couponTakeId = sendCouponResp.getCouponTakeId();
            if (couponTakeId != null) {
                result = couponTakeId.toString();
            }
        } else if (sendCouponResultEnum == SendCouponResultEnum.COUPON_ALREADY_TAKE) {
            if (sendCouponResp != null) {
                Long couponTakeId = sendCouponResp.getCouponTakeId();
                if (couponTakeId != null) {
                    result = couponTakeId.toString();
                }
            }
        } else {
            result = "";
        }
        return result;
    }

    /**
     * 中台发劵统一返回结果
     *
     * @param couponSendCodeEnum 发券结果 code 码
     * @param sendCouponResp     发券统一返回
     * @return result
     */
    public static String convertSendCouponResponse2(CouponSendCodeEnum couponSendCodeEnum,
                                                    SendCouponResp sendCouponResp) {
        if (couponSendCodeEnum == null || sendCouponResp == null
                || sendCouponResp.getCouponTakeId() == null) {
            return Strings.EMPTY;
        }
        if (CouponSendCodeEnum.SUCCESS.equals(couponSendCodeEnum)
                || couponSendCodeEnum == CouponSendCodeEnum.COUPON_ALREADY_TAKE) {
            return String.valueOf(sendCouponResp.getCouponTakeId());
        }
        return Strings.EMPTY;
    }
}
