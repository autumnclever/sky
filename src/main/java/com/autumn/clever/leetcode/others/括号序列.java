package com.autumn.clever.leetcode.others;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 给出一个仅包含字符'(',')','{','}','['和']',的字符串，判断给出的字符串是否是合法的括号序列
 * 括号必须以正确的顺序关闭，"()"和"()[]{}"都是合法的括号序列，但"(]"和"([)]"不合法。
 * https://www.nowcoder.com/practice/37548e94a270412c8b9fb85643c8ccc2?tpId=188&tags=&title=&diffculty=0&judgeStatus=0&rp=1&tab=answerKey
 *
 * @Author: zhangqiuying
 * @Date: 2021/2/15 上午11:37
 */
public class 括号序列 {
    public static void main(String[] args) {
        System.out.println(isValid3("()"));
        System.out.println(isValid3("()[]{}"));
        System.out.println(isValid3("(]"));
        System.out.println(isValid3("([)]"));
        System.out.println(isValid3("([])"));
    }

    public static boolean isValid3(String s) {
        if (s == null || s.length() % 2 != 0) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                case '{':
                case '[':
                    stack.push(s.charAt(i));
                    break;
                case ')':
                case ']':
                case '}':
                    char ex = ' ';
                    if (s.charAt(i) == ')') {
                        ex = '(';
                    } else if (s.charAt(i) == ']') {
                        ex = '[';
                    } else if (s.charAt(i) == '}') {
                        ex = '{';
                    }
                    if (stack.isEmpty() || !stack.pop().equals(ex)) {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static boolean isValid2(String s) {
        if (s == null || s.length() % 2 != 0) {
            return false;
        }
        int i = 0;
        while (i < s.length()) {
            switch (s.charAt(i)) {
                case '(':
                    if (s.charAt(i + 1) != ')') {
                        return false;
                    }
                    break;
                case '{':
                    if (s.charAt(i + 1) != '}') {
                        return false;
                    }
                    break;
                case '[':
                    if (s.charAt(i + 1) != ']') {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
            i += 2;
        }
        return true;
    }

    public static boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                case '{':
                case '[':
                    Integer value = map.get(s.charAt(i));
                    if (value == null || value.equals(0)) {
                        map.put(s.charAt(i), 1);
                    } else {
                        map.put(s.charAt(i), value + 1);
                    }
                    break;
                case ')':
                case ']':
                case '}':
                    char ex = ' ';
                    if (s.charAt(i) == ')') {
                        ex = '(';
                    } else if (s.charAt(i) == ']') {
                        ex = '[';
                    } else if (s.charAt(i) == '}') {
                        ex = '{';
                    }
                    Integer val = map.get(ex);
                    if (val == null || val.equals(0)) {
                        return false;
                    }
                    if (val - 1 == 0) {
                        map.remove(ex);
                    } else {
                        map.put(ex, val - 1);
                    }
                    break;
                default:
                    continue;
            }
        }
        return map.isEmpty();
    }
}
