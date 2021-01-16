package com.autumn.clever.leetcode.list;

/**
 * 删除链表中等于给定值 val 的所有节点。
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/11 下午1:34
 */
public class 移除链表元素2 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(6);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;

        ListNode result = removeElements(node1, 6);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }

        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode newHead = pre;
        while (pre != null) {
            if (pre.next != null && pre.next.val == val) {
                // 如果下一个结点的值和 val 相同，移除下一个结点，
                // pre 的 next 指针指向下一个结点的下一个结点
                pre.next = pre.next.next;
            } else {
                // 如果下一个结点的值和 val 不同，就判断下一个结点的下一个结点
                pre = pre.next;
            }
        }
        return newHead.next;
    }
}
