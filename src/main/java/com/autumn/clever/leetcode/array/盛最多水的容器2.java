package com.autumn.clever.leetcode.array;

/**
 * @Author: zhangqiuying
 * @Date: 2021/2/26 上午10:40
 */
public class 盛最多水的容器2 {
    public static void main(String[] args) {
        int[] height = {3, 1, 2, 5, 2, 4};
        System.out.println(maxArea(height));
    }

    public static int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int left = 0;
        int right = height.length - 1;
        int max = 0;
        while (left < right) {
            max = height[left] < height[right] ?
                    Math.max(max, (right - left) * height[left++]) :
                    Math.max(max, (right - left) * height[right--]);
        }
        return max;
    }
}
