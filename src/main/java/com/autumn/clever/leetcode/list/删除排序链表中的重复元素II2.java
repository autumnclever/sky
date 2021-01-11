package com.autumn.clever.leetcode.list;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/10 下午4:26
 */
public class 删除排序链表中的重复元素II2 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(3);
        ListNode node5 = new ListNode(4);
        ListNode node6 = new ListNode(4);
        ListNode node7 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;

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

        // 先定义一个新的头结点
        ListNode newHead = new ListNode(0);
        // 指向原头结点
        newHead.next = head;
        // 用于返回
        ListNode result = newHead;

        while (newHead != null) {
            // 取到 next 节点
            ListNode next = newHead.next;
            if (next == null) {
                break;
            }
            if (next == null || next.next == null || next.val != next.next.val) {
                // 遇到 val 值不重复的节点才添加到 newHead 中
                newHead.next = next;
                // 继续遍历
                newHead = newHead.next;
                continue;
            }
            while (next.next != null && next.val == next.next.val) {
                // 如果值相同，继续循环判断
                next.next = next.next.next;
            }
            // 找到不连续重复的结点，添加到 newHead 中
            newHead.next = next.next;
        }

        return result.next;
    }
}
