package com.autumn.clever.leetcode.array;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/31 上午12:09
 */
public class 最大子序和2 {
    public static void main(String[] args) {
        int[] array = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(array));
    }

    public static int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = nums[0];
        int max = sum;
        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(sum + nums[i], nums[i]);
            max = Math.max(sum, max);
        }
        return max;
    }
}
