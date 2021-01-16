package com.autumn.clever.leetcode.tree;

import java.util.Stack;

/**
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 * 二叉搜索树是排好序的，既然已经排序好，按照顺序去做差值，才能得到最小的绝对值差。
 * 中序遍历是符合递增顺序的，所以这道题目是对中序遍历的变形。
 * <p>
 * 1. 定义前置结点 preNode，遍历到当前结点的时候，去跟前置结点做差值运算，比较得到最新的 min；
 * 2. 将当前节点赋值给前置结点 preNode，供下一次递归的时候，给下一个遍历到的结点计算使用；
 *
 * 不管是递归方法也好，还是遍历方法也好，其实思路都是一样的，关键点在于前置结点的定义，
 * 然后前置结点和当前结点的计算，以及前置结点的重新赋值。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/12 下午4:49
 */
public class 二叉搜索树的最小绝对差2 {
    private static int min;
    private static int preValue;
    // 定义前置结点
    private static TreeNode preNode;

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        root.right = node1;
        node1.left = node2;
        System.out.println(getMinimumDifference4(root));
    }

    public static int getMinimumDifference(TreeNode root) {
        min = Integer.MAX_VALUE;
        preValue = -1;
        getMinimum(root);
        return min;
    }

    public static void getMinimum(TreeNode root) {
        if (root == null) {
            return;
        }
        getMinimum(root.left);
        if (preValue == -1) {
            preValue = root.val;
        } else {
            min = Math.min(min, Math.abs(root.val - preValue));
            preValue = root.val;
        }
        getMinimum(root.right);
    }

    public static int getMinimumDifference2(TreeNode root) {
        min = Integer.MAX_VALUE;
        preValue = 0;
        getMinimum2(root);
        return min;
    }

    public static void getMinimum2(TreeNode root) {
        if (root == null) {
            return;
        }
        getMinimum(root.left);
        min = Math.min(min, Math.abs(root.val - preValue));
        preValue = root.val;
        getMinimum(root.right);
    }

/**
 * 递归
 *
 * @param root
 * @return
 */
public static int getMinimumDifference3(TreeNode root) {
    min = Integer.MAX_VALUE;
    getMinimum3(root);
    return min;
}

public static void getMinimum3(TreeNode root) {
    if (root == null) {
        return;
    }
    getMinimum3(root.left);
    if (preNode != null) {
        // 遍历到当前 root 结点，和前置结点做差值计算
        min = Math.min(min, Math.abs(preNode.val - root.val));
    }
    // 把当前的 root 节点设置为前置结点，给下一次递归，下一个结点计算使用
    preNode = root;
    getMinimum3(root.right);
}

    /**
     * 遍历 - 辅助栈
     *
     * @return
     */
    public static int getMinimumDifference4(TreeNode root) {
        min = Integer.MAX_VALUE;
        getMinimums4(root);
        return min;
    }

    public static void getMinimums4(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (preNode != null) {
                min = Math.min(min, Math.abs(preNode.val - root.val));
            }
            preNode = root;
            root = root.right;
        }
    }
}
