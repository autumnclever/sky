package com.autumn.clever.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/16 下午8:33
 */
public class 翻转二叉树2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(7);
        TreeNode node3 = new TreeNode(9);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(1);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        List<List<Integer>> result = getTreeValue(root);
        for (List<Integer> r : result) {
            for (Integer i : r) {
                System.out.print(i.intValue());
                System.out.print("->");
            }
            System.out.println("------");
        }

        TreeNode node = invertTree(root);
        List<List<Integer>> result1 = getTreeValue(node);
        for (List<Integer> r : result1) {
            for (Integer i : r) {
                System.out.print(i.intValue());
                System.out.print("->");
            }
            System.out.println("------");
        }
    }

    public static List<List<Integer>> getTreeValue(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(root);
        while (!treeNodes.isEmpty()) {
            List<Integer> values = new LinkedList<>();
            int size = treeNodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = treeNodes.remove();
                values.add(node.val);
                if (node.left != null) {
                    treeNodes.add(node.left);
                }
                if (node.right != null) {
                    treeNodes.add(node.right);
                }
            }
            result.add(values);
        }
        return result;
    }

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
