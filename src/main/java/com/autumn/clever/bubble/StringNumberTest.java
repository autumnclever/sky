package com.autumn.clever.bubble;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.util.Strings;

/**
 * @Author: zhangqiuying
 * @Date: 2020/12/23 下午5:56
 */
public class StringNumberTest {
    public static void main(String[] args) {
        Request request = new Request();
        request.setCity("100023");
        request.setCity((Strings.isNotBlank(request.getCity()) && StringUtils.isNumeric(request.getCity())) ? request.getCity() : "1");
        System.out.println(request);
    }
}

class Request {
    String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Request{" +
                "city='" + city + '\'' +
                '}';
    }
}
