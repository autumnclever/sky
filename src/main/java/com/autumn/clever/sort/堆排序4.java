package com.autumn.clever.sort;

import java.util.Arrays;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/9 11:23
 */
public class 堆排序4 {
    public static void main(String[] args) {
        int[] arr = {72, 6, 57, 88, 60, 42, 83, 73, 48, 85};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        int len = array.length;
        for (int i = len / 2 - 1; i >= 0; i--) {
            adjust(array, i, len);
        }
        for (int i = len - 1; i > 0; i--) {
            exchange(array, 0, i);
            adjust(array, 0, i);
        }
    }

    public static void adjust(int[] array, int i, int len) {
        int temp = array[i];
        for (int k = 2 * i + 1; k < len; k = 2 * k + 1) {
            while (k + 1 < len && array[k] < array[k + 1]) {
                // 比较左右子孩子的大小，k保存
                k++;
            }
            if (temp < array[k]) {
                array[i] = array[k];
                i = k;
            } else {
                break;
            }
        }
        array[i] = temp;
    }

    public static void exchange(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
