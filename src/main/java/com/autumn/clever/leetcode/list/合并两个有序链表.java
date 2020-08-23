package com.autumn.clever.leetcode.list;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/20 16:35
 */
public class 合并两个有序链表 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        ListNode l3 = new ListNode(4);

        ListNode l4 = new ListNode(1);
        ListNode l5 = new ListNode(3);
        ListNode l6 = new ListNode(4);

        l1.next = l2;
        l2.next = l3;

        l4.next = l5;
        l5.next = l6;

        ListNode result = mergeTwoLists2(l1, l4);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        ListNode preHead = new ListNode(0);
        ListNode prep = preHead;

        while (l1 != null && l2 != null) {
            ListNode node = null;
            if (l1.val > l2.val) {
                node = new ListNode(l2.val);
                l2 = l2.next;
            } else {
                node = new ListNode(l1.val);
                l1 = l1.next;
            }
            prep.next = node;
            prep = prep.next;
        }

        prep.next = l1 == null ? l2 : l1;
        return preHead.next;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        ListNode head = null;
        if (l1.val > l2.val) {
            head = new ListNode(l2.val);
            l2 = l2.next;
        } else {
            head = new ListNode(l1.val);
            l1 = l1.next;
        }
        ListNode temp = head;
        while (l1 != null && l2 != null) {
            ListNode node = null;
            if (l1.val > l2.val) {
                node = new ListNode(l2.val);
                l2 = l2.next;
            } else {
                node = new ListNode(l1.val);
                l1 = l1.next;
            }
            temp.next = node;
            temp = temp.next;
        }
        if (l1 == null) {
            temp.next = l2;
        } else if (l2 == null) {
            temp.next = l1;
        }
        return head;
    }
}