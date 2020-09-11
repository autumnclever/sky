package com.autumn.clever.sort;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/9 20:45
 */
public class 希尔排序3 {
    public static void main(String[] args) {
        int[] arr = {5, 7, 1, 8, 4};
        sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void sort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        int k = array.length / 2;
        while (k > 0) {
            for (int i = k; i < array.length; i++) {
                int j = i;
                int temp = array[i];
                while (j >= k && array[j - k] > temp) {
                    array[j] = array[j - k];
                    j = j - k;
                }
                array[j] = temp;
            }
            k /= 2;
        }
    }

    public static void exchange(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
