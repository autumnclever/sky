package com.autumn.clever.剑指offer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。
 * 如果有多对数字的和等于s，则输出任意一对即可。
 * <p>
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者 [7,2]
 * <p>
 * 输入：nums = [10,26,30,31,47,60], target = 40
 * 输出：[10,30] 或者 [30,10]
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/5 下午9:36
 */
public class 剑指Offer57和为s的两个数字 {
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int[] array = twoSum2(nums, 9);
        System.out.println(Arrays.toString(array));
    }

    public static int[] twoSum2(int[] nums, int target) {
        int[] arr = new int[2];
        if (nums == null || nums.length == 0) {
            return arr;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int temp = nums[left] + nums[right];
            if (temp == target) {
                arr[0] = nums[left];
                arr[1] = nums[right];
                return arr;
            } else if (temp > target) {
                right--;
            } else if (temp < target) {
                left++;
            }
        }
        return arr;
    }

    public static int[] twoSum(int[] nums, int target) {
        int[] arr = new int[2];
        if (nums == null || nums.length == 0) {
            return arr;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer value = map.get(target - nums[i]);
            if (value != null) {
                arr[0] = target - nums[i];
                arr[1] = nums[i];
                return arr;
            }
            map.put(nums[i], 0);
        }
        return arr;
    }
}
