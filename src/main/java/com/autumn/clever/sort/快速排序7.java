package com.autumn.clever.sort;

/**
 * 分治思想 + 双指针
 * 平均时间复杂度 O(nlogn);
 * 最坏时间复杂度 O(logn2);
 * 最好时间复杂度 O(nlogn);
 * 空间复杂度 O(nlogn);
 * 排序不稳定
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/20 下午1:58
 */
public class 快速排序7 {

    public static void main(String[] args) {
        int[] arr = {5, 7, 1, 8, 4};
        quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void quickSort(int[] nums, int left, int right) {
        if (left < right) {
            // index 是 nums 最左边的数字应该在 nums 中的位置
            int index = getIndex(nums, left, right);
            // 排序 [left, index -1] 的值
            quickSort(nums, left, index - 1);
            // 排序 [index + 1, right] 的值
            quickSort(nums, index + 1, right);
        }
    }

    public static int getIndex(int[] nums, int left, int right) {
        // 拿最左边的数字作为基准数
        int base = nums[left];
        while (left < right) {
            while (left < right && nums[right] > base) {
                // 从右往左开始遍历，遇到大于 base 的数字停止
                right--;
            }
            if (left < right) {
                // 把遇到的小于 base 放到 left 位置上去
                nums[left++] = nums[right];
            }
            while (left < right && nums[left] < base) {
                // 从左往右开始遍历，遇到大于 base 的数字停止
                left++;
            }
            if (left < right) {
                nums[right--] = nums[left];
            }
        }
        // 循环终止的条件是 left > right，此时 left 是 base 应该在的索引值
        nums[left] = base;
        return left;
    }
}
