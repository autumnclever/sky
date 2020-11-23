package com.autumn.clever.sort;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/19 18:45
 */
public class 希尔排序6 {
    public static void main(String[] args) {

    }

    public static void shellSort(int[] array) {
        if (array == null) {
            return;
        }
        int len = array.length;
        int k = len / 2;
        while (k > 0) {
            for (int i = k; i < len; i++) {
                int j = i;
                int target = array[i];
                while (j >= k && target < array[j - k]) {
                    array[j] = array[j - k];
                    j -= k;
                }
                array[j] = target;
            }
            k /= 2;
        }
    }
}
