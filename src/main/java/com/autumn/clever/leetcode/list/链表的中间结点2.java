package com.autumn.clever.leetcode.list;

/**
 * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/10 上午10:35
 */
public class 链表的中间结点2 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
//        ListNode node6 = new ListNode(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
//        node5.next = node6;

        ListNode result = middleNode(node1);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

    }

    public static ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode fast = head;
        ListNode slow = head;
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
