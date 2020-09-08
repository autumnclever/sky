package com.autumn.clever.leetcode.tree;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/31 15:34
 */
public class 删除二叉搜索树中的节点 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node7;
        TreeNode node = root;
        printNode(node);
        System.out.println("------");
        node = deleteNode(root, 3);
        printNode(node);
    }

    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else {
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                root.val = getAfterValue(root);
                root.right = deleteNode(root.right, root.val);
            } else if (root.left != null) {
                root.val = getBeforeValue(root);
                root.left = deleteNode(root.left, root.val);
            }
        }
        return root;
    }

    private static int getAfterValue(TreeNode root) {
        root = root.right;
        while (root != null && root.left != null) {
            root = root.left;
        }
        return root.val;
    }

    private static int getBeforeValue(TreeNode root) {
        root = root.left;
        while (root != null && root.right != null) {
            root = root.right;
        }
        return root.val;
    }


    public static void printNode(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        printNode(node.left);
        printNode(node.right);
    }


    public static TreeNode deleteNode2(TreeNode root, int key) {
        if (root == null) {
            return root;
        }

        if (key == root.val) {
            if (root.left == null && root.right == null) {
                root = null;
            } else {
                root.right = changeNode(root);
                deleteNode2(root.right, root.val);
            }
        } else if (key > root.val) {
            deleteNode2(root.right, key);
        } else {
            deleteNode2(root.left, key);
        }
        return root;
    }

    public static TreeNode changeNode(TreeNode root) {
        if (root.left == null) {
            return root.right;
        }

        if (root.right == null) {
            return root.left;
        }

        TreeNode left = root.left;
        root.left = null;
        root = changeRight(root.right);
        root.left = left;
        return root;
    }

    public static TreeNode changeRight(TreeNode root) {
        if (root.right == null || root.left == null) {
            return root;
        }
        changeRight(root.right);
        if (root.right != null) {
            root.right.left = root.left;
            root.left = null;
        }
        return root;
    }
}
