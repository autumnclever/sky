package com.autumn.clever.sort;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/9 21:47
 */
public class 计数排序1 {
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
        int len = array.length;
        int min = array[0];
        int max = array[array.length - 1];
        for (int i = 0; i < len; i++) {
            min = min > array[i] ? array[i] : min;
            max = max < array[i] ? array[i] : max;
        }

        int[] temp = new int[max + 1];
        for (int i = 0; i < array.length; i++) {
            temp[array[i]]++;
        }

        int k = 0;
        for (int i = min; i < temp.length; i++) {
            while (temp[i] > 0) {
                array[k++] = i;
                temp[i]--;
            }
        }
    }
}
