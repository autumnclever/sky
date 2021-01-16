package com.autumn.clever.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/13 下午11:36
 */
public class 二叉树的所有路径2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(5);
        root.left = node1;
        root.right = node2;
        node1.right = node3;
        List<String> result = binaryTreePaths(root);
        for (String r : result) {
            System.out.println(r);
        }
    }

    public static List<String> binaryTreePaths(TreeNode root) {
        // 因为这里要统一返回一个路径的集合，在这个方法里面定义好，作为参数传入到子方法里面去
        List<String> paths = new ArrayList<>();
        deepTree(root, "", paths);
        return paths;
    }

    /**
     * 从根结点 root 开始遍历这个树，每到一个叶子结点是一条路径，也就是一个 path，添加到 paths 当中，统一返回
     * 注意：此处的叶子结点指的是，左孩子右孩子都是 null，只要有一个不为 null，就认为不是叶子结点
     *
     * @param root  树根结点
     * @param path  从根结点开始的每一条路径
     * @param paths 所有的路径
     */
    public static void deepTree(TreeNode root, String path, List<String> paths) {
        if (root == null) {
            return;
        }
        path += String.valueOf(root.val);
        if (root.left == null && root.right == null) {
            // 如果 root 是根结点，将它添加到需要返回的路径统计当中
            paths.add(path);
        } else {
            path += "->";
            deepTree(root.left, path, paths);
            deepTree(root.right, path, paths);
        }
    }
}
