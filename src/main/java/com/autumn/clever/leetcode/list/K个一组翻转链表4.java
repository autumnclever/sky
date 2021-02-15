package com.autumn.clever.leetcode.list;

/**
 * @Author: zhangqiuying
 * @Date: 2021/2/13 下午5:11
 */
public class K个一组翻转链表4 {
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

        ListNode head1 = node1;
        ListNode head2 = node1;
        ListNode head3 = node1;
        ListNode head4 = node1;
        ListNode result1 = reverseKGroup(head1, 2);
        while (result1 != null) {
            System.out.println(result1.val);
            result1 = result1.next;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            if (tail == null) {
                return head;
            }
            tail = tail.next;
        }
        ListNode newHead = reverseList(head, tail);
        head.next = reverseKGroup(tail, k);
        return newHead;
    }

    public static ListNode reverseList(ListNode head, ListNode tail) {
        ListNode pre = null;
        ListNode next = null;
        while (head != tail) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
