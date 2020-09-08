package com.autumn.clever.leetcode.tree;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/29 19:49
 */
public class 对称二叉树 {
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

//    public static boolean isSymmetric(TreeNode root) {
//        if (root == null || (root.right == null && root.left == null)) {
//            return true;
//        }
//        boolean leftIsSymmetric = isSymmetric(root.left);
//        boolean rightIsSymmetric = isSymmetric(root.right);
//        if (root.left != null && root.right != null && root.left.val != root.right.val) {
//            return false;
//        }
//        return leftIsSymmetric && rightIsSymmetric && true;
//    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null || (root.right == null && root.left == null)) {
            return true;
        }

        return isLRSymmetric(root.left, root.right);
    }

    public static boolean isLRSymmetric(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }

        if ((left == null && right != null) || (left != null && right == null)) {
            return false;
        }

        boolean isResultLR = isLRSymmetric(left.left, right.right);
        boolean isResultRL = isLRSymmetric(left.right, right.left);

        boolean isResult = left.val == right.val;
        return isResultLR && isResultRL && isResult;
    }
}
