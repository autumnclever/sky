package com.autumn.clever.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/13 下午3:07
 */
public class 二叉搜索树中第K小的元素2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(2);
        root.left = node1;
        root.right = node2;
        node1.right = node3;

        System.out.println(kthSmallest(root, 4));
    }

    /**
     * 二叉树的中序遍历变形，不需要全部遍历完，边遍历边去计算 k 的值，从栈里取出来的第几个数字，就是最小的第几个数字。
     *
     * @param root
     * @param k
     * @return
     */
    public static int kthSmallest(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        List<Integer> values = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while ((root != null || !stack.isEmpty()) && k > 0) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            values.add(root.val);
            root = root.right;
            k--;
        }
        return values.get(values.size() - 1);
    }
}
