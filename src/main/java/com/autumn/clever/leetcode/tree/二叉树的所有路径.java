package com.autumn.clever.leetcode.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/30 09:02
 */
public class 二叉树的所有路径 {
    static List<String> result = new LinkedList<>();
    static StringBuffer path = null;

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

//    public static List<String> binaryTreePaths(TreeNode root) {
//        deepTree(root);
//        path = null;
//        return result;
//    }
//
//    public static void deepTree(TreeNode root) {
//        if (root == null) {
//            result.add(path.toString());
//            path.deleteCharAt(path.length() - 1);
//            path.deleteCharAt(path.length() - 1);
//            return;
//        }
//        if (path == null) {
//            path = new StringBuffer();
//            path.append(root.val);
//        } else {
//            path.append("->");
//            path.append(root.val);
//        }
//        deepTree(root.left);
//        deepTree(root.right);
//    }

    public static List<String> binaryTreePaths(TreeNode root) {
        LinkedList<String> paths = new LinkedList<>();
        deepTree(root, "", paths);
        return paths;
    }

    public static void deepTree(TreeNode root, String path, LinkedList paths) {
        if (root == null) {
            return;
        }
        path += Integer.toString(root.val);
        if (root.left == null && root.right == null) {
            paths.add(path);
        } else {
            path += "->";
            deepTree(root.left, path, paths);
            deepTree(root.right, path, paths);
        }
    }
}
