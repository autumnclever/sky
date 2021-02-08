package com.autumn.clever.剑指offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/6 下午7:30
 */
public class 剑指Offer32I从上到下打印二叉树 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;
        int[] array = levelOrder(root);
        System.out.println(Arrays.toString(array));
    }

    public static int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        List<Integer> values = new ArrayList<>();
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        while (nodes != null && nodes.size() != 0) {
            int size = nodes.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodes.remove();
                values.add(node.val);
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
            }
        }
        int[] array = new int[values.size()];
        int index = 0;
        for (Integer value : values) {
            array[index++] = value;
        }
        return array;
    }
}
