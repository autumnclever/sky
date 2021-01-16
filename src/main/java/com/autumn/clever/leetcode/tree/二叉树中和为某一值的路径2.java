package com.autumn.clever.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
 * 从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/14 上午11:22
 */
public class 二叉树中和为某一值的路径2 {
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
        List<List<Integer>> paths = new ArrayList<>();
        LinkedList<Integer> path = new LinkedList<>();
        pathEqualSum(root, sum, path, paths);
        return paths;
    }

    public static void pathEqualSum(TreeNode root, int sum, LinkedList<Integer> path, List<List<Integer>> paths) {
        if (root == null) {
            return;
        }
        sum -= root.val;
        path.add(root.val);
        if (root.left == null && root.right == null && sum == 0) {
            paths.add(new ArrayList<>(path));
        }
        pathEqualSum(root.left, sum, path, paths);
        pathEqualSum(root.right, sum, path, paths);
        // 每一层都要移除
        path.removeLast();
    }
}
