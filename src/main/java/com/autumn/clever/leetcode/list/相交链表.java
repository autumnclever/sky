package com.autumn.clever.leetcode.list;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/22 16:08
 */
public class 相交链表 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(5);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(8);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        ListNode node7 = new ListNode(4);
        ListNode node8 = new ListNode(1);
        ListNode node9 = new ListNode(8);
        ListNode node10 = new ListNode(4);
        ListNode node11 = new ListNode(5);

        node7.next = node8;
        node8.next = node9;
        node9.next = node10;
        node10.next = node11;

        ListNode result = getIntersectionNode(node1, node7);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        while (nodeA != nodeB) {
            nodeA = nodeA == null ? headB : nodeA.next;
            nodeB = nodeB == null ? headA : nodeB.next;
        }
        return nodeA;
    }
}
