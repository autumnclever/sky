package com.autumn.clever.cattle;

import java.util.Stack;

/**
 * 反转链表：输入一个链表，反转链表后，输出新链表的表头。
 *
 * @Author: zhangqiuying
 * @Date: 2020/6/2 10:07
 */
public class Cattle15 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(6);
        ListNode node6 = new ListNode(7);
        ListNode node7 = new ListNode(8);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
//        System.out.println("-- 反转前 --");
//        while (head.next != null) {
//            System.out.print(head.val + "->");
//            head = head.next;
//        }

        System.out.println();
        System.out.println("-- 反转后 --");
        ListNode result = ReverseList(head);
        while (result != null) {
            System.out.print(result.val + "->");
            result = result.next;
        }
    }

    public static ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        Stack<Integer> stack = new Stack<Integer>();
        while (head != null) {
            stack.push(head.val);
            head = head.next;
        }

        ListNode result = new ListNode(stack.pop());
        ListNode newHead = result;
        while (!stack.isEmpty()) {
            ListNode node = new ListNode(stack.pop());
            result.next = node;
            result = result.next;
        }
        return newHead;
    }
}


