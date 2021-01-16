package com.autumn.clever.leetcode.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 * <p>
 * 层级遍历的变形，将每一层的最后一个数字塞到结果 list 里面，返回。
 * 需要注意的是：每一层遍历，往里塞左孩子右孩子，取的时候，也需要依次往外取左孩子右孩子，否则就会把孩子的孩子，也就是孙子给整丢了。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/13 下午3:52
 */
public class 二叉树的右视图2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(4);
        root.left = node1;
        root.right = node2;
        node1.right = node3;
        node2.right = node4;
        List<Integer> values = rightSideView2(root);
        for (int i = 0; i < values.size(); i++) {
            System.out.println(values.get(i));
        }
    }

    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            TreeNode node = list.getLast();
            result.add(node.val);
            list = new LinkedList<>();
            if (node.left != null) {
                list.add(node.left);
            }
            if (node.right != null) {
                list.add(node.right);
            }
        }
        return result;
    }

    public static List<Integer> rightSideView2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = list.remove();
                if (i == size - 1) {
                    result.add(node.val);
                }
                if (node.left != null) {
                    list.add(node.left);
                }
                if (node.right != null) {
                    list.add(node.right);
                }
            }
        }
        return result;
    }
}
