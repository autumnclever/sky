package com.autumn.clever.sort;

import java.util.Arrays;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/19 19:16
 */
public class 希尔排序8 {
    public static void main(String[] args) {
        int[] arr = {5, 7, 1, 8, 4};
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void shellSort(int[] array) {
        int k = array.length / 2;
        while (k > 0) {
            for (int i = k; i < array.length; i++) {
                int j = i;
                int target = array[j];
                while (j > 0 && target < array[j - k]) {
                    array[j] = array[j - k];
                    j -= k;
                }
                array[j] = target;
            }
            k /= 2;
        }
    }
}
