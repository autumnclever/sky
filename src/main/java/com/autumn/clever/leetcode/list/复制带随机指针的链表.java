package com.autumn.clever.leetcode.list;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/22 18:46
 */
public class 复制带随机指针的链表 {
    public static void main(String[] args) {
        Node node1 = new Node(7);
        Node node2 = new Node(13);
        Node node3 = new Node(11);
        Node node4 = new Node(10);
        Node node5 = new Node(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node2.random = node1;
        node3.random = node5;
        node4.random = node3;
        node5.random = node1;
//        Node cHead = node1;
//        while (node1 != null) {
//            System.out.println(node1.val);
//            if (node1.random != null) {
//                System.out.println(node1.random.val);
//            }
//            node1 = node1.next;
//        }
        System.out.println("------");
        Node copyHead = copyRandomList(node1);
        while (copyHead != null) {
            System.out.println(copyHead.val);
            if (copyHead.random != null) {
                System.out.println(copyHead.random.val);
            }
            copyHead = copyHead.next;
        }
    }

    public static Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }

        Map<Node, Node> map = new HashMap<>();
        Node cHead = new Node(head.val);
        Node cur = cHead;
        map.put(head, cHead);
        while (head != null) {
            if (head.next != null) {
                if (map.get(head.next) == null) {
                    cur.next = new Node(head.next.val);
                    map.put(head.next, cur.next);
                } else {
                    cur.next = map.get(head.next);
                }
            }

            if (head.random != null) {
                if (map.get(head.random) == null) {
                    cur.random = new Node(head.random.val);
                    map.put(head.random, cur.random);
                } else {
                    cur.random = map.get(head.random);
                }
            }
            cur = cur.next;
            head = head.next;
        }
        return cHead;
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
