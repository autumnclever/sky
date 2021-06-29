package com.autumn.clever.book.Java8实战;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.function.Predicate;

/**
 * java.util.function.Predicate<T>接口定义了一个名叫test的抽象方法，它接受泛型T对象，并返回一个boolean。
 * 1. vt. 断言；断定
 * 2. n. 谓语
 *
 * @author zhangqiuying
 * @description
 * @date 2021/6/25 2:29 下午
 */
public class PredicateDemo {
    
    public static void main(String[] args) {
        Predicate<String> notEmptyStringPredict = (String s) -> !s.isEmpty();
        List<String> stringList = Lists.newArrayList("", "秋颖", "小猫", " ", "小秋秋");
        System.out.println(stringList.toString());
        List<String> resultList = filter(stringList, notEmptyStringPredict);
        System.out.println(resultList.toString());
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> results = Lists.newArrayList();
        for (T l : list) {
            if (predicate.test(l)) {
                results.add(l);
            }
        }
        return results;
    }
}
