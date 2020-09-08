package com.autumn.clever.leetcode.tree;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/5 14:15
 */
public class 不同的二叉搜索树 {
    static int result = 0;

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(numTrees(i));
        }
//        System.out.println(numTrees(3));
    }

    public static int numTrees(int n) {
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1;
        G[2] = 2;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i + 1; j++) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

//    public static int numTrees(int n) {
//        int result = 0;
//        for (int i = 0; i <= n; i++) {
//            result += helper(i);
//        }
//        return result;
//    }

    public static int helper(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int left = 1;
        int right = 1;
        for (int i = 1; i <= n; i++) {
            left = helper(i - 1);
            right = helper(n - i);
        }
        return left * right;
    }
}
