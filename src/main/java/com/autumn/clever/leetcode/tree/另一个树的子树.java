package com.autumn.clever.leetcode.tree;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/1 11:01
 */
public class 另一个树的子树 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(2);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;

        TreeNode root2 = new TreeNode(4);
        TreeNode node33 = new TreeNode(1);
        TreeNode node44 = new TreeNode(2);
        root2.left = node33;
        root2.right = node44;

        System.out.println(isSubtree(root, root2));
    }

    public static boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) {
            return true;
        } else if (s == null) {
            return false;
        } else {
            return isSubtree(s.left, t) || isSubtree(s.right, t) || isSameTree(s, t);
        }
    }

    public static boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val == t.val) {
            return true;
        }

        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }
}
