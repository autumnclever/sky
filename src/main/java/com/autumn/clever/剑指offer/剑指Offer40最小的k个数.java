package com.autumn.clever.剑指offer;

/**
 * 输入整数数组 arr ，找出其中最小的 k 个数。
 * 例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/5 上午11:41
 */
public class 剑指Offer40最小的k个数 {
    public static void main(String[] args) {
        int[] array = {0, 0, 0, 2, 0, 1};
        int[] result = getLeastNumbers(array, 0);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }

    public static int[] getLeastNumbers(int[] arr, int k) {
        int[] result = new int[k];
        if (arr == null || arr.length == 0 || k <= 0) {
            return result;
        }
        sort(arr, 0, arr.length - 1, k);
        for (int i = 0; i < k; i++) {
            result[i] = arr[i];
        }
        return result;
    }

    public static void sort(int[] array, int left, int right, int k) {
        if (left < right) {
            int index = getIndex(array, left, right);
            sort(array, left, index - 1, k);
            if (index >= k - 1) {
                return;
            }
            sort(array, index + 1, right, k);
        }
    }

    public static int getIndex(int[] array, int left, int right) {
        int temp = array[left];
        while (left < right) {
            while (left < right && array[right] > temp) {
                right--;
            }
            if (left < right) {
                array[left++] = array[right];
            }
            while (left < right && array[left] < temp) {
                left++;
            }
            if (left < right) {
                array[right--] = array[left];
            }
        }
        array[left] = temp;
        return left;
    }
}
