package com.autumn.clever.leetcode.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/26 13:26
 */
public class 二叉树中和为某一值的路径 {
    static List<List<Integer>> result = null;
    static LinkedList<Integer> path = new LinkedList<>();

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(11);
        TreeNode node5 = new TreeNode(13);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(5);
        TreeNode node10 = new TreeNode(1);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node4.left = node7;
        node4.right = node8;
        node3.left = node5;
        node3.right = node6;
        node6.left = node9;
        node6.right = node10;

        List<List<Integer>> result = pathSum(node1, 22);
        for (List<Integer> r : result) {
            for (Integer i : r) {
                System.out.println(i);
            }
            System.out.println("-----");
        }
    }

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        result = new LinkedList<>();
        treePath(root, sum);
        return result;
    }

    public static void treePath(TreeNode root, int sum) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        sum -= root.val;
        if (sum == 0 && root.left == null && root.right == null) {
            result.add(new LinkedList<>(path));
        }
        treePath(root.left, sum);
        treePath(root.right, sum);
        path.removeLast();
    }
}
