package com.autumn.clever.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/5 11:38
 */
public class BSTIterator {
    static List<Integer> values = null;
    static int index;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(15);
        TreeNode node3 = new TreeNode(9);
        TreeNode node4 = new TreeNode(20);
//        root.left = node1;
//        root.right = node2;
//        node2.left = node3;
//        node2.right = node4;

        BSTIterator iterator = new BSTIterator(null);
        while (hasNext()) {
            System.out.println(next());
        }
    }

    public BSTIterator(TreeNode root) {
        values = new ArrayList<>();
        index = 0;
        getValues(root);
    }

    public static void getValues(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            getValues(root.left);
        }
        values.add(root.val);
        if (root.right != null) {
            getValues(root.right);
        }
    }

    public static int next() {
        return values.get(index++);
    }

    public static boolean hasNext() {
        return index <= values.size() - 1;
    }
}
