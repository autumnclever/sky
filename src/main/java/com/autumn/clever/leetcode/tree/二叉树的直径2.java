package com.autumn.clever.leetcode.tree;

/**
 * 给定一棵二叉树，你需要计算它的直径长度。
 * 一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/14 下午1:03
 */
public class 二叉树的直径2 {
    // 定义直径返回结果
    static int result =0;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
//        root.left = node1;
//        root.right = node2;
//        node1.left = node3;
//        node1.right = node4;
        System.out.println(diameterOfBinaryTree(root));
    }

    public static int diameterOfBinaryTree(TreeNode root) {
        getDepth(root);
        return result;
    }

    /**
     * 这个方法仅仅只是用来求树的深度，在求树的深度的同时，去计算二叉树的直径。
     * 这里的二叉树的直径，说白了，就是找一个节点，这个结点的左孩子的最大深度+右孩子的最大深度的值是最大的。
     *
     * @param root
     * @return
     */
    public static int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = getDepth(root.left);
        int right = getDepth(root.right);
        result = Math.max(left + right, result);
        return Math.max(left, right) + 1;
    }
}
