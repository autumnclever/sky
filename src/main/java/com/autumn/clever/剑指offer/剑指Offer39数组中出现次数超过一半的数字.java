package com.autumn.clever.剑指offer;

import java.util.Arrays;

/**
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 * 输出: 2
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/12 下午9:54
 */
public class 剑指Offer39数组中出现次数超过一半的数字 {
    public static void main(String[] args) {
//        int[] nums = {1, 2, 3, 2, 2, 2, 5, 4, 2};
        int[] nums = {3, 2, 3};
        System.out.println(majorityElement(nums));
    }

    public static int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length / 2];
//        int mid = nums.length / 2;
//        int num = nums[mid];
//        int left = mid;
//        int right = mid + 1;
//        while (nums[left] == num) {
//            left--;
//        }
//        while (nums[right] == num) {
//            right--;
//        }
//        int count = right-left+1;
//        return count>mid
    }
}
