package com.autumn.clever.leetcode.tree;

import java.util.ArrayList;

/**
 * 怎么看怎么像回溯算法
 * 给定一个二叉树和一个值 sum\ sum sum，请找出所有的根节点到叶子节点的节点值之和等于 sum\ sum sum 的路径，
 * 例如：
 * 给出如下的二叉树， sum=22\ sum=22 sum=22，
 * https://www.nowcoder.com/practice/840dd2dc4fbd4b2199cd48f2dadf930a?tpId=188&tags=&title=&diffculty=0&judgeStatus=0&rp=1&tab=answerKey
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/14 下午3:34
 */
public class 二叉树中和为某一值的路径3 {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(11);
        TreeNode node5 = new TreeNode(13);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(5);
        TreeNode node10 = new TreeNode(1);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node4.left = node7;
        node4.right = node8;
        node3.left = node5;
        node3.right = node6;
        node6.left = node9;
        node6.right = node10;

        ArrayList<ArrayList<Integer>> result = pathSum(node1, 22);
        for (ArrayList<Integer> r : result) {
            for (Integer i : r) {
                System.out.println(i);
            }
            System.out.println("-----");
        }
    }

    public static ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> arrays = new ArrayList<>();
        ArrayList<Integer> array = new ArrayList<>();
        path(root, sum, arrays, array);
        return arrays;
    }

    public static void path(TreeNode root, int sum, ArrayList<ArrayList<Integer>> arrays, ArrayList<Integer> array) {
        if (root == null) {
            return;
        }
        sum -= root.val;
        array.add(root.val);
        if (sum == 0 && root.left == null && root.right == null) {
            arrays.add(new ArrayList<>(array));
        }
        path(root.left, sum, arrays, array);
        path(root.right, sum, arrays, array);
        array.remove(array.size() - 1);
    }
}