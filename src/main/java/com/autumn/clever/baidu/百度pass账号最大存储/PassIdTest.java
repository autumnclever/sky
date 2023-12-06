package com.autumn.clever.baidu.百度pass账号最大存储;

import jodd.util.MathUtil;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-12-04 17:59
 **/
public class PassIdTest {


    public static void main(String[] args) {
        String passId = "9223372036854775807";
        byte[] bytes = passId.getBytes();
        System.out.println(bytes.length);
    }



}
