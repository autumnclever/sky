package com.autumn.clever.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/30 19:08
 */
public class 求根到叶子节点数字之和 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root.left = node1;
        root.right = node2;
        System.out.println(sumNumbers2(root));
    }

    public static int sumNumbers(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        StringBuffer num = new StringBuffer();
        List<Integer> sum = new ArrayList<>();
        help(root, sum, num);
        int result = 0;
        for (Integer i : sum) {
            result += i;
        }
        return result;
    }


    public static void help(TreeNode root, List<Integer> sum, StringBuffer num) {
        if (root == null) {
            return;
        }
        num.append(root.val);
        if (root.left == null && root.right == null) {
            sum.add(Integer.valueOf(num.toString()));
        }
        help(root.left, sum, num);
        help(root.right, sum, num);
        num.deleteCharAt(num.length() - 1);
    }

    public static int sumNumbers2(TreeNode root) {
        return helper(root, 0);
    }

    public static int helper(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int rest = sum * 10 + root.val;
        if (root.left == null && root.right == null) {
            return rest;
        }
        return helper(root.left, rest) + helper(root.right, rest);
    }
}
