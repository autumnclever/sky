package com.autumn.clever.cattle;

/**
 * 操作给定的二叉树，将其变换为源二叉树的镜像。
 *
 * @Author: zhangqiuying
 * @Date: 2020/6/4 23:37
 */
public class Cattle18 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(8);
        TreeNode node1 = new TreeNode(6);
        TreeNode node2 = new TreeNode(10);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(7);
        TreeNode node5 = new TreeNode(9);
        TreeNode node6 = new TreeNode(11);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        printTreeNode(root);
        System.out.println("--------");
        Mirror(root);
        printTreeNode(root);
    }

    public static void Mirror(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return;
        }

        TreeNode node = root.left;
        root.left = root.right;
        root.right = node;
        Mirror(root.left);
        Mirror(root.right);
    }

    public static void printTreeNode(TreeNode root) {
        if (root.left != null) {
            printTreeNode(root.left);
        }
        System.out.print(root.val + "-");
        if (root.right != null) {
            printTreeNode(root.right);
        }
    }
}

