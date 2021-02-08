package com.autumn.clever.剑指offer;

import java.util.Arrays;

/**
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
 * 使得所有奇数位于数组的前半部分，所有偶数位于数组的后半部分。
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/6 下午8:58
 */
public class 剑指Offer21调整数组顺序使奇数位于偶数前面 {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4};
        exchange2(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static int[] exchange2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[left] % 2 == 1) {
                left++;
            }
            while (left < right && nums[right] % 2 == 0) {
                right--;
            }
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
        return nums;
    }


    public static int[] exchange(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        help(nums, 0, nums.length - 1);
        return nums;
    }

    public static void help(int[] nums, int left, int right) {
        if (left < right) {
            int index = getIndex(nums, left, right);
            help(nums, left, index - 1);
            help(nums, index + 1, right);
        }
    }

    public static int getIndex(int[] nums, int left, int right) {
        int temp = nums[left];
        while (left < right) {
            while (left < right && nums[right] % 2 == 0) {
                right--;
            }
            if (left < right) {
                nums[left++] = nums[right];
            }
            while (left < right && nums[left] % 2 == 1) {
                left++;
            }
            if (left < right) {
                nums[right--] = nums[left];
            }
        }
        nums[left] = temp;
        return left;
    }
}
