package com.autumn.clever.leetcode.list;

/**
 * @Author: zhangqiuying
 * @Date: 2020/8/22 11:53
 */
public class 两数相加 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);

        ListNode l4 = new ListNode(5);
        ListNode l5 = new ListNode(6);
        ListNode l6 = new ListNode(4);

        l1.next = l2;
        l2.next = l3;

        l4.next = l5;
        l5.next = l6;

        ListNode result = addTwoNumbers(l1, l4);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 创建一个无实际意义的头结点，用来遍历
        ListNode head = new ListNode(0);
        // 用来标记头结点，用于返回
        ListNode result = head;

        // 用来标记当前两位数字相加，是否有进位
        int bit = 0;
        while (l1 != null || l2 != null) {
            // l1、l2只要有不空，就进行遍历
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = bit + x + y;
            // 计算下一位是否有进位
            bit = sum / 10;
            // 计算下一个节点
            ListNode next = new ListNode(sum % 10);
            head.next = next;
            head = head.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (bit > 0) {
            // 如果最后两个数相加有进位，补上去
            head.next = new ListNode(bit);
        }
        return result.next;
    }
}
