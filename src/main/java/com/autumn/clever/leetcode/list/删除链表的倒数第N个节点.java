package com.autumn.clever.leetcode.list;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/10 上午7:28
 */
public class 删除链表的倒数第N个节点 {
    public static void main(String[] args) {
        ListNode node10 = new ListNode(1);
        ListNode result10 = removeNthFromEnd2(node10, 1);
        System.out.println(result10.val);
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode newHead = new ListNode(0);
        newHead.next = head;

        ListNode fast = newHead;
        while (n > 0) {
            fast = fast.next;
            n--;
        }

        ListNode slow = newHead;
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return newHead.next;
    }

    public static ListNode removeNthFromEnd2(ListNode head, int n) {
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
}
