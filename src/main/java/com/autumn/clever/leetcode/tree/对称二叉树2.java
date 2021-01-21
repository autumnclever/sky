package com.autumn.clever.leetcode.tree;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/16 下午4:57
 */
public class 对称二叉树2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(3);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        System.out.println(isSymmetric(root));
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isLRSymmetric(root.left, root.right);
    }

    public static boolean isLRSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if ((left == null && right != null) || (left != null && left == null)) {
            // 左右孩子不同时为 null -> 肯定不是对称的
            return false;
        }
        boolean leftIsSymmetric = isLRSymmetric(left.left, right.right);
        boolean rightIsSymmetric = isLRSymmetric(left.right, right.left);
        return left.val == right.val && leftIsSymmetric && rightIsSymmetric;
    }
}
