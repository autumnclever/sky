package com.autumn.clever.leetcode.tree;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/4 13:20
 */
public class 完全二叉树的节点个数 {
    public static void main(String[] args) {

    }

    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 左子树的层数
        int left = countLevel(root.left);
        // 右子树的层数
        int right = countLevel(root.right);
        if (left == right) {
            // 如果左子树的层数和右子树的层数相同 -> 现在左子树的节点数量已经确定的了，即左子树是满二叉树，所以求得右子树节点个数 + 左子树节点个数(通过层数得到)
            return countNodes(root.right) + (1 << left);
        } else {
            // 如果左子树的层数和右子树的层数不相同 -> 现在右子树肯定是一颗满二叉树，即可以直接根据层数得到节点的个数，再加上左子树的个数节点个数即可
            return countNodes(root.left) + (1 << right);
        }
    }

    /**
     * 根据完全二叉树的特点，获取当前完全二叉树的层数
     *
     * @param root
     * @return
     */
    private static int countLevel(TreeNode root) {
        int level = 0;
        while (root != null) {
            level++;
            root = root.left;
        }
        return level;
    }
}
