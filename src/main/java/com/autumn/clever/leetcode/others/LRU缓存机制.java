package com.autumn.clever.leetcode.others;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import com.google.common.cache.*;

/**
 * @Author: zhangqiuying
 * @Date: 2021/2/13 下午7:49
 */
public class LRU缓存机制 {
    private static int capacity;
    private static Map<Integer, ListNode> map = null;
    private static ListNode head = null;
    private static ListNode tail = null;

    private static class ListNode {
        int key;
        int val;
        ListNode pre;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    public static void main(String[] args) {
//        LRU缓存机制 lru = new LRU缓存机制(2);
//        put(1, 1);
//        put(2, 2);
//        System.out.println(get(1));
//        put(3, 3);
//        System.out.println(get(2));
//        put(4, 4);
//        System.out.println(get(1));
//        System.out.println(get(3));
//        System.out.println(get(4));

//        System.out.println(get(2));
//        put(2, 6);
//        System.out.println(get(1));
//        put(1, 5);
//        put(1, 2);
//        System.out.println(get(1));
//        System.out.println(get(2));

        int[][] array = {{1, 1, 1}, {1, 2, 2}, {1, 3, 2}, {2, 1}, {1, 4, 4}, {2, 2}};
//        设计LRU缓存机制 lru = new 设计LRU缓存机制();
//        int[] result = lru.LRU(array, 3);
//        System.out.println(Arrays.toString(result));

        设计LRU缓存机制2 lru2 = new 设计LRU缓存机制2();
        int[] result2 = lru2.LRU(array, 3);
        System.out.println(Arrays.toString(result2));
    }

    public LRU缓存机制(int capacity) {
        LRU缓存机制.capacity = capacity;
        map = new HashMap<>();
        head = new ListNode();
        tail = new ListNode();
        head.next = tail;
        tail.pre = head;
    }

    public static int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        ListNode node = map.get(key);
        moveNodeToLast(node);
        return node.val;
    }

    public static void put(int key, int value) {
        if (map.containsKey(key)) {
            ListNode node = map.get(key);
            node.val = value;
            moveNodeToLast(node);
            return;
        }
        if (map.size() == capacity) {
            map.remove(head.next.key);
            removeNode(head.next);
        }
        ListNode node = new ListNode(key, value);
        map.put(key, node);
        addNodeToLast(node);
    }

    public static void removeNode(ListNode node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public static void addNodeToLast(ListNode node) {
        node.pre = tail.pre;
        node.pre.next = node;
        node.next = tail;
        tail.pre = node;
    }

    public static void moveNodeToLast(ListNode node) {
        removeNode(node);
        addNodeToLast(node);
    }
}
