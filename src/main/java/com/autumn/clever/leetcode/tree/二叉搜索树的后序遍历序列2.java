package com.autumn.clever.leetcode.tree;

/**
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。
 * 如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/15 下午3:44
 */
public class 二叉搜索树的后序遍历序列2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(3);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        int[] values1 = {1, 2, 3, 4, 5};
        int[] values2 = {4, 8, 6, 12, 16, 14, 10};
        int[] values3 = {1, 2, 5, 10, 6, 9, 4, 3};
        int[] values4 = {4, 8, 6, 12, 16, 14, 10};

        System.out.println(verifyPostorder(values3));
//        System.out.println(verifyPostorder(values4));
    }

    public static boolean verifyPostorder(int[] postorder) {
        return helper(postorder, 0, postorder.length - 1);
    }

    public static boolean helper(int[] postorder, int begin, int end) {
        if (postorder == null || postorder.length == 0 || end - begin <= 1) {
            return true;
        }
        // 根节点
        int rootValue = postorder[end];
        // 找到数组中，第一个大过根节点的值，就是右孩子的起始位置
        int index = begin;
        for (; index < end; index++) {
            if (postorder[index] > rootValue) {
                break;
            }
        }
        // 从右孩子的起始位置开始，到结尾继续遍历，如果出现比根节点小的值，就不符合二叉搜索树
        for (int i = index + 1; i < end; i++) {
            if (postorder[i] < rootValue) {
                return false;
            }
        }
        // 找到左右孩子的起始和终点位置，分别判断是否是二叉搜索树
        return helper(postorder, begin, index - 1) && helper(postorder, index, end - 1);
    }
}
