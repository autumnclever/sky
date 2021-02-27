package com.autumn.clever.leetcode.others;

/**
 * @Author: zhangqiuying
 * @Date: 2021/2/17 下午12:43
 */
public class 进制转换 {
    public static void main(String[] args) {
        System.out.println(solve(7, 2));
    }

    /**
     * 进制转换
     *
     * @param M int整型 给定整数
     * @param N int整型 转换到的进制
     * @return string字符串
     */
    public static String solve(int M, int N) {
        if (M == 0) {
            return "";
        }
        String rule = "0123456789ABCDEF";
        StringBuilder sb = new StringBuilder();
        boolean sy = false;
        if (M < 0) {
            sy = true;
            M = -M;
        }
        while (M != 0) {
            sb.append(rule.charAt(M % N));
            M /= N;
        }
        if (sy) {
            sb.append('-');
        }
        return sb.reverse().toString();
    }
}