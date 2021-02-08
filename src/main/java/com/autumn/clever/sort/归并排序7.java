package com.autumn.clever.sort;

import java.util.Arrays;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/26 下午10:46
 */
public class 归并排序7 {
    public static void main(String[] args) {
        int[] arr = {3, 9, 6, 2, 1, 8};
//        int[] arr = {3, 8};
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] array, int left, int right) {
        int mid = (left + right) / 2;
        if (left < right) {
            sort(array, left, mid);
            sort(array, mid + 1, right);
            mergeSort(array, left, mid, right);
        }
    }

    public static void mergeSort(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (array[i] > array[j]) {
                temp[k++] = array[j++];
            } else {
                temp[k++] = array[i++];
            }
        }

        while (i <= mid) {
            temp[k++] = array[i++];
        }

        while (j <= right) {
            temp[k++] = array[j++];
        }
        for (int m = 0; m < temp.length; m++) {
            array[left + m] = temp[m];
        }
    }
}
