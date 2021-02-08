package com.autumn.clever.剑指offer;

/**
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/4 下午9:25
 */
public class 剑指Offer25合并两个排序的链表 {
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

        ListNode result = mergeTwoLists(l1, l4);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode head = new ListNode(0);
        ListNode pre = head;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                head.next = l2;
                l2 = l2.next;
            } else {
                head.next = l1;
                l1 = l1.next;
            }
            head = head.next;
        }
        if (l1 != null) {
            head.next = l1;
        }
        if (l2 != null) {
            head.next = l2;
        }
        return pre.next;
    }
}
