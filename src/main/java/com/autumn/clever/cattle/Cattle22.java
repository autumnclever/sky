package com.autumn.clever.cattle;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 *
 * @Author: zhangqiuying
 * @Date: 2020/6/30 18:03
 */
public class Cattle22 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(1);
//        TreeNode node5 = new TreeNode(2);
//        TreeNode node6 = null;
//        TreeNode node7 = new TreeNode(1);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
//        node2.left = node5;
//        node2.right = node6;
        ArrayList<Integer> list = PrintFromTopToBottom(root);
        System.out.println(list.toString());
    }

    public static ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        LinkedList<TreeNode> linkedList = new LinkedList();

        if (root == null) {
            return list;
        }
        linkedList.add(root);
        while (!linkedList.isEmpty()) {
            TreeNode node = linkedList.pollFirst();
            list.add(node.val);
            if (node.left != null) {
                linkedList.add(node.left);
            }
            if (node.right != null) {
                linkedList.add(node.right);
            }
        }
        return list;
    }
}
