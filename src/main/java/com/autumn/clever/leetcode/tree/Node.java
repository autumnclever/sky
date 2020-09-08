package com.autumn.clever.leetcode.tree;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/28 14:35
 */
public class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}
