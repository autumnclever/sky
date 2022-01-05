package com.autumn.clever.grape;

import static com.autumn.clever.grape.ContentStatusEnum.OFFLINE;
import static com.autumn.clever.grape.ContentStatusEnum.TO_BE_ONLINE;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/11/15 4:40 下午
 */
public class Test {
    public static void main(String[] args) {
        ContentStatusEnum contentStatusEnum = ContentStatusEnum.getContentStatusEnum(1);
//        boolean isEqual = TO_BE_ONLINE.equals(contentStatusEnum);
        boolean istrue = TO_BE_ONLINE.equals(contentStatusEnum) || OFFLINE.equals(contentStatusEnum);
        boolean isEqual = !(TO_BE_ONLINE.equals(contentStatusEnum) || OFFLINE.equals(contentStatusEnum));
        System.out.println(istrue);
        System.out.println(isEqual);
    }
}
