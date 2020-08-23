package com.autumn.clever.leetcode.list;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/22 15:55
 */
public class 两两交换链表中的节点 {
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

        ListNode result = swapPairs(node1);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode newHead = pre;
        ListNode next = null;
        while (head != null && head.next != null) {
            next = head.next.next;
            pre.next = head.next;
            pre.next.next = head;
            head.next = next;
            pre = pre.next.next;
            head = pre.next;
        }
        pre.next = head;
        return newHead.next;
    }
}
