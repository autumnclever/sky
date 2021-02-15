package com.autumn.clever.leetcode.tree;

import java.util.Arrays;
import java.util.List;

/**
 * 题目描述
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 * 输入：
 * [1,2,3,4,5,6,7],[3,2,4,1,6,5,7]
 * 输出：
 * {1,2,5,3,4,6,7}
 * <p>
 * https://www.nowcoder.com/practice/8a19cbe657394eeaac2f6ea9b0f6fcf6?tpId=188&tqId=37520&rp=1&ru=%2Factivity%2Foj&qru=%2Fta%2Fjob-code-high-week%2Fquestion-ranking&tab=answerKey
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/13 下午4:31
 */
public class 从前序与中序遍历序列构造二叉树3 {
    public static void main(String[] args) {
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        TreeNode root = reConstructBinaryTree(preorder, inorder);
        List<List<Integer>> nodes = 二叉树的层序遍历.levelOrder(root);
        for (List<Integer> node : nodes) {
            for (Integer i : node) {
                System.out.println(i);
            }
            System.out.println("----");
        }
    }

    public static TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || pre.length == 0 || in == null || in.length == 0) {
            return null;
        }
        int value = pre[0];
        TreeNode root = new TreeNode(value);
        if (pre.length == 1) {
            return root;
        }
        int index = 0;
        for (; index < in.length; index++) {
            if (in[index] == value) {
                break;
            }
        }
        root.left = reConstructBinaryTree(Arrays.copyOfRange(pre, 1, index + 1), Arrays.copyOfRange(in, 0, index));
        root.right = reConstructBinaryTree(Arrays.copyOfRange(pre, index+1, pre.length), Arrays.copyOfRange(in, index + 1, in.length));
        return root;
    }
}
