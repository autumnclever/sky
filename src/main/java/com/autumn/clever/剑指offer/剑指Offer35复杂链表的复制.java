package com.autumn.clever.剑指offer;

/**
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，
 * 还有一个 random 指针指向链表中的任意节点或者 null。
 *
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/11 上午9:19
 */
public class 剑指Offer35复杂链表的复制 {
    public static void main(String[] args) {
        Node head = new Node(7);
        Node node1 = new Node(13);
        Node node2 = new Node(11);
        Node node3 = new Node(10);
        Node node4 = new Node(1);
        Node node5 = null;
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        head.random = node5;
        node1.random = head;
        node2.random = node4;
        node3.random = node2;
        node4.random = head;
        Node newHead = copyRandomList(head);
        while (newHead != null) {
            System.out.println("当前节点是" + newHead.val);
            if (newHead.random != null) {
                System.out.println("当前节点的随机节点是" + newHead.random.val);
            }
            newHead = newHead.next;
        }
    }

    public static Node copyRandomList(Node head) {
        if (head == null) {
            return head;
        }
        Node cur = head;
        while (cur != null) {
            Node node = new Node(cur.val);
            node.next = cur.next;
            cur.next = node;
            cur = node.next;
        }
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }

        cur = head.next;
        Node newHead = cur;
        Node pre = head;
        while (cur.next != null) {
            pre.next = cur.next;
            cur.next = cur.next.next;
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = null;
        return newHead;
    }
}
