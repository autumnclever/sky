package com.autumn.clever.leetcode.others;

/**
 * @Author: zhangqiuying
 * @Date: 2021/2/26 下午3:55
 */
public class two的幂 {
    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(16));
    }

    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
