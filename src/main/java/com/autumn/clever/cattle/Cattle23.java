package com.autumn.clever.cattle;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
 * 如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 *
 * @Author: zhangqiuying
 * @Date: 2020/7/1 10:13
 */
public class Cattle23 {
    public static void main(String[] args) {
        int[] sequence = {4, 6, 7, 5};
        System.out.println(VerifySquenceOfBST(sequence));
    }

    public static boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        return verify(sequence, 0, sequence.length - 1);
    }

    public static boolean verify(int[] sequence, int start, int end) {
        if (start >= end) {
            return true;
        }
        int root = sequence[end];
        int index;
        for (index = start; index < end; index++) {
            if (sequence[index] > root) {
                break;
            }
        }
        for (int j = index; j < end; j++) {
            if (root > sequence[j]) {
                return false;
            }
        }
        return verify(sequence, start, index - 1) && verify(sequence, index, end - 1);
    }
}
