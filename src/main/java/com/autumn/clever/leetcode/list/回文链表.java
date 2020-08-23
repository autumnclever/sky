package com.autumn.clever.leetcode.list;

import java.util.Stack;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/22 18:12
 */
public class 回文链表 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node7 = new ListNode(4);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(2);
        ListNode node6 = new ListNode(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node7;
        node7.next = node4;
//        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        boolean result = isPalindrome(node1);
        System.out.println(result);
    }

    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode newHead = head;
        ListNode preMiddle = new ListNode(0);
        preMiddle.next = head;
        while (fast != null) {
            if (fast.next == null) {
                break;
            }
            slow = slow.next;
            fast = fast.next.next;
            preMiddle = preMiddle.next;
        }
        ListNode middleNode = slow;
        preMiddle.next = null;
        Stack<ListNode> stack = new Stack<>();
        while (middleNode != null) {
            stack.push(middleNode);
            middleNode = middleNode.next;
        }
        while (newHead != null) {
            if (newHead.val != stack.pop().val) {
                return false;
            }
            newHead = newHead.next;
        }
        return true;
    }
}
