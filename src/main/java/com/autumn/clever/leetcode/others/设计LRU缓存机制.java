package com.autumn.clever.leetcode.others;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhangqiuying
 * @Date: 2021/2/13 下午9:31
 */
public class 设计LRU缓存机制 {
    Map<Integer, ListNode> map = null;
    int cap;
    ListNode head;
    ListNode tail;

    private class ListNode {
        int key;
        int val;
        ListNode pre;
        ListNode next;

        ListNode() {
        }

        ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    /**
     * lru design
     *
     * @param operators int整型二维数组 the ops
     * @param k         int整型 the k
     * @return int整型一维数组
     */
    public int[] LRU(int[][] operators, int k) {
        head = new ListNode();
        tail = new ListNode();
        head.next = tail;
        tail.pre = head;
        cap = k;
        map = new HashMap();
        List<Integer> values = new ArrayList();

        for (int i = 0; i < operators.length; i++) {
            int[] array = operators[i];
            if (array[0] == 1) {
                put(array[1], array[2]);
            } else if (array[0] == 2) {
                int value = get(array[1]);
                values.add(value);
            }
        }
        int[] array = new int[values.size()];
        for (int i = 0; i < values.size(); i++) {
            array[i] = values.get(i);
        }
        return array;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            node.val = value;
            moveNodeToLast(node);
            return;
        }
        if (map.size() == cap) {
            map.remove(head.next.val);
            removeNode(head.next);
        }
        ListNode node = new ListNode(key, value);
        map.put(key, node);
        addNodeToLast(node);
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        ListNode node = map.get(key);
        moveNodeToLast(node);
        return node.val;
    }

    public void removeNode(ListNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public void addNodeToLast(ListNode node) {
        node.pre = tail.pre;
        tail.pre.next = node;
        node.next = tail;
        tail.pre = node;
    }

    public void moveNodeToLast(ListNode node) {
        removeNode(node);
        addNodeToLast(node);
    }
}
