package com.autumn.clever.leetcode.list;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/22 17:28
 */
public class 链表的中间结点 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode node6 = new ListNode(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        ListNode result = middleNode(node1);
        System.out.println(result.val);
    }

    public static ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null) {
            if (fast.next == null) {
                return slow;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
