package com.autumn.clever.leetcode.stack;

import java.util.Stack;

/**
 * @Author: zhangqiuying
 * @Date: 2020/9/6 14:31
 */
public class 包含min函数的栈 {
    Stack<Integer> stack = null;
    Stack<Integer> minStack = null;

    public static void main(String[] args) {
        包含min函数的栈 min函数的栈 = new 包含min函数的栈();
        min函数的栈.push(-1);
//        min函数的栈.push(0);
//        min函数的栈.push(-3);
//        System.out.println(min函数的栈.min());
//        min函数的栈.pop();
        System.out.println(min函数的栈.top());
        System.out.println(min函数的栈.min());
    }

    public 包含min函数的栈() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        stack.push(x);
        if (minStack.isEmpty() || x <= minStack.peek()) {
            // 如果minStack为空 或者 x比minStack栈顶的值小
            minStack.push(x);
        } else {
            minStack.push(minStack.peek());
        }
    }

    public void pop() {
        stack.pop();
        minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int min() {
        return minStack.peek();
    }
}
