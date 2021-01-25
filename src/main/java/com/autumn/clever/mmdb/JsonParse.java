package com.autumn.clever.mmdb;

import com.alibaba.fastjson.JSONObject;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Calendar.DAY_OF_WEEK;
import static java.util.Calendar.HOUR_OF_DAY;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/21 下午1:05
 */
public class JsonParse {
    public static void main(String[] args) {
        String str = "[{\"week\":5,\"hour\":23,\"interval\":6},{\"week\":6,\"hour\":0,\"interval\":12}]\n";
        List<OrderInterval> list = JSONObject.parseArray(str, OrderInterval.class);
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(DAY_OF_WEEK);
        int hour = calendar.get(HOUR_OF_DAY);
        list = list.stream().filter(o -> o.getWeek() == week && o.getHour() == hour).limit(1).collect(Collectors.toList());
        list.stream().forEach(o -> {
                    System.out.println(o.toString());
                }
        );
    }

}

class OrderInterval {
    int week;
    int hour;
    int interval;

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    @Override
    public String toString() {
        return "OrderInterval{" +
                "week=" + week +
                ", hour=" + hour +
                ", interval=" + interval +
                '}';
    }
}
