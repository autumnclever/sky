package com.autumn.clever.sort;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/26 下午11:13
 */
public class 冒泡排序2 {
    public static void main(String[] args) {
        int[] arr = {15, 19, 26, 3, 5, 2, 1};
        sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void sort(int[] array) {
        if (array == null || array.length == 0) {
            return;
        }

        int len = array.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
}
