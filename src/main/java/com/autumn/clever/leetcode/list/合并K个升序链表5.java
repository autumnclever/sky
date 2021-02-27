package com.autumn.clever.leetcode.list;

import java.util.ArrayList;

/**
 * @Author: zhangqiuying
 * @Date: 2021/2/26 下午1:00
 */
public class 合并K个升序链表5 {
    public static void main(String[] args) {
        ArrayList<ListNode> lists = new ArrayList<>();
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

        lists.add(head1);
        lists.add(head2);
        lists.add(head3);

        ListNode head = mergeKLists(lists);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static ListNode mergeKLists(ArrayList<ListNode> lists) {
        ListNode node = null;
        for (ListNode list : lists) {
            node = mergeTwoLists(node, list);
        }
        return node;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode pre = new ListNode(0);
        ListNode head = pre;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                pre.next = l1;
                l1 = l1.next;
            } else {
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }
        if (l1 != null) {
            pre.next = l1;
        }
        if (l2 != null) {
            pre.next = l2;
        }
        return head.next;
    }
}
