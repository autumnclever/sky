package com.autumn.clever.leetcode.tree;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/5 09:51
 */
public class 最长同值路径 {
    static int result;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;
        System.out.println(longestUnivaluePath(root));
    }

    public static int longestUnivaluePath(TreeNode root) {
        result = 0;
        arrowLength(root);
        return result;
    }

    public static int arrowLength(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = arrowLength(root.left);
        int right = arrowLength(root.right);
        int arrowLeft = 0;
        int arrorRight = 0;
        if (root.left != null && root.left.val == root.val) {
            arrowLeft += left + 1;
        }
        if (root.right != null && root.right.val == root.val) {
            arrorRight += right + 1;
        }
        result = Math.max(result, arrowLeft + arrorRight);
        return Math.max(arrowLeft, arrorRight);
    }
}
