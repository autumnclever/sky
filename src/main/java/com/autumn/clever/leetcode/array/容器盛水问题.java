package com.autumn.clever.leetcode.array;

/**
 * 给定一个整形数组arr，已知其中所有的值都是非负的，将这个数组看作一个容器，请返回容器能装多少水。
 * 具体请参考样例解释
 * https://www.nowcoder.com/questionTerminal/f92929ec6e5642a690e7c197b0c40e38?orderByHotValue=1&mutiTagIds=1579&page=1&onlyReference=false
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/15 上午8:14
 */
public class 容器盛水问题 {
    public static void main(String[] args) {
        //        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int[] height = {4, 5, 1, 3, 2};
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
