package com.autumn.clever.leetcode.list;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/10 下午8:52
 */
public class 两两交换链表中的节点2 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ListNode result = swapPairs(node1);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 找到反转的截止结点
        ListNode tail = head.next.next;
        // 得到 [head, tail) 范围内反转之后的头结点
        // 此时 head 指针指针位于反转之后的链表的结尾
        ListNode newHead = swapTwoNodes(head, tail);
        // head 结点与后面反转的链表的头结点进行拼接
        head.next = swapPairs(tail);
        return newHead;
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
    public static ListNode swapTwoNodes(ListNode head, ListNode tail) {
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
