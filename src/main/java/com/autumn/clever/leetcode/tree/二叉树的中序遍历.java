package com.autumn.clever.leetcode.tree;

import com.autumn.clever.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/23 18:11
 */
public class 二叉树的中序遍历 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        if (root == null) {
            return nodes;
        }
        if (root.left != null) {
            nodes.addAll(inorderTraversal(root.left));
        }
        nodes.add(root.val);
        if (root.right != null) {
            nodes.addAll(inorderTraversal(root.right));
        }
        return nodes;
    }
}
