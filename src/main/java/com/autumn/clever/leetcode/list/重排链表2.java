package com.autumn.clever.leetcode.list;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/23 14:25
 */
public class 重排链表2 {
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

        reorderList(node1);
        while (node1 != null) {
            System.out.println(node1.val);
            node1 = node1.next;
        }
    }

    public static void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        ListNode fast = head;
        ListNode slow = head;
        ListNode preSlow = null;

        // 找到链表的中间节点
        while (fast != null) {
            if (fast.next == null) {
                break;
            }
            preSlow = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        preSlow.next = null;

        // 反转后半段链表
        ListNode newSlow = null;
        ListNode next = null;
        while (slow != null) {
            next = slow.next;
            slow.next = newSlow;
            newSlow = slow;
            slow = next;
        }

        ListNode headNext = null;
        ListNode slowNext = null;
        ListNode headLast = null;
        while (head != null) {
            headNext = head.next;
            head.next = newSlow;
            slowNext = newSlow.next;
            newSlow.next = headNext;
            if (headNext == null) {
                headLast = head.next;
            }
            head = headNext;
            newSlow = slowNext;
        }
        if (newSlow != null) {
            headLast.next = newSlow;
        }
    }
}
