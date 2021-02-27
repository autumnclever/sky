package com.autumn.clever.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: zhangqiuying
 * @Date: 2021/2/22 下午1:49
 */
public class 二叉树前序遍历 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        List<Integer> nodes = preorder(root);
        System.out.println(nodes.toString());
    }

    public static List<Integer> preorder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }

        List<Integer> nodes = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                nodes.add(root.val);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop().right;
        }
        return nodes;
    }
}
