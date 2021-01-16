package com.autumn.clever.leetcode.tree;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/13 下午4:40
 */
public class 二叉树的锯齿形层次遍历2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;
        List<List<Integer>> result = zigzagLevelOrder(root);
        for (List<Integer> r : result) {
            for (Integer i : r) {
                System.out.println(i);
            }
            System.out.println("------");
        }
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        LinkedList<TreeNode> list = new LinkedList();
        list.add(root);
        boolean direction = true;
        while (!list.isEmpty()) {
            LinkedList<Integer> result = new LinkedList<>();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = list.remove();
                if (direction) {
                    result.add(node.val);
                } else {
                    result.addFirst(node.val);
                }
                if (node.left != null) {
                    list.add(node.left);
                }
                if (node.right != null) {
                    list.add(node.right);
                }
            }
            results.add(result);
            direction = !direction;
        }
        return results;
    }
}
