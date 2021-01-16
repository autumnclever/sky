package com.autumn.clever.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/11 下午11:34
 */
public class 二叉树的后序遍历2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        List<Integer> nodes = postorderTraversal3(root);
        for (Integer node : nodes) {
            System.out.println(node);
        }
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        if (root == null) {
            return nodes;
        }
        nodes.addAll(postorderTraversal(root.left));
        nodes.addAll(postorderTraversal(root.right));
        nodes.add(root.val);
        return nodes;
    }

    public static List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        if (root == null) {
            return nodes;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == pre) {
                nodes.add(root.val);
                pre = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return nodes;
    }

    /**
     *
     * @param root
     * @return
     */
    public static List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        if (root == null) {
            return nodes;
        }
        Stack<TreeNode> stack = new Stack<>();
        // 临时记录刚刚处理过的上一个结点
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == pre) {
                // 如果右孩子为 null || 右孩子刚刚处理过 ->
                // 1.如果右孩子为 null -> 肯定是直接添加到 nodes 就好了
                // 2.如果右孩子刚刚处理过 -> 避免重复处理，此时将 root 的值添加到 nodes里面，然后继续下一个根结点
                nodes.add(root.val);
                // 记录刚刚处理过的结点
                pre = root;
                // 将根结点置空，停止往栈里面塞结点，而是直接从栈中拿结点
                root = null;
            } else {
                // 如果右孩子不 null && 右孩子刚才没有处理过 ->
                // 1.把刚才拿出来的根结点再塞回栈中
                // 2.处理右孩子
                stack.push(root);
                root = root.right;
            }
        }
        return nodes;
    }

/**
 * 栈的作用，依次记录定位到根节点
 * prep 的作用，记录上一个刚刚处理过的结点。因为 root 根结点在存在右子树的时候，会存在反复进栈的情况，
 * 当根节点第二次从栈中拿出来的时候，它的右孩子是刚刚处理过的，也就是此时 pre = root.right 的情况，
 * 这时候不应该再重复计算，而是直接将 root.val 记录在册，然后再从栈中拿出来下一个根结点依次处理。
 *
 * @param root
 * @return
 */
//    public List<Integer> postorderTraversal(TreeNode root) {
//        List<Integer> res = new ArrayList<Integer>();
//        if (root == null) {
//            return res;
//        }
//
//        Deque<TreeNode> stack = new LinkedList<TreeNode>();
//        TreeNode prev = null;
//        while (root != null || !stack.isEmpty()) {
//            while (root != null) {
//                stack.push(root);
//                root = root.left;
//            }
//            root = stack.pop();
//            if (root.right == null || root.right == prev) {
//                res.add(root.val);
//                prev = root;
//                root = null;
//            } else {
//                stack.push(root);
//                root = root.right;
//            }
//        }
//        return res;
//    }
}
