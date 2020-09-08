package com.autumn.clever.leetcode.tree;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/1 21:40
 */
public class 验证二叉搜索树 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(15);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(20);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;
        System.out.println(isValidBST(root));

//        TreeNode root2 = new TreeNode(10);
//        TreeNode node11 = new TreeNode(5);
//        TreeNode node2 = new TreeNode(4);
//        TreeNode node33 = new TreeNode(3);
//        TreeNode node6 = new TreeNode(6);
//        root2.left = node11;
//        root2.right = node2;
//        node2.left = node33;
//        node2.right = node6;
//        System.out.println(isValidBST(root2));
    }

    public static boolean help(TreeNode root, Integer small, Integer big) {
        if (root == null) {
            return true;
        }
        int value = root.val;
        if (small != null && value <= small) {
            return false;
        }
        if (big != null && value >= big) {
            return false;
        }
        if (!help(root.left, small, value)) {
            return false;
        }
        if (!help(root.right, value, big)) {
            return false;
        }
        return true;
    }

    public static boolean isValidBST(TreeNode root) {
        return helper(root, null, null);
    }

    public static boolean helper(TreeNode node, Integer lower, Integer upper) {
        if (node == null) return true;

        int val = node.val;
        if (lower != null && val <= lower) return false;
        if (upper != null && val >= upper) return false;

        if (!helper(node.right, val, upper)) return false;
        if (!helper(node.left, lower, val)) return false;
        return true;
    }
//    public static boolean isValidBST(TreeNode root) {
//        if (root == null) {
//            return true;
//        }
//        boolean leftIsValid = true;
//        if (root.left != null) {
//            leftIsValid = root.left.val < root.val && isValidBST(root.left);
//        }
//        boolean rightIsValid = true;
//        if (root.right != null) {
//            rightIsValid = root.right.val > root.val && isValidBST(root.right);
//        }
//        return leftIsValid && rightIsValid;
//    }
}
