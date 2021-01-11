package com.autumn.clever.leetcode.list;

/**
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-nodes-in-k-group
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/10 下午5:08
 */
public class K个一组翻转链表2 {
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

        ListNode head1 = node1;
        ListNode head2 = node1;
        ListNode head3 = node1;
        ListNode head4 = node1;
        ListNode result1 = reverseKGroup(head1, 2);
        while (result1 != null) {
            System.out.println(result1.val);
            result1 = result1.next;
        }
    }

    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return head;
        }

        // 指定一次反转中，反转的截止位置的结点
        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            if (tail == null) {
                // 不足 k 个结点，不进行反转，直接返回该组的头结点
                return head;
            }
            // 找到反转的截止结点
            tail = tail.next;
        }

        // [head, tail)范围内反转后的头结点
        ListNode newHead = reverseGroup(head, tail);
        // 反转下一组 k 个结点，返回它的头结点，并添加到 head 的屁股上
        // 从 tail 开始反转，反转 k 个节点
        head.next = reverseKGroup(tail, k);
        // 返回本次反转之后的头结点
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
}
