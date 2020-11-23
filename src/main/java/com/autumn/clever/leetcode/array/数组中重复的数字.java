package com.autumn.clever.leetcode.array;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/11 18:31
 */
public class 数组中重复的数字 {
    public static void main(String[] args) {
        int[] array = {2, 3, 1, 0, 2, 5, 3};
        System.out.println(findRepeatNumber(array));
    }

    public static int findRepeatNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            Integer value = map.get(nums[i]);
            if (value != null && value == 1) {
                return nums[i];
            } else {
                map.put(nums[i], 1);
            }
        }
        return 0;
    }

//    public static int findRepeatNumber(int[] nums) {
//        // 数组中最小数
//        int min = nums[0];
//        // 数组中最大数
//        int max = nums[nums.length - 1];
//
//        for (int i = 0; i < nums.length; i++) {
//            min = nums[i] < min ? nums[i] : min;
//            max = nums[i] > max ? nums[i] : max;
//        }
//
//        int[] temp = new int[max + 1];
//        for (int i = 0; i < nums.length; i++) {
//            temp[nums[i]]++;
//        }
//
//        for (int i = min; i < temp.length; i++) {
//            if (temp[i] > 1) {
//                return i;
//            }
//        }
//        return 0;
//    }
}
