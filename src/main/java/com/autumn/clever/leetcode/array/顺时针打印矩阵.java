package com.autumn.clever.leetcode.array;

/**
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/17 上午11:49
 */
public class 顺时针打印矩阵 {
    public static void main(String[] args) {
        int[][] matrix = {{1, 11}, {2, 12}, {3, 13}, {4, 14}, {5, 15}, {6, 16}, {7, 17}, {8, 18}, {9, 19}, {10, 20}};
        int[] array = spiralOrder(matrix);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i]);
        }
    }

    public static int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0];
        }
        int left = 0;   // 定义左边边缘索引
        int right = matrix[0].length - 1;   // 定义右边边缘索引
        int top = 0;    // 定义头部边缘索引
        int bottom = matrix.length - 1;     // 定义底部边缘索引
        int size = (right + 1) * (bottom + 1);
        int[] array = new int[size];
        int index = 0;  // 返回数组的索引
        while (true) {
            for (int i = left; i <= right; i++) {
                // 头部：从左到右遍历
                array[index++] = matrix[top][i];
            }
            if (++top > bottom) {
                // 头部横着遍历完一层，往下走一层，如果遇到底部边界，跳出循环
                break;
            }
            for (int i = top; i <= bottom; i++) {
                // 最右侧：从上到下遍历
                array[index++] = matrix[i][right];
            }
            if (--right < left) {
                // 最右侧竖着遍历完一列，往左移动一列，如果遇到了左边边界，跳出循环
                break;
            }
            for (int i = right; i >= left; i--) {
                // 底部：从右往左遍历
                array[index++] = matrix[bottom][i];
            }
            if (--bottom < top) {
                // 底部横着遍历完一层，往上移动一层，如果遇到头部边界，跳出循环
                break;
            }
            for (int i = bottom; i >= top; i--) {
                // 最左侧：从下到上遍历
                array[index++] = matrix[i][left];
            }
            if (++left > right) {
                // 最左侧竖着遍历完一列，往右移动一列，如果遇到了右边边界，跳出循环
                break;
            }
        }
        return array;
    }
}
