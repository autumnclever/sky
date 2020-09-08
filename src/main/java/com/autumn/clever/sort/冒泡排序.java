package com.autumn.clever.sort;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/6 16:53
 */
public class 冒泡排序 {
    public static void main(String[] args) {
        int[] arr = {15, 19, 26, 3, 5, 2, 1};
        sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static int[] sort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }
}
