package com.autumn.clever.mmdb;

import org.apache.logging.log4j.util.Strings;

import java.util.Calendar;
import java.util.Date;

import static java.util.Calendar.*;

/**
 * @Author: zhangqiuying
 * @Date: 2020/12/16 下午4:34
 */
public class StandardDateTest {
    private static final String SEC_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static void main(String[] args) {
        String str1 = "2020";
        String str2 = "2020-01";
        String str3 = "2020-10-09";
        String str4 = "2020-11-11 ";
        String str5 = "2020-11-11 13";
        String str6 = "2020-11-11 13:09";
        String str7 = "2020-11-11 13:12:07";
        String str8 = "2020-11-11 13:20:07";

        Date date1 = getStandardDate(str1);
        Date date2 = getStandardDate(str2);
        Date date3 = getStandardDate(str3);
        Date date4 = getStandardDate(str4);
        Date date5 = getStandardDate(str5);
        Date date6 = getStandardDate(str6);
        Date date7 = getStandardDate(str7);
        Date date8 = getStandardDate(str8);

        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date3);
        System.out.println(date4);
        System.out.println(date5);
        System.out.println(date6);
        System.out.println(date7);
        System.out.println(date8);
    }

    public static Date getStandardDate(String strTime) {
        try {
            if (Strings.isBlank(strTime)) {
                return null;
            }
            String[] timeArr = strTime.split(" ");
            if (timeArr == null || timeArr.length == 0) {
                return null;
            }

            Calendar calendar = Calendar.getInstance();

            String[] ymdArr = timeArr[0].split("-");
            if (ymdArr.length >= 1) {
                calendar.set(YEAR, Integer.valueOf(ymdArr[0]));
                calendar.set(MONTH, DECEMBER);
                calendar.set(DAY_OF_MONTH, 31);
                calendar.set(HOUR_OF_DAY, 0);
                calendar.set(MINUTE, 0);
                calendar.set(SECOND, 0);
                calendar.set(MILLISECOND, 0);
            }
            if (ymdArr.length >= 2) {
                calendar.set(DAY_OF_MONTH, 1);
                calendar.set(MONTH, Integer.valueOf(ymdArr[1]) - 1);
                calendar.add(MONTH, 1);
                calendar.add(DAY_OF_MONTH, -1);
            }
            if (ymdArr.length == 3) {
                calendar.set(DAY_OF_MONTH, Integer.valueOf(ymdArr[2]));
            }

            if (timeArr.length == 2) {
                String[] hmsArr = timeArr[1].split(":");
                if (hmsArr.length >= 1) {
                    calendar.set(HOUR_OF_DAY, Integer.valueOf(hmsArr[0]));

                }
                if (hmsArr.length >= 2) {
                    calendar.set(MINUTE, Integer.valueOf(hmsArr[1]));
                }
                if (hmsArr.length == 3) {
                    calendar.set(SECOND, Integer.valueOf(hmsArr[2]));
                }
            }

            return calendar.getTime();
        } catch (Exception e) {

        }
        return null;
    }
}
