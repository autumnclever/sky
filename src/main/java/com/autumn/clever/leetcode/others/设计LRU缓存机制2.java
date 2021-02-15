package com.autumn.clever.leetcode.others;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @Author: zhangqiuying
 * @Date: 2021/2/13 下午9:47
 */
public class 设计LRU缓存机制2 {
    LinkedHashMap<Integer, Integer> map = new LinkedHashMap();
    int cap;

    public int[] LRU(int[][] operators, int k) {
        cap = k;
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < operators.length; i++) {
            int[] operator = operators[i];
            if (operator[0] == 1) {
                put(operator[1], operator[2]);
            } else if (operator[0] == 2) {
                int value = get(operator[1]);
                values.add(value);
            }
        }
        int[] array = new int[values.size()];
        int i = 0;
        for (Integer val : values) {
            array[i++] = val;
        }
        return array;
    }

    public void put(int key, int value) {
        if (map.size() < cap) {
            map.put(key, value);
        } else {
            Iterator it = map.keySet().iterator();
            map.remove(it.next());
            map.put(key, value);
        }
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        int val = map.get(key);
        map.remove(key);
        map.put(key, val);
        return val;
    }
}
