package com.autumn.clever.sort;

/**
 * @Author: zhangqiuying
 * @Date: 2021/2/26 下午12:29
 */
public class 快速排序12 {
    public static void main(String[] args) {
        int[] arr = {5, 2, 3, 1, 4};
        sort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void sort(int[] nums, int left, int right) {
        if (left < right) {
            int index = getIndex(nums, left, right);
            sort(nums, left, index - 1);
            sort(nums, index + 1, right);
        }
    }

    public static int getIndex(int[] nums, int left, int right) {
        int temp = nums[left];
        while (left < right) {
            while (left < right && nums[right] > temp) {
                right--;
            }
            if (left < right) {
                nums[left++] = nums[right];
            }
            while (left < right && nums[left] < temp) {
                left++;
            }
            if (left < right) {
                nums[right--] = nums[left];
            }
        }
        nums[left] = temp;
        return left;
    }
}
