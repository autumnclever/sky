package com.autumn.clever.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/11 下午10:47
 */
public class 二叉树的中序遍历2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        List<Integer> nodes = inorderTraversal2(root);
        for (Integer node : nodes) {
            System.out.println(node);
        }
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        if (root == null) {
            return nodes;
        }
        nodes.addAll(inorderTraversal(root.left));
        nodes.add(root.val);
        nodes.addAll(inorderTraversal(root.right));
        return nodes;
    }

    public static List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        if (root == null) {
            return nodes;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            nodes.add(root.val);
            root = root.right;
        }
        return nodes;
    }

    public static List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        if (root == null) {
            return nodes;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            nodes.add(root.val);
            root = root.right;
        }
        return nodes;
    }
}
