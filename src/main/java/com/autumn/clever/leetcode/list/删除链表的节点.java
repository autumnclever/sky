package com.autumn.clever.leetcode.list;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/22 17:20
 */
public class 删除链表的节点 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        ListNode result = deleteNode(node1, 6);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode deleteNode(ListNode head, int val) {
        if (head == null) {
            return head;
        }

        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode newHead = pre;
        while (pre != null) {
            if (pre.next != null && pre.next.val == val) {
                pre.next = pre.next.next;
                break;
            } else {
                pre = pre.next;
            }
        }
        return newHead.next;
    }
}
