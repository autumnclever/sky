package com.autumn.clever.leetcode.array;

/**
 * @Author: zhangqiuying
 * @Date: 2021/2/26 上午8:56
 */
public class 最大子序和3 {
    public static void main(String[] args) {
        int[] array = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(array));
    }

    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int max = 0;
        int sum = 0;
        for (int num : nums) {
            sum = Math.max(sum, sum + num);
            max = Math.max(max, sum);
        }
        return max;
    }
}
