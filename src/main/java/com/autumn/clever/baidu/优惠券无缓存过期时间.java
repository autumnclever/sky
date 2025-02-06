/**
 * Copyright (C), 2024-2024, baidu
 */
package com.autumn.clever.baidu;

import com.autumn.clever.baidu.营销的一些实体类.CouponInfo;
import com.autumn.clever.baidu.营销的一些实体类.TimeUnitEnum;
import com.autumn.clever.baidu.营销的一些实体类.UseTimeEnum;
import com.google.common.collect.Lists;
import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * FileName: 优惠券无缓存过期时间
 * Author:   zhangqiuying
 * Date:     2024/7/1 18:08
 * Description:
 */
public class 优惠券无缓存过期时间 {

    /**
     * 2天的毫秒数
     */
    public static final long TWO_DAY_MILLI_SECOND = 2 * 24 * 60 * 60 * 1000;

    public static void main(String[] args) {
        List<String> allBatchIdList = Lists.newArrayList("13545629650", "13548476786", "13549168700", "13549462719", "13545629649", "13545629650", "13548476786", "13548735558", "13549168700", "13549462719", "13545629649", "13545629650", "13548476786", "13548564198", "13548735558", "13549168700", "13549409709", "13549462719", "13545629649", "13545629650", "13548476786", "13548564198", "13548735558", "13549168700", "13549409709", "13545629649", "13545629650", "13548476786", "13548564198", "13548735558", "13549168700", "13549409709", "13549462719", "13545629649", "13545629650", "13549168700", "13549409709", "13549462719", "13545629649", "13545629650", "13548476786", "13548564198", "13548735558", "13549168700", "13549409709", "13549462719", "13545629649", "13545629650", "13548476786", "13548564198", "13548735558", "13549168700", "13549409709", "13549462719", "13545629649", "13545629650", "13548476786", "13548564198", "13548735558", "13549168700", "13549409709", "13549462719", "13545629649", "13545629650", "13548476786", "13548564198", "13548735558", "13549409709", "13549462719", "13548476786", "13548564198", "13548735558", "13549409709", "13545629649", "13545629650", "13548476786", "13548564198", "13548735558", "13549168700", "13549409709", "13549462719", "13545629649", "13548476786", "13549168700", "13549409709", "13549462719", "13548476786", "13548564198", "13548735558", "13549409709", "13545629649", "13545629650", "13548476786", "13548564198", "13548735558", "13549168700", "13549409709", "13549462719", "13545629649", "13545629650", "13548476786", "13548564198", "13548735558", "13549409709", "13549462719", "13549168700", "13548476786", "13548564198", "13548735558", "13549168700", "13549462719", "13545629649", "13548476786", "13548564198", "13548735558", "13549168700", "13549462719", "13548476786", "13548564198", "13548735558", "13549168700", "13549409709", "13549462719", "13545629649", "13548735558", "13549462719", "13545629650", "13548476786", "13548564198", "13545629649", "13545629650", "13548476786", "13548564198", "13548735558", "13549168700", "13549409709", "13549462719", "13545629649", "13545629650", "13548476786", "13548564198", "13548735558", "13549168700", "13545629649", "13545629650", "13549168700", "13549409709", "13549462719", "13545629649", "13545629650", "13548476786", "13548564198", "13548735558", "13549168700", "13549409709", "13549462719", "13549168700", "13545629650", "13548476786", "13548564198", "13545629649", "13545629650", "13545629649", "13549462719", "13548476786", "13549409709", "13545629650", "13548564198", "13548735558", "13549168700", "13549409709", "13545629649", "13545629650", "13548476786", "13548564198", "13548735558", "13549168700", "13549409709", "13549462719", "13548476786", "13548564198", "13549168700", "13545629649", "13545629650", "13548476786", "13548564198", "13548735558", "13549168700", "13548564198", "13548735558", "13548564198", "13548735558");
        List<String> needDealBatchIdList = allBatchIdList
                .stream()
                .distinct().collect(Collectors.toList());
        System.out.println(needDealBatchIdList);

        CouponInfo couponInfo = new CouponInfo();
        couponInfo.setUseTimeType(2);
        couponInfo.setTimeUnit(2);
        couponInfo.setTimeValue(31);
        Date startTime = new Date(1861372800000L);
        System.out.println("startTime:" + startTime);
        couponInfo.setTakeEnd(startTime);

        Long expireAtTimeMS = getCouponExpireEndTime(couponInfo);
        System.out.println(expireAtTimeMS);
        System.out.println("expireAtTimeMS:" + expireAtTimeMS);
        Date now = new Date();

        long expireMilliSeconds = expireAtTimeMS - now.getTime() + TWO_DAY_MILLI_SECOND;
        System.out.println("expireMilliSeconds:" + expireMilliSeconds);

//        Date startTime = new Date(1861372800000L);
//        System.out.println("startTime:" + startTime);
//        Pair<Date, Date> expireTime = getCouponExpireTime(couponInfo, startTime);
//        Date endDate = expireTime.getRight();
//        System.out.println(endDate);
//        System.out.println("endDate:" + endDate);
    }

    public static Long getCouponExpireEndTime(CouponInfo couponInfo) {
        Pair<Date, Date> expireTime = getCouponExpireTime(couponInfo, couponInfo.getTakeEnd());
        if (expireTime == null) {
            return -1L;
        }
        Date endDate = expireTime.getRight();
        System.out.println("endDate:" + endDate);
        return endDate.getTime();
    }

    public static Pair<Date, Date> getCouponExpireTime(CouponInfo couponInfo, Date startTime) {
        UseTimeEnum useTimeEnum = UseTimeEnum.getUseTimeEnum(couponInfo.getUseTimeType());
        if (useTimeEnum == UseTimeEnum.SET_BEGIN_END || useTimeEnum == null) {
            return Pair.of(couponInfo.getUseStart(), couponInfo.getUseEnd());
        } else {
            TimeUnitEnum timeUnitEnum = TimeUnitEnum.getTimeUnitEnum(couponInfo.getTimeUnit());
            // 领取结束时间
            Date endTime = startTime;
            Integer timeValue = couponInfo.getTimeValue();
            if (UseTimeEnum.TAKE_BEGIN.equals(useTimeEnum)) {
                if (timeUnitEnum == TimeUnitEnum.HOUR) {
                    endTime = DateUtils.addHours(startTime, timeValue);
                } else if (timeUnitEnum == TimeUnitEnum.DAY) {
                    endTime = DateUtils.addDays(startTime, timeValue);
                } else if (timeUnitEnum == TimeUnitEnum.MONTH) {
                    endTime = DateUtils.addMonths(startTime, timeValue);
                }
                return Pair.of(startTime, endTime);
            } else if (UseTimeEnum.TAKE_BEGIN_NATURAL_TIME.equals(useTimeEnum)) {
                if (TimeUnitEnum.DAY.equals(timeUnitEnum)) {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(endTime);
                    calendar.add(Calendar.DATE, timeValue);
                    calendar.set(Calendar.HOUR_OF_DAY, NumberUtils.INTEGER_ZERO);
                    calendar.set(Calendar.MINUTE, NumberUtils.INTEGER_ZERO);
                    calendar.set(Calendar.SECOND, NumberUtils.INTEGER_ZERO);
                    calendar.set(Calendar.MILLISECOND, NumberUtils.INTEGER_ZERO);
                    endTime = calendar.getTime();
                }
                return Pair.of(startTime, endTime);
            }
        }
        return null;
    }
}
