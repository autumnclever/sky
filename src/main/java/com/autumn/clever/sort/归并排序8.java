package com.autumn.clever.sort;

import java.util.Arrays;

/**
 * 平均、最坏、最好时间复杂度 O(nlogn)
 * 空间复杂度 O(n)
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/26 下午10:58
 */
public class 归并排序8 {
    public static void main(String[] args) {
        int[] arr = {3, 9, 6, 2, 1, 8};
//        int[] arr = {3, 8};
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] array, int left, int right) {
        if (array == null || array.length == 0) {
            return;
        }
        int mid = (left + right) / 2;
        if (left < right) {
            sort(array, left, mid);
            sort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    public static void merge(int[] array, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (array[i] < array[j]) {
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
            array[left + m] = temp[m];
        }
    }
}
