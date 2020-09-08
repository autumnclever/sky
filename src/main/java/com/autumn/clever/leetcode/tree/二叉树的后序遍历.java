package com.autumn.clever.leetcode.tree;

import com.autumn.clever.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/23 18:13
 */
public class 二叉树的后序遍历 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        if (root == null) {
            return nodes;
        }
        if (root.left != null) {
            nodes.addAll(postorderTraversal(root.left));
        }
        if (root.right != null) {
            nodes.addAll(postorderTraversal(root.right));
        }
        nodes.add(root.val);
        return nodes;
    }
}
