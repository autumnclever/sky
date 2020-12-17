package com.autumn.clever.mmdb;

import com.google.common.collect.Maps;
import org.apache.commons.collections4.MapUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

/**
 * @Author: zhangqiuying
 * @Date: 2020/12/8 下午4:14
 */
public class Test2 {

    public static void main(String[] args) {
        MovieVO p1 = new MovieVO(1240838, "qiuying");
        MovieVO p2 = new MovieVO(345809, "laoda");
        MovieVO p3 = new MovieVO(461076, "wanglaoshi");


//        MovieVO p3 = new MovieVO(345809, "laoda");
//        MovieVO p4 = new MovieVO(1004, "yinngge");
//        MovieVO p5 = new MovieVO(1005, "erzi");
//        MovieVO p6 = new MovieVO(1006, "yongqiang");
//        MovieVO p7 = new MovieVO(1007, "xingang");
        List<MovieVO> movieVOS = new ArrayList<>();
        movieVOS.add(p1);
        movieVOS.add(p2);
        movieVOS.add(p3);
//        movieVOS.add(p4);
//        movieVOS.add(p5);
//        movieVOS.add(p6);
//        movieVOS.add(p7);

        movieVOS.stream().forEach(movieVO -> System.out.println(movieVO.getId()));

        List<MovieRelativePositionModel> positions = new ArrayList<>();

        MovieRelativePositionModel position1 = new MovieRelativePositionModel(1240838, 2, 3);
        MovieRelativePositionModel position2 = new MovieRelativePositionModel(345809, 2, 2);
        MovieRelativePositionModel position3 = new MovieRelativePositionModel(461076, 1, 1);
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

        // 保存相对位置排序的电影索引
        Map<Integer, Integer> relativeIndexMap = Maps.newHashMap();

//        relativePositions.stream()
//                .sorted(Comparator.comparing(position -> indexMap.get(position.getMovieId()), Comparator.nullsLast(Integer::compareTo)))
//                .collect(Collectors.toList());

        // 遍历 relativePositions，此循环执行后 indexMap -> 参与相对位置变化的电影极其原排序索引
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
                // indexMap 最终保存没有做任何干预逻辑的电影
                indexMap.remove(movieId);
                switch (typeEnum) {
                    case DROP:
                        // 排名下降 -> 索引值增大：初始索引 + 相对移动的位置
                        originIndex += position.getChangePosition();
                        relativeIndexMap.put(movieId, originIndex);
                        break;
                    case RISE:
                        // 排名上升 -> 索引值减小：初始索引 - 相对移动的位置
                        originIndex -= position.getChangePosition();
//                        while (relativeIndexMap.containsValue(originIndex)) {
//                            originIndex++;
//                        }
                        relativeIndexMap.put(movieId, originIndex);
                        break;
                    case EQUALS:
                        // 指定排名 -> 放到固定位 map 中
                        // changePosition 是正常排序顺序，-1 转换成 java 索引
                        originIndex = position.getChangePosition() - 1;
                        fixedIndexMap.put(movieId, originIndex);
                        break;
                }
            }
        });

        // 相对位置排序 整合至 固定位置排序 map
        if (MapUtils.isNotEmpty(relativeIndexMap)) {
            LinkedHashMap<Integer, Integer> relativeSortIndexMap = relativeIndexMap.entrySet().stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue))
                    .collect(
                            Collectors.toMap(
                                    Map.Entry::getKey,
                                    Map.Entry::getValue,
                                    (oldVal, newVal) -> oldVal,
                                    LinkedHashMap::new
                            )
                    );

            AtomicInteger newIndex = new AtomicInteger(0);
            relativeSortIndexMap.entrySet().stream().forEach(map -> {
                map.setValue(newIndex.getAndIncrement());
            });

            relativeSortIndexMap.forEach((movieId, relativeIndex) -> {
                index.set(relativeIndex);
                while (fixedIndexMap.containsValue(newIndex)) {
                    newIndex.getAndIncrement();
                }
                fixedIndexMap.put(movieId, newIndex.get());
            });
        }

        // 将无干预排序 整合至 固定位置排序
        if (MapUtils.isNotEmpty(indexMap)) {
            LinkedHashMap<Integer, Integer> newIndexMap = indexMap.entrySet().stream()
                    .sorted(Comparator.comparing(Map.Entry::getValue))
                    .collect(
                            Collectors.toMap(
                                    Map.Entry::getKey,
                                    Map.Entry::getValue,
                                    (oldVal, newVal) -> oldVal,
                                    LinkedHashMap::new
                            )
                    );

            AtomicInteger finalIndex = new AtomicInteger(0);
            newIndexMap.entrySet().stream().forEach(map -> {
                map.setValue(finalIndex.getAndIncrement());
            });

            newIndexMap.forEach((movieId, noChangeIndex) -> {
                finalIndex.set(noChangeIndex);
                while (fixedIndexMap.containsValue(finalIndex)) {
                    finalIndex.getAndIncrement();
                }
                fixedIndexMap.put(movieId, finalIndex.get());
            });
        }

        // 按照最终排序索引进行倒排，如果 fixedIndexMap 索引值为 null，放在最后面
        List<MovieVO> sortedMovieVOs = movieVOS.stream()
                .sorted(Comparator.comparing(movieVO -> fixedIndexMap.get(movieVO.getId()), Comparator.nullsLast(Integer::compareTo)))
                .collect(Collectors.toList());

        return sortedMovieVOs;
    }
}
