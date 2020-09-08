package com.autumn.clever.leetcode.tree;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/3 11:20
 */
public class 二叉树展开为链表 {
    public static void main(String[] args) {
        
    }

    public static void flatten(TreeNode root) {
        // 遍历节点
        TreeNode cur = root;

        while (cur != null) {
            if (cur.left != null) {
                // 当前节点的左子树节点
                TreeNode next = cur.left;
                // cur节点的右子树的前置节点
                TreeNode rightPre = next;
                while (rightPre.right != null) {
                    rightPre = rightPre.right;
                }
                rightPre.right = cur.right;
                cur.right = next;
                cur.left = null;
            }
            cur = cur.right;
        }
    }
}
