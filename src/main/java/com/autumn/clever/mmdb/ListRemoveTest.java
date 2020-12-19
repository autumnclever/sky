package com.autumn.clever.mmdb;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zhangqiuying
 * @Date: 2020/12/17 上午11:19
 */
public class ListRemoveTest {
    public static void main(String[] args) {
        List<MovieVO> movieVOS = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            MovieVO movieVO = new MovieVO(i, "我是第" + i + "个 movieVO");
            movieVOS.add(movieVO);
        }

        for (MovieVO movieVO : movieVOS) {
            if (movieVO.getId() % 2 == 0) {
                movieVO.setId(null);
            }
        }

        movieVOS = movieVOS.stream().filter(m -> m.getId() != null).collect(Collectors.toList());

        for (MovieVO movieVO : movieVOS) {
            System.out.println(movieVO.getName());
        }
    }
}
