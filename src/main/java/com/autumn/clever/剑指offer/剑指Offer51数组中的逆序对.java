package com.autumn.clever.剑指offer;

/**
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组，求出这个数组中的逆序对的总数。
 * 输入: [7,5,6,4]
 * 输出: 5
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/4 下午9:32
 */
public class 剑指Offer51数组中的逆序对 {
    public static void main(String[] args) {
        int[] nums = {7, 5, 6, 4};
        System.out.println(reversePairs(nums));
    }

    public static int reversePairs(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }
        return helpPairs(nums, 0, nums.length - 1);
    }

    public static int helpPairs(int[] nums, int left, int right) {
        int count = 0;
        if (left < right) {
            int mid = (left + right) / 2;
            int leftCount = helpPairs(nums, left, mid);
            int rightCount = helpPairs(nums, mid + 1, right);
            count = mergeCount(nums, left, mid, right) + leftCount + rightCount;
        }
        return count;
    }

    public static int mergeCount(int[] nums, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        int count = 0;
        while (i <= mid && j <= right) {
            if (nums[i] <= nums[j]) {
                temp[k++] = nums[i++];
            } else {
                temp[k++] = nums[j++];
                count += (mid - i + 1);
            }
        }
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        while (j <= right) {
            temp[k++] = nums[j++];
        }
        for (int m = 0; m < temp.length; m++) {
            nums[left + m] = temp[m];
        }
        return count;
    }
}
