package com.autumn.clever.leetcode.list;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/21 15:23
 */
public class K个一组翻转链表 {
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
        ListNode result1 = reverseKGroup1(head1, 2);
        while (result1 != null) {
            System.out.println(result1.val);
            result1 = result1.next;
        }

//        ListNode result2 = reverseKGroup3(head2, 2);
//        while (result2 != null) {
//            System.out.println(result2.val);
//            result2 = result2.next;
//        }
    }

    public static ListNode reverseKGroup1(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            if (tail == null) {
                return head;
            }
            tail = tail.next;
        }

        ListNode newHead = reverseGroup(head, tail);
        head.next = reverseKGroup1(tail, k);
        return newHead;
    }

    public static ListNode reverseGroup(ListNode head, ListNode tail) {
        ListNode pre = null;
        while (head != tail) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    public static ListNode reverseKGroup3(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode next = head;
        ListNode nextHead = null;

        for (int i = 1; i < k; i++) {
            if (next == null) {
                return head;
            }
            next = next.next;
            nextHead = next.next;
            next.next = null;
        }
        ListNode newHead = reverseGroup(head);
        newHead.next = reverseKGroup3(nextHead, k);
        return newHead;
    }

//    public static ListNode reverseKGroup(ListNode head, int k) {
//        if (head == null || head.next == null) {
//            return head;
//        }
//
//        ListNode preHead = new ListNode(0);
//        while (head != null) {
//            ListNode next = head;
//            ListNode currentHead = null;
//            int i;
//            for (i = 1; i < k; i++) {
//                next = next.next;
//                currentHead = next.next;
//                next.next = null;
//            }
//            if (i + 1 == k) {
//                ListNode node = reverseGroup(head);
//                preHead.next = node;
//                head = currentHead;
//            } else {
//                preHead
//            }
//        }
//    }

    public static ListNode reverseGroup(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            head = next;
            pre = head;
        }
        return head;
    }
}
