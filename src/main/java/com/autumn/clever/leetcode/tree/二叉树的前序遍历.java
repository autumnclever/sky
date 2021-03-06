package com.autumn.clever.leetcode.tree;

import com.autumn.clever.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/23 16:50
 */
public class 二叉树的前序遍历 {
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
        List<Integer> nodes = preorderTraversal(root);
        for (Integer node : nodes) {
            System.out.println(node);
        }
    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        if (root == null) {
            return nodes;
        }
        nodes.add(root.val);
        if (root.left != null) {
            nodes.addAll(preorderTraversal(root.left));
        }
        if (root.right != null) {
            nodes.addAll(preorderTraversal(root.right));
        }
        return nodes;
    }
}
