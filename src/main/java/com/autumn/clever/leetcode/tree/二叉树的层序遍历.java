package com.autumn.clever.leetcode.tree;

import com.autumn.clever.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 从上往下层级遍历
 *
 * @Author: zhangqiuying
 * @Date: 2020/8/23 18:43
 */
public class 二叉树的层序遍历 {
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
        List<List<Integer>> nodes = levelOrder(root);
        for (List<Integer> node : nodes) {
            for (Integer i : node) {
                System.out.println(i);
            }
            System.out.println("----");
        }
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        while (list.size() > 0) {
            List<Integer> array = new ArrayList<>();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = list.remove();
                array.add(node.val);
                if (node.left != null) {
                    list.add(node.left);
                }
                if (node.right != null) {
                    list.add(node.right);
                }
            }
            result.add(array);
        }
        return result;
    }
}
