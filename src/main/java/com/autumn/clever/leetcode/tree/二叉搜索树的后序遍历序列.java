package com.autumn.clever.leetcode.tree;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/2 17:39
 */
public class 二叉搜索树的后序遍历序列 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(3);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        int[] values1 = {1, 2, 3, 4, 5};
        int[] values2 = {4, 8, 6, 12, 16, 14, 10};
        int[] values3 = {1, 2, 5, 10, 6, 9, 4, 3};

//        System.out.println(verifyPostorder(values1));
//        System.out.println(verifyPostorder(values2));
        System.out.println(verifyPostorder(values3));
    }

    public static boolean verifyPostorder(int[] postorder) {
        if (postorder == null || postorder.length == 0 || postorder.length == 1) {
            return true;
        }
        return helper(postorder, 0, postorder.length - 1);
    }

    public static boolean helper(int[] postorder, int begin, int end) {
        if (postorder == null || (end - begin) <= 1) {
            return true;
        }
        int rootValue = postorder[end];
        int index = end - 1;
        for (; index >= begin; index--) {
            if (postorder[index] < rootValue) {
                // 找到第一个比根节点小的结点
                break;
            }
        }
        for (int i = begin; i <= index; i++) {
            if (postorder[i] > rootValue) {
                return false;
            }
        }
        return helper(postorder, begin, index) && helper(postorder, index + 1, end - 1);
    }
}
