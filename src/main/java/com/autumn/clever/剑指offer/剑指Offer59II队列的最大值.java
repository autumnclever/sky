package com.autumn.clever.剑指offer;

import java.util.LinkedList;

/**
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * <p>
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/dui-lie-de-zui-da-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/9 下午12:35
 */
public class 剑指Offer59II队列的最大值 {
    LinkedList<Integer> list1 = null;
    LinkedList<Integer> list2 = null;

    public static void main(String[] args) {
        剑指Offer59II队列的最大值 list = new 剑指Offer59II队列的最大值();
        System.out.println(list.max_value());
        list.push_back(1);
        list.push_back(2);
        System.out.println(list.max_value());
        System.out.println(list.pop_front());
        System.out.println(list.max_value());
    }

    public 剑指Offer59II队列的最大值() {
        list1 = new LinkedList<>();
        list2 = new LinkedList<>();
    }

    public int max_value() {
        if (!list2.isEmpty()) {
            return list2.peek();
        }
        return -1;
    }

    public void push_back(int value) {
        list1.add(value);
        while (!list2.isEmpty() && list2.peekLast() < value) {
            list2.pollLast();
        }
        list2.add(value);
    }

    public int pop_front() {
        if (!list1.isEmpty()) {
            if (list2.peek().equals(list1.peek())) {
                list2.pop();
            }
            return list1.pop();
        }
        return -1;
    }
}
