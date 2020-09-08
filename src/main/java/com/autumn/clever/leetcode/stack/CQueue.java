package com.autumn.clever.leetcode.stack;

import java.util.Stack;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/6 13:36
 */
public class CQueue {
    Stack<Integer> stack1 = null;
    Stack<Integer> stack2 = null;

    public static void main(String[] args) {
        CQueue queue = new CQueue();
//        System.out.println(queue.deleteHead());
        queue.appendTail(5);
//        queue.appendTail(2);
        System.out.println(queue.deleteHead());
        System.out.println(queue.deleteHead());
    }

    public CQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void appendTail(int value) {
        stack1.push(value);
    }

    public int deleteHead() {
        if (!stack2.isEmpty()) {
            return stack2.pop();
        }
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        if (stack2.isEmpty()) {
            return -1;
        } else {
            return stack2.pop();
        }
    }
}
