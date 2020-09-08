package com.autumn.clever.leetcode.tree;

import com.autumn.clever.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 从下往上层级遍历
 *
 * @Author: zhangqiuying
 * @Date: 2020/8/23 18:16
 */
public class 二叉树的层次遍历II {
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
        List<List<Integer>> nodes = levelOrderBottom(root);
        for (List<Integer> node : nodes) {
            for (Integer i : node) {
                System.out.println(i);
            }
            System.out.println("----");
        }
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<List<Integer>> stack = new Stack<>();
        LinkedList<TreeNode> linkedList = new LinkedList();
        linkedList.add(root);
        while (!linkedList.isEmpty()) {
            List<Integer> valueList = new ArrayList<>();
            // 记录一层，节点的个数
            int size = linkedList.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = linkedList.remove();
                valueList.add(node.val);
                if (node.left != null) {
                    linkedList.add(node.left);
                }
                if (node.right != null) {
                    linkedList.add(node.right);
                }
            }
            stack.push(valueList);
        }
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
        return result;
    }
}
