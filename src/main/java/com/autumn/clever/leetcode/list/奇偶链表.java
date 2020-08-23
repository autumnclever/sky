package com.autumn.clever.leetcode.list;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/22 17:35
 */
public class 奇偶链表 {
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

        ListNode result = oddEvenList2(node1);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode oddEvenList2(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            even.next = odd.next.next;
            odd = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    public static ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        ListNode oddHead = new ListNode(head.val);
        ListNode evenHead = new ListNode(head.next.val);
        ListNode oddNewHead = oddHead;
        ListNode evenNewHead = evenHead;
        int bit = 1;
        head = head.next.next;
        while (head != null) {
            if (bit == 1) {
                oddHead.next = new ListNode(head.val);
                oddHead = oddHead.next;
                bit = 0;
            } else {
                evenHead.next = new ListNode(head.val);
                evenHead = evenHead.next;
                bit = 1;
            }
            head = head.next;
        }
        oddHead.next = evenNewHead;

        return oddNewHead;
    }
}
