package com.autumn.clever.cattle;

/**
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 *
 * @Author: zhangqiuying
 * @Date: 2020/6/3 22:51
 */
public class Cattle16 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node4 = new ListNode(5);
        ListNode node7 = new ListNode(8);
        ListNode node8 = new ListNode(9);
        head.next = node4;
        node4.next = node7;
        node7.next = node8;

        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node5 = new ListNode(6);
        ListNode node6 = new ListNode(7);
        node1.next = node2;
        node2.next = node3;
        node3.next = node5;
        node5.next = node6;

        ListNode result = Merge(head, node1);
        while (result != null) {
            System.out.print(result.val + "->");
            result = result.next;
        }
    }

    public static ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }

        if (list2 == null) {
            return list1;
        }

        ListNode head = null;
        if (list1.val >= list2.val) {
            head = list2;
            head.next = Merge(list1, list2.next);
        } else {
            head = list1;
            head.next = Merge(list1.next, list2);
        }

        return head;
    }
}
