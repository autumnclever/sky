package com.autumn.clever.mmdb;


import com.google.common.collect.Maps;
import org.apache.commons.collections4.MapUtils;
import org.assertj.core.util.Lists;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

///**
// * @Author: zhangqiuying
// * @Date: 2020/12/3 下午9:38
// */
public class Test {
    public static void main(String[] args) {
//        MovieVO p1 = new MovieVO(1001, "qiuying");
//        MovieVO p2 = new MovieVO(1002, "wanglaoshi");
//        MovieVO p3 = new MovieVO(1003, "laoda");
//        MovieVO p4 = new MovieVO(1004, "yinngge");
//        MovieVO p5 = new MovieVO(1005, "erzi");
//        MovieVO p6 = new MovieVO(1006, "yongqiang");
//        MovieVO p7 = new MovieVO(1007, "xingang");
//        List<MovieVO> movieVOS = new ArrayList<>();
//        movieVOS.add(p1);
//        movieVOS.add(p2);
//        movieVOS.add(p3);
//        movieVOS.add(p4);
//        movieVOS.add(p5);
//        movieVOS.add(p6);
//        movieVOS.add(p7);

        MovieVO p1 = new MovieVO(1282401, "qiuying");
        MovieVO p2 = new MovieVO(1227751, "wanglaoshi");
        MovieVO p3 = new MovieVO(1240838, "laoda");
        MovieVO p4 = new MovieVO(247294, "yinngge");
        MovieVO p5 = new MovieVO(345809, "erzi");
        MovieVO p6 = new MovieVO(1228788, "yongqiang");
        MovieVO p7 = new MovieVO(401076, "xingang");
        MovieVO p8 = new MovieVO(1286015, "xingang");
        List<MovieVO> movieVOS = new ArrayList<>();
        movieVOS.add(p1);
        movieVOS.add(p2);
        movieVOS.add(p3);
        movieVOS.add(p4);
        movieVOS.add(p5);
        movieVOS.add(p6);
        movieVOS.add(p7);
        movieVOS.add(p8);

        movieVOS.stream().forEach(movieVO -> System.out.println(movieVO.getId()));

        List<MovieRelativePositionModel> positions = new ArrayList<>();
//        MovieRelativePositionModel position1 = new MovieRelativePositionModel(1001, 0, 1);
//        MovieRelativePositionModel position2 = new MovieRelativePositionModel(1002, 9, null);
//        MovieRelativePositionModel position3 = new MovieRelativePositionModel(1003, 1, 1);
//        MovieRelativePositionModel position4 = new MovieRelativePositionModel(1004, 1, 3);
//        MovieRelativePositionModel position5 = new MovieRelativePositionModel(1005, 9, null);
//        MovieRelativePositionModel position6 = new MovieRelativePositionModel(1006, 0, 5);
//        MovieRelativePositionModel position7 = new MovieRelativePositionModel(1007, 1, 2);
//        positions.add(position1);
//        positions.add(position2);
//        positions.add(position3);
//        positions.add(position4);
//        positions.add(position5);
//        positions.add(position6);
//        positions.add(position7);

        MovieRelativePositionModel position1 = new MovieRelativePositionModel(1240838, 1, 1);
        MovieRelativePositionModel position2 = new MovieRelativePositionModel(1228788, 0, 1);
        MovieRelativePositionModel position3 = new MovieRelativePositionModel(401076, 2, 1);
//        MovieRelativePositionModel position4 = new MovieRelativePositionModel(1004, 1, 3);
//        MovieRelativePositionModel position5 = new MovieRelativePositionModel(1005, 9, null);
//        MovieRelativePositionModel position6 = new MovieRelativePositionModel(1006, 0, 5);
//        MovieRelativePositionModel position7 = new MovieRelativePositionModel(1007, 1, 2);
        positions.add(position1);
        positions.add(position2);
        positions.add(position3);
//        positions.add(position4);
//        positions.add(position5);
//        positions.add(position6);
//        positions.add(position7);

        movieVOS = sortRelativePositionHandle(movieVOS, positions);
        System.out.println("----------");

        movieVOS.stream().forEach(movieVO -> System.out.println(movieVO.getId()));
    }


    private static List<MovieVO> sortRelativePositionHandle(List<MovieVO> movieVOS, List<MovieRelativePositionModel> relativePositions) {
        if (CollectionUtils.isEmpty(movieVOS) || CollectionUtils.isEmpty(relativePositions)) {
            return movieVOS;
        }

        AtomicInteger index = new AtomicInteger(0);
        // 热映列表中电影初始索引 map: <movieId, 初始电影索引>
        Map<Integer, Integer> indexMap = movieVOS.stream()
                .collect(toMap(movieVO -> movieVO.getId(), x -> index.getAndIncrement()));

        // 保存固定位置的电影索引
        Map<Integer, Integer> fixedIndexMap = Maps.newHashMap();

        // 保存相对位置的电影索引
        Map<Integer, Integer> relativeIndexMap = Maps.newHashMap();

        // 遍历 relativePositions，此循环执行后 indexMap -> 参与相对位置变化的电影
        relativePositions.stream().forEach(position -> {
            Integer movieId = position.getMovieId();
            // 获取电影在原热映列表中的初始索引
            Integer originIndex = indexMap.get(position.getMovieId());
            if (originIndex == null) {
                // 如果初始索引不存在，一律认为排在最后
                originIndex = indexMap.size() - 1;
            }

            Integer type = position.getChangeType();
            RelativePositionChangeTypeEnum typeEnum = RelativePositionChangeTypeEnum.getEnumByValue(type);
            if (type != null && typeEnum != null) {
                int relativeIndex = originIndex;
                switch (typeEnum) {
                    case DROP:
                        // 排名下降 -> 索引值增大：初始索引 + 相对移动的位置
                        indexMap.remove(movieId);
                        relativeIndex += position.getChangePosition();
                        if (relativeIndex > movieVOS.size()) {
                            relativeIndex = movieVOS.size() - 1;
                        }
                        relativeIndexMap.put(movieId, relativeIndex);
                        break;
                    case RISE:
                        // 排名上升 -> 索引值减小：初始索引 - 相对移动的位置
                        indexMap.remove(movieId);
                        relativeIndex -= position.getChangePosition();
                        if (relativeIndex < 0) {
                            relativeIndex = 0;
                        }
                        relativeIndexMap.put(movieId, relativeIndex);
                        break;
                    case EQUALS:
                        indexMap.remove(movieId);
                        fixedIndexMap.put(movieId, position.getChangePosition() - 1);
                        break;
                }
            }
        });

        // 将"相对位置排序 & 无干预位置排序"后的索引，插入到固定位置的索引中 -> "相对位置排序 & 无干预位置排序" + 固定位置 排序后的索引结果
        // 固定位优先级 > 相对位置 > 无干预

        // 相对位置排序整合到固定位中
        if (MapUtils.isNotEmpty(relativeIndexMap)) {
            relativeIndexMap.entrySet().stream()
                    .forEach(map -> {
                        List<Integer> fixedIndexes = Lists.newArrayList(fixedIndexMap.values());
                        int finalIndex = map.getValue();
                        while (fixedIndexes.contains(finalIndex)) {
                            finalIndex++;
                        }
                        fixedIndexMap.put(map.getKey(), finalIndex);
                    });
        }

        if (MapUtils.isNotEmpty(indexMap)) {
            LinkedHashMap<Integer, Integer> newIndexMap = indexMap.entrySet().stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue))
                    .collect(
                            toMap(
                                    Map.Entry::getKey,
                                    Map.Entry::getValue,
                                    (oldVal, newVal) -> oldVal,
                                    LinkedHashMap::new
                            )
                    );

            AtomicInteger newIndex = new AtomicInteger(0);
            newIndexMap.entrySet().stream().forEach(map -> {
                map.setValue(newIndex.getAndIncrement());
            });

            newIndexMap.entrySet().stream()
                    .forEach(map -> {
                        List<Integer> indexes = Lists.newArrayList(fixedIndexMap.values());
                        int noChangeIndex = map.getValue();
                        while (indexes.contains(noChangeIndex)) {
                            noChangeIndex++;
                        }
                        fixedIndexMap.put(map.getKey(), noChangeIndex);
                    });
        }


        // 整合 相对位置排序 & 无干预位置排序 -> indexMap
        // 相对位置排序优先级高于无干预位置排序
//        if (MapUtils.isNotEmpty(indexMap)) {
//            indexMap.entrySet().stream()
//                    .forEach(map -> {
//                        List<Integer> relativeIndexes = Lists.newArrayList(relativeIndexMap.values());
//                        int noChangeIndex = map.getValue();
//                        while (relativeIndexes.contains(noChangeIndex)) {
//                            noChangeIndex++;
//                        }
//                        relativeIndexMap.put(map.getKey(), noChangeIndex);
//                    });
//        }

        // 此时的 indexMap 的 value 是经过 "相对位置排序 & 无干预位置排序" 后的索引变化中间值
        // 按照索引变化中间值进行倒排 -> key 的排序即是热映列表经过干预后的排序
//        LinkedHashMap<Integer, Integer> newIndexMap = Maps.newLinkedHashMap();
//        if (MapUtils.isNotEmpty(relativeIndexMap)) {
//            newIndexMap = relativeIndexMap.entrySet().stream()
//                    .sorted(Comparator.comparing(Map.Entry::getValue))
//                    .collect(
//                            toMap(
//                                    Map.Entry::getKey,
//                                    Map.Entry::getValue,
//                                    (oldVal, newVal) -> oldVal,
//                                    LinkedHashMap::new
//                            )
//                    );
//        }

//        if (MapUtils.isNotEmpty(newIndexMap)) {
//            // newIndexMap 排序后，取出 movieId 在新的排序中的索引值 -> <movieId, 干预后的索引值>
//            AtomicInteger newIndex = new AtomicInteger(0);
//            newIndexMap.entrySet().stream().forEach(map -> {
//                map.setValue(newIndex.getAndIncrement());
//            });
//
//            // 将"相对位置排序 & 无干预位置排序"后的索引，插入到固定位置的索引中 -> "相对位置排序 & 无干预位置排序" + 固定位置 排序后的索引结果
//            // 固定位优先级 > 相对位置 > 无干预
//            newIndexMap.entrySet().stream()
//                    .forEach(map -> {
//                        List<Integer> fixedIndexes = Lists.newArrayList(fixedIndexMap.values());
//                        int finalIndex = map.getValue();
//                        while (fixedIndexes.contains(finalIndex)) {
//                            finalIndex++;
//                        }
//                        fixedIndexMap.put(map.getKey(), finalIndex);
//                    });
//        }

        // 按照干预后的索引进行倒排，如果 fixedIndexMap 索引值为 null，放在最后面
        List<MovieVO> sortedMovieVOs = movieVOS.stream()
                .sorted(Comparator.comparing(movieVO -> fixedIndexMap.get(movieVO.getId()), Comparator.nullsLast(Integer::compareTo)))
                .collect(Collectors.toList());

        return sortedMovieVOs;
    }
}