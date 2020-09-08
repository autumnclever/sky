package com.autumn.clever.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/3 17:05
 */
public class 二叉搜索树中第K小的元素 {
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

    public static int kthSmallest2(TreeNode root, int k) {
        int value = 0;
        Stack<TreeNode> stack = new Stack();
        TreeNode cur = root;
        while ((cur != null || !stack.isEmpty()) && k > 0) {
            // 树不为空 || 栈不为空
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            value = cur.val;
            k--;
            cur = cur.right;
        }
        return value;
    }

    public static int kthSmallest(TreeNode root, int k) {
        List<Integer> values = new ArrayList<>();
        Stack<TreeNode> stack = new Stack();
        TreeNode cur = root;
        while ((cur != null || !stack.isEmpty()) && k > 0) {
            // 树不为空 || 栈不为空
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            values.add(cur.val);
            k--;
            cur = cur.right;
        }
        return values.get(values.size() - 1);
    }
}
