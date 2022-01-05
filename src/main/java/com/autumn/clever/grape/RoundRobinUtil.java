package com.autumn.clever.grape;

import com.google.common.collect.Iterables;

import java.util.Iterator;
import java.util.List;

/**
 * @author zhangqiuying
 * @description
 * @date 2021/12/20 4:32 下午
 */
public class RoundRobinUtil<T> {
    
    private Iterator<T> iterator;

    public RoundRobinUtil(List<T> resourceList) {
        iterator = Iterables.cycle(resourceList).iterator();
    }

    public synchronized T getNextOne() {
        return iterator.next();
    }
}
