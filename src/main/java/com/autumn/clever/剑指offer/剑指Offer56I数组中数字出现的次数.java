package com.autumn.clever.剑指offer;

import java.util.Arrays;

/**
 * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
 * 输入：nums = [4,1,4,6]
 * 输出：[1,6] 或 [6,1]
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/6 下午3:18
 */
public class 剑指Offer56I数组中数字出现的次数 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 5, 2};
        System.out.println(Arrays.toString(singleNumbers2(nums)));
    }

    public static int[] singleNumbers2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int x = 0;
        int y = 0;
        int xoy = 0;
        int m = 1;
        for (int num : nums) {
            // 异或，相同为0，不同为1
            xoy ^= num;
        }
        while ((m & xoy) == 0) {
            // 找到异或计算结果中，为1的位置
            // &: 只要有0就为0，两个都为1，才为1，要想找到为1的那个值，需要让结果为1
            m <<= 1;
        }
        for (int num : nums) {
            if ((num & m) != 0) {
                x ^= num;
            } else {
                y ^= num;
            }
        }
        return new int[]{x, y};
    }

    public static int[] singleNumbers(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int x = 0;
        int y = 0;
        int xoy = 0;
        int m = 1;
        // 异或：相同为 0，不同为 1
        for (int num : nums) {
            xoy ^= num;
        }
        while ((m & xoy) == 0) {
            // 左移一位
            // 找到只出现一次的那两个数不同的二进制位置
            m <<= 1;
        }
        for (int num : nums) {
            if ((num & m) != 0) {
                x ^= num;
            } else {
                y ^= num;
            }
        }
        return new int[]{x, y};
    }
}
