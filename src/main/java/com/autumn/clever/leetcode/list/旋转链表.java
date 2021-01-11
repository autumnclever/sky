package com.autumn.clever.leetcode.list;

/**
 * @Author: zhangqiuying
 * @Date: 2021/1/10 上午8:56
 */
public class 旋转链表 {
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

        ListNode node = rotateRight(node1, 2);
        while (node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            /**
             * 1.链表长度为 0 或者 1
             * 2.k = 0
             * 都不需要进行移动
             */
            return head;
        }

        ListNode cur = head;
        // 找到链表的长度
        int count = 0;
        while (cur != null) {
            cur = cur.next;
            count++;
        }

        // 确定需要移动的次数
        k = k % count;
        if (k == 0) {
            // 如果k为0 -> 不需要移动
            return head;
        }
        // 找到正数的结点位置
        count -= k;
        ListNode preNewHead = head;
        while (count > 1) {
            // 移动 count-1 个位置 -> 新的头结点的前置结点
            preNewHead = preNewHead.next;
            count--;
        }

        // 找到新的头结点
        ListNode newHead = preNewHead.next;
        // 把新的头结点的前置结点的 next 置空
        preNewHead.next = null;
        cur = newHead;
        // 找到链表的尾节点
        while (cur.next != null) {
            cur = cur.next;
        }
        // 尾节点指向原链表的头结点
        cur.next = head;
        // 返回新的头结点
        return newHead;
    }
}