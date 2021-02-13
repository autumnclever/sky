package com.autumn.clever.剑指offer;

import java.util.Arrays;

/**
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。
 * 输入: n = 1
 * 输出: [1,2,3,4,5,6,7,8,9]
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/12 下午6:23
 */
public class 剑指Offer17打印从1到最大的n位数 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(printNumbers(2)));
    }

    public static int[] printNumbers(int n) {
        int end = (int) (Math.pow(10.0d, Double.valueOf(n)) - 1);
        int[] arr = new int[end];
        for (int i = 0; i < end; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }
}
