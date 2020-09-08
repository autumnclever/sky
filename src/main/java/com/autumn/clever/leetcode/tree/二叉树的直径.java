package com.autumn.clever.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/30 20:04
 */
public class 二叉树的直径 {
    static int pathSize = 0;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        System.out.println(diameterOfBinaryTree2(root));
    }

    public static int diameterOfBinaryTree(TreeNode root) {
        getAllPaths(root, new ArrayList<>());
        return pathSize;
    }

    public static void getAllPaths(TreeNode root, List<Integer> path) {
        if (root == null) {
            return;
        }
        if (root.left != null) {
            getAllPaths(root.left, path);
        }
        path.add(root.val);
        if (root.right != null) {
            getAllPaths(root.right, path);
        } else {
            if (pathSize < path.size() - 1) {
                pathSize = path.size() - 1;
            }
            path.remove(path.size() - 1);
        }
    }

    public static int diameterOfBinaryTree2(TreeNode root) {
        pathSize = 1;
        getDepth(root);
        return pathSize - 1;
    }

    public static int getDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = getDepth(root.left);
        int rightDepth = getDepth(root.right);
        pathSize = Math.max(pathSize, leftDepth + rightDepth + 1);
        return Math.max(leftDepth, rightDepth) + 1;
    }
}
