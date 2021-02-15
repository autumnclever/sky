package com.autumn.clever.leetcode.list;

/**
 * 牛客：
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/13 下午5:38
 */
public class CycleList3 {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        listNode1.next = listNode1;
        boolean result = hasCycle(listNode1);
        System.out.println(result);

        ListNode listNode10 = new ListNode(1);
        boolean result3 = hasCycle(listNode10);
        System.out.println(result3);

        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(2);
        ListNode listNode4 = new ListNode(0);
        ListNode listNode5 = new ListNode(-4);
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode3;
        boolean result1 = hasCycle(listNode2);
        System.out.println(result1);

        ListNode listNode6 = new ListNode(3);
        ListNode listNode7 = new ListNode(2);
        ListNode listNode8 = new ListNode(0);
        ListNode listNode9 = new ListNode(-4);
        listNode6.next = listNode7;
        listNode7.next = listNode8;
        listNode8.next = listNode9;
        boolean result2 = hasCycle(listNode6);
        System.out.println(result2);
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            if (fast == slow) {
                return true;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}
