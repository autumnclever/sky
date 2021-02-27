package com.autumn.clever.leetcode.array;

/**
 * @Author: zhangqiuying
 * @Date: 2021/2/25 下午11:00
 */
public class 寻找两个正序数组的中位数2 {
    public static void main(String[] args) {
//        int[] nums1 = {1, 2};
//        int[] nums2 = {3, 4};

        int[] nums1 = {1, 3};
        int[] nums2 = {2};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if ((nums1 == null || nums1.length == 0) && (nums2 == null || nums2.length == 0)) {
            return 0.0d;
        }
        int len = nums1.length + nums2.length;
        int left = 0;
        int right = 0;
        int index1 = 0;
        int index2 = 0;
        while ((index1 + index2) <= len / 2) {
            left = right;
            if (index1 < nums1.length && (index2 >= nums2.length || nums1[index1] < nums2[index2])) {
                right = nums1[index1++];
            } else {
                right = nums2[index2++];
            }
        }
        if (len % 2 == 0) {
            return (left + right) / 2.0;
        } else {
            return right;
        }
    }
}
