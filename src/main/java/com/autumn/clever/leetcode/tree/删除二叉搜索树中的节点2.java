package com.autumn.clever.leetcode.tree;

/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，
 * 并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * <p>
 * 一般来说，删除节点可分为两个步骤：
 * <p>
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * <p>
 * 说明： 要求算法时间复杂度为 O(h)，h 为树的高度。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/delete-node-in-a-bst
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/16 下午10:06
 */
public class 删除二叉搜索树中的节点2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node7;
        TreeNode node = root;
        printNode(node);
        System.out.println("------");
        node = deleteNode(root, 3);
        printNode(node);
    }

    public static void printNode(TreeNode node) {
        if (node == null) {
            return;
        }
        System.out.println(node.val);
        printNode(node.left);
        printNode(node.right);
    }

    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return root;
        }
        if (root.val > key) {
            // 如果根结点的值 > 要删除的结点的值 -> 要删除的结点在左孩子中
            // 将删除后的结点重新赋值到根节点的左孩子位置
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            // 如果根结点的值 < 要删除的结点的值 -> 要删除的结点在左孩子中
            // 将删除后的结点重新赋值到根节点的右孩子位置
            root.right = deleteNode(root.right, key);
        } else {
            // 当前结点是要删除的结点
            if (root.left == null && root.right == null) {
                // 如果是叶子节点，直接置空
                root = null;
            } else if (root.right != null) {
                // 找到当前节点的后驱结点
                TreeNode node = getAfterNode(root);
                // 把后驱结点的值赋值给当前结点
                root.val = node.val;
                // 在当前节点的右孩子中删除后驱结点
                root.right = deleteNode(root.right, node.val);
            } else if (root.left != null) {
                // 找到当前节点的前驱结点
                TreeNode node = getBeforeNode(root);
                // 把前驱结点的值赋值给当前结点
                root.val = node.val;
                // 在当前结点的左孩子中删除前驱结点
                root.left = deleteNode(root.left, node.val);
            }
        }
        return root;
    }

    /**
     * 获取一个结点的前驱节点：
     * 在二叉搜索树中，第一个小于 root.val 的结点
     *
     * @param root
     * @return
     */
    public static TreeNode getBeforeNode(TreeNode root) {
        if (root == null || root.left == null) {
            return null;
        }
        TreeNode left = root.left;
        while (left.right != null) {
            left = left.right;
        }
        return left;
    }

    /**
     * 获取一个结点的后置节点：
     * 在二叉搜索树中，第一个大过 root.val 的结点
     *
     * @param root
     * @return
     */
    public static TreeNode getAfterNode(TreeNode root) {
        if (root == null || root.right == null) {
            return null;
        }
        TreeNode right = root.right;
        while (right.left != null) {
            right = right.left;
        }
        return right;
    }
}
