package com.autumn.clever.leetcode.list;

/**
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 *
 * @Author: zhangqiuying
 * @Date: 2020/8/22 12:41
 */
public class 删除排序链表中的重复元素 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(3);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode result = deleteDuplicates(node1);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode result = head;
        while (result != null) {
            if (result.next != null && result.val == result.next.val) {
                result.next = result.next.next;
                continue;
            }
            result = result.next;
        }
        return head;
    }
}
