package com.autumn.clever.leetcode.tree;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/29 20:15
 */
public class 平衡二叉树 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(4);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
//        System.out.println(isSymmetric(root));
        System.out.println(isBalanced(root));
    }

    public static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        return Math.abs(heightTree(root.left) - heightTree(root.right)) <= 1
                && isBalanced(root.left) && isBalanced(root.right);
//        return isLRBalanced(root.left, root.right);
    }

    public static boolean isLRBalanced(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        boolean isLBalanced = isLRBalanced(left.left, left.right);
        boolean isRBalanced = isLRBalanced(right.left, right.right);

        boolean isBalanced = true;
        if ((left == null) && (right != null && (right.left != null || right.right != null))) {
            isBalanced = false;
        }

        if ((right == null) && (left != null && (left.left != null || left.right != null))) {
            isBalanced = false;
        }

        return isLBalanced && isRBalanced && isBalanced;
    }

    public static int heightTree(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(heightTree(root.left), heightTree(root.right)) + 1;
        }
    }
}
