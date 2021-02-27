package com.autumn.clever.leetcode.tree;

/**
 * 题目理解错了 不要这个看这个
 *
 * @Author: zhangqiuying
 * @Date: 2020/9/1 10:52
 */
public class 二叉树中的最大路径和 {
    static int max = 0;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root.left = node1;
        root.right = node2;
        System.out.println(maxPathSum(root));
    }

    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        help(root, 0);
        return max;
    }

    public static void help(TreeNode root, int sum) {
        if (root == null) {
            max = Math.max(max, sum);
            return;
        }
        sum += root.val;
        help(root.left, sum);
        help(root.right, sum);
        sum -= root.val;
    }
}
