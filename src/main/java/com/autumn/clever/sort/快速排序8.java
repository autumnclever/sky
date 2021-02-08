package com.autumn.clever.sort;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/26 下午10:37
 */
public class 快速排序8 {
    public static void main(String[] args) {
        int[] arr = {5, 7, 1, 8, 4};
        sort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void sort(int[] array, int left, int right) {
        if (left < right) {
            int index = getIndex(array, left, right);
            sort(array, left, index - 1);
            sort(array, index + 1, right);
        }
    }

    public static int getIndex(int[] array, int left, int right) {
        int base = array[left];
        while (left < right) {
            while (left < right && array[right] > base) {
                right--;
            }
            if (left < right) {
                array[left++] = array[right];
            }
            while (left < right && array[left] < base) {
                left++;
            }
            if (left < right) {
                array[right--] = array[left];
            }
        }
        array[left] = base;
        return left;
    }
}
