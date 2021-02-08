package com.autumn.clever.剑指offer;

/**
 * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * <p>
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 * <p>
 * 例如:
 * 给定的树 A:
 * 3
 * / \
 * 4   5
 * / \
 * 1   2
 * 给定的树 B：
 * <p>
 * 4
 * /
 * 1
 * <p>
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-de-zi-jie-gou-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/6 下午10:52
 */
public class 剑指Offer26树的子结构 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(2);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;

        TreeNode root2 = new TreeNode(4);
        TreeNode node33 = new TreeNode(1);
        TreeNode node44 = new TreeNode(2);
        root2.left = node33;
        root2.right = node44;

        System.out.println(isSubStructure(root, null));
    }

    public static boolean isSubStructure(TreeNode A, TreeNode B) {
        return (A != null && B != null) && (isSameTree(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    public static boolean isSameTree(TreeNode A, TreeNode B) {
        if (B == null) {
            return true;
        }
        if (A == null || A.val != B.val) {
            return false;
        }
        return isSameTree(A.left, B.left) && isSameTree(A.right, B.right);
    }

//    public static boolean isSameTree(TreeNode A, TreeNode B) {
//        if (A == null && B == null) {
//            return true;
//        }
//        if (A == null || B == null) {
//            return false;
//        }
//        if (A.val == B.val) {
//            return true;
//        }
//        return isSameTree(A.left, B.left) && isSameTree(A.right, B.right);
//    }
}
