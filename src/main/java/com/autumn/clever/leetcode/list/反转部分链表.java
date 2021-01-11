package com.autumn.clever.leetcode.list;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/9 下午7:30
 */
public class 反转部分链表 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode head1 = head;
        while (head1 != null) {
            System.out.println(head1.val);
            head1 = head1.next;
        }

        ListNode newHead = reverseBetween2(head, 1, 4);

        System.out.println("------");

        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }

    public static ListNode reverseBetween2(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = null;
        ListNode cur = head;
        while (m > 1) {
            pre = cur;
            cur = cur.next;
            m--;
            n--;
        }

        ListNode con = pre;
        ListNode tail = cur;
        ListNode third = null;
        while (n > 0) {
            third = cur.next;
            cur.next = pre;
            pre = cur;
            cur = third;
            n--;
        }

        if (con != null) {
            con.next = pre;
        } else {
            head = pre;
        }
        tail.next = cur;
        return head;
    }


    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        ListNode pre = null;

        while (m > 1) {
            pre = cur;
            cur = cur.next;
            m--;
            n--;
        }

        ListNode tail = cur;
        ListNode con = pre;
        ListNode third = null;

        while (n > 0) {
            third = cur.next;
            cur.next = pre;
            pre = cur;
            cur = third;
            n--;
        }

        if (con != null) {
            con.next = pre;
        } else {
            head = pre;
        }
        tail.next = cur;
        return head;
    }
}
