package com.autumn.clever.mmdb;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @Author: zhangqiuying
 * @Date: 2020/12/8 下午5:33
 */
public class Test3 {

    private List<MovieVO> sortRelativePositionHandle(List<MovieVO> movieVOS, List<MovieRelativePositionModel> relativePositions) {
        if (CollectionUtils.isEmpty(movieVOS) || CollectionUtils.isEmpty(relativePositions)) {
            return movieVOS;
        }

        Map<Integer, MovieRelativePositionModel> positionMap = relativePositions.stream()
                .collect(Collectors.toMap(MovieRelativePositionModel::getMovieId, (p -> p), (k1, k2) -> k1));

        Map<Integer, MovieVO> tooShortMap = Maps.newHashMap();
        Map<Integer, MovieVO> tooLongMap = Maps.newHashMap();
        List<MovieVO> relativeMovieVOs = Lists.newArrayList();
        AtomicInteger index = new AtomicInteger(-1);
        movieVOS.stream().forEach(movieVO -> {
            index.getAndIncrement();
            int movieId = movieVO.getId();
            if (positionMap.containsKey(movieId)) {
                MovieRelativePositionModel positionModel = positionMap.get(movieId);
                Integer type = positionModel.getChangeType();
                if (type != null) {
                    RelativePositionChangeTypeEnum typeEnum = RelativePositionChangeTypeEnum.getEnumByValue(type);
                    switch (typeEnum) {
                        case DROP:
//                            relativeMovieVOs.add();
                            break;
                        case RISE:
                        case EQUALS:
                    }
                }
            }
        });
        return movieVOS;
    }
}
