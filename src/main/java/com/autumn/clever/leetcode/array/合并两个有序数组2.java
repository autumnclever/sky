package com.autumn.clever.leetcode.array;

import java.util.Arrays;

/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 的空间大小等于 m + n，这样它就有足够的空间保存来自 nums2 的元素。
 * <p>
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * 题解：https://leetcode-cn.com/problems/merge-sorted-array/solution/88-by-ikaruga/
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/26 上午10:14
 */
public class 合并两个有序数组2 {
    public static void main(String[] args) {
        int[] nums1 = {2, 4, 6, 6, 6, 0, 0, 0};
        int[] nums2 = {3, 5, 7};
        merge(nums1, 5, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = nums1.length - 1;
        m--;
        n--;
        while (n >= 0) {
            while (m >= 0 && nums1[m] >= nums2[n]) {
                swap(nums1, nums1, i--, m--);
            }
            swap(nums1, nums2, i--, n--);
        }
    }

    public static void swap(int[] nums1, int[] nums2, int index1, int index2) {
        int a = nums1[index1];
        nums1[index1] = nums2[index2];
        nums2[index2] = a;
    }
}
