package com.autumn.clever.sort;

import java.util.Arrays;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/13 18:17
 */
public class 快速排序6 {
    public static void main(String[] args) {
        int[] arr = {5, 7, 1, 8, 4};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int index = getIndex(array, left, right);
            quickSort(array, left, index - 1);
            quickSort(array, index + 1, right);
        }
    }

    public static int getIndex(int[] array, int left, int right) {
        // 每次都取最左边的数为基数
        int temp = array[left];
        while (left < right) {
            while (left < right && array[right] > temp) {
                // 只要最右边的数字大，就继续循环
                right--;
            }
            if (left < right) {
                array[left++] = array[right];
            }
            while (left < right && array[left] < temp) {
                left++;
            }
            if (left < right) {
                array[right--] = array[left];
            }
        }
        array[left] = temp;
        return left;
    }
}
