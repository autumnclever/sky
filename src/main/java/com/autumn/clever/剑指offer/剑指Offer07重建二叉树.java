package com.autumn.clever.剑指offer;

import java.util.*;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/5 上午9:37
 */
public class 剑指Offer07重建二叉树 {
    private static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root = buildTree(preorder, inorder);
        List<List<Integer>> nodes = 剑指Offer07重建二叉树.levelOrder(root);
        for (List<Integer> node : nodes) {
            for (Integer i : node) {
                System.out.println(i);
            }
            System.out.println("----");
        }
    }

    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0) {
            return null;
        }
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return help(preorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }

    public static TreeNode help(int[] preorder, int preBegin, int preEnd, int inBegin, int inEnd) {
        if (preBegin > preEnd) {
            return null;
        }
        int val = preorder[preBegin];
        TreeNode root = new TreeNode(val);
        int index = map.get(val);
        int leftCount = index - inBegin;
        root.left = help(preorder, preBegin + 1, preBegin + leftCount, inBegin, index - 1);
        root.right = help(preorder, preBegin + leftCount + 1, preEnd, index + 1, inEnd);
        return root;
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        while (list.size() > 0) {
            List<Integer> array = new ArrayList<>();
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = list.remove();
                array.add(node.val);
                if (node.left != null) {
                    list.add(node.left);
                }
                if (node.right != null) {
                    list.add(node.right);
                }
            }
            result.add(array);
        }
        return result;
    }
}
