package com.autumn.clever.leetcode.tree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/24 15:19
 */
public class 从前序与中序遍历序列构造二叉树 {
    private static Map<Integer, Integer> inOrderMap;

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root = buildTree2(preorder, inorder);
        List<List<Integer>> nodes = 二叉树的层序遍历.levelOrder(root);
        for (List<Integer> node : nodes) {
            for (Integer i : node) {
                System.out.println(i);
            }
            System.out.println("----");
        }
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) {
            return null;
        }
        int rootValue = preorder[0];
        TreeNode root = new TreeNode(rootValue);
        // 统计左孩子的中序排序
        int[] leftInOrder = new int[inorder.length];
        // 统计左孩子节点个数
        int leftCount = 0;
        for (int i = 0; i < inorder.length; i++) {
            if (inorder[i] == rootValue) {
                break;
            }
            leftCount++;
            leftInOrder[i] = inorder[i];
        }
        // 统计右孩子的中序排列
        int[] rightInOrder = new int[inorder.length - leftCount - 1];
        // 右孩子的起点位置在中序数组中的第一个起点位置
        int inRightBegin = inorder.length - leftCount - 1;
        for (int i = inRightBegin; i < inorder.length; i++) {
            rightInOrder[i - inRightBegin] = inorder[i];
        }
        // 统计左孩子的前序排列
        int[] leftPreOrder = new int[leftCount];
        int leftIndex = 0;
        int[] rightPreOrder = new int[preorder.length - leftCount - 1];
        int rightIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            if (i <= 1 + leftCount) {
                leftPreOrder[leftIndex++] = preorder[i];
            } else {
                rightPreOrder[rightIndex] = preorder[i];
            }
        }

        root.left = buildTree(leftPreOrder, leftInOrder);
        root.right = buildTree(rightPreOrder, rightInOrder);
        return root;
    }

    public static TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null) {
            return null;
        }
        int length = preorder.length;
        inOrderMap = new HashMap<>();
        for (int i = 0; i < length; i++) {
            inOrderMap.put(inorder[i], i);
        }
        return buildTree2(preorder, 0, length - 1, inorder, 0, length - 1);
    }

    public static TreeNode buildTree2(int[] preOrder, int preBegin, int preEnd, int[] inOrder, int inBegin, int inEnd) {
        if (preBegin > preEnd) {
            return null;
        }
        // proOrder 的第一个值就是根节点
        int rootValue = preOrder[preBegin];
        // 找到根节点在 inOrder 中的索引
        int inRootIndex = inOrderMap.get(rootValue);
        // 创建根节点对象
        TreeNode root = new TreeNode(rootValue);
        // 左子树的结点个数
        int leftCount = inRootIndex - inBegin;
        root.left = buildTree2(preOrder, preBegin + 1, preBegin + leftCount, inOrder, inBegin, inRootIndex - 1);
        root.right = buildTree2(preOrder, preBegin + leftCount + 1, preEnd, inOrder, inRootIndex + 1, inEnd);
        return root;
    }
}
