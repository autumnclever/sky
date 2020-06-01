package com.autumn.clever.cattle;

/**
 * 输入一个链表，输出该链表中倒数第k个结点。
 *
 * @Author: zhangqiuying
 * @Date: 2020/5/31 22:14
 */
public class Cattle14 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(6);
        ListNode node6 = new ListNode(7);
        ListNode node7 = new ListNode(8);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        ListNode result = FindKthToTail(head, 8);
        System.out.println(result);
    }

    public static ListNode FindKthToTail(ListNode head, int k) {
        if (head == null || k <= 0) {
            return null;
        }

        ListNode p1 = head;
        ListNode p2 = head;

        while (k-- > 1) {
            if (p1.next != null) {
                p1 = p1.next;
            } else {
                return null;
            }
        }

        while (p1.next != null) {
            p2 = p2.next;
            p1 = p1.next;
        }

        return p2;
    }
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                ", next=" + next +
                '}';
    }
}
