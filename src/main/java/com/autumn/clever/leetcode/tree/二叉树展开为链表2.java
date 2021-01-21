package com.autumn.clever.leetcode.tree;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，原地将它展开为一个单链表。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/16 下午12:38
 */
public class 二叉树展开为链表2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(6);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;
        flatten2(root);
        printTree(root);
    }

    public static void printTree(TreeNode root) {
        List<List<Integer>> result = Lists.newArrayList();
        helper(root, result);
        for (List<Integer> array : result) {
            System.out.println(JSON.toJSONString(array));
        }
    }

    public static void helper(TreeNode root, List<List<Integer>> result) {
        if (root == null) {
            return;
        }

        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            List<Integer> layer = new ArrayList<>();
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodes.remove();
                layer.add(node.val);
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
            }
            result.add(layer);
        }
    }

    public static void flatten2(TreeNode root) {
        while (root != null) {
            if (root.left != null) {
                // 根结点的左孩子
                TreeNode left = root.left;
                // 根结点右孩子的前置节点
                TreeNode rightPre = left;
                while (rightPre.right != null) {
                    // 一直遍历，找到左孩子的最右节点
                    rightPre = rightPre.right;
                }
                // 根结点的右孩子整体移动到前置结点的右孩子上
                rightPre.right = root.right;
                // 根结点的左孩子全部移动到根结点的右孩子上
                root.right = left;
                // 根结点左孩子置空
                root.left = null;
            }
            root = root.right;
        }
    }

    public static void flatten(TreeNode root) {
        if (root == null || root.left == null) {
            return;
        }
        // 找到右孩子的前置节点，即左孩子中最右边的那个结点
        TreeNode pre = root.left;
        if (pre != null) {
            while (pre.right != null) {
                pre = pre.right;
            }
        }
        // 根结点的右孩子放到 pre 的右孩子上
        pre.right = root.right;
        // 根结点的右孩子置空
        root.right = null;
    }
}
