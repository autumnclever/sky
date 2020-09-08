package com.autumn.clever.leetcode.tree;

import java.util.List;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/2 09:50
 */
public class Nnode {
    public int val;
    public List<Nnode> children;

    public Nnode() {
    }

    public Nnode(int _val) {
        val = _val;
    }

    public Nnode(int _val, List<Nnode> _children) {
        val = _val;
        children = _children;
    }
}
