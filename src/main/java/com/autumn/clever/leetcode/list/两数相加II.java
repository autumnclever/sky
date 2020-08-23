package com.autumn.clever.leetcode.list;

/**
 * https://leetcode-cn.com/problems/add-two-numbers-ii/
 *
 * @Author: zhangqiuying
 * @Date: 2020/8/22 13:40
 */
public class 两数相加II {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
//        ListNode l2 = new ListNode(2);
//        ListNode l3 = new ListNode(4);
//        ListNode l7 = new ListNode(3);

        ListNode l4 = new ListNode(5);
//        ListNode l5 = new ListNode(6);
//        ListNode l6 = new ListNode(4);

//        l1.next = l2;
//        l2.next = l3;
//        l3.next = l7;

//        l4.next = l5;
//        l5.next = l6;

        ListNode result = addTwoNumbers(l1, l4);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode r1 = reverseListNode(l1);
        ListNode r2 = reverseListNode(l2);

        ListNode head = new ListNode(0);
        ListNode result = head;
        int bit = 0;
        while (r1 != null || r2 != null) {
            int x = r1 == null ? 0 : r1.val;
            int y = r2 == null ? 0 : r2.val;
            int sum = bit + x + y;
            ListNode node = new ListNode(sum % 10);
            head.next = node;
            head = head.next;
            bit = sum / 10;
            if (r1 != null) {
                r1 = r1.next;
            }
            if (r2 != null) {
                r2 = r2.next;
            }
        }
        if (bit > 0) {
            head.next = new ListNode(bit);
        }
        return reverseListNode(result.next);
    }

    public static ListNode reverseListNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = null;
        ListNode next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
