package com.autumn.clever.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/29 20:41
 */
public class 二叉搜索树的最小绝对差 {
    private static Integer min = Integer.MAX_VALUE;
    private static TreeNode pre = null;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        root.right = node1;
        node1.left = node2;
        System.out.println(getMinimumDifference(root));
    }

//    public static int getMinimumDifference(TreeNode root) {
//        List<Integer> rootValueList = getRootValueList(root);
//        int abs = rootValueList.get(rootValueList.size() - 1);
//        for (int i = 1; i < rootValueList.size(); i++) {
//            if (rootValueList.get(i) - rootValueList.get(i - 1) < abs) {
//                abs = rootValueList.get(i) - rootValueList.get(i - 1);
//            }
//        }
//        return abs;
//    }

    public static List<Integer> getRootValueList(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        if (root.left != null) {
            List<Integer> leftResult = getRootValueList(root.left);
            result.addAll(leftResult);
        }
        result.add(root.val);
        if (root.right != null) {
            List<Integer> rightResult = getRootValueList(root.right);
            result.addAll(rightResult);
        }
        return result;
    }

    public static int getMinimumDifference(TreeNode root) {
        preMin(root);
        return min;
    }

    public static void preMin(TreeNode root) {
        if (root == null) {
            return;
        }
        preMin(root.left);
        if (pre != null) {
            min = Math.min(min, root.val - pre.val);
        }
        pre = root;
        preMin(root.right);
    }
}
