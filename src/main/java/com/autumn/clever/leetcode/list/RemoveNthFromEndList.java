package com.autumn.clever.leetcode.list;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/19 14:55
 */
public class RemoveNthFromEndList {
    public static void main(String[] args) {
//        ListNode node1 = new ListNode(1);
//        ListNode node2 = new ListNode(2);
//        ListNode node3 = new ListNode(3);
//        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);
//        node1.next = node2;
//        node2.next = node3;
//        node3.next = node4;
//        node4.next = node5;
//        ListNode head = node1;
//        while (head != null) {
//            System.out.println(head.val);
//            head = head.next;
//        }
//
//        ListNode result1 = removeNthFromEnd3(node1, 2);
//
//        while (result1 != null) {
//            System.out.println(result1.val);
//            result1 = result1.next;
//        }

        ListNode node10 = new ListNode(1);
        ListNode result10 = removeNthFromEnd3(node10, 1);
        System.out.println(result10.val);

//        ListNode node6 = new ListNode(1);
//        ListNode node7 = new ListNode(2);
//        node6.next = node7;
//        ListNode result2 = removeNthFromEnd3(node6, 2);
//        System.out.println(result2.val);
    }

    public static ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode newHeader = new ListNode(0);
        newHeader.next = head;

        ListNode fast = newHeader;
        ListNode slow = newHeader;

        for (int i = 1; i <= n + 1; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return newHeader.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = head;
        ListNode after = head;

        int count = 0;
        while (count++ < n) {
            pre = pre.next;
        }

        if (pre == null) {
            return null;
        }

        while (pre.next != null) {
            pre = pre.next;
            after = after.next;
        }
        after.next = after.next.next;

        return head;
    }
}
