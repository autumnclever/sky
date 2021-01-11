package com.autumn.clever.leetcode.list;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/10 下午6:47
 */
public class K个一组翻转链表3 {
    public static void main(String[] args) {

    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return head;
        }

        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            if (tail == null) {
                return head;
            }
            tail = tail.next;
        }
        ListNode newHead = reverseGroup(head, tail);
        head.next = reverseKGroup(tail, k);
        return newHead;
    }

    public static ListNode reverseGroup(ListNode head, ListNode tail) {
        ListNode pre = null;
        ListNode next = null;
        while (head != tail) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 反转范围：[head, tail)
     * 即反转 head 链表，反转到 tail 为止
     * head 的长度大过 tail，即包含 tail 在内。
     * 返回反转 [head, tail) 之后的头结点
     * 不包含 tail，已经和 tail 截断
     *
     * @param head 需要反转的链表头结点
     * @param tail 反转的截止位置结点
     * @return 反转后的头结点
     */
    public static ListNode reverseListTillTail(ListNode head, ListNode tail) {
        ListNode pre = null;
        ListNode next = null;
        while (head != tail) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }
}
