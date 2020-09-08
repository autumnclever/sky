package com.autumn.clever.leetcode.tree;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/28 14:34
 */
public class 二叉搜索树与双向链表 {
    static Node pre, head;

    public static void main(String[] args) {
        Node root = new Node(4);
        Node node1 = new Node(2);
        Node node2 = new Node(5);
        Node node3 = new Node(1);
        Node node4 = new Node(3);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;

//        Node node = treeToDoublyList(root);
//        while () {
//
//        }
    }

    public static Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        Node pre = null;
        Node head = null;
        treeToList(root, pre, head);
        head.left = pre;
        pre.right = head;
        return head;
    }

    public static void treeToList(Node cur, Node pre, Node head) {
        if (cur == null) {
            return;
        }
        treeToList(cur.left, pre, head);
        if (pre != null) {
            pre.right = cur;
        } else {
            head = cur;
        }
        cur.left = pre;
        pre = cur;
        treeToList(cur.right, pre, head);
    }

//    public static Node treeToDoublyList(Node root) {
//        if (root == null) {
//            return null;
//        }
//        pre = null;
//        head = null;
//        treeToList(root);
//        head.left = pre;
//        pre.right = head;
//        return head;
//    }

    public static void treeToList(Node cur) {
        if (cur == null) {
            return;
        }
        treeToList(cur.left);
        if (pre != null) {
            pre.right = cur;
        } else {
            head = cur;
        }
        cur.left = pre;
        pre = cur;
        treeToList(cur.right);
    }
}