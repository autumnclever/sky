package com.autumn.clever.sort;

import java.util.Arrays;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/11 15:20
 */
public class 归并排序3 {
    public static void main(String[] args) {
        int[] arr = {3, 9, 6, 2, 1, 8};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] array, int left, int right) {
        if (array == null || array.length == 0) {
            return;
        }
        int mid = (left + right) / 2;
        if (left < right) {
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            sort(array, left, mid, right);
        }
    }

    public static void sort(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (array[i] <= array[j]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }

        while (i <= mid) {
            temp[k++] = array[i++];
        }

        while (j <= right) {
            temp[k++] = array[j++];
        }

        for (int m = 0; m < temp.length; m++) {
            array[m + left] = temp[m];
        }
    }
}
