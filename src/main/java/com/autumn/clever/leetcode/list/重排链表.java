package com.autumn.clever.leetcode.list;

/**
 * 题目审错了，做错了
 *
 * @Author: zhangqiuying
 * @Date: 2020/8/23 13:26
 */
public class 重排链表 {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
//        ListNode node5 = new ListNode(5);
//        ListNode node6 = new ListNode(6);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
//        node4.next = node5;
//        node5.next = node6;

        reorderList(node1);
        while (node1 != null) {
            System.out.println(node1.val);
            node1 = node1.next;
        }
    }

    public static void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        ListNode odd = head;
        ListNode even = head.next;
        ListNode cOdd = odd;
        ListNode cEven = even;

        // 分别找到奇偶链表
        while (even != null) {
            odd.next = even.next;
            if (even.next != null) {
                // 奇数链表的长度>=偶数
                even.next = odd.next.next;
            }
            odd = odd.next;
            even = even.next;
        }

        // 将偶链表反转
        ListNode newEven = null;
        ListNode next = null;
        while (cEven != null) {
            next = cEven.next;
            cEven.next = newEven;
            newEven = cEven;
            cEven = next;
        }

        // 奇偶链表合并
        ListNode oddNext = null;
        ListNode evenNext = null;
        while (cOdd != null) {
            oddNext = cOdd.next;
            cOdd.next = newEven;
            if (newEven != null) {
                // odd的长度一定是大于等于even的长度的，这里让odd最后一个元素放到链表中
                evenNext = newEven.next;
                newEven.next = oddNext;
            }
            cOdd = oddNext;
            newEven = evenNext;
        }
    }
}
