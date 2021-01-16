package com.autumn.clever.leetcode.tree;

/**
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/14 下午1:36
 */
public class 路径总和2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(11);
        TreeNode node4 = new TreeNode(13);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(2);
        TreeNode node8 = new TreeNode(1);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
//        node1.right = node4;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node5.right = node8;
        System.out.println(hasPathSum2(root, 22));
    }

    public static boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }
        return hasPathSum2(root.left, sum - root.val) || hasPathSum2(root.right, sum - root.val);
    }

    public static boolean hasPathSum(TreeNode root, int sum) {
        Boolean hasPath = false;
        isPathSum(root, sum, hasPath);
        return hasPath;
    }

    public static void isPathSum(TreeNode root, int sum, Boolean hasPath) {
        if (root == null) {
            return;
        }
        sum -= root.val;
        if (root.left == null && root.right == null && sum == 0) {
            hasPath = true;
        }
        isPathSum(root.left, sum, hasPath);
        isPathSum(root.right, sum, hasPath);
    }
}
