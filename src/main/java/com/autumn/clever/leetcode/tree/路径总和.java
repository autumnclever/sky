package com.autumn.clever.leetcode.tree;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/30 16:03
 */
public class 路径总和 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(11);
        TreeNode node4 = new TreeNode(13);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(2);
        TreeNode node8 = new TreeNode(1);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
//        node1.right = node4;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node5.right = node8;
        System.out.println(hasPathSum(root, 22));
    }

    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.right == null && root.left == null) {
            return root.val == sum;
        }

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

//    public static boolean hasPathSum(TreeNode root, int sum) {
//        if (root == null) {
//            return false;
//        }
//        return hasLRPathSum(root, sum);
//        int rest = sum - root.val;
//        boolean leftHas = hasPathSum(root.left, rest);
//        if (!leftHas) {
//            return hasPathSum(root.right, rest);
//        }
//        return leftHas;
//        boolean rightHas = hasPathSum(root.right, rest);
//        return leftHas || rightHas;
//    }

    public static boolean hasLRPathSum(TreeNode root, int sum) {
        int rest = sum - root.val;
        boolean leftHas = hasPathSum(root.left, rest);
        boolean rightHas = hasPathSum(root.right, rest);

        if (root.left == null && root.right == null) {
            if (rest == 0) {
                return true;
            } else {
                return false;
            }
        }
        return leftHas || rightHas;
    }
}
