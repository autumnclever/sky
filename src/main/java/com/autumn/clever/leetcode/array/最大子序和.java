package com.autumn.clever.leetcode.array;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * @Author: zhangqiuying
 * @Date: 2020/9/13 18:30
 */
public class 最大子序和 {
    public static void main(String[] args) {
        int[] array = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray2(array));
    }

    public static int maxSubArray(int[] nums) {
        int max = nums[0];
        int sum = 0;
        for (int num : nums) {
            if (sum >= 0) {
                sum += num;
            } else {
                sum = num;
            }
            max = Math.max(sum, max);
        }
        return max;
    }

    /**
     * 动态规划 - 怎么看怎么都觉得和上面的方法是一样的，只不过写法不同，理解方式有所偏差
     *
     * @param nums
     * @return
     */
    public static int maxSubArray2(int[] nums) {
        // 数组中，以第 i 结点为结尾的，最大连续子数组和
        int preNum = 0;
        int max = nums[0];
        for (int num : nums) {
            // 当前第 i 个元素的 preNum，取 第 i-1 个元素的 preNum + 1、第 i 个元素 之间的最大值
            // 即第 i-1 个元素的 preNum 是否 > 0，加上之后是否对于第 i 个元素的 preNum 数值有所帮助
            preNum = Math.max(preNum + num, num);
            // 记录 max，一直取最大值，也就是返回的结果
            max = Math.max(max, preNum);
        }
        return max;
    }
}
