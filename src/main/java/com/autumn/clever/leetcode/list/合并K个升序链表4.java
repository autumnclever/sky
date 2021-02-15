package com.autumn.clever.leetcode.list;

import java.util.ArrayList;

/**
 * https://www.nowcoder.com/practice/65cfde9e5b9b4cf2b6bafa5f3ef33fa6?tpId=188&tags=&title=&diffculty=0&judgeStatus=0&rp=1&tab=answerKey
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/14 下午2:42
 */
public class 合并K个升序链表4 {
    public static void main(String[] args) {
        ArrayList<ListNode> lists = new ArrayList<>();
        ListNode head1 = new ListNode(1);
        ListNode h1Node1 = new ListNode(4);
        ListNode h1Node2 = new ListNode(5);
        head1.next = h1Node1;
        h1Node1.next = h1Node2;
        ListNode head2 = new ListNode(1);
        ListNode h2Node1 = new ListNode(3);
        ListNode h2Node2 = new ListNode(4);
        head2.next = h2Node1;
        h2Node1.next = h2Node2;
        ListNode head3 = new ListNode(2);
        ListNode h3Node1 = new ListNode(6);
        head3.next = h3Node1;

        lists.add(head1);
        lists.add(head2);
        lists.add(head3);

        ListNode head = mergeKLists(lists);
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
    }

    public static ListNode mergeKLists(ArrayList<ListNode> lists) {
        if (lists == null || lists.size() == 0) {
            return null;
        }
        ListNode head = null;
        for (ListNode list : lists) {
            head = mergeLists2(head, list);
        }
        return head;
    }

    public static ListNode mergeLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode head = new ListNode(0);
        ListNode pre = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }
            head = head.next;
        }
        if (l1 != null) {
            head.next = l1;
        } else if (l2 != null) {
            head.next = l2;
        }
        return pre.next;
    }

    public static ListNode mergeLists2(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeLists2(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeLists2(l1, l2.next);
            return l2;
        }
    }
}
