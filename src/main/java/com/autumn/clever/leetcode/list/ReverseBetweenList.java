package com.autumn.clever.leetcode.list;

/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * <p>
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * @Author: zhangqiuying
 * @Date: 2020/8/14 15:47
 */
public class ReverseBetweenList {
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

        ListNode newHead = reverseBetween1(head, 2, 4);

        while (newHead != null) {
            System.out.println(newHead.val);
            newHead = newHead.next;
        }
    }

    public static ListNode reverseBetween1(ListNode head, int m, int n) {
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

    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = head;
        for (int i = 1; i < m; i++) {
            if (i != 1) {
                // 保存需要反转的头结点的前置结点
                pre = pre.next;
            }
            // 找到需要反转的头结点
            head = head.next;
        }

        ListNode preHeader = null;
        ListNode next = null;
        int j = m;
        while (head != null && j <= n) {
            next = head.next;
            head.next = preHeader;
            preHeader = head;
            head = next;
            j++;
        }

        pre.next = preHeader;
        preHeader.next = next;
        return head;
    }
}