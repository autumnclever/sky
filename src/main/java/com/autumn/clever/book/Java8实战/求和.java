package com.autumn.clever.book.Java8实战;

import java.util.Arrays;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: sky
 * @description:
 * @author: zhangqiuying
 * @create: 2024-01-24 20:29
 **/
public class 求和 {

    public static void main(String[] args) {

        Spu spu1 = new Spu(0, 200, 0);
        Spu spu2 = new Spu(0, 300, 1);
        Spu spu3 = new Spu(12, 200, 2);
        List<Spu> spuList = Arrays.asList(spu1, spu2, spu3);

//        spuList.stream().map(Spu::getStock).reduce((a, b) -> a + b).ifPresent(System.out::println);
//        int stock = spuList.stream().map(Spu::getStock).reduce(0, Integer::sum);
//        System.out.println(stock);

//        Map<LongSummaryStatistics, LongSummaryStatistics> map = spuList.stream().collect(Collectors.toMap(Collectors.summarizingLong(Spu::getStock), Collectors.summarizingLong(Spu::getRestStock)));

    }
}
