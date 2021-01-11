package com.autumn.clever.leetcode.list;

/**
 * 反转链表
 *
 * @Author: zhangqiuying
 * @Date: 2020/8/13 22:48
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
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

    public static ListNode reverseListNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 定义前置结点
        ListNode pre = null;
        // 定义即将反转的结点的下一个结点
        ListNode next = null;
        while (head != null) {
            // 预先将要反转的结点的下一个结点保存，
            // 否则链断了，就找不到它了
            next = head.next;
            // 指向前置节点
            head.next = pre;
            pre = head;
            // head 结点后移，继续往后遍历
            head = next;
        }
        // 反转之后的头结点
        return pre;
    }

}


class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
