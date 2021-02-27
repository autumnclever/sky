package com.autumn.clever.leetcode.others;

/**
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * 输入: 4
 * 输出: 2
 * ---------
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 * 由于返回类型是整数，小数部分将被舍去。
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sqrtx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/16 下午2:30
 */
public class x的平方根 {
    public static void main(String[] args) {
//        System.out.println(mySqrt(2));
//        System.out.println(mySqrt(3));
//        System.out.println(mySqrt(4));
//        System.out.println(mySqrt(5));
//        System.out.println(mySqrt(6));
//        System.out.println(mySqrt(7));
//        System.out.println(mySqrt(2147395599));

        System.out.println("------");

//        System.out.println(mySqrt2(2));
//        System.out.println(mySqrt2(3));
//        System.out.println(mySqrt2(4));
//        System.out.println(mySqrt2(5));
//        System.out.println(mySqrt2(6));
        System.out.println(mySqrt2(8));
//        System.out.println(mySqrt2(2147395599));
    }

    public static int mySqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        int left = 1;
        int right = x - 1;
        int mid = left;
        while (left <= right) {
            mid = (left + right) / 2;
            if (mid * mid <= x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return mid * mid > x ? mid - 1 : mid;
    }

    public static int mySqrt2(int x) {
        if (x == 0 || x == 1) {
            return x;
        }
        long left = 0;
        long right = x / 2 - 1;
        while (left <= right) {
            long mid = (left + right) >>> 1;
            if (mid * mid <= x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return (int) (left * left > x ? left - 1 : left);
    }
}
