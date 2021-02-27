package com.autumn.clever.leetcode.tree;

/**
 *
 * 题解：https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/solution/er-cha-shu-zhong-de-zui-da-lu-jing-he-by-ikaruga/
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/26 上午11:30
 */
public class 二叉树中的最大路径和2 {
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        TreeNode node1 = new TreeNode(-1);
//        TreeNode node2 = new TreeNode(3);
        root.left = node1;
//        root.right = node2;
        System.out.println(maxPathSum(root));
    }

    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        help(root);
        return max;
    }

    public static int help(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftMax = Math.max(0, help(root.left));
        int rightMax = Math.max(0, help(root.right));
        max = Math.max(max, leftMax + root.val + rightMax);
        return root.val + Math.max(leftMax, rightMax);
    }
}
