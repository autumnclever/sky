package com.autumn.clever.leetcode.list;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/22 12:55
 */
public class 删除排序链表中的重复元素II {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(4);
        ListNode node7 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

        ListNode result = deleteDuplicates(node1);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = new ListNode(0);
        ListNode result = newHead;
        newHead.next = head;
        while (newHead != null) {
            ListNode next = newHead.next;
            if (next == null) {
                break;
            }
            if (next.next == null || next.val != next.next.val) {
                newHead.next = next;
                newHead = newHead.next;
                continue;
            }

            while (next.next != null && next.val == next.next.val) {
                next = next.next;
            }
            newHead.next = next.next;
        }
        return result.next;
    }
}
