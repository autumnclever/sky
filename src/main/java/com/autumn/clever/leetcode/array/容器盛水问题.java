package com.autumn.clever.leetcode.array;

/**
 * @Author: zhangqiuying
 * @Date: 2021/2/15 上午8:14
 */
public class 容器盛水问题 {
    public static void main(String[] args) {
        //        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] height = {4,5,1,3,2};
        System.out.println(maxWater(height));
    }

    public static long maxWater(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int l = 0, r = arr.length - 1, area = 0;
        int min = Math.min(arr[l], arr[r]);
        while (l < r) {
            if (arr[l] < arr[r]) {
                l++;
                if (arr[l] < min) {
                    area += min - arr[l];
                } else {
                    min = Math.min(arr[l], arr[r]);
                }
            } else {
                r--;
                if (arr[r] < min) {
                    area += min - arr[r];
                } else {
                    min = Math.min(arr[r], arr[l]);
                }
            }
        }
        return area;
    }
}
