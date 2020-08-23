package com.autumn.clever.leetcode.list;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/20 14:55
 */
public class RotateRightList {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode node = rotateRight1(node1, 2);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static ListNode rotateRight1(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            /**
             * 1.链表长度为 0 或者 1
             * 2.k = 0
             * 都不需要进行移动
             */
            return head;
        }

        ListNode tempHead = head;

        int count = 0;  // 找到链表的长度
        while (tempHead != null) {
            count++;
            tempHead = tempHead.next;
        }

        k = k % count;  // 确定需要移动的次数
        if (k == 0) {
            // 如果k为0 -> 不需要移动
            return head;
        }

        // 找到正数的第几个节点
        int m = count - k;
        ListNode tail = head;
        for (int i = 1; i < m; i++) {
            tail = tail.next;
        }
        ListNode newHead = tail.next;
        tail.next = null;
        ListNode third = newHead;
        while (third.next != null) {
            third = third.next;
        }
        third.next = head;
        return newHead;
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || k == 0) {
            return head;
        }

        ListNode head1 = head;
        int count = 0;
        while (head1 != null) {
            count++;
            head1 = head1.next;
        }
        k = k % count;

        if (count == 1 || count == 0 || k == 0) {
            /**
             * 1.如果链表长度为1或者0，根本就不需要移动
             * 2.如果k=0，不管链表的长度是几，就都不需要移动了
             */
            return head;
        }

        ListNode newHead = new ListNode(0);
        newHead.next = head;

        ListNode fast = newHead;
        ListNode slow = newHead;

        for (int i = 1; i <= k; i++) {
            fast = fast.next;
        }

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        ListNode third = slow.next;
        slow.next = null;
        fast.next = head;
        return third;
    }
}
