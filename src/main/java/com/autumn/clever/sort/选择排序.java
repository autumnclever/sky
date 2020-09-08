package com.autumn.clever.sort;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/7 21:27
 */
public class 选择排序 {
    public static void main(String[] args) {
        int[] arr = {5, 7, 1, 8, 4};
        selectionSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = array[min];
                array[min] = array[i];
                array[i] = temp;
            }
        }
    }
}
