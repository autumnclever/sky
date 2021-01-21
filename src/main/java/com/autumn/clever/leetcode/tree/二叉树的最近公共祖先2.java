package com.autumn.clever.leetcode.tree;

/**
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：
 * “对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * <p>
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/16 下午5:58
 */
public class 二叉树的最近公共祖先2 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(0);
        TreeNode node6 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(4);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node4.left = node7;
        node4.right = node8;
        TreeNode node = lowestCommonAncestor(root, node1, node8);
        System.out.println(node.val);
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            // 1.遇到叶子结点 -> 返回 null;
            // 2.当前结点就是 p / q -> 返回当前结点
            return root;
        }
        // 看看左孩子中是否包含 p/q
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 看看右孩子中是否包含 p/q
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null && right == null) {
            // 左右孩子中都不包含，返回 null
            return null;
        }
        if (left == null) {
            // 左孩子中没有，在右孩子中，可能 p 和 q 都在，可能其中一个在
            return right;
        }
        if (right == null) {
            // 右孩子中没有，在左孩子中，可能 p 和 q 都在，可能其中一个在
            return left;
        }
        // 左右孩子都不为空，说明 p 和 q 分别在两边，返回 root
        return root;
    }
}
