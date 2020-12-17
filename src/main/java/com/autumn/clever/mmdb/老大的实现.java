package com.autumn.clever.mmdb;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

/**
 * @Author: zhangqiuying
 * @Date: 2020/12/9 下午12:53
 */
public class 老大的实现 {

    private List<MovieVO> sortRelativePositionHandle(List<MovieVO> movieVOS, List<MovieRelativePositionModel> relativePositions) {
        if (CollectionUtils.isEmpty(movieVOS) || CollectionUtils.isEmpty(relativePositions)) {
            return movieVOS;
        }

        AtomicInteger index = new AtomicInteger(0);
        // 热映列表中电影初始索引 map: <movieId, 初始电影索引>
        Map<Integer, Integer> indexMap = movieVOS.stream()
                .collect(toMap(MovieVO::getId, x -> index.getAndIncrement()));

        // 保存固定位置的电影索引
        Map<Integer, Integer> fixedIndexMap = Maps.newHashMap();

        // 保存相对位置排序的电影索引
        Map<Integer, Integer> relativeIndexMap = Maps.newHashMap();

        // 将 relativePositions 按照电影的原顺序进行排序
        relativePositions = relativePositions.stream()
                .sorted(Comparator.comparing(position -> indexMap.get(position.getMovieId()), Comparator.nullsLast(Integer::compareTo)))
                .collect(Collectors.toList());

        // 遍历 relativePositions，此循环执行后 indexMap -> 参与相对位置变化的电影极其原排序索引

        relativePositions.forEach(position -> {
            Integer movieId = position.getMovieId();
            // 获取电影在原热映列表中的初始索引
            Integer originIndex = indexMap.get(position.getMovieId());
            if (originIndex == null) {
                // 如果初始索引不存在，一律认为排在最后
                originIndex = indexMap.size() - 1;
            }

            Integer type = position.getChangeType();
            RelativePositionChangeTypeEnum typeEnum = RelativePositionChangeTypeEnum.getEnumByValue(type);
            if (typeEnum != null) {
                // indexMap 最终保存没有做任何干预逻辑的电影
                indexMap.remove(movieId);
                switch (typeEnum) {
                    case DROP:
                        // 排名下降 -> 索引值增大：初始索引 + 相对移动的位置
                        originIndex += position.getChangePosition();
                        while (relativeIndexMap.containsValue(originIndex)) {
                            // 只要存在相撞，index++
                            originIndex++;
                        }
                        relativeIndexMap.put(movieId, originIndex);
                        break;
                    case RISE:
                        // 排名上升 -> 索引值减小：初始索引 - 相对移动的位置
                        originIndex -= position.getChangePosition();
                        while (relativeIndexMap.containsValue(originIndex)) {
                            originIndex++;
                        }
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

        // 按照最终排序索引进行倒排，如果 fixedIndexMap 索引值为 null，放在最后面

        List<MovieVO> tmpList0 = Lists.newArrayList();
        int maxRelativeSize = MapUtils.isEmpty(relativeIndexMap) ? 0 : Math.abs(Collections.max(relativeIndexMap.values())) + 1;
        final MovieVO[] result0 = new MovieVO[movieVOS.size()+maxRelativeSize];
        final MovieVO[] result1 = new MovieVO[movieVOS.size()+maxRelativeSize];
        movieVOS.forEach(movieVO -> {
            if (movieVO != null) {
                if (relativeIndexMap.containsKey(movieVO.getId())) {
                    Integer relativeIndex = relativeIndexMap.get(movieVO.getId());
                    if(relativeIndex >= 0){
                        result0[relativeIndex] = movieVO;
                    } else {
                        result1[0-relativeIndex] = movieVO;
                    }


                } else {
                    tmpList0.add(movieVO);
                }
            }
        });
        int tmpIndex0 = 0;
        List<MovieVO> headList = Lists.newArrayList();
        for (int i = result1.length - 1; i >=0; i--){
            if (result1[i] != null){
                headList.add(result1[i]);
            }
        }
        Map<Integer, MovieVO> realIndexMap = Maps.newHashMap();
        int headIndex = headList.size()-1;
        for (int i = 0; i  < result0.length - 1; i++){
            if (result0[i] != null){
                if (i <= headIndex){
                    realIndexMap.put(++headIndex, result0[i]);
                } else {
                    headIndex = i;
                    realIndexMap.put(headIndex, result0[i]);
                }
            }
        }
        for(int i = 0; i < headList.size(); i++){
            result0[i]= headList.get(i);
        }
        realIndexMap.forEach((k,v) ->{
            result0[k] = v;
        });
        for (MovieVO movieVO : tmpList0) {
            while (result0[tmpIndex0] != null) {
                tmpIndex0++;
            }
            result0[tmpIndex0] = movieVO;
        }
        List<MovieVO> sortedMovieVOs = Lists.newArrayList(result0).stream().filter(Objects::nonNull).collect(Collectors.toList());


        List<MovieVO> tmpList = Lists.newArrayList();
        int maxSize = MapUtils.isEmpty(fixedIndexMap) ? 0 : Collections.max(fixedIndexMap.values()) + 1;
        final MovieVO[] result = new MovieVO[Math.max(movieVOS.size(), maxSize)];
        sortedMovieVOs.forEach(movieVO -> {
            if (movieVO != null) {
                if (fixedIndexMap.containsKey(movieVO.getId())) {
                    Integer fixedIndex = fixedIndexMap.get(movieVO.getId());
                    result[fixedIndex] = movieVO;
                } else {
                    tmpList.add(movieVO);
                }
            }
        });
        int tmpIndex2 = 0;
        for (MovieVO movieVO : tmpList) {
            while (result[tmpIndex2] != null) {
                tmpIndex2++;
            }
            result[tmpIndex2] = movieVO;
        }
        sortedMovieVOs = Lists.newArrayList(result).stream().filter(Objects::nonNull).collect(Collectors.toList());
        return sortedMovieVOs;
    }
}