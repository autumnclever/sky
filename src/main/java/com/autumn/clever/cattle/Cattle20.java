package com.autumn.clever.cattle;

import java.util.Stack;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
 * 注意：保证测试中不会当栈为空的时候，对栈调用pop()或者min()或者top()方法。
 *
 * @Author: zhangqiuying
 * @Date: 2020/6/8 16:58
 */
public class Cattle20 {
    public static void main(String[] args) {
        NewStack newStack = new NewStack();
        newStack.push(3);
//        newStack.push(4);
//        newStack.push(2);
//        newStack.push(1);
        newStack.push(7);
        newStack.push(9);
        System.out.println(newStack.min());
    }
}

class NewStack {
    private static Stack<Integer> stack1 = new Stack<Integer>();
    private static Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
        if (stack2.isEmpty() || stack2.peek() > node) {
            stack2.push(node);
        } else {
            stack2.push(stack2.peek());
        }
    }

    public void pop() {
        stack1.pop();
        stack2.pop();
    }

    public int top() {
        return stack1.peek();
    }

    public int min() {
        return stack2.peek();
    }
}
