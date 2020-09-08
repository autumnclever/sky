package com.autumn.clever.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/30 17:25
 */
public class 路径总和II {
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
        List<List<Integer>> paths = pathSum(node1, 22);
        for (List<Integer> path : paths) {
            System.out.println(path.toString());
        }
    }

    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> paths = new ArrayList<>();
        pathSum(root, sum, paths, new ArrayList<>());
        return paths;
    }

    public static void pathSum(TreeNode root, int sum, List<List<Integer>> paths, List<Integer> path) {
        if (root == null) {
            return;
        }
        path.add(root.val);
        if (root.left == null && root.right == null && root.val == sum) {
            paths.add(new ArrayList(path));
        }
        pathSum(root.left, sum - root.val, paths, path);
        pathSum(root.right, sum - root.val, paths, path);
        path.remove(path.size() - 1);
    }
}
