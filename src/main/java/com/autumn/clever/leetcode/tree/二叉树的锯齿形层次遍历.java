package com.autumn.clever.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/4 11:00
 */
public class 二叉树的锯齿形层次遍历 {
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
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        boolean direction = true;
        while (!nodes.isEmpty()) {
            LinkedList<Integer> values = new LinkedList<>();
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                // 移除并且返回链表的头结点
                TreeNode node = nodes.poll();
                if (direction) {
                    // 如果是正向，添加到value的结尾
                    values.add(node.val);
                } else {
                    // 如果是反向，添加到value的头部
                    values.addFirst(node.val);
                }

                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
            }
            result.add(values);
            direction = !direction;
        }
        return result;
    }
}
