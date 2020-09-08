package com.autumn.clever.leetcode.tree;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/24 18:13
 */
public class 二叉树的最近公共祖先 {
    public static void main(String[] args) {
        return;
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) {
            return null;
        }
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }
}