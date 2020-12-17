package com.autumn.clever.mmdb;

import com.alibaba.fastjson.JSONObject;

/**
 * @Author: zhangqiuying
 * @Date: 2020/12/7 下午5:40
 */
public class TestController {
    public static void main(String[] args) {
        MovieVO movieVO = new MovieVO();
//        movieVO.setId(23423);
        String message = "查询大数据接口失败提醒: \n the request is: %s, \n the response is: {}";
        Object result = JSONObject.toJSON(movieVO);
        message = String.format(message, result);
        System.out.println(message);
    }
}