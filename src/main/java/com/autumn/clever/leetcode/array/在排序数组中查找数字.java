package com.autumn.clever.leetcode.array;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/17 下午2:02
 */
public class 在排序数组中查找数字 {
    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        System.out.println(search(nums, 8));
    }

    public static int search(int[] nums, int target) {
        // 找到第一个大于 target 的值的索引
        // 找到第一个大于 target-1 的位置，本题中就是第一个 target 的索引
        // 二者做差，就是 target 在 nums 的个数
        return binaryRightSearch(nums, target) - binaryRightSearch(nums, target - 1);
    }

    /**
     * nums 中，找到第一个大于 target 的索引
     *
     * @param nums
     * @param target
     * @return
     */
    public static int binaryRightSearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] <= target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid - 1;
            }
        }
        return left;
    }
}
