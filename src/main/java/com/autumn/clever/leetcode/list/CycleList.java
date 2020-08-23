package com.autumn.clever.leetcode.list;

/**
 * 环形链表
 * 给定一个链表，判断链表中是否有环。
 * <p>
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/linked-list-cycle
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2020/8/18 11:37
 */
public class CycleList {
    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(1);
        listNode1.next = listNode1;
        boolean result = hasCycle1(listNode1);
        System.out.println(result);

        ListNode listNode10 = new ListNode(1);
        boolean result3 = hasCycle1(listNode10);
        System.out.println(result3);

        ListNode listNode2 = new ListNode(3);
        ListNode listNode3 = new ListNode(2);
        ListNode listNode4 = new ListNode(0);
        ListNode listNode5 = new ListNode(-4);
        listNode2.next = listNode3;
        listNode3.next = listNode4;
        listNode4.next = listNode5;
        listNode5.next = listNode3;
        boolean result1 = hasCycle1(listNode2);
        System.out.println(result1);

        ListNode listNode6 = new ListNode(3);
        ListNode listNode7 = new ListNode(2);
        ListNode listNode8 = new ListNode(0);
        ListNode listNode9 = new ListNode(-4);
        listNode6.next = listNode7;
        listNode7.next = listNode8;
        listNode8.next = listNode9;
        boolean result2 = hasCycle1(listNode6);
        System.out.println(result2);
    }

    public static boolean hasCycle1(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        if (head.next == head) {
            return true;
        }
        ListNode slow = head;
        ListNode fast = head;

        while (slow != null) {
            if (fast != slow && (fast == null || fast.next == null)) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
