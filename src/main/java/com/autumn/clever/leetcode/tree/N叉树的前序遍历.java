package com.autumn.clever.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/2 09:49
 */
public class N叉树的前序遍历 {
    public static void main(String[] args) {
        Nnode root = new Nnode(1);
        Nnode nnode1 = new Nnode(3);
        Nnode nnode2 = new Nnode(2);
        Nnode nnode3 = new Nnode(4);
        Nnode nnode4 = new Nnode(5);
        Nnode nnode5 = new Nnode(6);

        List<Nnode> children1 = new ArrayList<>();
        children1.add(nnode4);
        children1.add(nnode5);
        nnode1.children = children1;
        List<Nnode> children2 = new ArrayList<>();
        children2.add(nnode1);
        children2.add(nnode2);
        children2.add(nnode3);
        root.children = children2;


        List<Integer> values = preorder(root);
        for (Integer value : values) {
            System.out.println(value);
        }
    }

    public static List<Integer> preorder(Nnode root) {
        List<Integer> values = new ArrayList<>();
        if (root == null) {
            return values;
        }
        values.add(root.val);
        if (root.children != null && root.children.size() != 0) {
            for (Nnode child : root.children) {
                List<Integer> childValues = preorder(child);
                values.addAll(childValues);
            }
        }
        return values;
    }
}
