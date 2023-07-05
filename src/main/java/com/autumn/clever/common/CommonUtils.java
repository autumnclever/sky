package com.autumn.clever.common;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2023-06-16 14:50
 **/
public class CommonUtils {

    /**
     * 判断对象是否不为空
     *
     * @param reference 带判断对象
     * @param <T>       对象类型
     * @return true(对象非空)/false(对象为空)
     */
    public static <T> boolean checkNotNull(T reference) {
        return reference != null;
    }
}
