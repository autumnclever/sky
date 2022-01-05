package com.autumn.clever.leetcode.list;

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 * @author zhangqiuying
 * @description
 * @date 2021/10/18 8:29 下午
 */
public class 反转链表4 {
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

        ListNode reverseHead = node1;
        while (node1 != null) {
            System.out.println(node1.val);
            node1 = node1.next;
        }

        System.out.println("------");

        reverseHead = reverseList(reverseHead);
        while (reverseHead != null) {
            System.out.println(reverseHead.val);
            reverseHead = reverseHead.next;
        }
    }

    public static ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode next = null;

        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
