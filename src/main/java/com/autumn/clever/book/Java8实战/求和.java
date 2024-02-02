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

        Spu spu1 = new Spu(100, 200, 120);
        Spu spu2 = new Spu(90, 300, 100);
        Spu spu3 = new Spu(120, 100, 100);
        List<Spu> spuList = Arrays.asList(spu1, spu2, spu3);

//        Map<LongSummaryStatistics, LongSummaryStatistics> map = spuList.stream().collect(Collectors.toMap(Collectors.summarizingLong(Spu::getStock), Collectors.summarizingLong(Spu::getRestStock)));

    }
}
