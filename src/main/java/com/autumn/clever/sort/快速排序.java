package com.autumn.clever.sort;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/6 19:18
 */
public class 快速排序 {
    public static void main(String[] args) {
        int[] arr = {5, 7, 1, 8, 4};
        quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int index = sort(arr, left, right);
            quickSort(arr, 0, index - 1);
            quickSort(arr, index + 1, right);
        }
    }

    public static int sort(int[] arr, int left, int right) {
        int temp = arr[left];
        while (left < right) {
            while (left < right && arr[right] > temp) {
                right--;
            }
            if (left < right) {
                arr[left++] = arr[right];
            }
            while (left < right && arr[left] < temp) {
                left++;
            }
            if (left < right) {
                arr[right--] = arr[left];
            }
        }
        arr[left] = temp;
        return left;
    }
}
