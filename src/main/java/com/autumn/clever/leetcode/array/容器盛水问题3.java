package com.autumn.clever.leetcode.array;

/**
 * @Author: zhangqiuying
 * @Date: 2021/2/15 上午8:47
 */
public class 容器盛水问题3 {
    public static void main(String[] args) {
        int[] height = {3, 1, 2, 5, 2, 4};
        System.out.println(maxWater(height));
    }

    public static long maxWater(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int left = 0;
        int right = arr.length - 1;
        int min = Math.min(arr[left], arr[right]);
        int cap = 0;
        while (left < right) {
            if (arr[left] < arr[right]) {
                left++;
                if (arr[left] < min) {
                    cap += min - arr[left];
                } else {
                    min = Math.min(arr[left], arr[right]);
                }
            } else {
                right--;
                if (arr[right] < min) {
                    cap += min - arr[right];
                } else {
                    min = Math.min(arr[left], arr[right]);
                }
            }
        }
        return cap;
    }
}
