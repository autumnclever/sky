/**
 * Copyright (C), 2024-2025, baidu
 */
package com.autumn.clever.baidu.宝哥的异步任务;

import cn.hutool.core.util.NumberUtil;
import com.autumn.clever.baidu.enums.RedisKeyEnum;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * FileName: BdtDataJob
 * Author:   zhangqiuying
 * Date:     2025/4/19 19:23
 * Description:
 */
public class BdtDataJob {
    public static void main(String[] args) {
//        printBdtData();
//        parseAfsBuildKey();
//        testOptional();
        test();
    }

    private static void printBdtData() {
        String nowKey = "CP_BDT_DATA_20250418_1";

        if (nowKey.startsWith(RedisKeyEnum.CP_BDT_DATA.getRedisKey())) {
            String dateAndCnt = nowKey.substring(RedisKeyEnum.CP_BDT_DATA.getRedisKey().length());
            String[] dateAndCntArray = StringUtils.split(dateAndCnt, '_');
            if (dateAndCntArray == null
                    || dateAndCntArray.length != 2
                    || !NumberUtil.isInteger(dateAndCntArray[0])
                    || !NumberUtil.isInteger(dateAndCntArray[1])
                    || !(dateAndCntArray[0].length() == 8)) {
                System.out.println("key格式错误");
            }
        }

        int length = RedisKeyEnum.CP_BDT_DATA.getRedisKey().length();
        System.out.println(length);
    }

    private static void parseAfsBuildKey() {
//        String line = "63788104 婊¤冻";
        String line = "132753446	涓嶆弧瓒";
        String[] array = line.split("	");
        String label = array[1];
        List<Long> list = new ArrayList<>();
        if (label.equals("涓嶆弧瓒")) {
            Long shopId = Long.valueOf(array[0]);
            list.add(shopId);
            System.out.println(label + shopId);
        }
    }

    private static void testOptional() {
//        String value = "";
//        Optional<Integer> result = Optional.ofNullable(value).map(Integer::parseInt);
//        Integer num;
//        if (StringUtils.isNumeric(value)) {
//            num = Integer.parseInt(value);
//            System.out.println(num);
//        } else {
//
//        }
//        Optional<Integer> result = StringUtils.isNumeric(value) ? Optional.of(Integer.parseInt(value)) : Optional.of(0);
//        Integer num = result.isPresent() ? result.get() : 0;
//        System.out.println(num);
    }

    private static void test(){
        String value = "";
        boolean isNumeric = StringUtils.isNumeric(value);
        boolean isNumber = org.apache.commons.lang3.StringUtils.isNumeric(value);
        System.out.println(isNumeric);
        System.out.println(isNumber);
    }
}
