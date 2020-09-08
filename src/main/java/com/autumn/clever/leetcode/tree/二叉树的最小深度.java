package com.autumn.clever.leetcode.tree;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/30 14:15
 */
public class 二叉树的最小深度 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node2.right = node4;
        System.out.println(minDepth(root));
    }

    public static int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        int minDepth = Integer.MAX_VALUE;
        if (root.left != null) {
            minDepth = Math.min(minDepth(root.left), minDepth);
        }
        if (root.right != null) {
            minDepth = Math.min(minDepth(root.right), minDepth);
        }
        return minDepth + 1;
    }

//    public static int minDepth(TreeNode root) {
//        if (root == null) {
//            return 0;
//        }
//        if (root.left == null && root.right != null) {
//            return minDepth(root.right) + 1;
//        }
//        if (root.right == null && root.left != null) {
//            return minDepth(root.left) + 1;
//        }
//        return Math.min(getDepth(root.left), getDepth(root.right)) + 1;
//    }
//
//    public static int getDepth(TreeNode root) {
//        if (root == null) {
//            return 0;
//        } else {
//            return Math.max((getDepth(root.left)), getDepth(root.right)) + 1;
//        }
//    }
}
