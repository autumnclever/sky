package com.autumn.clever.leetcode.tree;

import java.util.Stack;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/25 10:14
 */
public class 二叉搜索树的第k大节点 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(2);
        root.left = node1;
        root.right = node2;
        node1.right = node3;

        int result = kthLargest(root, 4);
        System.out.println(result);
    }

    public static int kthLargest(TreeNode root, int k) {
        Stack<Integer> stack = getTreeStack(root);
        return stack.get(stack.size() - k);
    }

    public static Stack getTreeStack(TreeNode root) {
        Stack<Integer> stack = new Stack();
        if (root == null) {
            return stack;
        }
        if (root.left != null) {
            stack.addAll(getTreeStack(root.left));
        }
        stack.add(root.val);
        if (root.right != null) {
            stack.addAll(getTreeStack(root.right));
        }
        return stack;
    }
}
