package com.autumn.clever.leetcode.list;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/10 下午3:17
 */
public class 合并K个升序链表3 {
    public static void main(String[] args) {
        ListNode[] lists = new ListNode[3];
        ListNode head1 = new ListNode(1);
        ListNode h1Node1 = new ListNode(4);
        ListNode h1Node2 = new ListNode(5);
        head1.next = h1Node1;
        h1Node1.next = h1Node2;
        ListNode head2 = new ListNode(1);
        ListNode h2Node1 = new ListNode(3);
        ListNode h2Node2 = new ListNode(4);
        head2.next = h2Node1;
        h2Node1.next = h2Node2;
        ListNode head3 = new ListNode(2);
        ListNode h3Node1 = new ListNode(6);
        head3.next = h3Node1;

        lists[0] = head1;
        lists[1] = head2;
        lists[2] = head3;

        ListNode head = mergeKLists(lists);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        ListNode result = null;
        for (ListNode list : lists) {
            result = mergeTwoLists(result, list);
        }
        return result;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }

        if (l2 == null) {
            return l1;
        }

        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
