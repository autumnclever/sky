package com.autumn.clever.剑指offer;

/**
 * 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/12 下午9:00
 */
public class 剑指Offer64求1累加到n {
    public static void main(String[] args) {
        System.out.println(sumNums(9));
    }

    public static int sumNums(int n) {
        boolean result = n > 1 && (n += sumNums(n - 1)) > 0;
        return n;
    }
}
