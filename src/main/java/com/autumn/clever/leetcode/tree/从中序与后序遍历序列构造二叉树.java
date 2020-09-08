package com.autumn.clever.leetcode.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/3 20:09
 */
public class 从中序与后序遍历序列构造二叉树 {

    public static void main(String[] args) {
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode root = buildTree(inorder, postorder);
        printTree(root);
    }

    public static void printTree(TreeNode root) {
        System.out.println(root.val);
        if (root.left != null) {
            printTree(root.left);
        }
        if (root.right != null) {
            printTree(root.right);
        }
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || inorder.length == 0 || postorder == null || postorder.length == 0) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            // 用于保存中序值，及索引
            map.put(inorder[i], i);
        }
        return buildTree(inorder, postorder, 0, inorder.length, 0, postorder.length, map);
    }

    public static TreeNode buildTree(int[] inorder, int[] postorder, int inBegin, int inEnd, int postBegin, int postEnd, Map<Integer, Integer> map) {
        if (postEnd - postBegin <= 0 || postEnd < 0) {
            return null;
        }
        // 根节点的值
        int rootValue = postorder[postEnd - 1];
        TreeNode root = new TreeNode(rootValue);
        // 根节点在中序数组中的位置
        int index = map.get(rootValue);
        int rightCount = inEnd - index - 1;

        root.left = buildTree(inorder, postorder, inBegin, index, postBegin, postEnd - rightCount - 1, map);
        root.right = buildTree(inorder, postorder, index + 1, inEnd, postEnd - rightCount - 1, postEnd - 1, map);
        return root;
    }
}
