package com.autumn.clever.leetcode.tree;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/24 18:13
 */
public class 二叉树的最近公共祖先 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(0);
        TreeNode node6 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(4);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node4.left = node7;
        node4.right = node8;
        TreeNode node = lowestCommonAncestor(root, node1, node8);
        System.out.println(node.val);
    }

    /**
     * 这个方法的默认前提是在 root 子树中，肯定存在 p 或者 q
     * 在 root 的子树中，包含 p 或者 q，包含的话，包含的那个结点是什么？
     * 后续遍历的变形
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        // 左孩子中包含 p 或者 q 的那个结点是什么？
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 右孩子中包含 p 或者 q 的那个结点是什么？
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) {
            // 如果左右孩子都不包含 p / q -> 返回 null
            return null;
        }
        if (left == null) {
            // p 和 q 都在右孩子里
            return right;
        }
        if (right == null) {
            // p 和 q 都在左孩子里
            return left;
        }
        // 如果左孩子中有一个，右孩子中有另外一个 -> 返回根结点
        return root;
    }
}