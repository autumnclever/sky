package com.autumn.clever.book.Java8实战;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * java.util.function.Consumer<T>定义了一个名叫accept的抽象方法，它接受泛型T的对象，没有返回（void）。
 * 你如果需要访问类型T的对象，并对其执行某些操作，就可以使用这个接口。
 * 比如，你可以用它来创建一个forEach方法，接受一个Integers的列表，并对其中每个元素执行操作。
 *
 * @author zhangqiuying
 * @description
 * @date 2021/6/25 2:59 下午
 */
public class ConsumerDemo {
    
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Consumer<Integer> consumer = (i) -> System.out.println(i);
        forEach(list, consumer);
    }

    public static <T> void forEach(List<T> list, Consumer consumer) {
        for (T l : list) {
            consumer.accept(l);
        }
    }
}
