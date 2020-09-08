package com.autumn.clever.leetcode.tree;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/30 18:36
 */
public class 将有序数组转换为二叉搜索树 {
    public static void main(String[] args) {
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode root = sortedArrayToBST2(nums);
        printTree(root);
    }

    public static void printTree(TreeNode root) {
        System.out.println(root.val);
        if (root.left != null) {
            printTree(root.left);
        }
        if (root.right != null) {
            printTree(root.right);
        }
    }

    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        int index = nums.length / 2;
        int[] leftNums = new int[index];
        int[] rightNums = new int[nums.length - index - 1];
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < index) {
                leftNums[i] = nums[i];
            } else if (i > index) {
                rightNums[j++] = nums[i];
            }
        }
        TreeNode root = new TreeNode(nums[index]);
        TreeNode left = sortedArrayToBST(leftNums);
        TreeNode right = sortedArrayToBST(rightNums);
        root.left = left;
        root.right = right;
        return root;
    }

    public static TreeNode sortedArrayToBST2(int[] nums) {
        return help(nums, 0, nums.length - 1);
    }

    public static TreeNode help(int[] nums, int begin, int end) {
        if (begin > end) {
            return null;
        }
        int index = (begin + end + 1) / 2;
        TreeNode root = new TreeNode(nums[index]);
        root.left = help(nums, begin, index - 1);
        root.right = help(nums, index + 1, end);
        return root;
    }
}
