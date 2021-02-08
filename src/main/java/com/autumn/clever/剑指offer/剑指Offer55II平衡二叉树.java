package com.autumn.clever.剑指offer;

/**
 * @Author: zhangqiuying
 * @Date: 2021/2/7 下午9:55
 */
public class 剑指Offer55II平衡二叉树 {
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
        return Math.abs(treeDepth(root.left) - treeDepth(root.right)) <= 1
                && isBalanced(root.left) && isBalanced(root.right);
    }

    public static int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return Math.max(treeDepth(root.left), treeDepth(root.right)) + 1;
    }
}
