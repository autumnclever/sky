package com.autumn.clever.leetcode.tree;

import java.util.LinkedList;

/**
 * 给出一个完全二叉树，求出该树的节点个数。
 * <p>
 * 说明：
 * <p>
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，
 * 其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。
 * 若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-complete-tree-nodes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/16 下午2:11
 */
public class 完全二叉树的节点个数2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root.left = node1;
        node1.left = node2;
//        root.right = node2;
        System.out.println(countNodes2(root));
//        System.out.println(1<<3);
    }

    /**
     * 这个方法可以查询，但是没有利用到完全二叉树的特点，在这道题中不推荐使用
     *
     * @param root
     * @return
     */
    public static int countNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        int count = 0;
        while (!nodes.isEmpty()) {
            int size = nodes.size();
            count += size;
            for (int i = 0; i < size; i++) {
                TreeNode node = nodes.remove();
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
            }
        }
        return count;
    }

    public static int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 获取左孩子的层数
        int left = countTreeLevel(root.left);
        // 获取右孩子的层数
        int right = countTreeLevel(root.right);
        if (left == right) {
            // 左孩子层数 = 右孩子层数 -> 左孩子是满二叉树 -> 右孩子递归计算 + 左孩子直接得到结点数(包括根结点)
            return countNodes2(root.right) + (1 << left);
        } else {
            // 左孩子层数 != 右孩子层数 -> 右孩子是满二叉树 ->  左孩子递归计算 + 右边直接得到结点数(包括根结点)
            return countNodes2(root.left) + (1 << right);
        }
    }

    /**
     * 根据完全二叉树的特点，查询二叉树的层数:
     * 完全二叉树从左孩子开始遍历，遍历到没有，就是这棵树的层数
     *
     * @return 二叉树的
     */
    public static int countTreeLevel(TreeNode root) {
        int level = 0;
        while (root != null) {
            level++;
            root = root.left;
        }
        return level;
    }
}
