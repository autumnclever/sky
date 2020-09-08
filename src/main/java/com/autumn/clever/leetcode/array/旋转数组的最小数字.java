package com.autumn.clever.leetcode.array;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/6 14:02
 */
public class 旋转数组的最小数字 {
    public static void main(String[] args) {
        int[] array = {3, 4, 5, 1, 2};
        System.out.println(minArray(array));
    }

    public static int minArray(int[] numbers) {
        int low = 0;
        int high = numbers.length - 1;
        while (low < high) {
            // 获取中间索引值
            int middle = (low + high) / 2;
            if (numbers[middle] < numbers[high]) {
                // 如果中间位置的值比高位的值小 -> 最小值在middle的左边
                high = middle;
            } else if (numbers[middle] > numbers[high]) {
                // 如果中间位置的值比高位的值要大 -> 最小值在middle的右边，并且middle这一索引的值，肯定不是最小值，所以直接取middle+1
                low = middle + 1;
            } else {
                // 如果两值相同，缩小high的范围，往middle方向移动
                high -= 1;
            }
        }
        return numbers[low];
    }
}
