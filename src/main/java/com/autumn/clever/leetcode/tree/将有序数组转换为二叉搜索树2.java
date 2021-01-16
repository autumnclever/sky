package com.autumn.clever.leetcode.tree;

/**
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * <p>
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/15 下午10:43
 */
public class 将有序数组转换为二叉搜索树2 {
    public static void main(String[] args) {
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode root = sortedArrayToBST(nums);
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
        return helper(nums, 0, nums.length - 1);
    }

    /**
     * nums 数组中，需要确认根节点、左孩子区间范围、右孩子区间范围
     *
     * @param nums  结点排序数组
     * @param begin 数组中需要确认的起点索引
     * @param end   数组中需要确认的终点索引
     * @return
     */
    public static TreeNode helper(int[] nums, int begin, int end) {
        if (nums == null || nums.length == 0 || end < begin) {
            return null;
        }
        // 数组的中间元素作为根节点，注意这里 +1
        int rootIndex = (begin + end + 1) / 2;
        // 构造根结点
        TreeNode root = new TreeNode(nums[rootIndex]);
        // 构造左孩子
        root.left = helper(nums, begin, rootIndex - 1);
        // 构造右孩子
        root.right = helper(nums, rootIndex + 1, end);
        return root;
    }
}
