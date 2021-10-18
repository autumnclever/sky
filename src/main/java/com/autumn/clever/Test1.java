package com.autumn.clever;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.*;

/**
 * @Author: zhangqiuying
 * @Date: 2020/12/4 上午10:15
 */
public class Test1 {
    public static void main(String[] args) {
//        Date date = getOneDayAgoHourDate(12);
//        System.out.println(date.toString());
        Integer i = 2;

        System.out.println(i.equals(null));
    }

    public static Date getOneDayAgoHourDate(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(HOUR_OF_DAY, -hour);
        calendar.set(MINUTE, 0);
        calendar.set(SECOND, 0);
        calendar.set(MILLISECOND, 0);
        return calendar.getTime();
    }


}
