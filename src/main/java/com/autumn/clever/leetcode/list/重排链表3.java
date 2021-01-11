package com.autumn.clever.leetcode.list;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/10 下午1:32
 */
public class 重排链表3 {
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

        // 链表的中间结点
        ListNode slow = head;
        // 保存左半部的头结点
        ListNode left = slow;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // 链表右半边的头结点
        ListNode right = slow.next;
        // 左半边结点和右半边结点切割
        slow.next = null;

        // 右半部分链表反转
        ListNode rightHead = null;
        ListNode next = null;
        while (right != null) {
            next = right.next;
            right.next = rightHead;
            rightHead = right;
            right = next;
        }

        // 此时 left 是左半部链表的头结点，right 是右半部链表的头结点
        // 两条单独的链表进行合并
        ListNode leftNext = null;
        ListNode rightNext = null;
        while (left != null && rightHead != null) {
            leftNext = left.next;
            left.next = rightHead;
            rightNext = rightHead.next;
            rightHead.next = leftNext;
            left = leftNext;
            rightHead = rightNext;
        }
    }
}
