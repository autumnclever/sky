package com.autumn.clever.leetcode.list;

import java.util.Stack;

/**
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * <p>
 * <p>
 * <p>
 * 进阶：
 * <p>
 * 如果输入链表不能修改该如何处理
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/1/11 下午6:44
 */
public class 两数相加II2 {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(5);
//        ListNode l2 = new ListNode(2);
//        ListNode l3 = new ListNode(4);
//        ListNode l7 = new ListNode(3);

        ListNode l4 = new ListNode(5);
//        ListNode l5 = new ListNode(6);
//        ListNode l6 = new ListNode(4);

//        l1.next = l2;
//        l2.next = l3;
//        l3.next = l7;

//        l4.next = l5;
//        l5.next = l6;

        ListNode result = addTwoNumbers(l1, l4);
        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        // 定义头结点
        ListNode head = null;
        int bit = 0;
        // 边计算，边把指针指向刚刚计算好的那个结点，因为它即将成为最新的头结点
        while (!stack1.isEmpty() || !stack2.isEmpty() || bit != 0) {
            int val = bit;
            val += stack1.isEmpty() ? 0 : stack1.pop();
            val += stack2.isEmpty() ? 0 : stack2.pop();
            bit = val / 10;
            val = val % 10;
            // 新计算得到的结点放在原结点的尾部
            ListNode tail = new ListNode(val);
            // 把刚计算好的结点，放在原头结点的前面
            tail.next = head;
            // 让刚刚计算好的结点，成为最新的头结点
            head = tail;
        }
        return head;
    }
}
