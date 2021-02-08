package com.autumn.clever.剑指offer;

import java.util.LinkedList;

/**
 * 请实现两个函数，分别用来序列化和反序列化二叉树。
 * 你可以将以下二叉树：
 * <p>
 * 1
 * / \
 * 2   3
 * / \
 * 4   5
 * <p>
 * 序列化为 "[1,2,3,null,null,4,5]"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/xu-lie-hua-er-cha-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/8 下午6:53
 */
public class 剑指Offer37序列化二叉树 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);

        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node3.right = node4;
//        System.out.println(serialize2(root));
        String str = serialize(root);
        System.out.println(str);

        TreeNode node = deserialize(str);

    }

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        if (root == null) {
            return "[]";
        }
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.remove();
            if (node != null) {
                sb.append(node.val);
                sb.append(",");
                nodes.add(node.left);
                nodes.add(node.right);
            } else {
                sb.append("null,");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data.equals("[]")) {
            return null;
        }
        String[] values = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.valueOf(values[0]));
        LinkedList<TreeNode> nodes = new LinkedList<>();
        nodes.add(root);
        int i = 1;
        while (!nodes.isEmpty()) {
            TreeNode node = nodes.remove();
            if (!values[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(values[i]));
                nodes.add(node.left);
            }
            i++;
            if (!values[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(values[i]));
                nodes.add(node.right);
            }
            i++;
        }
        return root;
    }

    public static String serialize2(TreeNode root) {
        StringBuilder res = new StringBuilder("[");
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                res.append(node.val + ",");
                queue.add(node.left);
                queue.add(node.right);
            } else {
                res.append("null,");
            }
        }
        res.deleteCharAt(res.length() - 1);
        res.append("]");
        return res.toString();
    }
}
