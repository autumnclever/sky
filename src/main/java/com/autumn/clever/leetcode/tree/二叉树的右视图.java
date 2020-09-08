package com.autumn.clever.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/4 10:44
 */
public class 二叉树的右视图 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        root.left = node1;
        root.right = node2;
        node1.right = node3;
        node2.right = node4;
        List<Integer> values = rightSideView(root);
        for (int i = 0; i < values.size(); i++) {
            System.out.println(values.get(i));
        }
    }

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        if (root == null) {
            return values;
        }
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        while (nodes != null && nodes.size() != 0) {
            List<Integer> cengValues = new ArrayList<>();
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodes.remove();
                cengValues.add(node.val);
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
            }
            // 只保存当前一层最后一个值即可
            values.add(cengValues.get(cengValues.size() - 1));
        }
        return values;
    }
}
