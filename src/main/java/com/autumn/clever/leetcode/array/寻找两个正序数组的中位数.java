package com.autumn.clever.leetcode.array;

/**
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的中位数。
 * <p>
 * 进阶：你能设计一个时间复杂度为 O(log (m+n)) 的算法解决此问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/20 下午1:21
 */
public class 寻找两个正序数组的中位数 {
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
        int length = nums1.length + nums2.length;
        int index1 = 0;
        int index2 = 0;
        int left = 0;
        int right = 0;
        while ((index1 + index2) <= (length / 2)) {
            // 每次都把上一轮 right 的值赋值给 left，这样可以保存两个紧挨着的中位数
            left = right;
            if (index1 < nums1.length && (index2 >= nums2.length || nums1[index1] < nums2[index2])) {
                right = nums1[index1++];
            } else {
                right = nums2[index2++];
            }
        }
        if (length % 2 == 0) {
            return (left + right) / 2.0;
        } else {
            return right;
        }
    }
}
