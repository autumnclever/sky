package com.autumn.clever.leetcode.tree;

import java.util.List;

/**
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/14 下午3:59
 */
public class 从前序与中序遍历序列构造二叉树2 {
    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root = buildTree(preorder, inorder);
        List<List<Integer>> nodes = 二叉树的层序遍历.levelOrder(root);
        for (List<Integer> node : nodes) {
            for (Integer i : node) {
                System.out.println(i);
            }
            System.out.println("----");
        }
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, inorder, 0, preorder.length, 0, inorder.length);
    }

    /**
     * @param preorder 一颗完整的树的前序遍历数组
     * @param inorder  一颗完整的树的中序遍历数组
     * @param preBegin 一棵树(可以不完整)的前序遍历在 preorder 数组中的起始索引
     * @param preEnd   一棵树(可以不完整)的前序遍历在 preorder 数组中的终止索引+1
     * @param inBegin  一棵树(可以不完整)的中序遍历在 inorder 数组中的起始索引
     * @param inEnd    一棵树(可以不完整)的中序遍历在 inorder 数组中的终止索引+1
     * @return
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder, int preBegin, int preEnd, int inBegin, int inEnd) {
        if (preBegin == preEnd) {
            // 如果开始结点与结束结点位置相同 -> 不处理
            return null;
        }
        // preorder 数组中的第一个数字就是根节点，找到根结点
        int value = preorder[preBegin];
        // 找到根结点在 inorder 中序数组中的索引
        int index = inBegin;
        for (; index <= inEnd; index++) {
            if (inorder[index] == value) {
                break;
            }
        }
        // 得到左孩子的个数
        int leftNum = index - inBegin;
        TreeNode root = new TreeNode(value);
        // 根据左孩子个数，数组之间的相对关系，得到左孩子的在前序数组 preorder、中序数组 inorder 中的起始、终止索引 -> 递归构造左孩子
        root.left = buildTree(preorder, inorder, preBegin + 1, preBegin + leftNum + 1, inBegin, index);
        // 得到右孩子在前序数组 preorder、中序数组 inorder 中的起始、终止索引 -> 递归构造右孩子
        root.right = buildTree(preorder, inorder, preBegin + leftNum + 1, preEnd, index + 1, inEnd);
        return root;
    }

    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode buildTreeHelper(int[] preorder, int p_start, int p_end, int[] inorder, int i_start, int i_end) {
        // preorder 为空，直接返回 null
        if (p_start == p_end) {
            return null;
        }
        int root_val = preorder[p_start];
        TreeNode root = new TreeNode(root_val);
        //在中序遍历中找到根节点的位置
        int i_root_index = 0;
        for (int i = i_start; i < i_end; i++) {
            if (root_val == inorder[i]) {
                i_root_index = i;
                break;
            }
        }
        int leftNum = i_root_index - i_start;
        //递归的构造左子树
        root.left = buildTreeHelper(preorder, p_start + 1, p_start + leftNum + 1, inorder, i_start, i_root_index);
        //递归的构造右子树
        root.right = buildTreeHelper(preorder, p_start + leftNum + 1, p_end, inorder, i_root_index + 1, i_end);
        return root;
    }
}
